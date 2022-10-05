package com.software.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.software.entity.LoginUser;
import com.software.entity.User;
import com.software.service.LoginService;
import com.software.utils.JwtUtil;
import com.software.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wang Hao
 * @date 2022/10/5 21:59
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * jwt缓存key
     */
    private static final String LOGIN_KEY = "login:";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map<String, Object> login(User user) {
        //认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate == null) {
            throw new RuntimeException("登录失败！");
        }

        //根据userId生成jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(String.valueOf(id));
        Map<String, Object> result = new HashMap<>(1);
        result.put("token", jwt);

        //redis缓存
        new RedisUtil(stringRedisTemplate).set(LOGIN_KEY + id, JSONObject.toJSONString(loginUser));

        return result;
    }
}
