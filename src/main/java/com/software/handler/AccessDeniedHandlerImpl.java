package com.software.handler;

import com.alibaba.fastjson.JSONObject;
import com.software.entity.Result;
import com.software.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wang Hao
 * @date 2022/10/6 21:47
 * @description 未授权异常处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) {
        Result<Object> result = new Result<>(HttpStatus.FORBIDDEN.value(), "未授权");
        WebUtil.renderString(httpServletResponse, JSONObject.toJSONString(result));
    }
}
