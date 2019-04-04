package com.com.haoyx.com.haoyx.com.haoyx.exec;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @Author: haoyuexun
 * @Date: 2019/3/14 15:21
 */
public class BubbleSortTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private int arr[] = {578, 200, 110, 3, 2, -1, 55, 2, 7, 9, 4, 89};

    /**
     * 从前面开始比较--冒泡排序--把最大的值放到最后
     * 冒泡排序算法的运作如下:
     *
     *  比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     *  对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     *  针对所有的元素重复以上的步骤，除了最后一个。
     *  持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     *  时间复杂度O(n^2)  空间负责度O(1)
     */
    public int[] bubbleSort(int arr[]){
        logger.info("bubble sort前的数组是:{}", arr );
        int count = 0; //执行次数
        int len = arr.length;
        for(int i=0; i<len-1; i++){  //最多做n-1趟排序
            logger.info("第{}趟排序================", i+1);
            for(int j=0; j<len-i-1; j++){
                if(arr[j+1]<arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    count++;
                }
                logger.info("第{}次交换数组是:{}", j+1, arr);
            }
        }
        logger.info("数组是:{}\n,共交换次数：{}", arr, count);
        return arr;
    }

    /**
     *从前面开始比较--冒泡排序--优化
     * 冒泡排序的性能分析和算法优化（外层循环优化）
     * 问题：
     * 有的冒泡经过第一轮的交换已经是有序的了，如：2 1 3 4。数据越多的时候越慢，非常不适合大数据的排序
     *
     * 解决办法
     * 如果用一个flag来判断一下，当前数组是否已经有序，如果有序就退出循环，这样可以明显的提高冒泡排序的性能
     */
    public int[] bubbleSortBetter(int arr[]){
        logger.info("bubble sort前的数组是:{}", arr );
        boolean flag ;
        int count = 0; //执行次数
        int len = arr.length;
        for(int i=0; i<len-1; i++){  //最多做n-1趟排序
            logger.info("第{}趟排序================", i+1);
            flag = false;
            for(int j=0; j<len-i-1; j++){
                if(arr[j+1]<arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    count++;
                    flag = true;
                }

                logger.info("第{}次交换数组是:{}", j+1, arr);
            }
            if(!flag){
                //没有发生交换则退出循环；
                break;
            }
        }
        logger.info("数组是:{}\n,共交换次数：{}", arr, count);
        return arr;
    }

    /**
     * 从后面开始比较  -- 把最小的数放到最前
     */
    public int[] bubbleSort2(int arr[]){
        logger.info("bubble sort前的数组是:{}", arr );
        int len = arr.length;
        for(int i=0; i<len-1; i++){
            logger.info("第{}趟排序================", i+1);
            for(int j=len-1; j>i; j--){
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                logger.info("第{}次交换数组是:{}", len-j, arr);
            }

        }
        logger.info("数组是:{}", arr);
        return arr;
    }

    @Test
    public void test1(){
        this.bubbleSortBetter(arr);
    }

    @Test
    public void test2(){
        this.bubbleSort2(arr);
    }
}
