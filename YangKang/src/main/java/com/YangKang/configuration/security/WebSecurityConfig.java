package com.YangKang.configuration.security;


import com.YangKang.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private AuthEntryPoinJwt authEntryPoinJwt;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(accountService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthTokenFillter createAuthen(){
        return new AuthTokenFillter();
    }
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()

                .antMatchers("api/v1/accounts/**").hasAnyAuthority("ADMIN")
                .antMatchers("api/v1/categories/**").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers("api/v1/products/**").hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("api/v1/emails/**").permitAll()
                .antMatchers(HttpMethod.DELETE).hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST).hasAnyAuthority("ADMIN")
    //                hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers(HttpMethod.PUT).hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers(HttpMethod.GET).permitAll()

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoinJwt)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
        http.addFilterBefore(createAuthen(), UsernamePasswordAuthenticationFilter.class);
    }
}
