
package com.disneyapiproject.security.config;

import com.disneyapiproject.security.CustomUserDetailsService;
import com.disneyapiproject.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtAuthenticationFilter jwtAuthenticationFilter;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        super.configure(auth);
        auth.userDetailsService(customUserDetailsService);

    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

       httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/api/**","api/auth/**" ).permitAll()
               .anyRequest().authenticated()
                .and().exceptionHandling()
               .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Autowired
    public void setAttributes(CustomUserDetailsService userDetailsCustomService, @Lazy JwtAuthenticationFilter jwtAuthenticationFilter)
    { this.customUserDetailsService = userDetailsCustomService; this.jwtAuthenticationFilter = jwtAuthenticationFilter; }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();

    }

}
