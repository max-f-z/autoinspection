package com.autoinspection.polaris.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Aspect
@Component
public class RequestLogAspect {
private static Logger log = LoggerFactory.getLogger(RequestLogAspect.class);
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void controllerAspect() {
    }
    
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {

    	ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		ObjectMapper mapper = new ObjectMapper();
		try {
			log.info("reqUrl:" + request.getRequestURL().toString() 
		   + ", reqMethod:" + request.getMethod()
		   + ", reqClass:" + joinPoint.getSignature()
		   + ", params:" + mapper.writeValueAsString(joinPoint.getArgs()));
		} catch (JsonProcessingException e) {
		}
    }
}
