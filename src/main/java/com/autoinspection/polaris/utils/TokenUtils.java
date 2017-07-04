package com.autoinspection.polaris.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.autoinspection.polaris.model.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils implements Serializable  {
	private static final long serialVersionUID = 8458078604289609816L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String CLAIM_UID = "uid";
	public static final String CLAIM_ROLE = "role";
	public static final String CLAIN_ENABLE = "enable";
	public static final String secret = "polaris2017";
	
	@Value("${jwt.expiration}")
    private Long expiration;
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
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
    	claims.put(CLAIM_ROLE, user.getRole());
    	String token = generateToken(claims);
    	
    	claims.put("token", token);
    	
    	redisTemplate.opsForHash().putAll(Const.TOKEN_PREFIX+String.valueOf(user.getId()), claims);
    	redisTemplate.expire(Const.TOKEN_PREFIX+String.valueOf(user.getId()), expiration, TimeUnit.MINUTES);
    	
    	return token;
    }
    
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    public void refreshToken(String token) {
		threadPoolExecutor.submit(new RefreshTokenDelegator(token));
    }
    
    private static final BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(500);
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3, 2, TimeUnit.MINUTES, blockingQueue,new ThreadPoolExecutor.DiscardPolicy());
    
    private final class RefreshTokenDelegator implements Runnable {
        private String token;
        public RefreshTokenDelegator(final String token) {
            this.token = token;
        }

        public void run()  {
        	try{
        		if(isTokenValid(token)){
        			Map<String, Object> claims = getClaimsFromToken(token);
        			redisTemplate.expire(Const.TOKEN_PREFIX + claims.get(CLAIM_UID), expiration, TimeUnit.MINUTES);
        		}else{
        			logger.warn("token is invalid, can not refresh.");
        		}
        	}catch(Throwable ex){
        		logger.error("refresh token {} error.",ex);
        	}
        }
    }
    
    public String getTokenFromCache(Integer uid) {
    	Object token = redisTemplate.opsForHash().get(Const.TOKEN_PREFIX + uid, "token");
    	if(ObjectUtils.isEmpty(token)){
    		return "";
    	}
    	return (String) token;
    }
    
    public Boolean isTokenValid(String token) {
    	if(StringUtils.isEmpty(token))
    		return false;
    	Integer uid;
    	try {
	    	Map<String, Object> claims = getClaimsFromToken(token);
	    	uid = (Integer) claims.get(CLAIM_UID);
    	} catch (Exception e){
    		uid = null;
    		logger.error("error getting uid from token", token, e);
    	}
    	if (uid == null)
    		return false;
    	String tokenInCache = getTokenFromCache(uid);
    	if(ObjectUtils.nullSafeEquals(token, tokenInCache))
    		return true;
    	else 
    		return false;
    }
}
