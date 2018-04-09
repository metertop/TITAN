package com.com.haoyx.validat;

import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

/**
 * Created by haoyuexun on 2018/4/2.
 */
public class ValidatedTest {

    @Test
    public void addPeople()
    {
        People p1 = new People();
        p1.setId("1");
        p1.setName("ha");
        System.out.println("people's ID:" + p1.getId());


    }
}
