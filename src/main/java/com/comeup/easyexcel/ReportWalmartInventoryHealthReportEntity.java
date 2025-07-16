package com.comeup.easyexcel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * report_walmart_inventory_health_report
 * @author 
 */
public class ReportWalmartInventoryHealthReportEntity {

    private Integer id;

    /**
     * 报表时间
     */
    private Date reportDate;

    /**
     * 店铺id
     */
    private Integer websiteId;

    /**
     * 店铺名
     */
    private String websiteName;

    /**
     * gtin
     */
    private String gtin;

    /**
     * Item ID
     */
    private String itemId;

    /**
     * Vendor (Seller) SKU
     */
    private String sku;

    /**
     * Product Name
     */
    private String productName;

    /**
     * Brand Name(品牌)
     */
    private String brandName;

    /**
     * 是否在售,1在售
     */
    private Integer published;

    /**
     * 可供出售单位
     */
    private Integer availableToSellUnits;

    /**
     * 损坏的数量
     */
    private Integer damagedReceipts;

    /**
     * 入境单位
     */
    private Integer inboundUnits;

    /**
     * 占用立方
     */
    private BigDecimal cubeUsed;

    /**
     * 首次入库日期
     */
    private Date firstInStockDate;

    /**
     * ATS90天内数
     */
    private Integer ats90;

    /**
     * ATS91-180天内数
     */
    private Integer ats91To180;

    /**
     * ATS181-270天内数
     */
    private Integer ats181To270;

    /**
     * ATS271-365天内数
     */
    private Integer ats271To365;

    /**
     * ATS365天+内数
     */
    private Integer ats365;

    /**
     * 最近30天收到的单位
     */
    private Integer last30DaysUnitsReceived;

    /**
     * 最近30天订单单位
     */
    private Integer last30DaysPoUnits;

    /**
     * 供应天数基于最近 30 天的销售额
     */
    private Integer daysOfSupplyBasedOnLast30DaysSales;

    /**
     * 预计缺货日期
     */
    private Date predictedOutOfStockDate;

    /**
     * 基于最近30天销售额的建议补货数量
     */
    private Integer recommendedReplenishmentQuantityBasedOnLast30DaysSales;

    /**
     * 最近7天销售单位数量
     */
    private Integer last7DaysUnitsSales;

    /**
     * 最近7天销售额
     */
    private BigDecimal last7DaysSales;

    /**
     * 最近7天有货天数
     */
    private Integer last7DaysInstockDays;

    /**
     * 最近30天销售单位数量
     */
    private Integer last30DaysUnitsSales;

    /**
     * 最近30天销售额
     */
    private BigDecimal last30DaysSales;

    /**
     * 最近30天有货天数
     */
    private Integer last30DaysInstockDays;

    /**
     * 预估销量 未来一个月
     * Forecast Aug 12 - Sep 08
     */
    private Integer forecastSaleForFuture30Days;

    /**
     * 预估销量 未来第二个月
     * Forecast Sep 09 - Oct 06
     */
    private Integer forecastSaleForFuture60Days;

    /**
     * 建议库存
     * Suggested Units
     */
    private Integer suggestedUnits;

    public Integer getForecastSaleForFuture30Days() {
        return forecastSaleForFuture30Days;
    }

    public void setForecastSaleForFuture30Days(Integer forecastSaleForFuture30Days) {
        this.forecastSaleForFuture30Days = forecastSaleForFuture30Days;
    }

    public Integer getForecastSaleForFuture60Days() {
        return forecastSaleForFuture60Days;
    }

    public void setForecastSaleForFuture60Days(Integer forecastSaleForFuture60Days) {
        this.forecastSaleForFuture60Days = forecastSaleForFuture60Days;
    }

    public Integer getSuggestedUnits() {
        return suggestedUnits;
    }

