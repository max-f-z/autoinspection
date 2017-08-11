package com.autoinspection.polaris.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.mapper.SearchVehicleRequest;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.VehicleService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.vehicle.AddVehicleInfoResponse;
import com.autoinspection.polaris.vo.vehicle.DeleteVehicleInfoRequest;
import com.autoinspection.polaris.vo.vehicle.DeleteVehicleInfoResponse;
import com.autoinspection.polaris.vo.vehicle.ImportResponse;
import com.autoinspection.polaris.vo.vehicle.ListVehicleRequest;
import com.autoinspection.polaris.vo.vehicle.UpdateVehicleInfoResponse;
import com.autoinspection.polaris.vo.vehicle.VehicleTireVo;
import com.autoinspection.polaris.vo.vehicle.VehicleVo;
import com.mysql.jdbc.StringUtils;

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
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/vehicles/import", method = RequestMethod.POST)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public ImportResponse importFile(@RequestParam("file") MultipartFile file, @CurrentUser UserVo user) throws IllegalStateException, IOException {
		File destFile =  new File(System.getProperty("user.dir") + File.separator + (System.currentTimeMillis() / 1000L) + "-" + file.getOriginalFilename());
		file.transferTo(destFile);
		
		FileInputStream fileInputStream = new FileInputStream(destFile);
	    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
	    POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
	    HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
	    HSSFSheet sheet = workbook.getSheetAt(0);
	    
	    List<Integer> failedRows = new ArrayList<Integer>();
	    
	    int lastRowNum = sheet.getLastRowNum();
	    for (int i = 1; i <= lastRowNum; i++) {
	        HSSFRow row = sheet.getRow(i);
	        
	        VehicleVo vo = new VehicleVo();
	        vo.setCustomerName(row.getCell(1).getStringCellValue());
	        vo.setPlate(row.getCell(0).getStringCellValue());
	        vo.setVehicleType((int)row.getCell(2).getNumericCellValue());
	        
	        List<VehicleTireVo> tires = new ArrayList<VehicleTireVo>(); 
	        for (int cnt = 2; cnt < 16; cnt++) {
	        	if (row.getCell(2*cnt -1) != null && row.getCell(2*cnt) != null) {
		        	if (!StringUtils.isNullOrEmpty(row.getCell(2*cnt - 1).getStringCellValue()) && !StringUtils.isNullOrEmpty(row.getCell(2*cnt).getStringCellValue())) {
		        		VehicleTireVo tireVo = new VehicleTireVo();
		        		tireVo.setTireBrand(row.getCell(2*cnt).getStringCellValue());
		        		tireVo.setTirePosition(cnt-1);
		        		tireVo.setTireId(row.getCell(2*cnt - 1).getStringCellValue());
		        		tires.add(tireVo);
		        	}
	        	}
	        }
	        vo.setTires(tires);
	        try {
	        	vehicleService.insertVehicle(vo, user.getUid());
	        } catch (Exception e) {
	        	failedRows.add(i+1);
	        }
	    }
	    
	    ImportResponse resp = new ImportResponse();
	    resp.setFailedRows(failedRows);
		return resp;
	}
}
