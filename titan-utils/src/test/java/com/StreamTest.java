package com;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by haoyuexun on 2018/3/22.
 */
public class StreamTest {

    @Test
    public void test1(){
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());

        outputStream.forEach(System.out::println);
    }

    // parallelStream其实就是一个并行执行的流.它通过默认的ForkJoinPool,可能提高你的多线程任务的速度.
    // 你得到的展示顺序不一定
    @Test
    public void test2(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        numbers.parallelStream().forEach(System.out::println);
    }

    @Test
    public void test3(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        numbers.stream().forEach(System.out::println);
    }

    @Test
    public void test4(){
        double num = Math.ceil(12.345667);
        System.out.println(num);
    }
}
