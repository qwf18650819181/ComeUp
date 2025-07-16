package com.comeup;

/**
 * @author ht
 * @date 2021-10-28
 */
public class PublishProductAttribute {
    private String attrName;
    private String attrValue;
    private String attrText;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrText() {
        return attrText;
    }

    public void setAttrText(String attrText) {
        this.attrText = attrText;
    }

    public PublishProductAttribute() {

    }

    public PublishProductAttribute(String attrName, String attrValue, String attrText) {
        this.attrName = attrName;
        this.attrValue = attrValue;
        this.attrText = attrText;
    }
}
