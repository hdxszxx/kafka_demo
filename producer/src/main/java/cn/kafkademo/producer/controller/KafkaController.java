package cn.kafkademo.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author zxx
 * @version 1.0
 * @date 2022/3/15 11:14
 */
@RestController
@RequestMapping("/api/kafka/")
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("send")
    @ResponseBody
    public boolean send(@RequestParam String message) {
        try {
            kafkaTemplate.send("test-topic", message);
            kafkaTemplate.send("test-topic2", message);
            System.out.println("消息发送成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    @GetMapping("test")
    @ResponseBody
    public String test() {
        System.out.println("hello world!");
        return "ok";
    }
}
