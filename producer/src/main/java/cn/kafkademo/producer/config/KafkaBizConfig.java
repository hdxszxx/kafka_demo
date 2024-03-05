package cn.kafkademo.producer.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

/**
 * @version 1.0
 * @date 2024/3/4 16:18
 */
@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaBizConfig {

    private final KafkaProperties properties;

    public KafkaBizConfig(KafkaProperties properties) {
        this.properties = properties;
    }

    /**
     * 连接日志记录kafka集群的配置
     */
    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactoryBizSchedule() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryBizSchedule());
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactoryBizSchedule() {
        return new DefaultKafkaConsumerFactory<>(this.properties.buildConsumerProperties());
    }

    @Bean //生产者工厂配置
    public ProducerFactory<Object, Object> producerBizFactory() {
       return new DefaultKafkaProducerFactory<>(this.properties.buildProducerProperties());
    }

    @Bean //kafka发送消息模板
    public KafkaTemplate<?, ?> kafkaTemplate() {
        return new KafkaTemplate<Object, Object>(producerBizFactory());
    }
}
