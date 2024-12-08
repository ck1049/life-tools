package com.loafer.genshin.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loafer.genshin.api.response.CommonResponse;
import com.loafer.genshin.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.LinkedHashMap;
import java.util.Map;
import static com.loafer.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static com.loafer.common.exception.enums.GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;

/**
 * @author loafer
 * @since 2024-12-08 00:14:38
 **/
@ControllerAdvice
@Slf4j
public class GlobalWebExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 参数校验异常处理器
     * @param exception  参数校验异常
     * @return 提示信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CommonResponse<Void> handleValidationExceptions(MethodArgumentNotValidException exception) throws JsonProcessingException {
        Map<String, String> errors = new LinkedHashMap<>();
        log.error("MethodArgumentNotValidException occurred.", exception);
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return CommonResponse.fail(BAD_REQUEST.getCode(), objectMapper.writeValueAsString(errors));
    }

    /**
     * 自定义业务异常处理器
     * @param businessException 自定义业务异常
     * @return 提示信息
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResponse<Void> exceptionHandler(BusinessException businessException) {
        log.error("businessException occurred.", businessException);
        return CommonResponse.fail(businessException.getErrorCode().getCode(), businessException.getErrorCode().getMsg());
    }

    /**
     * 系统异常处理器
     * @param throwable 系统异常
     * @return 提示信息
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResponse<Void> throwableHandler(Throwable throwable) {
        log.error("throwable occurred.",throwable);
        return CommonResponse.fail(INTERNAL_SERVER_ERROR.getCode(), throwable.getMessage());
    }
}
