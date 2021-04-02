package uz.pdp.task2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth

                // ------- BY PERMISSION BASED AUTHENTICATION

//                .inMemoryAuthentication()
//                .withUser("super_admin").password(passwordEncoder().encode("123")).roles("SUPER_ADMIN").authorities("READ", "READ_BY_ID", "DELETE,", "ADD_PRODUCT", "EDIT_PRODUCT")
//                .and()
//                .withUser("moderator").password(passwordEncoder().encode("1234")).roles("MODERATOR").authorities("ADD_PRODUCT", "EDIT_PRODUCT")
//                .and()
//                .withUser("operator").password(passwordEncoder().encode("12345")).roles("OPERATOR").authorities("READ");
//

                // ------- BY ROLE BASED AUTHENTICATION

                .inMemoryAuthentication()
                .withUser("super_admin").password(passwordEncoder().encode("123")).roles("SUPER_ADMIN")
                .and()
                .withUser("moderator").password(passwordEncoder().encode("1234")).roles("MODERATOR")
                .and()
                .withUser("operator").password(passwordEncoder().encode("12345")).roles("OPERATOR");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                // ------- BY PERMISSION BASED AUTHENTICATION

//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/product/**").hasAuthority("READ")
//                .antMatchers(HttpMethod.POST, "/api/product/**").hasAuthority("ADD_PRODUCT")
//                .antMatchers(HttpMethod.PUT, "/api/product/**").hasAuthority("EDIT_PRODUCT")
//                .antMatchers("/api/**").hasAnyAuthority("READ", "READ_BY_ID", "DELETE,", "ADD_PRODUCT", "EDIT_PRODUCT")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();

                // ------- BY ROLE BASED AUTHENTICATION

                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/product/**").hasAnyRole("OPERATOR", "SUPER_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/product/**").hasAnyRole("MODERATOR", "SUPER_ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/product/**").hasAnyRole("MODERATOR", "SUPER_ADMIN")
                .antMatchers("/api/**").hasRole("SUPER_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

