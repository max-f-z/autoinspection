package com.autoinspection.polaris.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.utils.TokenUtils;
import com.autoinspection.polaris.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WXTokenFilter extends OncePerRequestFilter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TokenUtils wxTokenUtils;
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authToken = request.getHeader(tokenHeader);
		logger.info("wx authToken: " + authToken);
		if (wxTokenUtils.isTokenValidWX(authToken)) {
			wxTokenUtils.refreshTokenWX(authToken);
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;  
			httpResponse.setCharacterEncoding("UTF-8");    
			httpResponse.setContentType("application/json; charset=utf-8");   
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
			
			ObjectMapper mapper = new ObjectMapper();  
			Result<Object> result = new Result<Object>(ErrorCode.TOKEN_INVALID);  
			httpResponse.getWriter().write(mapper.writeValueAsString(result)); 
			return;
		}
		
		filterChain.doFilter(request, response);
	}
	
}
