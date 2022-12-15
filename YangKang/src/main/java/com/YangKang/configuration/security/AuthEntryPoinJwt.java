package com.YangKang.configuration.security;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthEntryPoinJwt implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("exception"+ exception);
        int httpResponseStatus = HttpServletResponse.SC_BAD_REQUEST;
        if (exception instanceof UsernameNotFoundException){
            System.out.println(exception.getMessage());
            httpResponseStatus = HttpServletResponse.SC_NOT_FOUND;
        } else if (exception instanceof BadCredentialsException) {
            httpResponseStatus = HttpServletResponse.SC_UNAUTHORIZED;
        } else if (exception instanceof InsufficientAuthenticationException) {
            httpResponseStatus = HttpServletResponse.SC_UNAUTHORIZED;
        }
        response.sendError(httpResponseStatus,exception.getMessage());
    }
}
