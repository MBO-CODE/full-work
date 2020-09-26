package com.sda.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

///////////////////////////////////////////////Authorities base user////////////////////////////////////////////////////
        authenticationManagerBuilder.inMemoryAuthentication()
                    .withUser("admin")
                    .password(passwordEncoder().encode("pass1"))
                    .roles("ADMIN","USER")
                .and()
                    .withUser("user")
                    .password(passwordEncoder().encode("pass"))
                    .roles("USER");

////////////////////////////////////////////////Authorities base user///////////////////////////////////////////////////
//                authenticationManagerBuilder.inMemoryAuthentication()
//                    .withUser("admin")
//                    .password(passwordEncoder().encode("pass1"))
//                    .authorities("READ","WRITE")
//                .and()
//                    .withUser("user")
//                    .password(passwordEncoder().encode("pass"))
//                    .authorities("READ");



    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //** -> si tot ce urmeaza,indiferent ca e getAll, populate, findById
        httpSecurity.authorizeRequests()
//////////////////////////////////////////////////////Role based matchers///////////////////////////////////////////////
//                    .antMatchers("/api/v1/companies/**").hasRole("ADMIN")
//                    .anyRequest().authenticated()

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                .anyRequest()
                .permitAll() //toata lumea are acces aici (USER,ADMIN, ETC)

///////////////////////////////////////////Authorities base matchers////////////////////////////////////////////////////
//                .antMatchers("/api/v1/companies/create").hasAuthority("WRITE")
//                .anyRequest().authenticated()
                .and()
                    .httpBasic();

        httpSecurity.csrf().disable();
    }

    /*
    asta face instante de cryptPassword
    vezi mai sus in metoda password
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

