package com.autoinspection.polaris.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.service.UserService;
import com.autoinspection.polaris.service.VehicleService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.Const;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.utils.TokenUtils;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.auth.AuthRequest;
import com.autoinspection.polaris.vo.auth.AuthResponse;
import com.autoinspection.polaris.vo.vehicle.VehicleTireVo;
import com.autoinspection.polaris.vo.vehicle.VehicleVo;
import com.mysql.jdbc.StringUtils;

@RestController
@RequestMapping(path = "/v1/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
    private TokenUtils tokenUtils;
	
	@RequestMapping(path = "/signin", method = RequestMethod.POST)
	public AuthResponse login(@RequestBody AuthRequest request) throws BizException {
		if (request == null || StringUtils.isNullOrEmpty(request.getUname()) || StringUtils.isNullOrEmpty(request.getPwd()) || !userService.validateUser(request.getUname(), request.getPwd())) {
			throw new BizException(ErrorCode.INVALID_USR_OR_PWD);
		}
		
		UserEntity user = userService.getByUnamePwd(request.getUname(), request.getPwd());
		AuthResponse resp = new AuthResponse();
		
		String token = (String) redisTemplate.opsForHash().get(Const.TOKEN_PREFIX + user.getId(), "token");
		if (StringUtils.isNullOrEmpty(token)) {
			resp.setToken(tokenUtils.generateToken(user));
		} else {
			resp.setToken(token);
		}
		return resp;
	}
	
	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public Result<String> test() throws BizException {
		VehicleVo vo = new VehicleVo();
		vo.setPlate("辽B 66666");
		vo.setCustomerName("客户");
		vo.setVehicleType(1);
		
		List<VehicleTireVo> tireVos = new ArrayList<VehicleTireVo>();
		VehicleTireVo tvo1 = new VehicleTireVo();
		tvo1.setTireBrand("goodyear");
		tvo1.setTireId("gy001");
		tvo1.setTirePosition(1);
		VehicleTireVo tvo2 = new VehicleTireVo();
		tvo2.setTireBrand("goodyear");
		tvo2.setTireId("gy002");
		tvo2.setTirePosition(2);
		
		tireVos.add(tvo1);
		tireVos.add(tvo2);
		vo.setTires(tireVos);
		
		vehicleService.insertVehicle(vo, 1);
		
		return new Result<>("");
	}
}
