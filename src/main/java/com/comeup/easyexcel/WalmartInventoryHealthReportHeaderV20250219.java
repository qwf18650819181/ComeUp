package com.comeup.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 2025-02-19 改版
 */
public class WalmartInventoryHealthReportHeaderV20250219 {
    @ExcelProperty("GTIN")
    private String gtin;

    /**
     * Item ID
     */
    @ExcelProperty("Item ID")
    private String itemId;

    /**
     * Vendor (Seller) SKU
     */
    @ExcelProperty("Vendor (Seller) SKU")
    private String sku;

    /**
     * Product Name
     */
    @ExcelProperty("Product Name")
    private String productName;

    /**
     * Brand Name(品牌)
     */
    @ExcelProperty("Brand Name")
    private String brandName;

    /**
     * 在售状态
     */
    @ExcelProperty("Publishing Status")
    private String publishingStatus;

    /**
     * 生命周期
     */
    @ExcelProperty("Item Lifecycle")
    private String itemLifecycle;

    /**
     * 可供出售单位
     */
    @ExcelProperty("Available to Sell Units")
    private Integer availableToSellUnits;

    /**
     * 损坏的数量
     */
    @ExcelProperty("Damaged Receipts")
    private Integer damagedReceipts;

    /**
     * 入境单位
     */
    @ExcelProperty("Inbound Units")
    private Integer inboundUnits;

    /**
     * Pending Review
     */
    @ExcelProperty("Pending Review")
    private Integer pendingReview;

    /**
     * 占用立方
     */
    @ExcelProperty("Cube Used")
    private BigDecimal cubeUsed;

    /**
     * 首次入库日期
     */
    @ExcelProperty("First In Stock Date")
    private Date firstInStockDate;

    /**************************** change ******************************/

    /**
     * 供应天数基于最近 30 天的销售额
     */
    @ExcelProperty("Days of supply")
    private String daysOfSupplyBasedOnLast30DaysSales;

    /**
     * 预计缺货日期 2023/12/23
     */
    @ExcelProperty("Predicted out of stock date")
    private String predictedOutOfStockDate;

    /**
     * 建议库存
     * Suggested Units
     */
    @ExcelProperty("Suggested Units")
    private Integer suggestedUnits;

    /**
     * ATS90天内数
     */
    @ExcelProperty("ATS 0-90 days")
    private Integer ats90;

    /**
     * ATS91-180天内数
     */
    @ExcelProperty("ATS 91-180 days")
    private Integer ats91To180;

    /**
     * ATS181-270天内数
     */
    @ExcelProperty("ATS 181-270 days")
    private Integer ats181To270;

    /**
     * ATS271-365天内数
     */
    @ExcelProperty("ATS 271-365 days")
    private Integer ats271To365;

    /**
     * ATS365天+内数
     */
    @ExcelProperty("ATS 365+ days")
    private Integer ats365;

    /**
     * 最近30天收到的单位
     */
    @ExcelProperty("Last 30 days units received")
    private Integer last30DaysUnitsReceived;

    /**
     * 最近30天订单单位
     */
    @ExcelProperty("Last 30 days PO units")
    private Integer last30DaysPoUnits;

    /**
     * 最近7天销售单位数量
     */
    @ExcelProperty("Last 7 days units sales")
    private Integer last7DaysUnitsSales;

    /**
     * 最近7天销售额
     */
    @ExcelProperty("Last 7 days sales")
    private BigDecimal last7DaysSales;

    /**
     * 最近7天有货天数
     */
    @ExcelProperty("Last 7 days instock days")
    private Integer last7DaysInstockDays;

    /**
     * 最近30天销售单位数量
     */
    @ExcelProperty("Last 30 days units sales")
    private Integer last30DaysUnitsSales;

    /**
     * 最近30天销售额
     */
    @ExcelProperty("Last 30 days sales")
    private BigDecimal last30DaysSales;

    /**
     * 最近30天有货天数
     */
    @ExcelProperty("Last 30 days instock days")
    private Integer last30DaysInstockDays;

    public Integer getAts181To270() {
        return ats181To270;
    }

    public void setAts181To270(Integer ats181To270) {
        this.ats181To270 = ats181To270;
    }

    public Integer getAts271To365() {
        return ats271To365;
    }

    public void setAts271To365(Integer ats271To365) {
        this.ats271To365 = ats271To365;
    }

    public Integer getAts365() {
        return ats365;
    }

    public void setAts365(Integer ats365) {
        this.ats365 = ats365;
    }

    public Integer getAts90() {
        return ats90;
    }

