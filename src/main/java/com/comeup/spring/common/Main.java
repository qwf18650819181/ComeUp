package com.comeup.spring.common;

import com.comeup.spring.common.condition.ACondition;
import com.comeup.spring.common.transaction.MyTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @auth: qwf
 * @date: 2024年1月22日 0022
 * @description:
 */
@Configuration
//@ComponentScan("com.comeup.spring.common.transaction")
@ComponentScan
//@EnableAsync
@EnableAspectJAutoProxy
@Slf4j
@Conditional(ACondition.class)
public class Main {

//    @Bean
//    public MyFactoryBean myFactoryBean() {
//        return new MyFactoryBean();
//    }

//    @Bean
//    public A a2() {
//        return new A();
//    }



    public static void main(String[] args) {

        String a = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<AmazonEnvelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"amznenvelope.xsd\">\n" +
                "    <Header>\n" +
                "        <DocumentVersion>1.01</DocumentVersion>\n" +
                "        <MerchantIdentifier>AQARDCZ1R5YOA</MerchantIdentifier>\n" +
                "    </Header>\n" +
                "    <MessageType>Price</MessageType>\n" +
                "    <Message>\n" +
                "        <MessageID>1</MessageID>\n" +
                "        <Price>\n" +
                "            <SKU>BW5W8512752-9-L</SKU>\n" +
                "            <StandardPrice currency=\"USD\">46.99</StandardPrice>\n" +
                "        </Price>\n" +
                "    </Message>\n" +
                "</AmazonEnvelope>";

        System.out.println(a.replaceAll("\n", "").replaceAll(" ", ""));

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
        log.info("logger");
//        UserService userService = applicationContext.getBean(UserService.class);
//        userService.test();
        MyTransaction myTransaction = applicationContext.getBean(MyTransaction.class);
        myTransaction.test();



//        A bean = applicationContext.getBean(A.class);
//        System.out.println(bean);
//        System.out.println(bean.getProperty("user.dir"));
//        System.out.println(bean.getProperty("SESSIONNAME"));
    }





}
