package com.loafer.lottery.controller;

import com.loafer.lottery.service.ILottoService;
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

@RestController
@RequestMapping("/lotto")
@Tag(name = "大乐透")
@AllArgsConstructor
public class LottoController {

    private ILottoService lottoService;

    @Operation(summary = "保存大乐透开奖信息")
    @GetMapping("/save")
    public ResponseEntity<String> save(
            @Parameter(name = "startIndex", description = "起始位置", required = true, in = ParameterIn.QUERY) @RequestParam(name = "startIndex") Integer startIndex,
            @Parameter(name = "pageSize", description = "每页大小", required = true, in = ParameterIn.QUERY) @RequestParam(name = "pageSize") Integer pageSize) throws InterruptedException {
        return ResponseEntity.ok(lottoService.saveFromOfficialWebsite(startIndex, pageSize) ? "保存成功！" : "保存失败！");
    }
}
