package com.beam.mail.Jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
@Service
public class JwtService {
    private static final String SECRET_KEY = "-Secret-";
    public String generateToken(String id) {
        String token = Jwts.builder()
                .setSubject(id)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
        return token;
    }
}
