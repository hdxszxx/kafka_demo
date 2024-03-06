package com.example.consumer1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @author zxx
 * @version 1.0
 * @date 2022/3/15 11:15
 */
@Component
@Slf4j
//@KafkaListener(topicPartitions = {
//        @TopicPartition(topic = "text.Partitions", partitions = "0-1")
//})
@KafkaListener(topics = "text.Partitions", containerFactory="kafkaListenerContainerFactoryBizSchedule")
public class ConsumerListener {



    @KafkaHandler(isDefault = true)
    public void onMessage1(String message) {
//        System.out.println("我是第一个消费者:" + message);
//        System.out.println("消费成功：" + message);
        log.info("消费成功：{}" , message);
    }
//    @KafkaListener(topics = "test-topic2")
//    public void onMessage2(String message) {
//        System.out.println("我是第二个消费者:" + message);
//    }
}
