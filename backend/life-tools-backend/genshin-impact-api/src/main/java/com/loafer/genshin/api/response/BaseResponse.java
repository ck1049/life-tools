package com.loafer.genshin.api.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * 通用出参
 * @author loafer
 * @since 2024-12-08 15:16:29
 **/
@Setter
@Getter
@ToString
public class BaseResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Boolean success;

    private Integer responseCode;

    private String responseMessage;
}
