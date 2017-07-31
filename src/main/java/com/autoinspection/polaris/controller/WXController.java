package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.RegistrationDisplayEntity;
import com.autoinspection.polaris.model.entity.RemainEntity;
import com.autoinspection.polaris.model.entity.ServiceEntity;
import com.autoinspection.polaris.model.entity.StationEntity;
import com.autoinspection.polaris.resolver.WXUser;
import com.autoinspection.polaris.service.AppointmentService;
import com.autoinspection.polaris.service.ServiceService;
import com.autoinspection.polaris.service.StationService;
import com.autoinspection.polaris.service.WXService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.wx.AppointmentRequest;
import com.autoinspection.polaris.vo.wx.AuthCodeRequest;
import com.autoinspection.polaris.vo.wx.RegisterRequest;
import com.autoinspection.polaris.vo.wx.RegisterResponse;
import com.autoinspection.polaris.vo.wx.SignInRequest;
import com.autoinspection.polaris.vo.wx.SignInResponse;
import com.autoinspection.polaris.vo.wx.SignUpRequest;
import com.autoinspection.polaris.vo.wx.SignUpResponse;
import com.autoinspection.polaris.vo.wx.UpdateUserRequest;
import com.autoinspection.polaris.vo.wx.UserInfoResponse;

@RestController
@RequestMapping(path = "/v1/wx")
public class WXController {
	
	@Autowired
	private WXService wxService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private AppointmentService appointmentService;
	
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
	@RequestMapping(path = "/api/stations", method = RequestMethod.GET)
	public List<StationEntity> listStations() {
		return stationService.listAllStations();
	}
	
	@Permission( permissionTypes = { PermissionEnum.WXUSER })
	@RequestMapping(path = "/api/updateUser", method = RequestMethod.POST)
	public Result<String> updateUser(@RequestBody UpdateUserRequest request, @WXUser UserVo user) throws BizException {
		return wxService.updateUser(request, user.getUid());
	}
	
	@Permission( permissionTypes = { PermissionEnum.WXUSER })
	@RequestMapping(path = "/api/services", method = RequestMethod.GET)
	public List<ServiceEntity> listServices() throws BizException {
		return serviceService.listAllServices();
	}
	
	@Permission( permissionTypes = { PermissionEnum.WXUSER })
	@RequestMapping(path = "/api/appointments", method = RequestMethod.POST)
	public List<RemainEntity> listAppointments(@RequestBody AppointmentRequest request) throws BizException {
		return appointmentService.listAppointments(request);
	}
	
	@Permission( permissionTypes = { PermissionEnum.WXUSER })
	@RequestMapping(path = "/api/register", method = RequestMethod.POST)
	public RegisterResponse register(@RequestBody RegisterRequest request, @WXUser UserVo user) throws BizException {
		RegisterResponse resp = new RegisterResponse();
		resp.setRegId(appointmentService.register(request, user.getUid()));
		return resp;
	}
	
	@Permission( permissionTypes = { PermissionEnum.WXUSER })
	@RequestMapping(path = "/api/registrations", method = RequestMethod.GET)
	public List<RegistrationDisplayEntity> listRegistrations(@WXUser UserVo user) throws BizException {
		return appointmentService.listRegistrations(user.getUid());
	}
	
	@Permission( permissionTypes = { PermissionEnum.WXUSER })
	@RequestMapping(path = "/api/cancelRegistration", method = RequestMethod.POST) 
	public Result<String> cancelRegistration(){
		return new Result<>("");
	}
	
	@Permission( permissionTypes = { PermissionEnum.WXUSER })
	@RequestMapping(path = "/api/userinfo", method = RequestMethod.GET)
	public UserInfoResponse getUserInfo(@WXUser UserVo user) throws BizException {
		return wxService.getUserInfo(user.getUid());
	}
}
