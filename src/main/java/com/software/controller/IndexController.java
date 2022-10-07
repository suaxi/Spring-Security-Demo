package com.software.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Hao
 * @date 2022/10/5 23:15
 */
@RestController
public class IndexController {

    @GetMapping("/test")
    //@PreAuthorize("hasAuthority('system:dept:list')")
    //@PreAuthorize("hasAuthority('system:test')")
    @PreAuthorize("@myAuthorityExpression.hasAuthority('system:test')")
    public String test() {
        return "test";
    }
}
