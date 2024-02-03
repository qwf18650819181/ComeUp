package com.comeup.liteflow.component;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @auth: qwf
 * @date: 2024年1月25日 0025
 * @description:
 */
@Component("c")
public class ComponentNodeC extends NodeComponent {
    @Override
    public void process() throws Exception {
        System.out.println("ComponentNodeC start");
    }
}
