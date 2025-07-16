package com.loafer.genshin.listener;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
/**
 * @author loafer
 * @since 2025-07-16 01:15:43
 **/
@SpringBootTest
public class KafkaTest {

    @Resource
    private KafkaTemplate<byte[], String> kafkaTemplate;

    @Test
    public void testSender() {
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("test", "Spring kafka test message " +  i + ".");
        }
    }

}
