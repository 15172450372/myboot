package com.example.myboot.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: zhouwei
 * @Description: 配置security
 * @Date: 2020/6/17 下午5:09
 * @Version: 1.0
 **/
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailHandler customAuthenticationFailHandler;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    /**
     * 定制认证规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //将自定的CustomUserDetailsService装配到AuthenticationManagerBuilder
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new CustomPasswordEncoder());
    }

    /**
     * 定制授权规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/security/hello","/login").permitAll()
                .antMatchers("/security/admin").hasRole("admin")
                .antMatchers("/security/getUser").hasAuthority("select")
                .anyRequest().authenticated()
                .and()
        //登录配置
        .formLogin()
                .loginPage("/security/login_page")  //登录页，当未登录时会重定向到该页面
                .usernameParameter("username")  //默认的用户名参数
                .passwordParameter("password")  //默认的密码参数
                .loginProcessingUrl("/login")  //前端登录请求地址
                .successHandler(customAuthenticationSuccessHandler)  //登录成功处理
                .failureHandler(customAuthenticationFailHandler)  //登录失败处理
                .permitAll()
                .and()
        //注销配置
        .logout().invalidateHttpSession(true)  //销毁session
                .logoutSuccessHandler(customLogoutSuccessHandler) //退出处理
                .and()
                .exceptionHandling()
                //.authenticationEntryPoint()  //认证异常处理
                .accessDeniedHandler(customAccessDeniedHandler)  //授权异常处理

                .and()
        //跨域
         .cors().and()
        //记住我
        .rememberMe()
                .rememberMeParameter("rememberMe")
                .and()
        .csrf().disable();
    }

}
