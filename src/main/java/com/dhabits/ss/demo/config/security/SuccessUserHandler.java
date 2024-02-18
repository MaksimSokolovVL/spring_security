package com.dhabits.ss.demo.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Map<String, String> redirectMap = new HashMap<>();
        redirectMap.put("ROLE_ADMIN", "/admin");
        redirectMap.put("ROLE_USER", "/user");
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String redirectUrl = roles.stream()
                .filter(redirectMap::containsKey)
                .findFirst()
                .map(redirectMap::get)
                .orElse("/");
        response.sendRedirect(redirectUrl);
    }
}
