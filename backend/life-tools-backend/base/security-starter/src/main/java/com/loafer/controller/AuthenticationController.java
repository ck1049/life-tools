package com.loafer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 认证控制器，如果继承模块未提供，则使用默认的认证控制器，用于快速集成一个登录模块
 * @author loafer
 * @since 2025-03-08 17:40:59
 **/
@ConditionalOnProperty(prefix = "security.authentication", name = "custom", havingValue = "disable", matchIfMissing = true)
@RestController
@RequestMapping
@Tag(name = "认证")
public class AuthenticationController {

    @Operation(summary = "登录")
    @PostMapping("/login")
    public String login(
            @Parameter(name = "username", description = "用户名", example = "张三")
            @RequestParam(name = "username") String username,
            @Parameter(name = "password", description = "密码(RSA密文)", example = "linoikwenfwlkafn")
            @RequestParam(name = "password") String password) {
        return "登录成功";
    }

    @Operation(summary = "登出")
    @PostMapping("/logout")
    public String logout() {
        return "登出成功";
    }
}
