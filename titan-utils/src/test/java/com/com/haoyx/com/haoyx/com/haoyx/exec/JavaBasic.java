package com.com.haoyx.com.haoyx.com.haoyx.exec;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Author: haoyuexun
 * @Date: 2019/3/15 11:38
 */
public class JavaBasic {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *　在Java中游8种基本数据类型：
     *
     * 　　浮点型：float(4 byte), double(8 byte)
     * 　　整型：byte(1 byte), short(2 byte), int(4 byte) , long(8 byte)
     * 　　字符型: char(2 byte)
     * 　　布尔型: boolean(JVM规范没有明确规定其所占的空间大小，仅规定其只能够取字面值"true"和"false")
     * 　　对于这8种基本数据类型的变量，变量直接存储的是“值”，因此在用关系操作符==来进行比较时，比较的就是 “值” 本身。要注意浮点型和整型都是有符号类型的，而char是无符号类型的（char类型取值范围为0~2^16-1).
     *
     * 　　也就是说比如：
     * 　　int n=3;
     * 　　int m=3;　
     * 　　变量n和变量m都是直接存储的"3"这个数值，所以用==比较的时候结果是true。
     * 　　而对于非基本数据类型的变量，在一些书籍中称作为 引用类型的变量。比如上面的str1就是引用类型的变量，
     *     引用类型的变量存储的并不是 “值”本身，而是于其关联的对象在内存中的地址。比如下面这行代码：
     *
     *     equals比较的又是什么？
     *
     * 　　equals方法是基类Object中的方法,在Object类中，equals方法是用来比较两个对象的引用是否相等，即是否指向同一个对象。
     *  但是每个类又会进行重写，例如String，String类对equals方法进行了重写，用来比较指向的字符串对象所存储的字符串是否相等。
     *
     * 　　其他的一些类诸如Double，Date，Integer等，都对equals方法进行了重写用来比较指向的对象所存储的内容是否相等。
     *
     * 总结来说：
     *
     * 　　1）对于==，如果作用于基本数据类型的变量，则直接比较其存储的 “值”是否相等；

     * 　　　　如果作用于引用类型的变量，则比较的是所指向的对象的地址
     *
     * 　　2）对于equals方法，注意：equals方法不能作用于基本数据类型的变量
     * 　　　　如果没有对equals方法进行重写，则比较的是引用类型的变量所指向的对象的地址；
     * 　　　　诸如String、Date等类对equals方法进行了重写的话，比较的是所指向的对象的内容。
     */

    @Test
    public void test1(){
        String str1 = new String("hello");
        String str2 = new String("hello");
        logger.info("==比较：{}", str1==str2 );
        logger.info("equals比较：{}", str1.equals(str2));

        String str6 = new String("hi");
        str1 = str6;
        str2 = str6;
        logger.info("==比较：{}", str1==str2 );
        logger.info("equals比较：{}", str1.equals(str2));


        String str3 = "hello";
        String str4 = "hello";
        logger.info("==比较：{}", str3==str4 );
        logger.info("equals比较：{}", str3.equals(str4));
    }

    @Test
    public void test2(){
        Integer str1 = new Integer(1);
        Integer str2 = new Integer(1);
        logger.info("==比较：{}", str1==str2 );
        logger.info("equals比较：{}", str1.equals(str2));

        int a = 2;
        int b = 2;
        logger.info("==比较：{}", a==b );

    }
}

