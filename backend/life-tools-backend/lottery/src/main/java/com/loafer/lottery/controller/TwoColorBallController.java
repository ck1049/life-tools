package com.loafer.lottery.controller;

import com.loafer.lottery.service.ITwoColorBallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/twoColorBall")
@Tag(name = "双色球")
@AllArgsConstructor
public class TwoColorBallController {

    private ITwoColorBallService twoColorBallService;

    @Operation(summary = "保存双色球开奖信息")
    @GetMapping("/save")
    public ResponseEntity<String> save(
            @Parameter(name = "pageNo", description = "页码", required = true, in= ParameterIn.QUERY) @RequestParam(name = "pageNo") Integer pageNo,
            @Parameter(name = "pageSize", description = "每页大小", required = true, in=ParameterIn.QUERY) @RequestParam(name = "pageSize")  Integer pageSize) throws IOException {
        return ResponseEntity.ok(twoColorBallService.saveFromOfficialWebsite(pageNo, pageSize) ? "保存成功！" : "保存失败！");
    }
}
