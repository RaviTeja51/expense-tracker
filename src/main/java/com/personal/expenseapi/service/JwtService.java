package com.personal.expenseapi.service;

import com.personal.expenseapi.exception.UnAuthorizedAccessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private ExpenseUserService userService;
    private UserDetailsService userDetailsService;

    @Autowired
    public JwtService(ExpenseUserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    private static final String JWT_SALT = "!A%D*G-KaPdSgUkXp2s5v8y/B?E(H+Mb";

    public boolean isValidJwToken(String token, UserDetails userDetails) throws UnAuthorizedAccessException {

        String[] chunks = token.split("\\.");

        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
        SecretKeySpec keySpec =  new SecretKeySpec(JWT_SALT.getBytes(),sa.getJcaName());

        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];
        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, keySpec);

        if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new UnAuthorizedAccessException("INVALID JWT TOKEN","Couldn't verify the token",
                    HttpStatus.UNAUTHORIZED.toString());
        }
        if(isTokenExpired(token)){
            throw new UnAuthorizedAccessException("INVALID JWT TOKEN","Token expired",
                    HttpStatus.UNAUTHORIZED.toString());
        }
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

    public String getUserNameToken(String token){
       return  extractClaim(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Key getSigningKey(){
        byte[] keyBytes = JWT_SALT.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
