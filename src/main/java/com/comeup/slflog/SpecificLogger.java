package com.comeup.slflog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth: qwf
 * @date: 2024年2月2日 0002
 * @description:
 */
public class SpecificLogger {


    public static final Logger LOGGER = LoggerFactory.getLogger(SpecificLogger.class);


    public void testLog() {
        LOGGER.info("SpecificLogger logger");
    }

}
