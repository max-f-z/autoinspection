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
	public static final String CLAIM_ROLES = "roles";
	public static final String CLAIN_ENABLE = "enable";
	
	private Claims getClaimsFromToken(String token) {
	    Claims claims;
	    try {
	        claims = Jwts.parser()
	                .setSigningKey("123")
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
    	claims.put(CLAIM_ROLES, "roles");
    	return generateToken(claims);
    }
    
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "123")
                .compact();
    }
    
    public Boolean isTokenValid(String token) {
    	return true;
    }
}
