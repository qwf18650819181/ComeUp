--odps sql
--********************************************************************--
--# 功能描述:
--# 创建所属: 康伟杰
--# 创建时间: 2023-03-21 13:59:04
--# 代码审查:
--# 修改时间    修改人    修改内容
--#
--********************************************************************--
--#=========================================================
--# 创建者: 康伟杰
--# 目标表:
--# 来源表:
--#=========================================================
-- DROP TABLE IF EXISTS fba_replenishment_skc;
CREATE TABLE IF NOT EXISTS fba_replenishment_skc
(
    id                        BIGINT COMMENT '主键id'
    ,product_id               BIGINT COMMENT '商品id' -- product_id
    ,product_sku              STRING COMMENT '商品编码' -- spu
    ,skc                      STRING COMMENT 'skc'
    ,image_url                STRING COMMENT '商品图片' -- dwd_product_develop_image_a type='requirement' 多条, product_id = product_id
    ,category_id              BIGINT COMMENT '商品类别id' -- dwd_product_a id = product_id 取 category_id
    ,category_name            STRING COMMENT '商品类别名称' -- dwd_product_a id = product_id 取 category_name
    ,season                   BIGINT COMMENT '季节枚举类型：季节，4（春秋季）5（夏季）7（冬季）8（四季））' -- sold_out_forecast.season_id
    ,store_id                 BIGINT COMMENT '店铺id'
    ,store_name               STRING COMMENT '店铺名称'
    ,store_url                STRING COMMENT '店铺链接' -- 拼接: https://www.amazon.com/dp/ + dwd_amazon_store_product_sku_a parent_asin
    ,store_group              STRING COMMENT '店铺小组' -- sa_store.team
    ,store_brand_id           BIGINT COMMENT '店铺品牌id'
    ,store_brand_name         STRING COMMENT '店铺品牌名称'
    ,status                   BIGINT COMMENT '处理状态：1已处理，0未处理'
    ,air_suggest_qty          BIGINT COMMENT '空运-建议发FBA量'
    ,sea_suggest_qty          BIGINT COMMENT '海运-建议发FBA量'
    ,forecast_sales           BIGINT COMMENT '销量预测'
    ,stocking_days            BIGINT COMMENT '备货时长'
    ,sale_maturity_begin_date BIGINT COMMENT '成熟期开始时间'
    ,sale_maturity_end_date   BIGINT COMMENT '成熟期结束时间'
    ,create_date              DATETIME COMMENT '创建时间'
    ,create_by                STRING COMMENT '创建者'
    ,update_date              DATETIME COMMENT '更新时间'
    ,update_by                STRING COMMENT '更新者'
    ,version                  BIGINT COMMENT '版本号'
    ,location                 STRING COMMENT '地区'
    ,express_suggest_qty      BIGINT COMMENT '快递-建议发FBA量'
)
    COMMENT 'FBA智能补货-skc级别'
    PARTITIONED BY
(
    pt                        STRING COMMENT '分区'
)
    LIFECYCLE 7
;