    public void setSuggestedUnits(Integer suggestedUnits) {
        this.suggestedUnits = suggestedUnits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
        this.websiteId = websiteId;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Integer getAvailableToSellUnits() {
        return availableToSellUnits;
    }

    public void setAvailableToSellUnits(Integer availableToSellUnits) {
        this.availableToSellUnits = availableToSellUnits;
    }

    public Integer getDamagedReceipts() {
        return damagedReceipts;
    }

    public void setDamagedReceipts(Integer damagedReceipts) {
        this.damagedReceipts = damagedReceipts;
    }

    public Integer getInboundUnits() {
        return inboundUnits;
    }

    public void setInboundUnits(Integer inboundUnits) {
        this.inboundUnits = inboundUnits;
    }

    public BigDecimal getCubeUsed() {
        return cubeUsed;
    }

    public void setCubeUsed(BigDecimal cubeUsed) {
        this.cubeUsed = cubeUsed;
    }

    public Date getFirstInStockDate() {
        return firstInStockDate;
    }

    public void setFirstInStockDate(Date firstInStockDate) {
        this.firstInStockDate = firstInStockDate;
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

    public Integer getLast30DaysUnitsReceived() {
        return last30DaysUnitsReceived;
    }

    public void setLast30DaysUnitsReceived(Integer last30DaysUnitsReceived) {
        this.last30DaysUnitsReceived = last30DaysUnitsReceived;
    }

    public Integer getLast30DaysPoUnits() {
        return last30DaysPoUnits;
    }

    public void setLast30DaysPoUnits(Integer last30DaysPoUnits) {
        this.last30DaysPoUnits = last30DaysPoUnits;
    }

    public Integer getDaysOfSupplyBasedOnLast30DaysSales() {
        return daysOfSupplyBasedOnLast30DaysSales;
    }

    public void setDaysOfSupplyBasedOnLast30DaysSales(Integer daysOfSupplyBasedOnLast30DaysSales) {
        this.daysOfSupplyBasedOnLast30DaysSales = daysOfSupplyBasedOnLast30DaysSales;
    }

    public Date getPredictedOutOfStockDate() {
        return predictedOutOfStockDate;
    }

    public void setPredictedOutOfStockDate(Date predictedOutOfStockDate) {
        this.predictedOutOfStockDate = predictedOutOfStockDate;
    }

    public Integer getRecommendedReplenishmentQuantityBasedOnLast30DaysSales() {
        return recommendedReplenishmentQuantityBasedOnLast30DaysSales;
    }

    public void setRecommendedReplenishmentQuantityBasedOnLast30DaysSales(Integer recommendedReplenishmentQuantityBasedOnLast30DaysSales) {
        this.recommendedReplenishmentQuantityBasedOnLast30DaysSales = recommendedReplenishmentQuantityBasedOnLast30DaysSales;
    }

    public Integer getLast7DaysUnitsSales() {
        return last7DaysUnitsSales;
    }

    public void setLast7DaysUnitsSales(Integer last7DaysUnitsSales) {
        this.last7DaysUnitsSales = last7DaysUnitsSales;
    }

    public BigDecimal getLast7DaysSales() {
        return last7DaysSales;
    }

    public void setLast7DaysSales(BigDecimal last7DaysSales) {
        this.last7DaysSales = last7DaysSales;
    }

    public Integer getLast7DaysInstockDays() {
        return last7DaysInstockDays;
    }

    public void setLast7DaysInstockDays(Integer last7DaysInstockDays) {
        this.last7DaysInstockDays = last7DaysInstockDays;
    }

    public Integer getLast30DaysUnitsSales() {
        return last30DaysUnitsSales;
    }

    public void setLast30DaysUnitsSales(Integer last30DaysUnitsSales) {
        this.last30DaysUnitsSales = last30DaysUnitsSales;
    }

    public BigDecimal getLast30DaysSales() {
        return last30DaysSales;
    }

    public void setLast30DaysSales(BigDecimal last30DaysSales) {
        this.last30DaysSales = last30DaysSales;
    }

    public Integer getLast30DaysInstockDays() {
        return last30DaysInstockDays;
    }

    public void setLast30DaysInstockDays(Integer last30DaysInstockDays) {
        this.last30DaysInstockDays = last30DaysInstockDays;
    }
}