package com.company.config;

// PROJECT NAME -> lesson_22_1
// TIME -> 17:22
// MONTH -> 06
// DAY -> 29

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Authentication
        // deMUFID -> dem1234
        // Mufid -> muf4647

        auth.inMemoryAuthentication()
                .withUser("deMUFID").password("{bcrypt}$2a$10$M86UkzconJlQ22whUSu1kOsiiJVRvIlRjdL3.cIIPfL2hmVYlUei2").roles("ADMIN")
                .and()
                .withUser("Mufid").password("{bcrypt}$2a$10$vXDAHRowkNpBaGO8Vacw3.h6rz3T2Ky0DkcVpKIWf.Jotcz4yLak2").roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    // Authorization

        http.authorizeHttpRequests()
                .antMatchers("/article/*").permitAll()
                .antMatchers("/article/adm/*").hasAnyRole("MODERATOR","ADMIN","PUBLISHER")
                .antMatchers("/profile/adm/*").hasRole("ADMIN")
                .antMatchers("/profile/*").hasRole("USER")
                .antMatchers("/article_like/*").hasRole("USER")
                .antMatchers("/types/public/*").permitAll()
                .antMatchers("/types/adm/*").hasRole("ADMIN")
                .antMatchers("/types/adm/*").hasRole("ADMIN")
                .antMatchers("/attach/*").permitAll()
                .antMatchers("/attach/adm/*").hasRole("ADMIN")
                .antMatchers("/auth/*").hasRole("USER")
                .antMatchers("/category/public/*").hasRole("USER")
                .antMatchers("/category/adm/*").hasRole("ADMIN")
                .antMatchers("/comment/*").hasRole("USER")
                .antMatchers("/comment_like/*").hasRole("USER")
                .antMatchers("/region/public/*").hasRole("USER")
                .antMatchers("/region/adm/*").hasRole("ADMIN")
                .antMatchers("/article_save/*").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin();
//                .and().httpBasic();

    }
}
