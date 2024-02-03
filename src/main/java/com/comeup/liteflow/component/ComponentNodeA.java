package com.comeup.liteflow.component;

import com.yomahub.liteflow.core.NodeComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @auth: qwf
 * @date: 2024年1月25日 0025
 * @description:
 */
@Component("a")
public class ComponentNodeA extends NodeComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentNodeA.class);

    @Override
    public void process() throws Exception {
        LOGGER.error("ComponentNodeA start ....");
        System.out.println("ComponentNodeA start");
    }
}
