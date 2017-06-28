package com.autoinspection.polaris.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PermissionInterceptor extends HandlerInterceptorAdapter  {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        
        Permission permission = (Permission) method.getAnnotation(Permission.class);  
        if (permission != null) {  
            
            PermissionEnum[] permissionTypes = permission.permissionTypes();
            
            System.out.println(permissionTypes);
            
            return true;
        }  
        return true;  
    }  
}
