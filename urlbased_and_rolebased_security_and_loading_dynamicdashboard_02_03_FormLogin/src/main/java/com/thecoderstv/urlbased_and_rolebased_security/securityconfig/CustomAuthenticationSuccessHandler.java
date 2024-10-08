package com.thecoderstv.urlbased_and_rolebased_security.securityconfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetURL = determineTargetURL(authentication);
        if (response.isCommitted()) {
            return;
        }
        response.sendRedirect(targetURL);
    }

    private String determineTargetURL(Authentication authentication) {
        boolean roleAdmin = authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        if (roleAdmin) {
            return "/admin/dashboard";
        } else {
            return "/user/dashboard";
        }
    }
}