WITH amazon_store_product_sku AS
    (
        SELECT  t.store_id
             ,t.erp_sku
             ,t.parent_asin
        FROM    (
                    SELECT  ROW_NUMBER() OVER (PARTITION BY sku.disainfo_id,spec.spec_sku ORDER BY sku.open_date DESC ) AS rn
                        ,sku.disainfo_id AS store_id
                         ,spec.spec_sku AS erp_sku
                         ,sku.parent_asin AS parent_asin
                    FROM    dwd_amazon_store_product_sku_a sku
                                INNER JOIN dwd_product_spec_a spec
                                           ON      spec.product_spec_id = sku.product_spec_id
                                               AND     spec.pt = '@@{yyyyMMdd}'
                    WHERE   sku.pt = '@@{yyyyMMdd}' -- AND     sku.status <> 'Inactive'
                      -- AND     sku.delete_status IS NULL
                      AND     sku.fulfillment_channel <> 'DEFAULT'
                ) t
        WHERE   t.rn = 1
    )
   ,store_label_config AS
    (
        SELECT  CAST(fc.store_id AS BIGINT) AS store_id
             ,fc.skc AS skc
             ,fc.location AS location
             ,MAX(fc.popular_label_id) AS label_id
        FROM    ads_sku_store_sales_forecast fc
        WHERE   fc.pt = '@@{yyyyMMdd}'
          AND     fc.store_id > 0
        GROUP BY fc.store_id
               ,fc.skc
               ,fc.location
    )
   ,input AS
    (
        SELECT  i.dimension_id AS store_id
             ,i.sku AS sku
             ,MAX(i.fba_min_shipment_qty) AS fba_min_shipment_qty
        FROM    ads_sku_sales_forecast_input i
        WHERE   i.pt = '@@{yyyyMMdd}'
        GROUP BY i.dimension_id
               ,i.sku
    )
   ,forecast_skc_pre AS
    (
        SELECT  MAX(forecast.product_id) AS product_id
             ,MAX(forecast.spec_id) AS product_spec_id
             ,MAX(forecast.spu) AS product_sku
             ,forecast.skc AS skc
             ,MAX(forecast.season_id) AS season
             ,forecast.dimension_id AS store_id
             ,MAX(dimension_name) AS store_name
             ,MAX(ps.parent_asin) AS parent_asin
             ,0 AS status
             ,SUM(forecast.suggested_shipping_quantity1) AS sea_suggest_qty
             ,SUM(forecast.suggested_shipping_quantity2) AS air_suggest_qty
             ,SUM(forecast.suggested_shipping_quantity3) AS express_suggest_qty
             ,SUM(forecast.forecast_sales_total) AS forecast_sales
             ,MAX(forecast.replenish_days) AS stocking_days
             ,GETDATE() AS create_date
             ,'ads_sold_out_forecast_mr' AS create_by
             ,GETDATE() AS update_date
             ,'ads_sold_out_forecast_mr' AS update_by
             ,0 AS version
             ,'' AS location
        FROM    ads_sold_out_forecast_compute forecast
                    LEFT JOIN amazon_store_product_sku ps
                              ON      ps.store_id = forecast.dimension_id
                                  AND     ps.erp_sku = forecast.sku
                    LEFT JOIN input
                              ON      input.store_id = forecast.dimension_id
                                  AND     input.sku = forecast.sku
        WHERE   forecast.pt = '@@{yyyyMMdd}'
          AND     forecast.suggested_shipping_quantity1 + forecast.suggested_shipping_quantity2 > NVL(input.fba_min_shipment_qty,0)
        GROUP BY forecast.dimension_id
               ,forecast.skc
    )
   ,brand AS
    (
        SELECT  product.store_id AS store_id
             ,product.skc AS skc
             ,MAX(product.brand_id) AS brand_id --品牌id
             ,MAX(product.brand_name) AS brand_name --品牌名称
        FROM    dwd_bd_brand_store_skc_a product
                    INNER JOIN dwd_bd_brand_store_a store
                               ON      store.store_id = product.store_id
                                   AND     store.pt = '@@{yyyyMMdd}'
                    INNER JOIN dwd_bd_brand_a brand
                               ON      store.brand_id = brand.brand_id
                                   AND     brand.pt = '@@{yyyyMMdd}'
        WHERE   product.pt = '@@{yyyyMMdd}'
          AND     product.is_open_fba_brand = 1
        GROUP BY product.store_id
               ,product.skc
    )
   ,forecast_skc_final AS
    (
        SELECT  pre.product_id AS product_id
             ,pre.product_sku AS product_sku
             ,pre.skc AS skc
             ,CASE   WHEN INSTR(product.main_image_url,'?') > 0 THEN SUBSTR(product.main_image_url,1,INSTR(product.main_image_url,'?') - 1)
                     ELSE product.main_image_url
            END AS image_url
             ,CAST(product.category_id AS BIGINT) AS category_id
             ,CASE   WHEN product.catename_3 IS NOT NULL THEN catename_3
                     WHEN product.catename_2 IS NOT NULL THEN catename_2
                     ELSE product.catename_1
            END AS category_name
             ,pre.season AS season
             ,CAST(pre.store_id AS BIGINT) AS store_id
             ,pre.store_name AS store_name
             ,CONCAT('https://www.amazon.com/dp/',pre.parent_asin) AS store_url
             ,sa_store.team AS store_group
             ,brand.brand_id AS store_brand_id
             ,brand.brand_name AS store_brand_name
             ,pre.status AS status
             ,pre.express_suggest_qty AS express_suggest_qty
             ,pre.air_suggest_qty AS air_suggest_qty
             ,pre.sea_suggest_qty AS sea_suggest_qty
             ,pre.forecast_sales AS forecast_sales
             ,pre.stocking_days AS stocking_days
             ,CASE   WHEN config.sale_maturity_begin_date IS NOT NULL THEN cast(substr(config.sale_maturity_begin_date,2) as BIGINT)
                     WHEN configp1.sale_maturity_begin_date IS NOT NULL THEN cast(substr(configp1.sale_maturity_begin_date,2) as BIGINT)
                     WHEN configp2.sale_maturity_begin_date IS NOT NULL THEN cast(substr(configp2.sale_maturity_begin_date,2) as BIGINT)
            END AS sale_maturity_begin_date
             ,CASE   WHEN config.sale_maturity_end_date IS NOT NULL THEN cast(substr(config.sale_maturity_end_date,2) as BIGINT)
                     WHEN configp1.sale_maturity_end_date IS NOT NULL THEN cast(substr(configp1.sale_maturity_end_date,2) as BIGINT)
                     WHEN configp2.sale_maturity_end_date IS NOT NULL THEN cast(substr(configp2.sale_maturity_end_date,2) as BIGINT)
            END AS sale_maturity_end_date
             ,pre.create_date AS create_date
             ,pre.create_by AS create_by
             ,pre.update_date AS update_date
             ,pre.update_by AS update_by
             ,pre.version AS version
             ,TOUPPER(storelabel.location) AS location
             ,CASE   WHEN storelabel.label_id > 0
            AND config.hot_latest_send_fba_date IS NOT NULL THEN config.hot_latest_send_fba_date
                     WHEN storelabel.label_id > 0
                         AND configp1.hot_latest_send_fba_date IS NOT NULL THEN configp1.hot_latest_send_fba_date
                     WHEN storelabel.label_id > 0
                         AND configp2.hot_latest_send_fba_date IS NOT NULL THEN configp2.hot_latest_send_fba_date
                     WHEN storelabel.label_id IS NULL
                         AND config.latest_send_fba_date IS NOT NULL THEN config.latest_send_fba_date
                     WHEN storelabel.label_id IS NULL
                         AND configp1.latest_send_fba_date IS NOT NULL THEN configp1.latest_send_fba_date
                     WHEN storelabel.label_id IS NULL
                         AND configp2.latest_send_fba_date IS NOT NULL THEN configp2.latest_send_fba_date
            END AS suggest_end_date
        FROM    forecast_skc_pre pre
                    LEFT JOIN ads_product_sku_info product
                              ON      product.product_spec_id = pre.product_spec_id
                                  AND     product.pt = '@@{yyyyMMdd}'
                    LEFT JOIN dwd_oms_sa_store_a sa_store
                              ON      sa_store.id = pre.store_id
                                  AND     sa_store.pt = '@@{yyyyMMdd}'
                    LEFT JOIN brand
                              ON      brand.store_id = pre.store_id
                                  AND     brand.skc = pre.skc
                    LEFT JOIN dwd_EgSys_DistributionAccountInfo_a account
                              ON      account.disainfo_id = pre.store_id
                                  AND     account.pt = '@@{yyyyMMdd}'
                    LEFT JOIN dwd_product_scm_category_mapping_a cate
                              ON      cate.product_category_id = product.category_id
                                  AND     cate.is_deleted = 0
                                  AND     cate.pt = '@@{yyyyMMdd}'
                    LEFT JOIN dwd_scm_bd_category_group_item_a citem
                              ON      citem.category_id = cate.scm_category_id
                                  AND     TOUPPER(citem.location) = TOUPPER(sa_store.location)
                                  AND     citem.pt = '@@{yyyyMMdd}'
                    LEFT JOIN dwd_scm_bd_replenishment_config_a config
                              ON      config.business_type = account.businesstype
                                  AND     config.config_type = 3
                                  AND     config.is_deleted = 0
                                  AND     config.is_enabled = 1
                                  AND     TOUPPER(config.location) = TOUPPER(sa_store.location)
                                  AND     config.category_group_id = citem.group_id
                                  AND     config.season = pre.season
                                  AND     config.pt = '@@{yyyyMMdd}'
                    LEFT JOIN dwd_scm_bd_category_group_item_a citem1
                              ON      citem1.category_id = citem.category_2_id
                                  AND     TOUPPER(citem1.location) = TOUPPER(sa_store.location)
                                  AND     citem1.pt = '@@{yyyyMMdd}'
                    LEFT JOIN dwd_scm_bd_replenishment_config_a configp1
                              ON      configp1.business_type = account.businesstype
                                  AND     configp1.config_type = 3
                                  AND     configp1.is_deleted = 0
                                  AND     configp1.is_enabled = 1
                                  AND     TOUPPER(configp1.location) = TOUPPER(sa_store.location)
                                  AND     configp1.category_group_id = citem1.group_id
                                  AND     configp1.season = pre.season
                                  AND     configp1.pt = '@@{yyyyMMdd}'
                    LEFT JOIN dwd_scm_bd_category_group_item_a citem2
                              ON      citem2.category_id = citem.category_1_id
                                  AND     TOUPPER(citem2.location) = TOUPPER(sa_store.location)
                                  AND     citem2.pt = '@@{yyyyMMdd}'
                    LEFT JOIN dwd_scm_bd_replenishment_config_a configp2
                              ON      configp2.business_type = account.businesstype
                                  AND     configp2.config_type = 3
                                  AND     configp2.is_deleted = 0
                                  AND     configp2.is_enabled = 1
                                  AND     TOUPPER(configp2.location) = TOUPPER(sa_store.location)
                                  AND     configp2.category_group_id = citem2.group_id
                                  AND     configp2.season = pre.season
                                  AND     configp2.pt = '@@{yyyyMMdd}'
                    LEFT JOIN store_label_config storelabel
                              ON      pre.skc = storelabel.skc
                                  AND     pre.store_id = storelabel.store_id
    )
   ,forecast_skc_final_filter_suggest_date AS
    (
        SELECT  CAST(SUBSTRING(CAST(skc.product_id AS STRING),2) AS BIGINT) AS product_id
             ,skc.product_sku AS product_sku
             ,skc.skc AS skc
             ,NVL(skc.image_url,'') AS image_url
             ,NVL(skc.category_id,0) AS category_id
             ,NVL(skc.category_name,'') AS category_name
             ,NVL(skc.season,0) AS season
             ,NVL(skc.store_id,0) AS store_id
             ,NVL(skc.store_name,'') AS store_name
             ,NVL(skc.store_url,'') AS store_url
             ,NVL(skc.store_group,'') AS store_group
             ,NVL(skc.store_brand_id,0) AS store_brand_id
             ,NVL(skc.store_brand_name,'') AS store_brand_name
             ,NVL(skc.status,0) AS status
             ,NVL(skc.express_suggest_qty,0) AS express_suggest_qty
             ,NVL(skc.air_suggest_qty,0) AS air_suggest_qty
             ,NVL(skc.sea_suggest_qty,0) AS sea_suggest_qty
             ,NVL(skc.forecast_sales,0) AS forecast_sales
             ,NVL(skc.stocking_days,0) AS stocking_days
             ,NVL(skc.sale_maturity_begin_date,0) AS sale_maturity_begin_date
             ,NVL(skc.sale_maturity_end_date,0) AS sale_maturity_end_date
             ,NVL(skc.create_date,GETDATE()) AS create_date
             ,NVL(skc.create_by,'') AS create_by
             ,NVL(skc.update_date,GETDATE()) AS update_date
             ,NVL(skc.update_by,'') AS update_by
             ,NVL(skc.version,0) AS version
             ,NVL(skc.location,'') AS location
        FROM    forecast_skc_final skc
        WHERE   TO_DATE(CONCAT('@@{yyyy}',skc.suggest_end_date),'yyyyMMdd') > TO_DATE('@@{yyyyMMdd}','yyyyMMdd')
    )
