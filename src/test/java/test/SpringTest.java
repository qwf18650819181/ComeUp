package test;

import com.comeup.spring.Main;
import com.comeup.spring.aspect.AComponent;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.inject.Inject;

/**
 * @auth: qwf
 * @date: 2024年1月28日 0028
 * @description:
 */
@SpringJUnitConfig(Main.class)
public class SpringTest {

    @Inject
    ApplicationContext applicationContext;

    @Test
    public void printA() {

        AComponent a = (AComponent) applicationContext.getBean("AComponent");
        a.test();

    }

}
