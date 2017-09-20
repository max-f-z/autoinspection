package com.autoinspection.polaris.controller;

import com.autoinspection.polaris.utils.ExcelUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.service.InspectionService;
import com.autoinspection.polaris.service.ParametersService;
import com.autoinspection.polaris.service.TyreInStockService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.vo.Pager;
import com.autoinspection.polaris.vo.Pagination;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.vehicle.InStockImportResponse;
import com.autoinspection.polaris.model.entity.ParametersEntity;
import com.autoinspection.polaris.model.entity.TyreInstockEntity;
import com.autoinspection.polaris.resolver.CurrentUser;

@RestController
@RequestMapping(value = "${api.path}/instock")
public class TyreInStockController {

	@Autowired
	TyreInStockService tyreInStockService;

	@Value("${page.limit.default}")
	private int pageDefaultLimit;
	
	@Autowired
	InspectionService inspectionService;
	
	@Autowired
	ParametersService parametersService;

	@RequestMapping(value = "/tyre/search", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<TyreInstockEntity> getEntity(@RequestParam Map<String, String> parameterMap) {
		String barCode = parameterMap.get("barCode");
		String brand = parameterMap.get("brand");
		TyreInstockEntity entity = new TyreInstockEntity();

		if (null != barCode || null != brand) {

			entity.setBarCode(barCode);
			entity.setTyreBrand(brand);

			return this.tyreInStockService.getTyreInstock(entity);

		} else {
			return null;
		}

	}

	@RequestMapping(value = "/tyre/list", method = RequestMethod.GET)
	// @Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<TyreInstockEntity> getList(@RequestParam Map<String, String> parameterMap) {

		int skip = Integer.valueOf(parameterMap.get("skip"));
		int pageSize = Integer.valueOf(parameterMap.get("pageSize"));
		String brand = parameterMap.get("brand");
		TyreInstockEntity entity = new TyreInstockEntity();
		;

		if (null != brand) {
			entity.setTyreBrand(brand);
		}

		return this.tyreInStockService.getList(skip, pageSize, entity);

	}

	@RequestMapping(value = "/tyre/pageList", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public Pager<List<TyreInstockEntity>> listVehicleMileages(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "barCode", required = false) String barCode) {

		if (page == null || page < 1) {
			page = 1;
		}
		if (size == null || size < 1) {
			size = pageDefaultLimit;
		}
		
		TyreInstockEntity entity = new TyreInstockEntity();
		if (null != brand) {
			entity.setTyreBrand(brand);
		}
		if(null != barCode&&!"".equalsIgnoreCase(barCode)){
			entity.setBarCode(barCode);
		}
		List<TyreInstockEntity> result = this.tyreInStockService.getList(page, size, entity);
		
	    int total =  this.tyreInStockService.getCount(entity);
		
		Pagination pagination = new Pagination(page, size, total);
		Pager p = new Pager(result, pagination);
		return p;

	}

