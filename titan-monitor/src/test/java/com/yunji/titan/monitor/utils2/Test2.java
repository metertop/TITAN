package com.yunji.titan.monitor.utils2;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.yunji.titan.utils.config.RocketMqDataSource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by haoyuexun on 2018/4/23.
 */
public class Test2 {
    private Logger logger = LoggerFactory.getLogger(Test2.class);
    ApplicationContext context;


    @Test
    public void test1(){
        context = new ClassPathXmlApplicationContext("classpath:root-context.xml");
        RocketMqDataSource rocketMqDataSource = (RocketMqDataSource) context.getBean("rocketMqDataSource");
        producer(rocketMqDataSource);
        consumer1(rocketMqDataSource);
    }

    public void producer(RocketMqDataSource rocketMqDataSource){

        String result = "{\"cpuUsage\":0.88,\"createTime\":1524475582162,\"iops\":2.2,\"ip\":\"172.16.0.173\",\"memoryUsage\":19.31,\"serverType\":0}";
        try {
            for(int i=0; i<1000; i++){
                rocketMqDataSource.getProducer().sendOneway(
                        new Message(rocketMqDataSource.getRocketTopic(), "*", "uploadMonitorKey", result.getBytes()));

            }

        } catch (Exception e) {
            logger.error("error", e);
        }


    }


    public void consumer1(RocketMqDataSource rocketMqDataSource) {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("titan-datacollect-" + rocketMqDataSource.getRocketTopic());
        consumer.setNamesrvAddr("172.16.16.105:9876;172.16.0.124:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        logger.info("----{}", consumer);
        try {
            consumer.subscribe(rocketMqDataSource.getRocketTopic(), "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                logger.info("currentThreadName:{} and Receive New Messages:{}", Thread.currentThread().getName(), msgs);


                msgs.parallelStream().forEach(msg -> {
                    logger.info("TagA----MsgBody:{}", new String(msg.getBody()));

                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

        });


        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }


    }



}
