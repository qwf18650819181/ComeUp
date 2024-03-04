package com.comeup.shiying;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public enum SheinRecommendPlatformEnum {
    SHEIN("Shein", "shein"),
    TEMU("拼多多", "temu");

    private final String platform;
    private final String omsPublishPlatform;

    SheinRecommendPlatformEnum(String platform, String omsPublishPlatform) {
        this.platform = platform;
        this.omsPublishPlatform = omsPublishPlatform;
    }

    public String getPlatform() {
        return platform;
    }

    public String getOmsPublishPlatform() {
        return omsPublishPlatform;
    }

    public static SheinRecommendPlatformEnum of(List<String> platforms) {
        List<String> filterPlatforms = platforms.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        SheinRecommendPlatformEnum platformEnum = Stream.of(SheinRecommendPlatformEnum.values()).filter(sheinRecommendPlatformEnum -> filterPlatforms.contains(sheinRecommendPlatformEnum.getPlatform())).findAny().orElse(null);
        if (platformEnum == null) {
            log.info("[of] SheinRecommendPlatformEnum of : {} timestamp: {}", JSON.toJSONString(12), LocalDateTime.now());
        } else {
            return platformEnum;
        }
        return null;
    }

    public static void main(String[] args) {
        of(new ArrayList<>(Arrays.asList("Shein", "oms")));
    }


}
