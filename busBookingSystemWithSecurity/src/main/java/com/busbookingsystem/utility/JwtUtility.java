package com.busbookingsystem.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtility {

    private final String secret="this-is-my-super-secret-key-is-1234567890";
    private final SecretKey secretKey= Keys.hmacShaKeyFor(secret.getBytes());
    private final long exipirationTime=1000*60*60;
    public String generateToken(String username){
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+exipirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }
    public String extractUsername(String token){

        //Claims body= extractClaims(token);
        return extractClaims(token).getSubject();

    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    public boolean validateToken(String username, UserDetails userDetails,String token) {
//       return username.equals(userDetails.getUsername()) && isTokenExpired(token);
//
//    }
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
}
