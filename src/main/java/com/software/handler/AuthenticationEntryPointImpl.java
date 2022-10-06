package com.software.handler;

import com.alibaba.fastjson.JSONObject;
import com.software.entity.Result;
import com.software.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wang Hao
 * @date 2022/10/6 21:41
 * @description 认证异常处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result<Object> result = new Result<>(HttpStatus.UNAUTHORIZED.value(), "未认证，请重新登录");
        WebUtil.renderString(httpServletResponse, JSONObject.toJSONString(result));
    }
}
