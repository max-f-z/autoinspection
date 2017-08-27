package com.autoinspection.polaris.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.RegistrationDisplayEntity;
import com.autoinspection.polaris.model.entity.ServiceEntity;
import com.autoinspection.polaris.model.entity.UserEntity;
import com.autoinspection.polaris.model.entity.VehicleTypeEntity;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.AppointmentService;
import com.autoinspection.polaris.service.InspectionService;
import com.autoinspection.polaris.service.MaintenanceService;
import com.autoinspection.polaris.service.ServiceService;
import com.autoinspection.polaris.service.UserService;
import com.autoinspection.polaris.service.VehicleService;
import com.autoinspection.polaris.service.VehicleTypeService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.Inspection.AddInspectionRequest;
import com.autoinspection.polaris.vo.Inspection.AddInspectionResponse;
import com.autoinspection.polaris.vo.Inspection.AddMaintenanceRequest;
import com.autoinspection.polaris.vo.Inspection.AddMaintenanceResponse;
import com.autoinspection.polaris.vo.Inspection.DeleteInspectionRequest;
import com.autoinspection.polaris.vo.Inspection.DeleteInspectionResponse;
import com.autoinspection.polaris.vo.Inspection.GetInspectionsRequest;
import com.autoinspection.polaris.vo.Inspection.GetMaintenanceRequest;
import com.autoinspection.polaris.vo.Inspection.InspectionVo;
import com.autoinspection.polaris.vo.Inspection.MaintenanceVo;
import com.autoinspection.polaris.vo.Inspection.UpdateInspectionRequest;
import com.autoinspection.polaris.vo.Inspection.UpdateInspectionResponse;
import com.autoinspection.polaris.vo.Inspection.UpdateMaintenanceRequest;
import com.autoinspection.polaris.vo.Inspection.UpdateMaintenanceResponse;
import com.autoinspection.polaris.vo.Inspection.VehicleInfoRequest;
import com.autoinspection.polaris.vo.vehicle.VehicleInfoVo;
import com.autoinspection.polaris.vo.vehicle.VehicleVo;

@RestController
@RequestMapping(value="${api.path}/input")
public class InputController {
	
	@Autowired
	private AppointmentService appoitnmentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private InspectionService inspectionService;
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private MaintenanceService maintenanceService;
	
	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@RequestMapping(path = "/listRegistrations", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ENDUSER })
	public List<RegistrationDisplayEntity> listRegistrations(@CurrentUser UserVo user) throws BizException {
		int uid = user.getUid();
		UserEntity userEntity = userService.getById(uid);
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String regDate = formatter.format(now);
		
		return appoitnmentService.listRegistrationsForEndUser(userEntity.getStationId(), regDate);
	}
	
	@RequestMapping(path = "/inspection/inspections", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ENDUSER })
	public List<InspectionVo> listInspections(@RequestBody GetInspectionsRequest request) {
		return inspectionService.listInspections((request.getPage()-1) * request.getPageSize(), request.getPageSize(), request.getPlate());
	}
	
	@RequestMapping(path = "/inspection/add", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ENDUSER })
	public AddInspectionResponse addInspection(@RequestBody AddInspectionRequest request, @CurrentUser UserVo user) {
		AddInspectionResponse resp = new AddInspectionResponse();
		resp.setId(inspectionService.insertInspection(request, user.getUid()));
		return resp;
	}
	
	@RequestMapping(path = "/inspection/update", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ENDUSER })
	public UpdateInspectionResponse addInspection(@RequestBody UpdateInspectionRequest request, @CurrentUser UserVo user) {
		UpdateInspectionResponse resp = new UpdateInspectionResponse();
		resp.setAffectedRows(inspectionService.updateInspection(request, user.getUid()));
		return resp;
	}
	
	@RequestMapping(path = "/inspection/delete", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ENDUSER })
	public DeleteInspectionResponse deleteInspection(@RequestBody DeleteInspectionRequest request, @CurrentUser UserVo user) {
		DeleteInspectionResponse resp = new DeleteInspectionResponse();
		resp.setAffectedRows(inspectionService.deletInspection(request));
		return resp;
	}
	
	@RequestMapping(path = "/maintenance/info", method = RequestMethod.POST) 
	@Permission( permissionTypes = { PermissionEnum.ENDUSER})
	public MaintenanceVo getMaintenance(@RequestBody GetMaintenanceRequest request) {
		return maintenanceService.getMaintenance(request.getInspectionId());
	}
	
	@RequestMapping(path = "/maintenance/update", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ENDUSER})
	public UpdateMaintenanceResponse updateMaintenance(@RequestBody UpdateMaintenanceRequest request, @CurrentUser UserVo user) {
		UpdateMaintenanceResponse resp = new UpdateMaintenanceResponse();
		int rows = maintenanceService.updateMaintenance(request, user.getUid());
		resp.setAffectedRows(rows);
		return resp;
	}
	
	@RequestMapping(path = "/maintenance/add", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ENDUSER})
	public AddMaintenanceResponse addMaintenance(@RequestBody AddMaintenanceRequest request, @CurrentUser UserVo user) {
		AddMaintenanceResponse resp = new AddMaintenanceResponse();
		long id = maintenanceService.insertMaintenance(request, user.getUid());
		resp.setId(id);
		return resp;
	}
	
	@RequestMapping(value="/services", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ENDUSER })
	public List<ServiceEntity> getStations() {
		return serviceService.listAllServices();
	}
	
	@RequestMapping(path = "/inspection/vehicleInfo", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ENDUSER })
	public VehicleInfoVo getVehicleInfo(@RequestBody VehicleInfoRequest request) {
		VehicleVo vo = vehicleService.getDetailByPlate(request.getPlate());
		VehicleInfoVo info = new VehicleInfoVo();
		if (vo != null && vo.getId() != 0) {
			info.setCustomerName(vo.getCustomerName());
			info.setPlate(vo.getPlate());
			VehicleTypeEntity en = vehicleTypeService.getById(vo.getVehicleType());
			info.setTypeCode(en.getCode());
			info.setTypeName(en.getType());
			info.setTireNum(en.getTireNum());
			info.setBackUp(2);
		}
		return info;
	}
}
