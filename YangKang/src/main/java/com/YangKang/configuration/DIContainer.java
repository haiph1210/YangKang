package com.YangKang.configuration;

import com.YangKang.configuration.security.JwtUtils;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DIContainer {
    @Bean
    public ModelMapper provideModalMaper(){
        return new ModelMapper();
    }
    @Bean
    public JSONObject providerJSON(){
        return new JSONObject();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
