package com.autoinspection.polaris.interceptor;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.utils.TokenUtils;

public class PermissionInterceptor extends HandlerInterceptorAdapter  {
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        
        Permission permission = (Permission) method.getAnnotation(Permission.class);  
        if (permission != null) {  
            PermissionEnum[] permissionTypes = permission.permissionTypes();
            
            String authToken = request.getHeader(tokenHeader);
            PermissionEnum role = tokenUtils.getRoleFromToken(authToken);
            if (!Arrays.asList(permissionTypes).contains(role)) {
            	throw new BizException(ErrorCode.NOT_AUTHORIZED);
            }
        }  
        return true;  
    }  
}
