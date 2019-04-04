package com.com.haoyx.com.haoyx.com.haoyx.exec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: haoyuexun
 * @Date: 2019/3/12 17:16
 */
@Component
public class User {
    private String name;
    private int id;
    public void fun() {
        System.out.println("dao");
    }

    public User() {
        System.out.println("初始化User实例");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        System.out.println("name is"+ name);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
        System.out.println("id is "+id);
    }




}
