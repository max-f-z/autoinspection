package com.autoinspection.polaris.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.Result;

@ControllerAdvice
public class ApiExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
	
	@ExceptionHandler({
		HttpRequestMethodNotSupportedException.class,
		HttpMediaTypeNotSupportedException.class,
		HttpMediaTypeNotAcceptableException.class,
		MissingPathVariableException.class,
		MissingServletRequestParameterException.class,
		ServletRequestBindingException.class,
		ConversionNotSupportedException.class,
		TypeMismatchException.class,
		HttpMessageNotReadableException.class,
		HttpMessageNotWritableException.class,
		MethodArgumentNotValidException.class,
		MissingServletRequestPartException.class,
		BindException.class,
		NoHandlerFoundException.class,
		AsyncRequestTimeoutException.class
	})
	@ResponseBody
	public final ResponseEntity<Result<String>> handleException(Exception ex, WebRequest request) {
		logger.error("***Error***", ex);
		ResponseEntity<Object> response = new ExceptionHandlerBridge().handleException(ex, request);
	    return new ResponseEntity<Result<String>>(new Result<String>("", response.getStatusCode().toString(), ex.getMessage()), response.getStatusCode());
	}
	
	@ExceptionHandler(BizException.class)
    @ResponseBody
    public ResponseEntity<Result<String>>  handleCheckedException(BizException ex) {
    	logger.error("***Error***", ex);
    	 return new ResponseEntity<Result<String>>(new Result<String>("", ex.getErrorCode(), ex.getErrorMessage()), HttpStatus.OK);
    }
	
	@ExceptionHandler({RuntimeException.class,Exception.class})
    @ResponseBody
    public ResponseEntity<Result<String>> handleUnexpectedServerError(RuntimeException ex) {
    	logger.error("***Error***", ex);
        return new ResponseEntity<Result<String>>(new Result<String>("", "500", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	    
	class ExceptionHandlerBridge extends ResponseEntityExceptionHandler{
    }
}