    public void setAts90(Integer ats90) {
        this.ats90 = ats90;
    }

    public Integer getAts91To180() {
        return ats91To180;
    }

    public void setAts91To180(Integer ats91To180) {
        this.ats91To180 = ats91To180;
    }

    public Integer getAvailableToSellUnits() {
        return availableToSellUnits;
    }

    public void setAvailableToSellUnits(Integer availableToSellUnits) {
        this.availableToSellUnits = availableToSellUnits;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public BigDecimal getCubeUsed() {
        return cubeUsed;
    }

    public void setCubeUsed(BigDecimal cubeUsed) {
        this.cubeUsed = cubeUsed;
    }

    public Integer getDamagedReceipts() {
        return damagedReceipts;
    }

    public void setDamagedReceipts(Integer damagedReceipts) {
        this.damagedReceipts = damagedReceipts;
    }

    public String getDaysOfSupplyBasedOnLast30DaysSales() {
        return daysOfSupplyBasedOnLast30DaysSales;
    }

    public void setDaysOfSupplyBasedOnLast30DaysSales(String daysOfSupplyBasedOnLast30DaysSales) {
        this.daysOfSupplyBasedOnLast30DaysSales = daysOfSupplyBasedOnLast30DaysSales;
    }

    public Date getFirstInStockDate() {
        return firstInStockDate;
    }

    public void setFirstInStockDate(Date firstInStockDate) {
        this.firstInStockDate = firstInStockDate;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public Integer getInboundUnits() {
        return inboundUnits;
    }

    public void setInboundUnits(Integer inboundUnits) {
        this.inboundUnits = inboundUnits;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemLifecycle() {
        return itemLifecycle;
    }

    public void setItemLifecycle(String itemLifecycle) {
        this.itemLifecycle = itemLifecycle;
    }

    public Integer getLast30DaysInstockDays() {
        return last30DaysInstockDays;
    }

    public void setLast30DaysInstockDays(Integer last30DaysInstockDays) {
        this.last30DaysInstockDays = last30DaysInstockDays;
    }

    public Integer getLast30DaysPoUnits() {
        return last30DaysPoUnits;
    }

    public void setLast30DaysPoUnits(Integer last30DaysPoUnits) {
        this.last30DaysPoUnits = last30DaysPoUnits;
    }

    public BigDecimal getLast30DaysSales() {
        return last30DaysSales;
    }

    public void setLast30DaysSales(BigDecimal last30DaysSales) {
        this.last30DaysSales = last30DaysSales;
    }

    public Integer getLast30DaysUnitsReceived() {
        return last30DaysUnitsReceived;
    }

    public void setLast30DaysUnitsReceived(Integer last30DaysUnitsReceived) {
        this.last30DaysUnitsReceived = last30DaysUnitsReceived;
    }

    public Integer getLast30DaysUnitsSales() {
        return last30DaysUnitsSales;
    }

    public void setLast30DaysUnitsSales(Integer last30DaysUnitsSales) {
        this.last30DaysUnitsSales = last30DaysUnitsSales;
    }

    public Integer getLast7DaysInstockDays() {
        return last7DaysInstockDays;
    }

    public void setLast7DaysInstockDays(Integer last7DaysInstockDays) {
        this.last7DaysInstockDays = last7DaysInstockDays;
    }

    public BigDecimal getLast7DaysSales() {
        return last7DaysSales;
    }

    public void setLast7DaysSales(BigDecimal last7DaysSales) {
        this.last7DaysSales = last7DaysSales;
    }

    public Integer getLast7DaysUnitsSales() {
        return last7DaysUnitsSales;
    }

    public void setLast7DaysUnitsSales(Integer last7DaysUnitsSales) {
        this.last7DaysUnitsSales = last7DaysUnitsSales;
    }

    public Integer getPendingReview() {
        return pendingReview;
    }

    public void setPendingReview(Integer pendingReview) {
        this.pendingReview = pendingReview;
    }

    public String getPredictedOutOfStockDate() {
        return predictedOutOfStockDate;
    }

    public void setPredictedOutOfStockDate(String predictedOutOfStockDate) {
        this.predictedOutOfStockDate = predictedOutOfStockDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPublishingStatus() {
        return publishingStatus;
    }

    public void setPublishingStatus(String publishingStatus) {
        this.publishingStatus = publishingStatus;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getSuggestedUnits() {
        return suggestedUnits;
    }

    public void setSuggestedUnits(Integer suggestedUnits) {
        this.suggestedUnits = suggestedUnits;
    }
}