INSERT OVERWRITE TABLE fba_replenishment_skc PARTITION (pt = '@@{yyyyMMdd}')
SELECT  ROW_NUMBER() OVER (ORDER BY skc.store_id,skc.skc ) AS id
        ,product_id
     ,product_sku
     ,skc
     ,image_url
     ,category_id
     ,category_name
     ,season
     ,store_id
     ,store_name
     ,store_url
     ,store_group
     ,store_brand_id
     ,store_brand_name
     ,status
     ,air_suggest_qty
     ,sea_suggest_qty
     ,forecast_sales
     ,stocking_days
     ,sale_maturity_begin_date
     ,sale_maturity_end_date
     ,create_date
     ,create_by
     ,update_date
     ,update_by
     ,version
     ,location
     ,express_suggest_qty
FROM    forecast_skc_final_filter_suggest_date skc
;

-- DROP TABLE IF EXISTS fba_replenishment_sku;
CREATE TABLE IF NOT EXISTS fba_replenishment_sku
(
    id                                 BIGINT COMMENT '主键id'
    ,fba_replenishment_skc_id          BIGINT COMMENT 'skc级别表主键id' --
    ,product_id                        BIGINT COMMENT '商品id'
    ,skc                               STRING COMMENT 'skc'
    ,sku                               STRING COMMENT 'sku'
    ,size                              STRING COMMENT '尺码' -- dwd_product_spec_a id = spec_id 1:1
    ,store_id                          BIGINT COMMENT '店铺id'
    ,store_name                        STRING COMMENT '店铺名称'
    ,fba_on_the_way_stock              BIGINT COMMENT '在途库存'
    ,fba_available_stock               BIGINT COMMENT '可售库存'
    ,fba_available_stock_of_fulfilable BIGINT COMMENT '可售库存(fulfilable)'
    ,fba_available_stock_of_transfer   BIGINT COMMENT '可售库存(transfer)'
    ,fba_available_stock_of_processing BIGINT COMMENT '可售库存(processing)'
    ,plan_qty                          BIGINT COMMENT 'plan量' -- 不用给
    ,local_share_stock                 BIGINT COMMENT '本地仓共享可用库存' -- 不用给
    ,local_private_stock               BIGINT COMMENT '本地仓个人可用库存' -- 不用给
    ,sales_qty_in_7                    BIGINT COMMENT '7天销量' -- 未知
    ,sales_qty_in_14                   BIGINT COMMENT '14天销量' -- 未知
    ,sales_qty_in_30                   BIGINT COMMENT '30天销量' -- 未知
    ,return_rate                       DECIMAL COMMENT '退货率' -- 未知
    ,stocking_days                     BIGINT COMMENT '备货时长'
    ,category_coefficient              DECIMAL COMMENT '品类系数'
    ,forecast_sales                    BIGINT COMMENT '销量预测'
    ,out_of_stock_date                 DATETIME COMMENT '断货时间'
    ,out_of_stock_days                 BIGINT COMMENT '断货天数'
    ,air_suggest_qty                   BIGINT COMMENT '空运-建议发FBA量'
    ,sea_suggest_qty                   BIGINT COMMENT '海运-建议发FBA量'
    ,stock_sales_rate                  DECIMAL COMMENT '补货后库销比' -- 计算，(库存+补货量)/预测总销量
    ,suggest_delivery_date             DATETIME COMMENT '建议发货日'
    ,apply_for_air_qty                 BIGINT COMMENT '申请发空运数量' -- 不用给
    ,apply_for_sea_qty                 BIGINT COMMENT '申请发海运数量' -- 不用给
    ,apply_reason                      STRING COMMENT '与建议差异较大时填写原因' -- 不用给
    ,seller_sku                        STRING COMMENT 'seller sku' -- 取最近上架的
    ,fnsku                             STRING COMMENT 'fn sku' -- 取最近上架的
    ,item_name                         STRING COMMENT '品名' -- 取最近上架的
    ,marketplace_id                    STRING COMMENT '市场' -- 店铺第一个市场
    ,marketplace_chinese               STRING COMMENT '市场中文名' -- 店铺第一个市场
    ,create_date                       DATETIME COMMENT '创建时间'
    ,create_by                         STRING COMMENT '创建者'
    ,update_date                       DATETIME COMMENT '更新时间'
    ,update_by                         STRING COMMENT '更新者'
    ,version                           BIGINT COMMENT '版本号'
    ,location                          STRING COMMENT '地区'
    ,has_multi_seller_sku              BIGINT COMMENT '是否存在多个seller sku，1是0否'
    ,business_user_account             STRING COMMENT '业务员code'
    ,business_user_name                STRING COMMENT '业务员'
    ,pending_send_air_date_array       STRING COMMENT '待发空运时间数组 yyyyMMdd-yyyyMMdd 逗号分隔'
    ,pending_send_air_qty_array        STRING COMMENT '待发空运数量数组 逗号分隔'
    ,latest_sea_send_date              DATETIME COMMENT '海运最晚出库时间'
    ,latest_air_send_date              DATETIME COMMENT '空运最晚出库时间'
    ,local_stock                       BIGINT COMMENT '本地库存'
    ,express_suggest_qty               BIGINT COMMENT '快递-建议发FBA量'
)
    COMMENT 'FBA智能补货-sku级别'
    PARTITIONED BY
