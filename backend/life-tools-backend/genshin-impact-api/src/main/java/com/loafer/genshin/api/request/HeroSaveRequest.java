package com.loafer.genshin.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author loafer
 * @since 2024-12-08 15:16:29
 **/
@Getter
@Setter
@Schema(description = "英雄保存请求参数")
public class HeroSaveRequest implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "name", description = "英雄名称", example = "刻晴", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "英雄名称不能为空")
    private String name;

    @Schema(name = "birthday", description = "生日", example = "2000-11-20", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "生日不能为空")
    private String birthday;

    @Schema(name = "sex", description = "性别", example = "女", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "性别不能为空")
    private String sex;

    @Schema(name = "armsType", description = "武器类型", example = "单手剑", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "武器类型不能为空")
    private String armsType;

    @Schema(name = "element", description = "元素类型", example = "雷", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "元素类型不能为空")
    private String element;
}
