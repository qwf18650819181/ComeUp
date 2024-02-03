package com.comeup.shiying;

import lombok.Data;

/**
 * fba智能补货列表导出
 * @author ht
 * @date 2023-03-03
 */
@Data
public class FbaReplenishmentSalesPercentageExport {
    @NsyExcelProperty("站点")
    private String storeName;
    @NsyExcelProperty("parent code")
    private String parentCode;
    @NsyExcelProperty("SPU")
    private String spu;
    @NsyExcelProperty("SKC")
    private String skc;
    @NsyExcelProperty("SKC占parent比例(系统建议)")
    private String sysSalesPercentage;
    @NsyExcelProperty("SKC占parent比例(用户设置)")
    private String userSalesPercentage;

}




