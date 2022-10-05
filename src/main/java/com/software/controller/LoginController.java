package com.software.controller;

import com.software.entity.Result;
import com.software.entity.User;
import com.software.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Wang Hao
 * @date 2022/10/5 12:39
 */
@RestController
@RequestMapping("/user")
@Api(tags = "认证授权接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        return new Result<>(200, "登录成功", loginService.login(user));
    }
}
