package com.loafer.lottery.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字典表，用于存储常见配置选项，包括模块信息
 * </p>
 *
 * @author loafer
 * @since 2023-11-04
 */
@Getter
@Setter
@Schema(description = "字典表，用于存储常见配置选项，包括模块信息")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "模块，用于指示配置选项属于哪个模块")
    private String module;

    @Schema(description = "键名，用于唯一标识每个配置选项")
    private String keyName;

    @Schema(description = "值，存储配置选项的具体数值")
    private String value;

    @Schema(description = "描述，提供有关配置选项的详细信息")
    private String description;

    @Schema(description = "创建时间，记录配置选项的添加时间")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间，记录配置选项的最后更新时间")
    private LocalDateTime updatedAt;
}
