package com.todorkrastev.krastevsgymrest.service.impl;

import com.todorkrastev.krastevsgymrest.config.JwtConfig;
import com.todorkrastev.krastevsgymrest.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

@Service
public class JwtServiceImpl implements JwtService {
    private final JwtConfig jwtConfig;

    public JwtServiceImpl(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }


    @Override
    public UserDetails extractUserInformation(String jwtToken) {
        Claims claims = extractClaims(jwtToken);

        String userName = getUserName(claims);
        List<String> roles = getRoles(claims);

        return new User(userName, "", roles.stream().map(SimpleGrantedAuthority::new).toList());
    }

    @SuppressWarnings("unchecked")
    private static List<String> getRoles(Claims claims) {
        return claims.get("roles", List.class);
    }

    private static String getUserName(Claims claims) {
        return claims.getSubject();
    }

    private Claims extractClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSingingKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSingingKey() {
        byte[] keyBytes = jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
