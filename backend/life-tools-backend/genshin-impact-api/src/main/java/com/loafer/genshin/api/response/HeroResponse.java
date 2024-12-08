package com.loafer.genshin.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author loafer
 * @since 2024-12-08 19:15:16
 **/
@ApiResponse(responseCode = "200", description = "查询成功")
public class HeroResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "id", description = "英雄id", example = "1")
    private String id;

    @Schema(name = "name", description = "姓名", example = "刻晴")
    private String name;

    @Schema(name = "birthday", description = "生日", example = "2000-11-20")
    @NotBlank(message = "生日不能为空")
    private String birthday;

    @Schema(name = "sex", description = "性别", example = "女")
    @NotBlank(message = "性别不能为空")
    private String sex;

    @Schema(name = "armsType", description = "武器类型", example = "单手剑")
    @NotBlank(message = "武器类型不能为空")
    private String armsType;

    @Schema(name = "element", description = "元素类型", example = "雷")
    @NotBlank(message = "元素类型不能为空")
    private String element;
}
