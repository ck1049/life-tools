package com.loafer.genshin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.loafer.genshin.api.request.HeroSaveRequest;
import com.loafer.genshin.api.response.CommonResponse;
import com.loafer.genshin.properties.NacosConfigProperties;
import com.loafer.genshin.service.domain.HeroDomainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

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
    @Resource
    private HeroDomainService heroDomainService;

    @Operation(summary = "健康检查")
    @GetMapping("/health")
    public String health(){
        return nacosConfigProperties.getServerName();
    }

    @Operation(summary = "保存英雄信息")
    @PostMapping("/save")
    public CommonResponse<String> save(@Valid @RequestBody HeroSaveRequest heroSaveRequest) throws JsonProcessingException {
        return heroDomainService.save(heroSaveRequest);
    }

    @Operation(summary = "根据姓名查询分页信息")
    @GetMapping("/searchAfterByName")
    public CommonResponse<List<SearchHit<HashMap>>> searchAfterByName(
            @Parameter(name = "name", description = "姓名", example = "芙宁娜")
            @RequestParam(value = "name", required = false) String name,
            @Parameter(name = "lastId", description = "上次查询结束id", example = "1")
            @RequestParam(value = "lastId", required = false, defaultValue = "0") Long lastId) {
        return heroDomainService.searchAfterByName(name, lastId);
    }

    @Operation(summary = "根据姓名查询分页信息(使用script)")
    @GetMapping("/searchAfterByNameUseScript")
    public CommonResponse<List<SearchHit<HashMap>>> searchAfterByNameUseScript(
            @Parameter(name = "name", description = "姓名", example = "芙宁娜")
            @RequestParam(value = "name", required = false) String name,
            @Parameter(name = "lastId", description = "上次查询结束id", example = "1")
            @RequestParam(value = "lastId", required = false, defaultValue = "0") Long lastId) {
        return heroDomainService.searchAfterByNameUseScript(name, lastId);
    }
}
