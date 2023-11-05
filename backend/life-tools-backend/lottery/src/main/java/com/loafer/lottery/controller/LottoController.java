package com.loafer.lottery.controller;

import com.loafer.lottery.service.ILottoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotto")
@Api(value = "大乐透")
@AllArgsConstructor
public class LottoController {

    private ILottoService lottoService;

    @ApiOperation("保存大乐透开奖信息")
    @GetMapping("/save")
    public ResponseEntity<String> save(
            @ApiParam(name = "startIndex", value = "起始位置", required = true) Integer startIndex,
            @ApiParam(name = "pageSize", value = "每页大小", required = true) Integer pageSize) throws InterruptedException {
        return ResponseEntity.ok(lottoService.saveFromOfficialWebsite(startIndex, pageSize) ? "保存成功！" : "保存失败！");
    }
}
