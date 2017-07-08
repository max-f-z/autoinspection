package com.autoinspection.polaris.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.autoinspection.polaris.utils.TokenUtils;
import com.autoinspection.polaris.vo.UserVo;

@Component
public class CurrentUserResolver implements HandlerMethodArgumentResolver {
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().isAssignableFrom(UserVo.class) && parameter.hasParameterAnnotation(CurrentUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Object header = webRequest.getHeader(tokenHeader);
		String token = String.valueOf(header);
		UserVo user = new UserVo();
		user.setUid(tokenUtils.getIdFromToken(token));
		user.setRole(tokenUtils.getRoleFromToken(token).ordinal());
		return user;
	}
}