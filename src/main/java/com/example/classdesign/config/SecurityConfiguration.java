package com.example.classdesign.config;

import com.example.classdesign.entity.User;
import com.example.classdesign.mapper.UserMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    UserDetailsService userDetailsService;
    @Resource
    UserMapper userMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/static/**","/page/auth/**","/api/auth/**").permitAll()
                .antMatchers("/api/user/**","/page/user/**").hasRole("user")
                .antMatchers("/api/admin/**","/page/admin/**").hasRole("admin")
                //.antMatchers("/api/vip/**","/page/vip/**").hasRole("vip")
                .anyRequest().hasAnyRole("user","admin","vip")
                .and()
                .formLogin()
                .loginPage("/page/auth/login")
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
//                .rememberMe()
//                .rememberMeParameter("remember")
//                .tokenRepository(repository);
    }

    private void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException {
        HttpSession session = req.getSession();
        User user = userMapper.findPasswordByUsername(authentication.getName());
        session.setAttribute("user", user);
        switch (user.getUrole()) {
            case "admin":
                resp.sendRedirect("/page/admin/index");
                break;
            case "user":
                resp.sendRedirect("/page/user/index");
                break;
//            case "vip":
//                resp.sendRedirect("/page/vip/index");
//                break;
        }
    }
}
