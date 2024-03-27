package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.model.web.AccessToken;
import com.dhabits.ss.demo.domain.model.web.LoginRq;
import com.dhabits.ss.demo.service.AccessTokenService;
import com.dhabits.ss.demo.service.AuthJwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthJwtService implements AuthJwt {
    private final AuthenticationManager authenticationManager;

    private final AccessTokenService accessTokenService;

    @Override
    public AccessToken authenticate(LoginRq loginRq) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRq.username(),
                loginRq.password()
        );
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String idToken = accessTokenService.generateIdToken(authenticate);
        log.error("### ->  ->  -> {}", idToken);
        return new AccessToken(idToken);
    }
}
