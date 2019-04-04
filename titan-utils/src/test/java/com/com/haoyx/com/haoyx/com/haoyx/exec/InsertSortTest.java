package com.com.haoyx.com.haoyx.com.haoyx.exec;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 插入排序
 * @Description:
 * @Author: haoyuexun
 * @Date: 2019/3/18 14:53
 *   https://blog.csdn.net/qq_42857603/article/details/81605124
 *
 */
public class InsertSortTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private int arr[] = {578, 200, 110, 3, 2, -1, 55, 2, 7, 9, 4, 89};

    /**
     * 插入法排序原理
     * 利用插入法对无序数组排序时，我们其实是将数组R划分成两个子区间R[1．．i-1]（已排好序的有序区）和R[i．．n]（当前未排序的部分，可称无序区）。
     * 插入排序的基本操作是将当前无序区的第1个记录R[i]插人到有序区R[1．．i-1]中适当的位置上，使R[1．．i]变为新的有序区。
     * 因为这种方法每次使有序区增加1个记录，通常称增量法。
     * 插入排序与打扑克时整理手上的牌非常类似。摸来的第1张牌无须整理，此后每次从桌上的牌(无序区)中摸最上面的1张并插入左手的牌(有序区)
     * 中正确的位置上。为了找到这个正确的位置，须自左向右(或自右向左)将摸来的牌与左手中已有的牌逐一比较。
     *
     */

    public int[] insertSort(int[] arr) {
        if(arr == null || arr.length == 0)
            return arr;
        for(int i=1; i<arr.length; i++) { //假设第一个数位置时正确的；要往后移，必须要假设第一个。
            int j = i;
            int target = arr[i]; //待插入的
            //后移
            while(j > 0 && target < arr[j-1]) {
                arr[j] = arr[j-1];
                j --;
            }
            //插入
            arr[j] = target;
        }
        return arr;

    }


    public int[] insertSort2(int[] arr){
        int temp = 0;
        int j;
        logger.info("交换前数组是:{}", arr);
        /*
         * 第一个for循环
         * 把数组分成两部分，右边为未排序，左边为已排序
         * 记录排序与未排序分割点temp（temp为下一个排序对象）
         */
        for(int i=1; i<arr.length; i++) {
            //只能从当前索引往前循环，因为索引前的数组皆为有序的，索引只要确定当前索引的数据的为止即可
            logger.info("第{}趟排序================", i);
            temp = arr[i];
            /*
             * 第二个for循环
             * 将排序对象temp与已排序数组比较
             * 当temp比最近左边的数大时（按从小到大循序排列时）
             * 直接结束本次循环，进行下一个数排序
             * 否则比左边这个数小时将这个数后移，腾出这个数的位置
             */
            for(j=i-1; j>=0; j--) {

                if (temp > arr[j]) {
                    break;
                }else{
                    arr[j+1] = arr[j];
                }
                logger.info("第{}次排序是：", arr);
            }
            arr[j+1]=temp;
        }
        logger.info("交换后数组是:{}", arr);
        return arr;
    }


    public int[] doInsertSort(int[] arr){
        for(int index = 1; index<arr.length; index++){//外层向右的index，即作为比较对象的数据的index
            int temp = arr[index];//用作比较的数据
            int leftindex = index-1;
            while(leftindex>=0 && arr[leftindex]>temp){//当比到最左边或者遇到比temp小的数据时，结束循环
                arr[leftindex+1] = arr[leftindex];
                leftindex--;
            }
            arr[leftindex+1] = temp;//把temp放到空位上
            logger.info("排序是：{}", arr);
        }
        return arr;
    }



    @Test
    public void test1(){
        this.doInsertSort(arr);
    }


}
