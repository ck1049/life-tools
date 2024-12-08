package com.loafer.genshin.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 通用出参
 * @author loafer
 * @since 2024-12-08 15:16:29
 **/
@Setter
@Getter
public class CommonResponse<T> extends BaseResponse {

    @Serial
    private static final long serialVersionUID = 1L;

    private T data;

    public static <T> CommonResponse<T> of(T data) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setSuccess(true);
        commonResponse.setData(data);
        return commonResponse;
    }

    public static <T> CommonResponse<T> fail(Integer errorCode, String errorMessage) {
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.setSuccess(false);
        commonResponse.setResponseCode(errorCode);
        commonResponse.setResponseMessage(errorMessage);
        return commonResponse;
    }

}
