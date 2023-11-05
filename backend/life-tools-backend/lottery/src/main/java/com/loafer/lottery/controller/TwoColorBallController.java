package com.loafer.lottery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.loafer.lottery.service.ITwoColorBallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/twoColorBall")
@Api(value = "双色球")
@AllArgsConstructor
public class TwoColorBallController {

    private ITwoColorBallService twoColorBallService;

    @ApiOperation("保存双色球开奖信息")
    @GetMapping("/save")
    public ResponseEntity<String> save(
            @ApiParam(name = "pageNo", value = "页面", required = true) Integer pageNo,
            @ApiParam(name = "pageSize", value = "每页大小", required = true) Integer pageSize) throws InterruptedException, IOException {
        return ResponseEntity.ok(twoColorBallService.saveFromOfficialWebsite(pageNo, pageSize) ? "保存成功！" : "保存失败！");
    }
}
