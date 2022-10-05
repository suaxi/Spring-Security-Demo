package com.software.service;

import com.software.entity.User;

import java.util.Map;

/**
 * @author Wang Hao
 * @date 2022/10/5 21:59
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    Map<String, Object> login(User user);
}
