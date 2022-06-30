package com.company.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// PROJECT NAME Kun_Uz
// TIME 17:36
// MONTH 06
// DAY 15
@Configuration
public class SecuredFilterConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JwtFilter> filterRegistrationBeanRegion() {

        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<JwtFilter>();
        bean.setFilter(jwtFilter);

        bean.addUrlPatterns("/article/adm/*");
        bean.addUrlPatterns("/article_like/*");
        bean.addUrlPatterns("/types/adm/*");
        bean.addUrlPatterns("/attach/adm/*");
        bean.addUrlPatterns("/category/adm/*");
        bean.addUrlPatterns("/profile/adm/*");
        bean.addUrlPatterns("/comment_like/*");
        bean.addUrlPatterns("/region/adm/*");
        bean.addUrlPatterns("/article_saved/*");

        return bean;
    }

}
