package com.autoinspection.polaris.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.autoinspection.polaris.vo.Result;

@ControllerAdvice
@SuppressWarnings("rawtypes")
public class ResponseAdvice implements ResponseBodyAdvice {

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		
		if (body.getClass().equals(com.autoinspection.polaris.vo.Result.class)) {
			return body;
		}
		Result<Object> result = new Result<Object>(body);
		return result;
	}

}
