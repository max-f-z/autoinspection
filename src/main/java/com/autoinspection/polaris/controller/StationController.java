package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.StationEntity;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.StationService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.station.AddStationRequest;
import com.autoinspection.polaris.vo.station.AddStationResponse;
import com.autoinspection.polaris.vo.station.DeleteStationRequest;
import com.autoinspection.polaris.vo.station.DeleteStationResponse;
import com.autoinspection.polaris.vo.station.UpdateStationRequest;
import com.autoinspection.polaris.vo.station.UpdateStationResponse;

@RestController
@RequestMapping(value="${api.path}/station")
public class StationController {
	@Autowired
	private StationService stationService;
	
	@RequestMapping(value="/stations", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<StationEntity> getStations() {
		return stationService.listAllStations();
	}
	
	@RequestMapping(value="/stations/{id}", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public StationEntity getStation(@PathVariable Integer id) throws BizException {
		StationEntity station = stationService.getStationById(id);
		if (station == null) {
			throw new BizException(ErrorCode.NOT_FOUND);
		}
		return station;
	}
	
	@RequestMapping(value="/stations/add", method = RequestMethod.POST) 
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public AddStationResponse insertStation(@RequestBody AddStationRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		
		int id = stationService.insertStation(request, user.getUid());
		AddStationResponse resp = new AddStationResponse();
		if (id != 0) {
			resp.setId(id);
		}
		return resp;
	}
	
	@RequestMapping(value="/stations/update", method = RequestMethod.PUT)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public UpdateStationResponse updateStation(@RequestBody UpdateStationRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = stationService.updateStation(request, user.getUid());
		UpdateStationResponse resp = new UpdateStationResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
	
	@RequestMapping(value="/stations/delete", method = RequestMethod.DELETE)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public DeleteStationResponse deleteStation(@RequestBody DeleteStationRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = stationService.deleteStation(request.getId(), user.getUid());
		DeleteStationResponse resp = new DeleteStationResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
}
