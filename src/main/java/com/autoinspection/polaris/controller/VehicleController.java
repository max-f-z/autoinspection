package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.mapper.SearchVehicleRequest;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.VehicleService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.vehicle.AddVehicleInfoResponse;
import com.autoinspection.polaris.vo.vehicle.DeleteVehicleInfoRequest;
import com.autoinspection.polaris.vo.vehicle.DeleteVehicleInfoResponse;
import com.autoinspection.polaris.vo.vehicle.ListVehicleRequest;
import com.autoinspection.polaris.vo.vehicle.UpdateVehicleInfoResponse;
import com.autoinspection.polaris.vo.vehicle.VehicleVo;

@RestController
@RequestMapping(value="${api.path}/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(value="/vehicles", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<VehicleVo> listVehicles(@RequestBody ListVehicleRequest request) {
		return vehicleService.listVehicles((request.getPage()-1) * request.getPageSize(), request.getPageSize());
	}
	
	@RequestMapping(value="/vehicles/{id}", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public VehicleVo getVehicleInfo(@PathVariable Integer id) throws BizException {
		VehicleVo vo = vehicleService.getDetail(id);
		if (vo == null) {
			throw new BizException(ErrorCode.NOT_FOUND);
		}
		return vo;
	}
	
	@RequestMapping(value="/vehicles/add", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public AddVehicleInfoResponse addVehicle(@RequestBody VehicleVo vo, @CurrentUser UserVo user) throws BizException {
		AddVehicleInfoResponse resp = new AddVehicleInfoResponse();
		resp.setId(vehicleService.insertVehicle(vo, user.getUid()));
		return resp;
	}
	
	@RequestMapping(value="/vehicles/update", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public UpdateVehicleInfoResponse updateVehicle(@RequestBody VehicleVo vo, @CurrentUser UserVo user) {
		UpdateVehicleInfoResponse resp = new UpdateVehicleInfoResponse();
		resp.setAffectedRows(vehicleService.updateVehicle(vo, user.getUid()));
		return resp;
	}
	
	@RequestMapping(value="/vehicles/delete", method = RequestMethod.DELETE)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public DeleteVehicleInfoResponse deleteVehicle(@RequestBody DeleteVehicleInfoRequest request, @CurrentUser UserVo user) {
		DeleteVehicleInfoResponse resp = new DeleteVehicleInfoResponse();
		resp.setAffectedRows(vehicleService.deleteVehicle(request.getId()));
		return resp;
	}
	
	@RequestMapping(value="/vehicles/search", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<VehicleVo> deleteVehicle(@RequestBody SearchVehicleRequest request) {
		return vehicleService.search((request.getPage()-1) * request.getPageSize(), request.getPageSize(), request.getSearch());
	}
}
