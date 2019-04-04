package com.haoyx.annotations;

import java.lang.annotation.*;

/**
 * Created by haoyuexun on 2018/2/26.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyLog {

    String value() default "";

}
