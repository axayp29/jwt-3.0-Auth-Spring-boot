package com.us1.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtProvider {
	
	public static String Secret_Key="kdmklmndjkefkwbfanefwefnshbfkwsdddfe";
	
    private static SecretKey key = Keys.hmacShaKeyFor(Secret_Key.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Authentication auth) {
    
        String jwt = Jwts.builder()
                .setIssuer("Green-Apex")
                .issuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("username", auth.getName())
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getUsernameFromToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        String username = String.valueOf(claims.get("username"));
        return username;
    }
}