package com.loafer.mq.messaging;

import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

/**
 * mq 消息
 * @author loafer
 * @since 2025-01-19 15:59:23
 **/
@Getter
public class Message implements Serializable {

    /**
     * 消息id
     */
    private final String msgId;
    /**
     * 消息体
     */
    private final String body;

    public Message(String body) {
        this.msgId = UUID.randomUUID().toString();
        this.body = body;
    }

    public Message(String msgId, String body) {
        this.msgId = msgId;
        this.body = body;
    }

}
