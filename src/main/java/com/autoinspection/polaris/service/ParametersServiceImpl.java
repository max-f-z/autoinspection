package com.autoinspection.polaris.service;

import com.autoinspection.polaris.utils.Const;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.ParametersEntity;
import com.autoinspection.polaris.model.mapper.ParametersMapper;
import org.springframework.web.client.RestTemplate;

@Service
public class ParametersServiceImpl implements ParametersService {
	
	@Autowired
	ParametersMapper parametersMapper;


	@Autowired
	private RedisTemplate<String, Object> redisTemplate;



	@Override
	public List<ParametersEntity> getParametersByType(String paraType) {
		
		return this.parametersMapper.getParametersByType(paraType);
		
	}
	//从编码转义名字
	@Override
	public String decode(String id, String type) {
		Map<String, String> brandMap = redisTemplate.<String, String>opsForHash().entries(
				Const.PARAM_PREFIX + type);
		if(brandMap == null || brandMap.isEmpty()) {
			List<ParametersEntity> types = parametersMapper.getParametersByType(type);
			HashMap<String, String> map = new HashMap<>();
			for(ParametersEntity entity: types) {
				map.put(entity.getKeyCode(), entity.getKeyValues());
			}
			redisTemplate.opsForHash().putAll(Const.PARAM_PREFIX+type, map);
			redisTemplate.expire(Const.PARAM_PREFIX+type, 60, TimeUnit.MINUTES);
		}
		String result = brandMap.get(id);
		if(result == null) {
			return id;
		}
		return result;
	}
	//从名字转义编码
	@Override
	public String encode(String name, String type) {
		Map<String, String> brandMap = redisTemplate.<String, String>opsForHash().entries(
				type + Const.PARAM_PREFIX);
		if(brandMap == null || brandMap.isEmpty()){
			List<ParametersEntity> types = parametersMapper.getParametersByType(type);
			HashMap<String, String> map = new HashMap<>();
			for(ParametersEntity entity: types) {
				map.put(entity.getKeyValues(),entity.getKeyCode());
			}
			redisTemplate.opsForHash().putAll(type + Const.PARAM_PREFIX, map);
			redisTemplate.expire(type + Const.PARAM_PREFIX, 60, TimeUnit.MINUTES);
		}
		String code = brandMap.get(name);
		if(code == null) {
			return name;
		}
		return code;
	}

}
