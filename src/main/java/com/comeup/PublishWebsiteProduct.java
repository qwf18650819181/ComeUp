package com.comeup;

public class PublishWebsiteProduct {

    private Integer productId;
    private Integer storeId;

    private Integer realStoreId;

    private String storeName;
    private String platform;

    private String salesmanUserAccount;

    private String salesmanUserName;

    public String getSalesmanUserAccount() {
        return salesmanUserAccount;
    }

    public void setSalesmanUserAccount(String salesmanUserAccount) {
        this.salesmanUserAccount = salesmanUserAccount;
    }

    public String getSalesmanUserName() {
        return salesmanUserName;
    }

    public void setSalesmanUserName(String salesmanUserName) {
        this.salesmanUserName = salesmanUserName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }


    public Integer getRealStoreId() {
        return realStoreId;
    }

    public void setRealStoreId(Integer realStoreId) {
        this.realStoreId = realStoreId;
    }
}
