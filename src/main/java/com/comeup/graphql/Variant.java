package com.comeup.graphql;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年12月23日 星期一
 * @version: 1.0
 */
public class Variant {

    Long id;
    String sku;
    String title;     // 商品名称
    String barcode;     // 条形码 UPC, or ISBN
    String inventoryPolicy;  // 缺货时是否同意客户订货  deny continue
    Integer inventoryQuantity;      // 库存数量（含所有位置）
    String price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    Date updatedAt;
    int position;
}
