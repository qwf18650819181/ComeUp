package com.comeup.design.mediator;

import java.math.BigDecimal;

/**
 * @author: qiu wanzi
 * @date: 2024年3月8日 0008
 * @version: 1.0
 * @description: TODO
 */
public class Seller {

    private BigDecimal price;
    private Mediator mediator;

    public Seller(BigDecimal price, Mediator mediator) {
        this.price = price;
        this.mediator = mediator;
        this.mediator.setSeller(this);
    }
    public BigDecimal getPrice() {
        return price;
    }

}
