package com.codeup.springtest;

import com.codeup.springtest.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@ComponentScan("com.codeup.springtest")
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Login Configuration
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/posts") // user's homepage, it can be any URL
                .permitAll() //anyone can go to login page

                // Logout Configuration
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value

                // Pages that can be viewed without having to login
                .and()
                .authorizeRequests()
                .antMatchers( "/", "/posts", "posts/show") // anyone can see the home and the ads pages
                .permitAll()

                // Pages that require authentication
                .and()
                .authorizeRequests()
                .antMatchers("/posts/create", "/posts/delete","/posts/edit")
                .authenticated();
    }

}
