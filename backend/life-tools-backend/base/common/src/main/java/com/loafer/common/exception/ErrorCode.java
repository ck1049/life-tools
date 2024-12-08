package com.loafer.common.exception;

import lombok.Data;
import com.loafer.common.exception.enums.GlobalErrorCodeConstants;

/**
 * 错误码对象
 * 全局错误码，占用 [0, 999], 参见 {@link GlobalErrorCodeConstants}
 * @author loafer
 */
@Data
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
