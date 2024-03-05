package com.example.consumer.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@Configuration
@ConfigurationProperties("spring.logs.kafka")
public class KafkaLogsConfigProperties {

    private String bootstrapServers;

    private String groupId;

    private boolean enableAutoCommit;
}


