package com.autoinspection.polaris.config;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.autoinspection.polaris.filter.TokenFilter;
import com.autoinspection.polaris.interceptor.PermissionInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
  	public Filter TokenFilter() {
	    return new TokenFilter();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(TokenFilter());
        registrationBean.addUrlPatterns("/v1/api/*");
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        return registrationBean;
  	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/v1/**");
        super.addInterceptors(registry);
	}
	
	
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private int port;
	@Value("${redis.password}")
	private String password;
	@Value("${redis.timeout}")
	private int timeout;
	
	@Value("${redis.pool.maxIdle}")
	private int maxIdle;
	@Value("${redis.pool.minIdle}")
	private int minIdle;
	@Value("${redis.pool.maxWaitMillis}")
	private int maxWaitMillis;
	
    @Bean
    public RedisConnectionFactory jedisConnectionFactory(){
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setDatabase(0);
        jedisConnectionFactory.setPassword(password);
        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(jedisConnectionFactory());
        return redisTemplate;
    }
    
    @Bean
	public RedisTemplate< String, Object > redisTemplate() {
		final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
		template.setConnectionFactory( jedisConnectionFactory() );
		template.setKeySerializer( new StringRedisSerializer() );
		template.setHashValueSerializer( new GenericJackson2JsonRedisSerializer(  ) );
		template.setValueSerializer( new GenericJackson2JsonRedisSerializer( ) );
		return template;
	}
//	@Bean  
//	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {  
//	    ServletRegistrationBean registration = new ServletRegistrationBean(  
//	            dispatcherServlet);  
//	    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);  
//	    return registration;  
//	}  
}
