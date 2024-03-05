package cn.kafkademo.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @date 2024/3/4 16:18
 */
@Configuration
public class KafkaLogConfig {

    @Resource
    KafkaLogsConfigProperties kafkaLogsConfigProperties;

    /**
     * 连接日志记录kafka集群的配置
     */
    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactoryLogSchedule() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryLogSchedule());
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactoryLogSchedule() {
        return new DefaultKafkaConsumerFactory<>(kafkaLogsConfigProperties.buildConsumerProperties());
    }

    @Bean //生产者工厂配置
    public ProducerFactory<Object, Object> producerLogFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaLogsConfigProperties.buildProducerProperties());
    }

    @Bean //kafka发送消息模板
    public KafkaTemplate<?, ?> kafkaLogTemplate() {
        return new KafkaTemplate<Object, Object>(producerLogFactory());
    }

}
