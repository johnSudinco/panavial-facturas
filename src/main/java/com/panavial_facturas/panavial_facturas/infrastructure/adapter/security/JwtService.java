package com.panavial_facturas.panavial_facturas.infrastructure.adapter.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;

@Service
public class JwtService {

    private final String secret = "3f8d9a7c2e6b4f1a9d8c7e5f3a2b1c0d";

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Long extractUserId(String token) {
        return Long.valueOf(parseToken(token).getSubject());
    }
}

