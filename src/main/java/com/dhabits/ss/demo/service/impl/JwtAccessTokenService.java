package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.service.AccessTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtAccessTokenService implements AccessTokenService {
    private final JwtEncoder jwtEncoder;

    @Override
    public String generateIdToken(Authentication authentication) {
        UserDetails userDetails = Optional.of(authentication.getPrincipal())
                .filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast)
                .orElseThrow(() -> new RuntimeException("userDetails is null"));

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .claim("scope", roles) //роли пользователя например: "USER", "ADMIN"
                .issuedAt(Instant.now()) //время создания JWT
//                .expiresAt(Instant.now().plus(Duration.ofMinutes(15L))) //время жизни JWT
                .expiresAt(Instant.now().plus(15L, ChronoUnit.MINUTES)) //время жизни JWT
                .subject(userDetails.getUsername()) //субъект, который идентифицирует принципала
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
