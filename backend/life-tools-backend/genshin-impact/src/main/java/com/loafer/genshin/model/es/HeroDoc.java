package com.loafer.genshin.model.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 英雄
 * @author loafer
 * @since 2024-12-08 01:13:49
 **/
@Getter
@Setter
@Accessors(chain = true)
@Document(indexName = "hero")
@Setting(shards = 2, replicas = 2)
public class HeroDoc implements Serializable {

    /**
     * 索引ID
     */
    @Id
    @Field(name = "id", type = FieldType.Long)
    private Long id;
    /**
     * 名称
     */
    @Field(name = "name", type = FieldType.Text)
    private String name;
    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @Field(name = "birthday", type = FieldType.Date)
    private LocalDate birthday;
    /**
     * 性别
     */
    @Field(name = "sex", type = FieldType.Keyword)
    private String sex;
    /**
     * 武器类型
     */
    @Field(name = "arms_type", type = FieldType.Keyword)
    private String armsType;
    /**
     * 元素类型
     */
    @Field(name = "element", type = FieldType.Keyword)
    private String element;
}
