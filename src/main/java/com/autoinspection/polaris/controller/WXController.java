package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.StationEntity;
import com.autoinspection.polaris.service.StationService;
import com.autoinspection.polaris.service.WXService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.wx.AuthCodeRequest;
import com.autoinspection.polaris.vo.wx.SignInRequest;
import com.autoinspection.polaris.vo.wx.SignInResponse;
import com.autoinspection.polaris.vo.wx.SignUpRequest;
import com.autoinspection.polaris.vo.wx.SignUpResponse;

@RestController
@RequestMapping(path = "/v1/wx")
public class WXController {
	
	@Autowired
	private WXService wxService;
	
	@Autowired
	private StationService stationService;
	
	@RequestMapping(path = "/signup", method = RequestMethod.POST)
	public SignUpResponse signUp(@RequestBody SignUpRequest req) throws BizException {
		return wxService.signUp(req);
	}
	
	@RequestMapping(path = "/authCode", method = RequestMethod.POST)
	public Result<String> authCode(@RequestBody AuthCodeRequest req) throws BizException {
		return wxService.authCode(req);
	}
	
	@RequestMapping(path = "/signin", method = RequestMethod.POST)
	public SignInResponse signIn(@RequestBody SignInRequest req) throws BizException {
		return wxService.signIn(req);
	}
	
	@Permission( permissionTypes = { PermissionEnum.WXUSER })
	@RequestMapping(path = "/api/list", method = RequestMethod.GET)
	public Result<String> list() {
		return new Result<>("hello list");
	}
	
	@RequestMapping(path = "/api/stations", method = RequestMethod.GET)
	public List<StationEntity> listStations() {
		return stationService.listAllStations();
	}
}
