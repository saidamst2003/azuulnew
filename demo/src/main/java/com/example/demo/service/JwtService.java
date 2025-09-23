package com.example.demo.service;

import com.example.demo.dto.AuthUserDTO;
import com.example.demo.model.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret:MySuperSecureJWTKeyWith32+Characters!!}")
    private String SECRET_KEY;

    private final long JWT_EXPIRATION_MS = 1000L * 60 * 60 * 24;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(AuthUserDTO user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.id());
        claims.put("name", user.fullName());
        claims.put("role", user.role().name());

        Date now = new Date();
        Date expiry = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.email())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserPrincipal userPrincipal) {
        final String username = extractUsername(token);
        return (username.equals(userPrincipal.getUsername()) && !isTokenExpired(token));
    }
}
