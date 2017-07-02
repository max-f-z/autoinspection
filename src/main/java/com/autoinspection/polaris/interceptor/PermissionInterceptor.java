package com.autoinspection.polaris.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;

public class PermissionInterceptor extends HandlerInterceptorAdapter  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        
        Permission permission = (Permission) method.getAnnotation(Permission.class);  
        if (permission != null) {  
            
            PermissionEnum[] permissionTypes = permission.permissionTypes();
            
            System.out.println(permissionTypes);
            
//            throw new BizException(ErrorCode.USER_NOTFOUND);
            return true;
        }  
        return true;  
    }  
}
