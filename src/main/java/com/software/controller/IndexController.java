package com.software.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Hao
 * @date 2022/10/5 12:39
 */
@RestController
@RequestMapping("/test")
public class IndexController {

    @GetMapping
    public String test() {
        return "test";
    }
}