	@RequestMapping(value = "/tyre/count", method = RequestMethod.GET)
	@Permission(permissionTypes = { PermissionEnum.ADMIN })
	public int getCount(@RequestParam Map<String, String> parameterMap) {
		String brand = parameterMap.get("brand");
		TyreInstockEntity entity = new TyreInstockEntity();
		;

		if (null != brand) {
			entity.setTyreBrand(brand);
		}

		return this.tyreInStockService.getCount(entity);

	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/tyre/import", method = RequestMethod.POST)
	@Permission(permissionTypes = { PermissionEnum.ADMIN })
	public InStockImportResponse importFile(@RequestParam("file") MultipartFile file, @CurrentUser UserVo user)
			throws IllegalStateException, IOException, BizException {
		File destFile = new File(System.getProperty("user.dir") + File.separator + (System.currentTimeMillis() / 1000L)
				+ "-" + file.getOriginalFilename());
		file.transferTo(destFile);
		
		if(null!=file&&null!=file.getOriginalFilename()){
			String[] files = file.getOriginalFilename().split(".");
			if(files.length>1){
				if(files[1].equalsIgnoreCase("XLS")){
					
				}else if(files[1].equalsIgnoreCase("XLSX")){
					XSSFWorkbook wk = new XSSFWorkbook();
				}else{
					
				}
			}
		}
		

		FileInputStream fileInputStream = new FileInputStream(destFile);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
		HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
		HSSFSheet sheet = workbook.getSheetAt(0);

		List<Integer> failedRows = new ArrayList<Integer>();
		List<String> duplicatedRows = new ArrayList<String>();
		List<String> notBrandcode = new ArrayList<String>();

		int lastRowNum = sheet.getLastRowNum();

		TyreInstockEntity entity = null;
		List<TyreInstockEntity> list = new ArrayList<TyreInstockEntity>();

		Map<String, String> duplicated = new HashMap<String, String>();

		for (int i = 1; i <= lastRowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			entity = new TyreInstockEntity();
			
			

			if (row.getCell(0).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_STRING) {
				if(row.getCell(0).getStringCellValue().equalsIgnoreCase("")){
					break;
				}
				entity.setTyreBrand(row.getCell(0).getStringCellValue());
			} else if (row.getCell(0).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_NUMERIC) {
				System.out.println(row.getCell(0).getNumericCellValue());
				int tmp = (int) row.getCell(0).getNumericCellValue();
				entity.setTyreBrand(String.valueOf(tmp));
			}else{
				break;
			}
			
			if (row.getCell(1).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_STRING) {
			} else if (row.getCell(1).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_NUMERIC) {
			}else{
				break;
			}
			String tyreType = ExcelUtils.convertCellValueToString(row.getCell(1));
			entity.setTyreType(tyreType);
			// 超级单胎的处理
			List<ParametersEntity> result = this.parametersService.getParametersByType("superSingle");
			entity.setSupersingle("0");
			if(null != result && result.size()>0){
				for( ParametersEntity item: result){
					if(null != item.getKeyValues()&&item.getKeyValues().equalsIgnoreCase(tyreType)){
						entity.setSupersingle("1");
						break;
					}
				}
				
			}
			
			//

			if (row.getCell(2).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_STRING) {
				entity.setBarCode(row.getCell(2).getStringCellValue());
			} else if (row.getCell(2).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_NUMERIC) {
				int tmp = (int) row.getCell(2).getNumericCellValue();
				entity.setBarCode(String.valueOf(tmp));
			}else{
				break;
			}
			
			if (row.getCell(3).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_STRING) {
				entity.setFigure(row.getCell(3).getStringCellValue());
			} else if (row.getCell(3).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_NUMERIC) {
				int tmp = (int) row.getCell(3).getNumericCellValue();
				entity.setFigure(String.valueOf(tmp));
			}else{
				break;
			}
			

			String ky = entity.getBarCode().concat(entity.getTyreBrand()).concat(entity.getTyreType());

			String rowindex = duplicated.get(ky);

			if (null != rowindex && !"".equalsIgnoreCase(rowindex)) {
				duplicatedRows.add("excel中" + "第" + i + "行与第" + rowindex + "行重复");
			} else {
				duplicated.put(ky, String.valueOf(i));
			}

			List<TyreInstockEntity> existing = tyreInStockService.getTyreInstock(entity);

			if (null != existing && existing.size() > 0) {
				failedRows.add(i);
			} else {
				entity.setUsed("1");
				entity.setEnable(true);
				list.add(entity);
			}
		}

		if (list.size() > 0) {
			int i = tyreInStockService.insertBatchTyreInstock(list, user.getUid());
		}

		InStockImportResponse resp = new InStockImportResponse();
		resp.setFailedRows(failedRows);
		resp.setDuplicatedRows(duplicatedRows);
		return resp;
	}

}
