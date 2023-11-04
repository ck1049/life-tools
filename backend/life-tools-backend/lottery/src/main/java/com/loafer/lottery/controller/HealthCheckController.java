package com.loafer.lottery.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @RequestMapping("/check")
    public String check() {
        return "success";
    }
}
