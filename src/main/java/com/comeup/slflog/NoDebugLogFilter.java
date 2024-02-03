package com.comeup.slflog;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;

/**
 * @auth: qwf
 * @date: 2024年2月1日 0001
 * @description:
 */
public class NoDebugLogFilter extends TurboFilter {
    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if (Level.DEBUG.equals(level)) {
            return FilterReply.DENY;
        }
        return FilterReply.NEUTRAL;
    }
}
