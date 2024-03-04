package com.comeup.shiying.sizechart;

import java.util.List;


/**
 * @author ht
 * @date 2021-11-19
 */
public class SizeChart {
    private String elasticity;
    private List<SizeDetail> detail;
    private String suitPart;

    public String getElasticity() {
        return elasticity;
    }

    public void setElasticity(String elasticity) {
        this.elasticity = elasticity;
    }

    public List<SizeDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<SizeDetail> detail) {
        this.detail = detail;
    }

    public String getSuitPart() {
        return suitPart;
    }

    public void setSuitPart(String suitPart) {
        this.suitPart = suitPart;
    }

    public static class SizeDetail {
        private String size;
        private List<ItemDetail> itemDetail;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public List<ItemDetail> getItemDetail() {
            return itemDetail;
        }

        public void setItemDetail(List<ItemDetail> itemDetail) {
            this.itemDetail = itemDetail;
        }
    }

    public static class ItemDetail {
        private String item;
        private List<OptionDetail> optionDetail;

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public List<OptionDetail> getOptionDetail() {
            return optionDetail;
        }

        public void setOptionDetail(List<OptionDetail> optionDetail) {
            this.optionDetail = optionDetail;
        }
    }

    public static class OptionDetail {
        private String option;
        private String length;

        public String getOption() {
            return option;
        }

        public void setOption(String option) {
            this.option = option;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }
    }
}
