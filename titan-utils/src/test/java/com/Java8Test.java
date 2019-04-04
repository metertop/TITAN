package com;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by haoyuexun on 2018/3/21.
 */
public class Java8Test {


    public static void main(String[] arg) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(1, "red", "红富士"));
        apples.add(new Apple(2, "red", "红富士"));

        apples.add(new Apple(3, "red", "山东苹果"));
        apples.add(new Apple(4, "red", "山东苹果"));

        apples.add(new Apple(5, "green", "红富士"));
        apples.add(new Apple(6, "green", "红富士"));

        apples.add(new Apple(7, "green", "山东苹果"));
        apples.add(new Apple(8, "green", "山东苹果"));

        //filter 筛选符合条件的数据并返回一个新的流

        //collect将流中的数据收集
        List<Apple> reds=apples.stream().filter(apple -> apple.getColor().equals("red")).collect(Collectors.toList());
        System.out.println(reds);

        //映射,将流中的每个元素变成映射成另一个元素，注意，所有流操作都是在一个新的流中处理，原始流中的数据都不会改变
        List<String> colors=apples.stream().map(apple -> apple.getColor()).distinct().collect(Collectors.toList());//从apple中提取颜色
        System.out.println(colors);
/*
        //distinct 去重(根据对象的equals和hashcode方法比较两个对象是否是同一个对象)
        List<Apple> distincts=apples.stream().distinct().collect(Collectors.toList());
        System.out.println("distincts" + distincts);


        //限制结果集的大小
        List<Apple> limits=apples.stream().limit(1).collect(Collectors.toList());
        System.out.println(limits);

        //跳过元素个数,结合limit可以实现类似数据库的分页
        List<Apple> skips=apples.stream().skip(6).collect(Collectors.toList());
        System.out.println(skips);

        //映射,将流中的每个元素变成映射成另一个元素，注意，所有流操作都是在一个新的流中处理，原始流中的数据都不会改变
        List<String> colors=apples.stream().map(apple -> apple.getColor()).collect(Collectors.toList());//从apple中提取颜色
        System.out.println(colors);


        //anyMatch是否存在满足条件的苹果
        System.out.println(apples.stream().anyMatch(apple -> apple.getWeight()==2));

        //allMatch所有元素是否都满足条件
        System.out.println(apples.stream().allMatch(apple -> apple.getWeight()==2));

        //noneMatch是否所有元素都不满足条件
        System.out.println(apples.stream().noneMatch(apple -> apple.getWeight()==10));

        //findAny从流中查找任意一个元素，通常结合filter一起用
        System.out.println(apples.stream().findAny().get());

        //findFirst从流中查找第一个元素，通常结合filter一起用
        System.out.println(apples.stream().filter(apple -> apple.getWeight()>6).findFirst().get());

        //count统计流中的元素个数
        System.out.println("count:"+apples.stream().count());

        //reduce 将流中所有元素反复结合起来，得到一个值。可以用累加来帮助理解reduce操作

        //累加
        int sum=0;
        List<Integer> numbers= Arrays.asList(1,2,3,4,5,6,7);
        for(int i : numbers){
            sum+=i;
        }
        System.out.println("sum:"+sum);

        //用reduce实现累加；结合上面循环累加的理解就是第一个参数是初始值sum，后面的a,b就是sum与i
        System.out.println("reduce sum:"+numbers.stream().reduce(0,(a,b)->a+b));

        //用reduce查找最大值
        System.out.println("max:"+numbers.stream().reduce(0,(a,b)-> a>=b ? a : b));

        //Interge中提供了静态的sum和max方法，代码可以进一步简化
        System.out.println("reduce sum:"+numbers.stream().reduce(0,Integer::sum));
*/

     /*
      基本类型数组流.上面代码计算总和的方式包含了一个自动的从int转成Integer的操作，Java 8引入了三个原始类型特化流接口来解决这个问题：IntStream、 DoubleStream 和

        LongStream，分别将流中的元素特化为 int 、 long 和 double ，从而避免了暗含的装箱成本。
        将流转换为特化版本的常用方法是 mapToInt 、 mapToDouble 和 mapToLong 。这些方法和前
        面说的 map 方法的工作方式一样，只是它们返回的是一个特定类型的流，而不是 Stream<T>,此外它们多了一些直接的运算方法，如sum()

        */


        System.out.println("mapToInt sum:"+apples.stream().mapToInt(Apple::getWeight).sum());

        //所有返回流的方法都可以随意组合使用
        //计算weight>1并且颜色是red的所有苹果的总重量
        System.out.println("weight:"+apples.stream().filter(apple -> apple.getWeight()>1).filter(apple -> apple.getColor().equals("red")).
                map(apple -> apple.getWeight()).reduce(0,Integer::sum));

        System.out.println(apples.stream().filter(apple -> apple.getColor().equals("green")).map(apple -> apple.getWeight()).reduce(0,Integer::sum));


        int[] intArray = {12,3,34,67,100,99};
//        * 第一种构造intStream *
        IntStream intStream = IntStream.of(intArray);
//        * 第二种构造intStream *
//        IntStream intStream2 = IntStream.of(12,3,34,67,100,99);
//        * 这个是重点，获得当前int数组的统计信息，包括 *
        IntSummaryStatistics statistics = intStream.summaryStatistics();
//        * 计算出最大，最小，平均等等，是不是很好用，赶紧get起来 *
        System.out.println("the max:" + statistics.getMax());
        System.out.println("the min:" + statistics.getMin());
        System.out.println("the average:" + statistics.getAverage());
        System.out.println("the sum:" + statistics.getSum());
        System.out.println("the count:" + statistics.getCount());



    }






}
