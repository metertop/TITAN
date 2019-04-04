package com.com.haoyx.com.haoyx.com.haoyx.exec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: haoyuexun
 * @Date: 2019/3/12 18:31
 */

public class UserTest {
    public static void main(String[] args) {
        System.out.println("加载context文件");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:iocBeans.xml");
        System.out.println("第1个ioc");
        User u1=(User) context.getBean("user");
        u1.setId(123);

        System.out.println("第2个ioc");
        User u2=(User) context.getBean("user");
        u2.setId(456);

        System.out.println("u1的id是："+u1.getId());
        System.out.println("u1的id是："+u2.getId());



    }
}
