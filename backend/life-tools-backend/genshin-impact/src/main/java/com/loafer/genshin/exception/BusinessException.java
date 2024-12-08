package com.loafer.genshin.exception;

import com.loafer.common.exception.ErrorCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 * @author loafer
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }

    public BusinessException(Throwable cause, ErrorCode errorCode) {
        super(errorCode.getMsg(), cause);
        this.errorCode = errorCode;
    }

    public BusinessException(Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(errorCode.getMsg(), cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

}
