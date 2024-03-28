package com.comeup.design.mediator;

import java.math.BigDecimal;

/**
 * @author: qiu wanzi
 * @date: 2024年3月8日 0008
 * @version: 1.0
 * @description: TODO
 */
public class Buyer {

    private BigDecimal price;
    private Mediator mediator;

    public Buyer(BigDecimal price, Mediator mediator) {
        this.price = price;
        this.mediator = mediator;
        this.mediator.setBuyer(this);
    }
    public BigDecimal getPrice() {
        return price;
    }
}
