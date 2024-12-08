package com.loafer.genshin.controller;

import com.loafer.genshin.api.request.HeroSaveRequest;
import com.loafer.genshin.properties.NacosConfigProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author loafer
 * @since 2024-12-08 14:00:18
 **/
@Tag(name = "英雄")
@RestController
@RequestMapping("/hero")
public class HeroController {

    @Resource(name = "genshinNacosConfigProperties")
    private NacosConfigProperties nacosConfigProperties;

    @Operation(summary = "健康检查")
    @GetMapping("/health")
    public String health(){
        return nacosConfigProperties.getServerName();
    }

    @Operation(summary = "保存英雄信息")
    @PostMapping("/save")
    public String save(@Valid @RequestBody HeroSaveRequest heroSaveRequest){
        return "success";
    }
}
