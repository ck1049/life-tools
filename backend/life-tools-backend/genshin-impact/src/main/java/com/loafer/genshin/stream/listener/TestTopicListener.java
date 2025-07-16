package com.loafer.genshin.stream.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author loafer
 * @since 2025-07-16 01:21:45
 **/
@Component
public class TestTopicListener {

    private final String topic = "test";

    @KafkaListener(topics = topic)
    public void onMessage(List<ConsumerRecord<byte[], String>> messageList, Acknowledgment acknowledgment) {
        for (ConsumerRecord<byte[], String> message : messageList) {
            System.out.println(message.value());
        }
        acknowledgment.acknowledge();
    }
}
