package com.red_social.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    //codigo para jwt, create token, decode token, get claims, get specific claim
    @Value("${security.jwt.private.key}")
    private String privateKey;
    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    public String createToken(Authentication authentication){
        // authentication se utiliza para el manejo de token
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        // queda en el context holder
        String username = authentication.getPrincipal().toString();

        // a revisar
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String jwt = JWT.create()
                .withIssuer(userGenerator)
                .withSubject(username)
                .withClaim("authorities",authorities)
                .withIssuedAt(new Date()) // token expira en 2 hrs
                .withExpiresAt(new Date(System.currentTimeMillis() + (120 * 60000)))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis())) // a partir de cuando es valido el token
                .sign(algorithm);
        return jwt;
    }
    public DecodedJWT decodedJWT(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(userGenerator)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        }
        catch (JWTVerificationException e){
            throw new JWTVerificationException("Invalid token. Not authorized");
        }
    }
    public String getUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }
    public Claim getSpecificClaim(DecodedJWT decodedJWT,String claimName){
        return decodedJWT.getClaim(claimName);
    }
    public Map<String,Claim> getAllClaims(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}