(
    pt                                 STRING COMMENT '分区'
)
    LIFECYCLE 7
;

WITH product_sku_pre AS
    (
        SELECT  ROW_NUMBER() OVER (PARTITION BY sku.disainfo_id,spec.spec_sku ORDER BY sku.open_date DESC ) AS rn
            ,sku.open_date AS open_date
             ,sku.disainfo_id AS store_id
             ,spec.spec_sku AS erp_sku
             ,sku.seller_sku AS seller_sku
             ,sku.marketplace_id AS marketplace_id
             ,sku.marketplace_chinese AS marketplace_chinese
             ,sku.fnsku AS fnsku
             ,sku.item_name AS item_name
             ,spec.product_spec_id AS product_spec_id
        FROM    dwd_amazon_store_product_sku_a sku
                    INNER JOIN dwd_product_spec_a spec
                               ON      spec.product_spec_id = sku.product_spec_id
                                   AND     spec.pt = '@@{yyyyMMdd}'
        WHERE   sku.pt = '@@{yyyyMMdd}'
          AND     sku.fulfillment_channel <> 'DEFAULT'
    )
   ,seller_sku_shipment_info AS
    (
        SELECT *
        FROM (
                 SELECT  ROW_NUMBER() OVER (PARTITION BY pre.seller_sku,pre.store_id ORDER BY item.create_date DESC ) AS rn
                ,pre.seller_sku AS seller_sku
                      ,pre.store_id AS store_id
                      ,item.create_date AS shipment_create_date
                 FROM    product_sku_pre pre
                             INNER JOIN dwd_fba_inbound_shipment_a shipment
                                        ON      pre.store_id = shipment.amazon_store_id
                                            AND     shipment.pt = '@@{yyyyMMdd}'
                             INNER JOIN dwd_fba_inbound_shipment_item_a item
                                        ON      item.pt = '@@{yyyyMMdd}'
                                            AND     item.seller_sku = pre.seller_sku
                                            AND     shipment.shipment_id = item.shipment_id
             )
        WHERE  rn = 1
    )
   ,amazon_store_product_sku AS
    (
        SELECT *
        FROM (
                 SELECT ROW_NUMBER() OVER (PARTITION BY item.erp_sku,item.store_id ORDER BY item.compare_date DESC ) AS rn
                ,item.store_id AS store_id
                      ,item.erp_sku AS erp_sku
                      ,item.seller_sku AS seller_sku
                      ,item.marketplace_id AS marketplace_id
                      ,item.marketplace_chinese AS marketplace_chinese
                      ,item.fnsku AS fnsku
                      ,item.item_name AS item_name
                      ,item.product_spec_id AS product_spec_id
                      ,item.has_multi_seller_sku AS has_multi_seller_sku
                 FROM (
                          SELECT  t.store_id
                               ,t.erp_sku
                               ,t.seller_sku
                               ,t.marketplace_id
                               ,t.marketplace_chinese
                               ,t.fnsku
                               ,t.item_name
                               ,t.product_spec_id
                               ,1 AS has_multi_seller_sku
                               ,CASE WHEN info.shipment_create_date IS NULL THEN t.open_date ELSE info.shipment_create_date END AS compare_date
                          FROM    product_sku_pre t
                                      LEFT JOIN seller_sku_shipment_info info
                                                ON t.store_id = info.store_id
                                                    AND t.seller_sku = info.seller_sku
                          where   EXISTS (
                              SELECT  pre.store_id
                              FROM    product_sku_pre pre
                              WHERE      pre.store_id = t.store_id
                                AND     pre.erp_sku = t.erp_sku
                                AND     t.rn <> pre.rn
                          )
                      ) item
             )
        WHERE rn = 1
        UNION ALL
        SELECT  1 as rn
             ,t.store_id
             ,t.erp_sku
             ,t.seller_sku
             ,t.marketplace_id
             ,t.marketplace_chinese
             ,t.fnsku
             ,t.item_name
             ,t.product_spec_id
             ,0 AS has_multi_seller_sku
        FROM    product_sku_pre t
        where   NOT EXISTS (
            SELECT  pre.store_id
            FROM    product_sku_pre pre
            WHERE      pre.store_id = t.store_id
              AND     pre.erp_sku = t.erp_sku
              AND     t.rn <> pre.rn
        )

    )
   ,on_the_way_stock AS
    (
        SELECT  otw.store_id AS store_id
             ,otw.sku AS sku
             ,SUM(otw.quantity) AS quantity
             ,SUM(otw.quantity_of_plan) AS quantity_of_plan
             ,SUM(otw.quantity_of_shipment) AS quantity_of_shipment
        FROM    ads_fba_on_the_way otw
        WHERE   otw.pt = '@@{yyyyMMdd}'
        GROUP BY otw.store_id
               ,otw.sku
    )
   ,store_sku_sale AS
    (
        SELECT  CAST(fc.store_id AS BIGINT) AS store_id
             ,fc.sku AS sku
             ,MAX(fc.sku_nearly_average_daily_sale_qty) AS daily_sale
        FROM    ads_sku_store_sales_forecast fc
        WHERE   fc.pt = '@@{yyyyMMdd}'
          AND     fc.store_id > 0
        GROUP BY fc.store_id
               ,fc.sku
    )
   ,store_sku_local_stock AS
    (
        SELECT  p.store_id
             ,p.product_spec_id
             ,NVL(ss.Company_sale_stock,0) + NVL(s.stock,0) + NVL(cs.company_stock,0) - NVL(css.store_company_stock,0) + NVL(ds.department_stock,0) - NVL(dss.store_department_stock,0) AS local_stock
        FROM    amazon_store_product_sku p
                    LEFT JOIN dwd_amazon_store_a store
                              ON      p.store_id = store.amazon_store_id
                                  AND     store.pt = '@@{yyyyMMdd}' -- 公司共享
                    LEFT JOIN   (
            SELECT  s.product_spec_id AS product_spec_id
                 ,SUM(s.Company_sale_stock) AS Company_sale_stock
                 ,s.deliverylocation AS deliverylocation
            FROM    dws_sku_deliverylocation_gather s
            WHERE   s.pt = '@@{yyyyMMdd}'
            GROUP BY s.product_spec_id
                   ,s.deliverylocation
        ) ss
                                ON      p.product_spec_id = ss.product_spec_id
                                    AND     TOUPPER(store.delivery_location) = TOUPPER(ss.deliverylocation) -- 个人可用 + 采购在途
                    LEFT JOIN   (
            SELECT  s.disainfo_id AS store_id
                 ,s.product_spec_id AS product_spec_id
                 ,s.deliverylocation AS deliverylocation
                 ,SUM(s.available_qty + s.work_in_supplier) AS stock
            FROM    dws_sku_store_businesstype_gather AS s
            WHERE   s.pt = '@@{yyyyMMdd}'
              AND     s.disainfo_id > 0
            GROUP BY s.disainfo_id
                   ,s.product_spec_id
                   ,s.deliverylocation
        ) s
                                ON      p.product_spec_id = s.product_spec_id
                                    AND     p.store_id = s.store_id
                                    AND     TOUPPER(store.delivery_location) = TOUPPER(s.deliverylocation) -- 不是本店铺的公司共享库存
                    LEFT JOIN   (
            SELECT  s.product_spec_id AS product_spec_id
                 ,s.deliverylocation AS deliverylocation
                 ,SUM(s.privatestock_company) AS company_stock
            FROM    dws_sku_store_businesstype_gather s
            WHERE   s.pt = '@@{yyyyMMdd}'
              AND     s.privatestock_company > 0
            GROUP BY s.product_spec_id
                   ,s.deliverylocation
        ) cs
                                ON      s.product_spec_id = cs.product_spec_id
                                    AND     TOUPPER(cs.deliverylocation) = TOUPPER(store.delivery_location)
                    LEFT JOIN   (
            SELECT  s.product_spec_id AS product_spec_id
                 ,s.disainfo_id AS store_id
                 ,SUM(s.privatestock_company) AS store_company_stock
            FROM    dws_sku_store_businesstype_gather s
            WHERE   s.pt = '@@{yyyyMMdd}'
              AND     s.disainfo_id > 0
              AND     s.privatestock_company > 0
            GROUP BY s.disainfo_id
                   ,s.product_spec_id
        ) css
                                ON      s.product_spec_id = css.product_spec_id
                                    AND     s.store_id = css.store_id -- 不是本店铺的、是本部门的 部门共享库存
                    LEFT JOIN   (
            SELECT  s.product_spec_id AS product_spec_id
                 ,s.deliverylocation AS deliverylocation
                 ,s.businesstype AS businesstype
                 ,SUM(s.privatestock_depart) AS department_stock
            FROM    dws_sku_store_businesstype_gather s
            WHERE   s.pt = '@@{yyyyMMdd}'
              AND     s.privatestock_depart > 0
            GROUP BY s.product_spec_id
                   ,s.deliverylocation
                   ,s.businesstype
        ) ds
                                ON      s.product_spec_id = ds.product_spec_id
                                    AND     TOUPPER(ds.deliverylocation) = TOUPPER(store.delivery_location)
                                    AND     TOUPPER(ds.businesstype) = TOUPPER(store.business_type)
                    LEFT JOIN   (
            SELECT  s.product_spec_id AS product_spec_id
                 ,s.disainfo_id AS store_id
                 ,SUM(s.privatestock_depart) AS store_department_stock
            FROM    dws_sku_store_businesstype_gather s
            WHERE   s.pt = '@@{yyyyMMdd}'
              AND     s.disainfo_id > 0
              AND     s.privatestock_depart > 0
            GROUP BY s.disainfo_id
                   ,s.product_spec_id
        ) dss
                                ON      s.product_spec_id = dss.product_spec_id
                                    AND     s.store_id = dss.store_id
    )
   ,sku_pre AS
    (
        SELECT  skc.id AS fba_replenishment_skc_id
             ,skc.product_id AS product_id
             ,skc.skc AS skc
             ,forecast.sku AS sku
             ,spec.size AS size
   ,skc.store_id AS store_id
   ,skc.store_name AS store_name
   ,otw.quantity_of_shipment AS fba_on_the_way_stock
   ,forecast.available_stock AS fba_available_stock
   ,forecast.available_stock_of_fulfilable AS fba_available_stock_of_fulfilable
   ,forecast.available_stock_of_transfer AS fba_available_stock_of_transfer
   ,forecast.available_stock_of_processing AS fba_available_stock_of_processing
   ,otw.quantity_of_plan AS plan_qty
   ,0 AS local_share_stock
   ,0 AS local_private_stock
   ,sale.sale_vol_7 AS sales_qty_in_7
   ,sale.sale_vol_14 AS sales_qty_in_14
   ,sale.sale_vol_30 AS sales_qty_in_30
   ,sale.return_qty / sale.output_qty AS return_rate
   ,forecast.replenish_days AS stocking_days
   ,forecast.coefficient_total AS category_coefficient
   ,forecast.forecast_sales_total AS forecast_sales
   ,forecast.out_of_stock_date AS out_of_stock_date
   ,forecast.out_of_stock_days AS out_of_stock_days
   ,forecast.suggested_shipping_quantity1 AS sea_suggest_qty
   ,forecast.suggested_shipping_quantity2 AS air_suggest_qty
   ,forecast.suggested_shipping_quantity3 AS express_suggest_qty
   ,CASE   WHEN sku_sale.daily_sale IS NULL
    OR sku_sale.daily_sale <= 0 THEN 0
    ELSE (
    forecast.available_stock + NVL(otw.quantity,0) + forecast.suggested_shipping_quantity1 + forecast.suggested_shipping_quantity2
    ) / sku_sale.daily_sale
END AS stock_sales_rate -- 需要用到空运，则都是紧急，当天发，否则常规补货日发
            ,CASE   WHEN sa_store.fba_regular_replenishment_day = '周一' THEN DATEADD(GETDATE(),
                            IF(WEEKDAY(GETDATE()) <= 0,0 - WEEKDAY(GETDATE()),6 - WEEKDAY(GETDATE()))
                    ,'dd')
                    WHEN sa_store.fba_regular_replenishment_day = '周二' THEN DATEADD(GETDATE(),
                            IF(WEEKDAY(GETDATE()) <= 1,1 - WEEKDAY(GETDATE()),7 - WEEKDAY(GETDATE()))
                    ,'dd')
                    WHEN sa_store.fba_regular_replenishment_day = '周三' THEN DATEADD(GETDATE(),
                            IF(WEEKDAY(GETDATE()) <= 2,2 - WEEKDAY(GETDATE()),8 - WEEKDAY(GETDATE()))
                    ,'dd')
                    WHEN sa_store.fba_regular_replenishment_day = '周四' THEN DATEADD(GETDATE(),
                            IF(WEEKDAY(GETDATE()) <= 3,3 - WEEKDAY(GETDATE()),9 - WEEKDAY(GETDATE()))
                    ,'dd')
                    WHEN sa_store.fba_regular_replenishment_day = '周五' THEN DATEADD(GETDATE(),
                            IF(WEEKDAY(GETDATE()) <= 4,4 - WEEKDAY(GETDATE()),10 - WEEKDAY(GETDATE()))
                    ,'dd')
                    WHEN sa_store.fba_regular_replenishment_day = '周六' THEN DATEADD(GETDATE(),
                            IF(WEEKDAY(GETDATE()) <= 5,5 - WEEKDAY(GETDATE()),11 - WEEKDAY(GETDATE()))
                    ,'dd')
                    WHEN sa_store.fba_regular_replenishment_day = '周日' THEN DATEADD(GETDATE(),
                            IF(WEEKDAY(GETDATE()) <= 6,6 - WEEKDAY(GETDATE()),12 - WEEKDAY(GETDATE()))
                    ,'dd')
                    ELSE GETDATE()
END AS regular_replenish_date
            ,forecast.suggested_shipping_quantity1 AS apply_for_sea_qty
            ,forecast.suggested_shipping_quantity2 AS apply_for_air_qty
            ,forecast.suggested_shipping_quantity3 AS apply_for_express_qty
            ,'' AS apply_reason
            ,ps.seller_sku AS seller_sku
            ,ps.fnsku AS fnsku
            ,ps.item_name AS item_name
            ,ps.marketplace_id AS marketplace_id
            ,ps.marketplace_chinese AS marketplace_chinese
            ,GETDATE() AS create_date
            ,'ads_sold_out_forecast_mr' AS create_by
            ,GETDATE() AS update_date
            ,'ads_sold_out_forecast_mr' AS update_by
            ,0 AS version
            ,skc.location AS location
            ,ps.has_multi_seller_sku AS has_multi_seller_sku
            ,mapping.user_account AS business_user_account
            ,mapping.user_name AS business_user_name
            ,forecast.pending_send_air_date_array AS pending_send_air_date_array
            ,forecast.pending_send_air_qty_array AS pending_send_air_qty_array
            ,CASE   WHEN forecast.suggested_shipping_quantity1 > 0 THEN forecast.suggested_shipping_date1
                    ELSE NULL
END AS latest_sea_send_date
            ,CASE   WHEN forecast.suggested_shipping_quantity2 > 0 THEN forecast.suggested_shipping_date2
                    ELSE NULL
END AS latest_air_send_date
            ,CASE   WHEN forecast.suggested_shipping_quantity3 > 0 THEN forecast.suggested_shipping_date3
                    ELSE NULL
END AS latest_express_send_date
            ,NVL(stock.local_stock,0) AS local_stock
    FROM    fba_replenishment_skc skc
    INNER JOIN ads_sold_out_forecast_compute forecast
    ON      forecast.dimension_id = skc.store_id
    AND     forecast.skc = skc.skc
    AND     forecast.pt = '@@{yyyyMMdd}'
    LEFT JOIN dwd_product_spec_a spec
    ON      spec.product_spec_id = forecast.spec_id
    AND     spec.pt = '@@{yyyyMMdd}'
    LEFT JOIN amazon_store_product_sku ps
    ON      ps.store_id = forecast.dimension_id
    AND     ps.erp_sku = forecast.sku
    LEFT JOIN store_sku_local_stock stock
    ON      ps.store_id = stock.store_id
    AND     ps.product_spec_id = stock.product_spec_id
    LEFT JOIN dwd_oms_sa_store_config_a sa_store
    ON      sa_store.store_id = forecast.dimension_id
    AND     sa_store.pt = '@@{yyyyMMdd}'
    LEFT JOIN dws_sale_disAInfo_sku sale
    ON      sale.disainfo_id = skc.store_id
    AND     sale.product_spec_id = forecast.spec_id
    AND     sale.pt = '@@{yyyyMMdd}'
    LEFT JOIN dwd_amazon_store_sku_salesperson_mapping_a mapping
    ON      skc.store_id = mapping.amazon_store_id
    AND     ps.seller_sku = mapping.seller_sku
    AND     mapping.pt = '@@{yyyyMMdd}'
    LEFT JOIN on_the_way_stock otw
    ON      otw.store_id = skc.store_id
    AND     otw.sku = forecast.sku
    LEFT JOIN store_sku_sale sku_sale
    ON      sku_sale.sku = forecast.sku
    AND     sku_sale.store_id = forecast.dimension_id
    WHERE   skc.pt = '@@{yyyyMMdd}'
)
,sku_final AS
(
    SELECT  sku.fba_replenishment_skc_id AS fba_replenishment_skc_id
            ,sku.product_id AS product_id
            ,sku.skc AS skc
            ,sku.sku AS sku
            ,sku.size AS size
            ,sku.store_id AS store_id
            ,sku.store_name AS store_name
            ,sku.fba_on_the_way_stock AS fba_on_the_way_stock
            ,sku.fba_available_stock AS fba_available_stock
            ,sku.fba_available_stock_of_fulfilable AS fba_available_stock_of_fulfilable
            ,sku.fba_available_stock_of_transfer AS fba_available_stock_of_transfer
            ,sku.fba_available_stock_of_processing AS fba_available_stock_of_processing
            ,sku.plan_qty AS plan_qty
            ,sku.local_share_stock AS local_share_stock
            ,sku.local_private_stock AS local_private_stock
            ,sku.sales_qty_in_7 AS sales_qty_in_7
            ,sku.sales_qty_in_14 AS sales_qty_in_14
            ,sku.sales_qty_in_30 AS sales_qty_in_30
            ,sku.return_rate AS return_rate
            ,sku.stocking_days AS stocking_days
            ,sku.category_coefficient AS category_coefficient
            ,sku.forecast_sales AS forecast_sales
            ,sku.out_of_stock_date AS out_of_stock_date
            ,sku.out_of_stock_days AS out_of_stock_days
            ,sku.express_suggest_qty AS express_suggest_qty
            ,sku.air_suggest_qty AS air_suggest_qty
            ,sku.sea_suggest_qty AS sea_suggest_qty
            ,sku.stock_sales_rate AS stock_sales_rate
            ,CASE   WHEN sku.air_suggest_qty > 0 THEN DATETRUNC(GETDATE(),'DD')
                    ELSE DATETRUNC(sku.regular_replenish_date,'DD')
            END AS suggest_delivery_date -- 需要用到空运，则都是紧急，当天发，否则常规补货日发
            ,sku.apply_for_express_qty AS apply_for_express_qty
            ,sku.apply_for_air_qty AS apply_for_air_qty
            ,sku.apply_for_sea_qty AS apply_for_sea_qty
            ,sku.apply_reason AS apply_reason
            ,sku.seller_sku AS seller_sku
            ,sku.fnsku AS fnsku
            ,sku.item_name AS item_name
            ,sku.marketplace_id AS marketplace_id
            ,sku.marketplace_chinese AS marketplace_chinese
            ,sku.create_date AS create_date
            ,sku.create_by AS create_by
            ,sku.update_date AS update_date
            ,sku.update_by AS update_by
            ,sku.version AS version
            ,sku.location AS location
            ,sku.has_multi_seller_sku AS has_multi_seller_sku
            ,sku.business_user_account AS business_user_account
            ,sku.business_user_name AS business_user_name
            ,sku.pending_send_air_date_array AS pending_send_air_date_array
            ,sku.pending_send_air_qty_array AS pending_send_air_qty_array
            ,sku.latest_express_send_date AS latest_express_send_date
            ,sku.latest_air_send_date AS latest_air_send_date
            ,sku.latest_sea_send_date AS latest_sea_send_date
            ,sku.local_stock AS local_stock
    FROM    sku_pre sku
)
INSERT OVERWRITE TABLE fba_replenishment_sku PARTITION (pt = '@@{yyyyMMdd}')
SELECT  NULL AS id
     ,sku.fba_replenishment_skc_id AS fba_replenishment_skc_id
     ,sku.product_id AS product_id
     ,sku.skc AS skc
     ,sku.sku AS sku
     ,NVL(sku.size,'') AS size
        ,NVL(sku.store_id,0) AS store_id
        ,NVL(sku.store_name,'') AS store_name
        ,NVL(sku.fba_on_the_way_stock,0) AS fba_on_the_way_stock
        ,NVL(sku.fba_available_stock,0) AS fba_available_stock
        ,NVL(sku.fba_available_stock_of_fulfilable,0) AS fba_available_stock_of_fulfilable
        ,NVL(sku.fba_available_stock_of_transfer,0) AS fba_available_stock_of_transfer
        ,NVL(sku.fba_available_stock_of_processing,0) AS fba_available_stock_of_processing
        ,NVL(sku.plan_qty,0) AS plan_qty
        ,NVL(sku.local_share_stock,0) AS local_share_stock
        ,NVL(sku.local_private_stock,0) AS local_private_stock
        ,NVL(sku.sales_qty_in_7,0) AS sales_qty_in_7
        ,NVL(sku.sales_qty_in_14,0) AS sales_qty_in_14
        ,NVL(sku.sales_qty_in_30,0) AS sales_qty_in_30
        ,NVL(sku.return_rate,0) AS return_rate
        ,NVL(sku.stocking_days,0) AS stocking_days
        ,NVL(sku.category_coefficient,0) AS category_coefficient
        ,NVL(sku.forecast_sales,0) AS forecast_sales
        ,sku.out_of_stock_date AS out_of_stock_date
        ,NVL(sku.out_of_stock_days,0) AS out_of_stock_days
        ,NVL(sku.air_suggest_qty,0) AS air_suggest_qty
        ,NVL(sku.sea_suggest_qty,0) AS sea_suggest_qty
        ,NVL(sku.stock_sales_rate,0) AS stock_sales_rate
        ,sku.suggest_delivery_date AS suggest_delivery_date
        ,NVL(sku.apply_for_air_qty,0) AS apply_for_air_qty
        ,NVL(sku.apply_for_sea_qty,0) AS apply_for_sea_qty
        ,NVL(sku.apply_reason,'') AS apply_reason
        ,NVL(sku.seller_sku,'') AS seller_sku
        ,NVL(sku.fnsku,'') AS fnsku -- 待整合
        ,NVL(sku.item_name,'') AS item_name -- 待整合
        ,NVL(sku.marketplace_id,'') AS marketplace_id
        ,NVL(sku.marketplace_chinese,'') AS marketplace_chinese
        ,NVL(sku.create_date,TO_DATE('1970-01-01','yyyy-MM-dd')) AS create_date
        ,NVL(sku.create_by,'') AS create_by
        ,NVL(sku.update_date,TO_DATE('1970-01-01','yyyy-MM-dd')) AS update_date
        ,NVL(sku.update_by,'') AS update_by
        ,NVL(sku.version,0) AS version
        ,NVL(sku.location,'') AS location
        ,NVL(sku.has_multi_seller_sku,0) AS has_multi_seller_sku
        ,NVL(sku.business_user_account,'') AS business_user_account
        ,NVL(sku.business_user_name,'') AS business_user_name
        ,NVL(sku.pending_send_air_date_array,'') AS pending_send_air_date_array
        ,NVL(sku.pending_send_air_qty_array,'') AS pending_send_air_qty_array
        ,NVL(sku.latest_sea_send_date,NULL) AS latest_sea_send_date
        ,NVL(sku.latest_air_send_date,NULL) AS latest_air_send_date
        ,NVL(sku.local_stock,0) AS local_stock
        ,NVL(sku.express_suggest_qty,0) AS express_suggest_qty
