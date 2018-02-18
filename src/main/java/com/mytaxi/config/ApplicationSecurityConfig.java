package com.mytaxi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Basic Auth configuration class
 *
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private BasicAuthenticationPoint basicAuthenticationPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //Settings for proper functioning of h2-console
        http.csrf().disable();
        http.headers().frameOptions().disable();
        
        http.authorizeRequests().antMatchers("/", "/home", "/h2-console/**").permitAll().anyRequest().authenticated();
        http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception
    {
        authBuilder.inMemoryAuthentication().withUser("mytaxiAdmin").password("admin123").roles("USER");
    }
}
