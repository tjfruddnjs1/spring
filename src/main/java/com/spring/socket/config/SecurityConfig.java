package com.spring.socket.config;

import com.spring.socket.service.UserService;
import com.spring.socket.util.SpringSecurity;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @NonNull
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @NonNull
    private final UserService userService;

    @Bean
    public FilterRegistrationBean<Filter> getSpringSecurityFilterChainBindedToError(
        @Qualifier("springSecurityFilterChain") Filter springSecurityFilterChain) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(springSecurityFilterChain);
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationHandler();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationHandler();
    }

    /**
     * ?????? DB??? DataSource??? Query ??? Password Encoder ??????.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * Spring Security?????? ???????????? ????????? ?????? ????????? URL ????????? ????????? ?????????.
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**");
    }

    /**
     * Spring Security??? ?????? ??????????????? ??? URL ?????? ????????? ????????? ?????????.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers()
            .frameOptions(frameOptionsConfig -> frameOptionsConfig.deny())
            .referrerPolicy(referrerPolicyConfig -> referrerPolicyConfig.policy(
                ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
            .httpStrictTransportSecurity(
                hstsConfig -> hstsConfig.includeSubDomains(false).maxAgeInSeconds(31536000)
                    .preload(true))
            .contentSecurityPolicy(cspConfig -> cspConfig.policyDirectives(
                "default-src 'self'; script-src 'self' 'unsafe-inline'; img-src 'self' data:; style-src https://fonts.googleapis.com/ 'self' 'unsafe-inline'; font-src https://fonts.gstatic.com/ 'self';"))
            .and().authorizeRequests()
            .anyRequest().permitAll()
            .and().formLogin().loginPage(SpringSecurity.LOGIN_URL)
            .loginProcessingUrl(SpringSecurity.LOGIN_PROCESS_URL)
            .successHandler(customAuthenticationSuccessHandler())
            .failureHandler(customAuthenticationFailureHandler())
            .usernameParameter(SpringSecurity.PARAM_USERNAME)
            .passwordParameter(SpringSecurity.PARAM_PASSWORD)
            .and().logout()
            .logoutRequestMatcher(new AntPathRequestMatcher(SpringSecurity.LOGOUT_URL))
            .logoutSuccessUrl(SpringSecurity.LOGIN_URL)
            .invalidateHttpSession(true)
            .and().exceptionHandling()
            .accessDeniedPage(SpringSecurity.LOGIN_SUCCESS_URL);
    }
}