FROM    sku_final sku
;

-- DROP TABLE IF EXISTS fba_replenishment_label_info;
CREATE TABLE IF NOT EXISTS fba_replenishment_label_info
(
    id           BIGINT COMMENT '主键id' -- fba_replenishment_skc_id BIGINT     comment 'skc级别表主键id', -- 去掉，不使用
    ,product_id  BIGINT COMMENT '商品id'
    ,skc         STRING COMMENT 'skc'
    ,store_id    BIGINT COMMENT '店铺id'
    ,store_name  STRING COMMENT '店铺名称'
    ,label_type  STRING COMMENT '标签类型：商品标签、店铺标签'
    ,label_id    BIGINT COMMENT '标签主键id' -- 商品标签 dwd_product_label_a product_id = product_id and is_delete = 0
    ,label_name  STRING COMMENT '标签名称'
    ,is_manual   BIGINT COMMENT '是否人工导入的标签：1是0否'
    ,create_date DATETIME COMMENT '创建时间'
    ,create_by   STRING COMMENT '创建者'
    ,update_date DATETIME COMMENT '更新时间'
    ,update_by   STRING COMMENT '更新者'
    ,version     BIGINT COMMENT '版本号'
    ,location    STRING COMMENT '地区'
)
    COMMENT 'FBA智能补货-标签信息'
    PARTITIONED BY
