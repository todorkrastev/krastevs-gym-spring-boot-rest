package com.todorkrastev.krastevsgymrest.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BearerTokenGenerator {

    private static final String SECRET_KEY = "d2aUj+XLQy8SEEE/nCw7LW3OpCKuPcycveYiU0tQsFwp1fDxC1GcTx5Vrgi/BBmlFOyuITNrc5Tt3Zj2Jec/Zw==";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public static String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", new String[]{role});

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}