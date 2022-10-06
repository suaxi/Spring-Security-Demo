package com.software.config;

import com.software.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Wang Hao
 * @date 2022/10/5 18:04
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //permitAll放行swagger相关资源
                .antMatchers("/doc.html", "/webjars/**", "/swagger-resources/**", "/v2/**").permitAll()
                //允许匿名访问的资源路径
                .antMatchers("/user/login").anonymous()
                //需进行认证授权才能访问的资源路径
                .anyRequest().authenticated();

        //添加jwt认证过滤器
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置认证异常处理器
        http.exceptionHandling()
                //认证
                .authenticationEntryPoint(authenticationEntryPoint)
                //授权
                .accessDeniedHandler(accessDeniedHandler);

        //允许跨域
        http.cors();
    }
}
