package com.loafer.mq.producer;

import com.alibaba.fastjson.JSON;
import com.loafer.mq.messaging.Message;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;

/**
 * @author loafer
 * @since 2025-01-19 16:14:34
 **/
@Slf4j
public class StreamProducer {

    private StreamBridge streamBridge;

    @Resource
    public void setStreamBridge(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    /**
     * 发送消息
     * @param bingingName bingingName
     * @param tag tag
     * @param msg msg
     * @return 发送结果
     */
    public boolean send(String bingingName, String tag, String msg) {
        return send(bingingName, tag, msg, Map.of());
    }

    /**
     * 发送消息
     * @param bingingName bingingName
     * @param tag tag
     * @param msg msg
     * @param headers headers
     * @return 发送结果
     */
    public boolean send(String bingingName, String tag, String msg, Map<String, Object> headers) {
        Message message = new Message(msg);
        log.info("send message : {} , {} , {}", bingingName, tag, JSON.toJSONString(message));
        MessageBuilder<Message> messageMessageBuilder = MessageBuilder.withPayload(message);
        headers.forEach(messageMessageBuilder::setHeader);
        boolean result = streamBridge.send(bingingName, messageMessageBuilder.setHeader("TAGS", tag).build());
        log.info("send result : {} , {} , {}", bingingName, tag, result);
        return result;
    }
}
