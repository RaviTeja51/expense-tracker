package com.personal.expenseapi.service;

import com.personal.expenseapi.exception.UnAuthorizedAccessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private ExpenseUserService userService;

    @Autowired
    public JwtService(ExpenseUserService userService) {
        this.userService = userService;
    }

    private static final String JWT_SALT = "!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+Mb";

    public boolean isValidJwToken(String authHeader) throws UnAuthorizedAccessException {
        if(!authHeader.startsWith("Bearer ")){
            throw new UnAuthorizedAccessException("MISSING BEARER TOKEN",
                    "Authorization token not found", HttpStatus.UNAUTHORIZED.toString());
        }

        String token = authHeader.substring(7);
        String username = extractClaim(token,Claims::getSubject);

        return true;
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails  userDetails){
        return Jwts.builder().setClaims(extraClaims).
                setSubject(userDetails.getUsername()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + (24*60*60*1000) + 10000)).
                signWith(getSigningKey(), SignatureAlgorithm.HS256).
                compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).
                build().
                parseClaimsJws(token).
                getBody();

    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Key getSigningKey(){
        byte[] keyBytes = JWT_SALT.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
