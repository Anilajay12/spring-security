package com.test.securitydemo.config.jwt;

import com.test.securitydemo.service.AppUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtils {

    @Value("${my.token.secret}")
    private String jwtSecret;
//    private final String jwtSecret = generateSecretKey();


    @Value("${my.token.expiry}")
    private long jwtExpiration;

    public String extractUsernameFromToken(String token) {
        return Jwts
                .parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public String generateToken(AppUserDetails appUserDetails) {
        return Jwts.builder()
                .signWith(key())
                .subject(appUserDetails.getUsername())
                .claims(new HashMap<>())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .compact();
    }

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateSecretKey() {
        // length means (32 bytes are required for 256-bit key)
        int length = 32;

        // Create a secure random generator
        SecureRandom secureRandom = new SecureRandom();

        // Create a byte array to hold the random bytes
        byte[] keyBytes = new byte[length];

        // Generate the random bytes
        secureRandom.nextBytes(keyBytes);

        // Encode the key in Base64 format for easier storage and usage
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public String extractTokenFromHeader(HttpServletRequest request) {
        return (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith("Bearer ")) ? request.getHeader("Authorization").substring(7) : null;
    }

    public boolean validateToken(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload().getExpiration().after(new Date());
    }
}
