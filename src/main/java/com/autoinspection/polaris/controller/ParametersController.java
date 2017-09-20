package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.ParametersEntity;
import com.autoinspection.polaris.service.ParametersService;

@RestController
@RequestMapping(value="${api.path}/para")
public class ParametersController {
	
	@Autowired
	ParametersService parametersService;
	
	
	@RequestMapping(value = "/getPara", method = RequestMethod.POST)
	@Permission(permissionTypes = {PermissionEnum.ADMIN,PermissionEnum.ENDUSER,PermissionEnum.WXUSER})
	public List<ParametersEntity> getParametersByType(@RequestParam(value = "paraType", required = true) String paraType){
		
		return this.parametersService.getParametersByType(paraType);
	}

}
