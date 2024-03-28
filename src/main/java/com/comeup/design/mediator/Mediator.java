package com.comeup.design.mediator;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: qiu wanzi
 *  note: 中介者模式
 * @date: 2024年3月8日 0008
 * @version: 1.0
 * @description: TODO
 */
@Slf4j
public class Mediator {

    private Buyer buyer;
    private Seller seller;

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean checkPrice() {
        log.info("[log] Mediator checkPrice buyer.getPrice: {} timestamp: {}", JSON.toJSONString(buyer.getPrice()), LocalDateTime.now());
        log.info("[log] Mediator checkPrice seller.getPrice: {} timestamp: {}", JSON.toJSONString(seller.getPrice()), LocalDateTime.now());
        return buyer.getPrice().compareTo(seller.getPrice()) == 0;
    }


    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        Seller seller = new Seller(BigDecimal.TEN, mediator);
        Buyer buyer = new Buyer(BigDecimal.TEN, mediator);
        if (mediator.checkPrice()) {
            log.info("success");
        }
    }


}
