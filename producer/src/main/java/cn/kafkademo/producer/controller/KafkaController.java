package cn.kafkademo.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zxx
 * @version 1.0
 * @date 2022/3/15 11:14
 */
@RestController
@RequestMapping("/api/kafka/")
public class KafkaController {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Resource
    private KafkaTemplate<String, Object> kafkaLogTemplate;

    @Autowired
    private List<KafkaTemplate> kafkaTemplates;

    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(50);

    @GetMapping("send")
    @ResponseBody
    public boolean send(@RequestParam Integer num) {
        try {
            System.out.println(kafkaTemplates.size());
            for (int i = 1; i <= num;i++) {
                String a = String.valueOf(i);
                threadPoolExecutor.execute(() -> {
                    kafkaTemplate.send("text.Partitions", 0, a, "0");
                    System.out.println("消息发送成功...");
                });
                Thread.sleep(10);
            }
//            kafkaTemplate.send("test-topic2", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @GetMapping("send1")
    @ResponseBody
    public boolean send1(@RequestParam Integer num) {
        try {
            for (int i = 1; i <= num;i++) {
                String a = String.valueOf(i);
                threadPoolExecutor.execute(() -> {
                    kafkaTemplate.send("text.Partitions", 1, a, "1");
                    System.out.println("消息发送成功...");
                });
                Thread.sleep(10);
            }
//            kafkaTemplate.send("test-topic2", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @GetMapping("send/logs")
    @ResponseBody
    public boolean sendLogs(@RequestParam Integer num) {
        try {
            for (int i = 1; i <= num;i++) {
                String a = String.valueOf(i);
                threadPoolExecutor.execute(() -> {
                    kafkaLogTemplate.send("text.Partitions", 0, a, "Logs-0");
                    System.out.println("消息发送成功...");
                });
                Thread.sleep(10);
            }
//            kafkaTemplate.send("test-topic2", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @GetMapping("send1/logs")
    @ResponseBody
    public boolean send1Logs(@RequestParam Integer num) {
        try {
            for (int i = 1; i <= num;i++) {
                String a = String.valueOf(i);
                threadPoolExecutor.execute(() -> {
                    kafkaLogTemplate.send("text.Partitions", 1, a, "Logs-1");
                    System.out.println("消息发送成功...");
                });
                Thread.sleep(10);
            }
//            kafkaTemplate.send("test-topic2", message);
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
