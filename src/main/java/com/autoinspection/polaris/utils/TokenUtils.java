package com.autoinspection.polaris.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.autoinspection.polaris.model.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils implements Serializable  {
	private static final long serialVersionUID = 8458078604289609816L;

	public static final String CLAIM_UID = "uid";
	public static final String CLAIM_ROLE = "role";
	public static final String CLAIN_ENABLE = "enable";
	public static final String secret = "polaris2017";
	
	private Claims getClaimsFromToken(String token) {
	    Claims claims;
	    try {
	        claims = Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody();
	    } catch (Exception ex) {
	        claims = null;
	    }
	    return claims;
    }

    public String generateToken(UserEntity user) {
    	Map<String, Object> claims = new HashMap<>();
    	claims.put(CLAIM_UID, user.getId());
    	claims.put(CLAIM_ROLE, "role");
    	return generateToken(claims);
    }
    
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    public Boolean isTokenValid(String token) {
    	return true;
    }
}
