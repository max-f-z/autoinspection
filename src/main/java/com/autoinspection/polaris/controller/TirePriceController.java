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
import com.autoinspection.polaris.model.entity.TirePriceEntity;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.TirePriceService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.tireprice.AddTirePriceRequest;
import com.autoinspection.polaris.vo.tireprice.AddTirePriceResponse;
import com.autoinspection.polaris.vo.tireprice.DeleteTirePriceRequest;
import com.autoinspection.polaris.vo.tireprice.DeleteTirePriceResponse;
import com.autoinspection.polaris.vo.tireprice.SearchTirePriceRequest;
import com.autoinspection.polaris.vo.tireprice.UpdateTirePriceRequest;
import com.autoinspection.polaris.vo.tireprice.UpdateTirePriceResponse;

@RestController
@RequestMapping(value="${api.path}/tireprice")
public class TirePriceController {

	@Autowired
	private TirePriceService tirePriceService;
	
	@RequestMapping(value="/prices", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<TirePriceEntity> getTirePrices() {
		return tirePriceService.listTirePrices();
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<TirePriceEntity> search(@RequestBody SearchTirePriceRequest request) {
		return tirePriceService.findByBrand(request.getBrand(),request.search);
	}
	
	@RequestMapping(value="/prices/{id}", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public TirePriceEntity getTirePrice(@PathVariable Integer id) throws BizException {
		TirePriceEntity entity = tirePriceService.getTirePriceById(id);
		if (entity == null) {
			throw new BizException(ErrorCode.NOT_FOUND);
		}
		return entity;
	}
	
	@RequestMapping(value="/prices/add", method = RequestMethod.POST) 
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public AddTirePriceResponse insertTirePrice(@RequestBody AddTirePriceRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		
		int id = tirePriceService.insertTirePrice(request, user.getUid());
		AddTirePriceResponse resp = new AddTirePriceResponse();
		if (id != 0) {
			resp.setId(id);
		}
		return resp;
	}
	
	@RequestMapping(value="/prices/update", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public UpdateTirePriceResponse updateTirePrice(@RequestBody UpdateTirePriceRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = tirePriceService.updateTirePrice(request, user.getUid());
		UpdateTirePriceResponse resp = new UpdateTirePriceResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
	
	@RequestMapping(value="/prices/delete", method = RequestMethod.DELETE)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public DeleteTirePriceResponse deleteStation(@RequestBody DeleteTirePriceRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = tirePriceService.deleteTirePrice(request.getId(), user.getUid());
		DeleteTirePriceResponse resp = new DeleteTirePriceResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
}
