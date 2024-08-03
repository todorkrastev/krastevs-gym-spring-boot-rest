package com.todorkrastev.krastevsgymrest.service.impl;

import com.todorkrastev.krastevsgymrest.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceImplTest {

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private JwtServiceImpl jwtService;

    private Key signingKey;

    @BeforeEach
    void setUp() {
        String secret = "mysecretkeymysecretkeymysecretkeymysecretkey";
        when(jwtConfig.getSecret()).thenReturn(secret);
        signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void testExtractUserInformation() {
        String jwtToken = Jwts.builder()
                .setSubject("testuser")
                .claim("roles", List.of("ROLE_USER"))
                .signWith(signingKey)
                .compact();

        UserDetails userDetails = jwtService.extractUserInformation(jwtToken);

        assertEquals("testuser", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    void testExtractUserInformationWithMultipleRoles() {
        String jwtToken = Jwts.builder()
                .setSubject("testuser")
                .claim("roles", List.of("ROLE_USER", "ROLE_ADMIN"))
                .signWith(signingKey)
                .compact();

        UserDetails userDetails = jwtService.extractUserInformation(jwtToken);

        assertEquals("testuser", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}