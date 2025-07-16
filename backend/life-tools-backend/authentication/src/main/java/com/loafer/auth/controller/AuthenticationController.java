package com.loafer.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 认证控制器
 * @author loafer
 * @since 2025-03-08 17:40:59
 **/
@RequestMapping("/auth")
@Tag(name = "认证")
public class AuthenticationController {

    @Operation(summary = "登录")
    @PostMapping("/login")
    public String login() {
        return "登录成功";
    }

    @Operation(summary = "登出")
    @PostMapping("/logout")
    public String logout() {
        return "登出成功";
    }
}
