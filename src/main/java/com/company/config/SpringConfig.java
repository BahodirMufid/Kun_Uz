package com.company.config;

// PROJECT NAME -> lesson_22_1
// TIME -> 17:22
// MONTH -> 06
// DAY -> 29

import com.company.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Authentication
        // deMUFID -> dem1234
        // Mufid -> muf4647

//        auth.inMemoryAuthentication()
//                .withUser("mufid@mail.ru").password("{noop}4647").roles("ADMIN")
//                .and()
//                .withUser("qobil@gmail.com").password("{noop}1111").roles("PUBLISHER")
//                .and()
//                .withUser("otabek@mail.ru").password("{noop}1234").roles("MODERATOR");

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorization

        http.authorizeHttpRequests()
                .antMatchers("/article/*").permitAll()
                .antMatchers("/article/adm/*").hasAnyRole("MODERATOR", "ADMIN", "PUBLISHER")
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
//                .and()
//                .formLogin();
                .and().httpBasic();

        http.cors().disable();
        http.csrf().disable();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            // md5 checking matches with database
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String md5 = Md5Util.getMd5(rawPassword.toString());
                return md5.equals(encodedPassword);
            }
        };
    }

}
