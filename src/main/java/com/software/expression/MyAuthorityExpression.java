package com.software.expression;

import com.software.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Wang Hao
 * @date 2022/10/7 11:49
 */
@Component("myAuthorityExpression")
public class MyAuthorityExpression {

    public boolean hasAuthority(String authority) {
        //当前用户权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        if (permissions != null && permissions.size() > 0) {
            return permissions.contains(authority);
        }
        return false;
    }
}
