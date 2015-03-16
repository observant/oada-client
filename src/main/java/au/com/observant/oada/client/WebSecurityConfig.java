/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * Web security configuration for the application.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    public static final String COOKIE_CSRF = "XSRF-TOKEN";

    /**
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
        auth
            .inMemoryAuthentication()
                .withUser("marissa")
                .password("wombat")
                .roles("USER")
            .and()
                .withUser("sam")
                .password("kangaroo")
                .roles("USER");
        // @formatter:on
    }

    /**
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // @formatter:off
        web
            .ignoring()
            .antMatchers("/index.hml")
            .antMatchers("/login.hml")
        ;
        // @formatter:on
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/oada/**")
                .hasRole("USER")
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .logoutUrl("/logout.do")
                .permitAll()
            .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login.do")
                .failureUrl("/login.html?authentication_error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
            .and()
                // this is needed for CSRF token handling as header value
                // see https://spring.io/blog/2015/01/12/the-login-page-angular-js-and-spring-security-part-ii
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf()
                .csrfTokenRepository(csrfTokenRepository())
                ;
        // @formatter:on
    }

    static class CsrfHeaderFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                FilterChain filterChain) throws ServletException, IOException {
            CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            if (csrf != null) {
                Cookie cookie = WebUtils.getCookie(request, COOKIE_CSRF);
                String token = csrf.getToken();
                if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                    cookie = new Cookie(COOKIE_CSRF, token);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
            filterChain.doFilter(request, response);
        }
    }

    CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName(COOKIE_CSRF);
        return repository;
    }
}
