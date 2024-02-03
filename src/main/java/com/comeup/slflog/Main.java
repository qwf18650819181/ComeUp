package com.comeup.slflog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final Marker DB_MARKER = MarkerFactory.getMarker("JA");


    public static void main(String[] args) {

        MDC.put("discriminator", "STDOUT1");

        LOGGER.info(DB_MARKER, "marker");
        MDC.put("discriminator", "STDOUT2");
        LOGGER.warn("LOGGER");
        LOGGER.info("logger");

        SpecificLogger specificLogger = new SpecificLogger();
        specificLogger.testLog();
    }



}
