package com.software.filter;

import com.alibaba.fastjson.JSONObject;
import com.software.entity.LoginUser;
import com.software.utils.JwtUtil;
import com.software.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wang Hao
 * @date 2022/10/5 22:52
 * @description jwt认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //解析token
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            //请求头中未携带token，执行放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token不合法！");
        }

        //从redis中获取用户信息
        String loginUserStr = new RedisUtil(stringRedisTemplate).get("login:" + userId);
        LoginUser loginUser = JSONObject.parseObject(loginUserStr, LoginUser.class);
        if (loginUser == null) {
            throw new RuntimeException("用户未登录！");
        }

        //存入SecurityContext
        //TODO 权限信息暂未设置
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
