package com.comeup.slflog;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.time.LocalDateTime;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description: 日志 打多打少都不好,恰如其分最重要
 */
@Slf4j
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final Marker DB_MARKER = MarkerFactory.getMarker("JA");


    public static void main(String[] args) {

        MDC.put("discriminator", "STDOUT1");

        log.info(DB_MARKER, "marker");
        MDC.put("discriminator", "STDOUT2");

        log.warn("LOGGER");
        log.info("logger");
        log.info("{}", LocalDateTime.now());

        SpecificLogger specificLogger = new SpecificLogger();
        specificLogger.testLog();

        log.info("[参数] Main main args: {} timestamp: {}", JSON.toJSONString(args), LocalDateTime.now());

//        log.info("[$TYPE$] $CLASS$ $METHOD$ $OBJECT$: {} timestamp: {}", JSON.toJSONString($OBJECT$), LocalDateTime.now());

        try {

        } catch (Exception e) {
            log.error("失败错误异常", e);
        }
        log.warn("[参数] Main main request: {} timestamp: {}", JSON.toJSONString(specificLogger), LocalDateTime.now());
        System.out.println("success");
    }



}