(
    pt           STRING COMMENT '分区'
)
    LIFECYCLE 7
;

WITH store_label AS
    (
        SELECT  fc.store_id AS store_id
             ,MAX(fc.store_name) AS store_name
             ,fc.skc AS skc
             ,MAX(fc.product_id) AS product_id
             ,'店铺标签' AS label_type
             ,fc.popular_label_id AS label_id
             ,MAX(fc.popular_label_name) AS label_name
             ,0 AS is_manual
             ,GETDATE() AS create_date
             ,'ads_sold_out_forecast_output' AS create_by
             ,GETDATE() AS update_date
             ,'ads_sold_out_forecast_output' AS update_by
             ,0 AS version
             ,'' AS location
        FROM    ads_sku_store_sales_forecast fc
        WHERE   fc.pt = '@@{yyyyMMdd}'
          AND     fc.store_id > 0
        GROUP BY fc.store_id
               ,fc.skc
               ,fc.popular_label_id
    )
   ,product_label AS
    (
        SELECT  sl.store_id AS store_id
             ,MAX(sl.store_name) AS store_name
             ,sl.skc AS skc
             ,MAX(sl.product_id) AS product_id
             ,'商品标签' AS label_type
             ,pl.label_id AS label_id
             ,MAX(config.label_name) AS label_name
             ,0 AS is_manual
             ,GETDATE() AS create_date
             ,'ads_sold_out_forecast_output' AS create_by
             ,GETDATE() AS update_date
             ,'ads_sold_out_forecast_output' AS update_by
             ,0 AS version
             ,'' AS location
        FROM    store_label sl
                    INNER JOIN dwd_product_label_a pl
                               ON      pl.product_id = sl.product_id
                                   AND     pl.pt = '@@{yyyyMMdd}'
                    INNER JOIN dwd_config_label_a config
                               ON      config.label_id = pl.label_id
                                   AND     config.pt = '@@{yyyyMMdd}'
        WHERE   pl.is_delete = 0
          AND     config.label_group = '季节'
        GROUP BY sl.store_id
               ,sl.skc
               ,pl.label_id
    )
   ,yearly_label AS
    (
        SELECT  fc.store_id AS store_id
             ,MAX(fc.store_name) AS store_name
             ,fc.skc AS skc
             ,MAX(fc.product_id) AS product_id
             ,'去年店铺标签' AS label_type
             ,MAX(yl.label_id) AS label_id
             ,MAX(yl.label_name) AS label_name
             ,0 AS is_manual
             ,GETDATE() AS create_date
             ,'ads_sold_out_forecast_output' AS create_by
             ,GETDATE() AS update_date
             ,'ads_sold_out_forecast_output' AS update_by
             ,0 AS version
             ,'' AS location
        FROM    ads_sku_store_sales_forecast fc
                    LEFT JOIN ads_popular_goods_label_yearly yl
                              ON      yl.store_id = fc.store_id
                                  AND     yl.platform_id = fc.platform_id
                                  AND     yl.spec_id = fc.spec_id
                                  AND     yl.business_type = fc.business_type
                                  AND     yl.scm_category_id = fc.scm_category_id
                                  AND     yl.season_id = fc.season_id
        WHERE   fc.pt = '@@{yyyyMMdd}'
          AND     fc.store_id > 0
          AND     yl.label_id > 0
        GROUP BY fc.store_id
               ,fc.skc
    )
   ,label AS
    (
        SELECT  *
        FROM    store_label
        UNION ALL
        SELECT  *
        FROM    product_label
        UNION ALL
        SELECT  *
        FROM    yearly_label
    )
INSERT OVERWRITE TABLE fba_replenishment_label_info PARTITION (pt = '@@{yyyyMMdd}')
SELECT  NULL AS id
     ,CAST(SUBSTRING(CAST(final.product_id AS STRING),2) AS BIGINT) AS product_id
     ,final.skc AS skc
     ,final.store_id AS store_id
     ,final.store_name AS store_name
     ,final.label_type AS label_type
     ,final.label_id AS label_id
     ,final.label_name AS label_name
     ,final.is_manual AS is_manual
     ,final.create_date AS create_date
     ,final.create_by AS create_by
     ,final.update_date AS update_date
     ,final.update_by AS update_by
     ,final.version AS version
     ,final.location AS location
FROM    label final
;



