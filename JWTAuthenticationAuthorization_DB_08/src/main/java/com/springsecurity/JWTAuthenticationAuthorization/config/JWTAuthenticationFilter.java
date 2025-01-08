package com.springsecurity.JWTAuthenticationAuthorization.config;

import com.springsecurity.JWTAuthenticationAuthorization.security.JWTUtility;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtility jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);

            try {
                username = this.jwtUtil.extractUsername(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal argument while fetching the username");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Invalid token due to some changes!");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // security logic
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // fetch user details
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                Boolean validateToken = this.jwtUtil.validateToken(jwtToken, userDetails);
                if (validateToken) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
