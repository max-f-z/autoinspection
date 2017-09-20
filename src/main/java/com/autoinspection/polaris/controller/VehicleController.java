package com.autoinspection.polaris.controller;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.VehicleTypeEntity;
import com.autoinspection.polaris.model.mapper.SearchVehicleRequest;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.VehicleMileageService;
import com.autoinspection.polaris.service.VehicleService;
import com.autoinspection.polaris.service.VehicleTypeService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.utils.ExcelUtils;
import com.autoinspection.polaris.vo.Pager;
import com.autoinspection.polaris.vo.Pagination;
import com.autoinspection.polaris.vo.Result;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.vehicle.AddVehicleInfoResponse;
import com.autoinspection.polaris.vo.vehicle.CheckTireRequest;
import com.autoinspection.polaris.vo.vehicle.CheckTireResponse;
import com.autoinspection.polaris.vo.vehicle.DeleteVehicleInfoRequest;
import com.autoinspection.polaris.vo.vehicle.DeleteVehicleInfoResponse;
import com.autoinspection.polaris.vo.vehicle.ImportResponse;
import com.autoinspection.polaris.vo.vehicle.ListVehicleRequest;
import com.autoinspection.polaris.vo.vehicle.UpdateVehicleInfoResponse;
import com.autoinspection.polaris.vo.vehicle.VehicleMileageVo;
import com.autoinspection.polaris.vo.vehicle.VehicleTireVo;
import com.autoinspection.polaris.vo.vehicle.VehicleVo;
import com.mysql.jdbc.StringUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "${api.path}/vehicle")
public class VehicleController {


  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${page.limit.default}")
  private int pageDefaultLimit;

  @Autowired
  private VehicleService vehicleService;

  @Autowired
  private VehicleTypeService vehicleTypeService;

  @Autowired
  private VehicleMileageService vehicleMileageService;

  @RequestMapping(value = "/vehicles", method = RequestMethod.POST)
  @Permission(permissionTypes = {PermissionEnum.ADMIN,PermissionEnum.ENDUSER})
  public Pager<List<VehicleVo>> listVehicles(@RequestBody ListVehicleRequest request) {
	  
	  List<VehicleVo> result = vehicleService
        .listVehicles((request.getPage() - 1) * request.getPageSize(), request.getPageSize());
	  
	  int total = this.vehicleService.getgetCount();
	  Pagination pagination = new Pagination((request.getPage() - 1) * request.getPageSize(), request.getPageSize(), total);
	  Pager p = new Pager(result, pagination);
	  return p;
  }

  @RequestMapping(value = "/vehicles/{id}", method = RequestMethod.GET)
  @Permission(permissionTypes = {PermissionEnum.ADMIN,PermissionEnum.ENDUSER})
  public VehicleVo getVehicleInfo(@PathVariable Integer id) throws BizException {
    VehicleVo vo = vehicleService.getDetail(id);
    if (vo == null) {
      throw new BizException(ErrorCode.NOT_FOUND);
    }
    return vo;
  }

  @RequestMapping(value = "/vehicles/add", method = RequestMethod.POST)
  @Permission(permissionTypes = {PermissionEnum.ADMIN,PermissionEnum.ENDUSER})
  public AddVehicleInfoResponse addVehicle(@RequestBody VehicleVo vo, @CurrentUser UserVo user)
      throws BizException {
    AddVehicleInfoResponse resp = new AddVehicleInfoResponse();
    resp.setId(vehicleService.insertVehicle(vo, user.getUid()));
    return resp;
  }

  @RequestMapping(value = "/vehicles/update", method = RequestMethod.POST)
  @Permission(permissionTypes = {PermissionEnum.ADMIN})
  public UpdateVehicleInfoResponse updateVehicle(@RequestBody VehicleVo vo,
      @CurrentUser UserVo user) {
    UpdateVehicleInfoResponse resp = new UpdateVehicleInfoResponse();
    resp.setAffectedRows(vehicleService.updateVehicle(vo, user.getUid()));
    return resp;
  }

  @RequestMapping(value = "/vehicles/tire", method = RequestMethod.POST)
  @Permission(permissionTypes = {PermissionEnum.ADMIN})
  public CheckTireResponse checkTire(@RequestBody CheckTireRequest request,
      @CurrentUser UserVo user) throws BizException {
    CheckTireResponse resp = new CheckTireResponse();
    if (StringUtils.isNullOrEmpty(request.getTireId())) {
      throw new BizException(ErrorCode.INVALID_PARAM);
    }
    resp.setExists(vehicleService.checkTireExists(request.getTireId()));
    return resp;
  }

  @RequestMapping(value = "/vehicles/types", method = RequestMethod.GET)
  @Permission(permissionTypes = {PermissionEnum.ADMIN, PermissionEnum.ENDUSER})
  public List<VehicleTypeEntity> vehicleTypes() {
    return vehicleService.getTypes();
  }

  @RequestMapping(value = "/vehicles/delete", method = RequestMethod.DELETE)
  @Permission(permissionTypes = {PermissionEnum.ADMIN})
  public DeleteVehicleInfoResponse deleteVehicle(@RequestBody DeleteVehicleInfoRequest request,
      @CurrentUser UserVo user) {
    DeleteVehicleInfoResponse resp = new DeleteVehicleInfoResponse();
    resp.setAffectedRows(vehicleService.deleteVehicle(request.getId()));
    return resp;
  }

  @RequestMapping(value = "/vehicles/search", method = RequestMethod.POST)
  @Permission(permissionTypes = {PermissionEnum.ADMIN, PermissionEnum.ENDUSER})
  public Pager deleteVehicle(@RequestBody SearchVehicleRequest request) {

    List<VehicleVo> vehicleVoList = vehicleService
        .search((request.getPage() - 1) * request.getPageSize(), request.getPageSize(),
            request.getSearch());
    Long rowCount = vehicleService.countVehicles(request.getSearch());
    Pagination pagination = new Pagination(request.getPage(), request.getPageSize(), rowCount.intValue());
    Pager p = new Pager(vehicleVoList, pagination);
    return p;
  }

//  @SuppressWarnings("resource")
//  @RequestMapping(value = "/vehicles/import", method = RequestMethod.POST)
//  @Permission(permissionTypes = {PermissionEnum.ADMIN})
//  public ImportResponse importVehicleFile(@RequestParam("file") MultipartFile file,
//      @CurrentUser UserVo user) throws IllegalStateException, IOException {
//    File destFile = new File(
//        System.getProperty("user.dir") + File.separator + (System.currentTimeMillis() / 1000L) + "-"
//            + file.getOriginalFilename());
//    file.transferTo(destFile);
//
//    FileInputStream fileInputStream = new FileInputStream(destFile);
//    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//    POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
//    HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
//    HSSFSheet sheet = workbook.getSheetAt(0);
//
//    List<Integer> failedRows = new ArrayList<Integer>();
//
//    int lastRowNum = sheet.getLastRowNum();
//    for (int i = 1; i <= lastRowNum; i++) {
//      HSSFRow row = sheet.getRow(i);
//
//      VehicleVo vo = new VehicleVo();
//      vo.setCustomerName(row.getCell(1).getStringCellValue());
//      vo.setPlate(row.getCell(0).getStringCellValue());
//      vo.setVehicleType((int) row.getCell(2).getNumericCellValue());
//
//      List<VehicleTireVo> tires = new ArrayList<VehicleTireVo>();
//      for (int cnt = 2; cnt < 16; cnt++) {
//        if (row.getCell(2 * cnt - 1) != null && row.getCell(2 * cnt) != null) {
//          if (!StringUtils.isNullOrEmpty(row.getCell(2 * cnt - 1).getStringCellValue())
//              && !StringUtils.isNullOrEmpty(row.getCell(2 * cnt).getStringCellValue())) {
//            VehicleTireVo tireVo = new VehicleTireVo();
//            tireVo.setTireBrand(row.getCell(2 * cnt).getStringCellValue());
//            tireVo.setTirePosition(cnt - 1);
//            tireVo.setTireId(row.getCell(2 * cnt - 1).getStringCellValue());
//            tires.add(tireVo);
//          }
//        }
//      }
//      vo.setTires(tires);
//      try {
//        vehicleService.insertVehicle(vo, user.getUid());
//      } catch (Exception e) {
//        failedRows.add(i + 1);
//      }
//    }
//
//    ImportResponse resp = new ImportResponse();
//    resp.setFailedRows(failedRows);
//    return resp;
//  }

  @SuppressWarnings("resource")
  @RequestMapping(value = "/vehicles/import", method = RequestMethod.POST)
  @Permission(permissionTypes = {PermissionEnum.ADMIN})
  public Result<ImportResponse> importFile(@RequestParam("file") MultipartFile file,
      @RequestParam("customerName") String customerName, @CurrentUser UserVo user)
      throws IllegalStateException, IOException {
    HSSFSheet sheet = saveFileAndOpenSheet(file);

    List<Integer> failedRows = new ArrayList<Integer>();
    List<Integer> invalidRows = validateVehicleSheet(sheet);
    if (invalidRows.size() == 0) {
      Map<String, Integer> idAndTireNumMap = getVehicleTypeIdAndTireNumMap();
      int lastRowNum = sheet.getLastRowNum();
      for (int i = 1; i <= lastRowNum; i++) {
        HSSFRow row = sheet.getRow(i);

        VehicleVo vo = new VehicleVo();
        vo.setCustomerName(customerName);
        if (row.getCell(0).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_STRING) {
		} else if (row.getCell(0).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_NUMERIC) {
		}else{
			break;
		}
        //车牌号
        vo.setPlate(convertCellValueToString(row.getCell(0)));
        if (row.getCell(1).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_STRING) {
		} else if (row.getCell(1).getCellType() == org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_NUMERIC) {
		}else{
			break;
		}
        //车型
        String vehicleType = convertCellValueToString(row.getCell(1));
        if(null == vehicleType || vehicleType.equalsIgnoreCase("")){
        	break;
        }
        vo.setVehicleType(vehicleType);
        //车型编码
        vo.setVehicleModel(convertCellValueToString(row.getCell(2)));
        //路线
        vo.setLine(convertCellValueToString(row.getCell(3)));
        //初始里程
        vo.setInitialDistance(convertCellValueToString(row.getCell(4)));

        List<VehicleTireVo> tires = new ArrayList<VehicleTireVo>();
        for (int cnt = 5; cnt < idAndTireNumMap.get(convertCellValueToString(row.getCell(2)).concat(vehicleType)) * 3 + 5; ) {
          VehicleTireVo tireVo = new VehicleTireVo();
          tireVo.setTireId(convertCellValueToString(row.getCell(cnt)));
          tireVo.setTireBrand(convertCellValueToString(row.getCell(cnt + 1)));
          tireVo.setTireType(convertCellValueToString(row.getCell(cnt + 2)));
          tireVo.setTirePosition((cnt -5) / 3 + 1);
          tires.add(tireVo);
          cnt = cnt + 3;
        }
        //备胎
        String b1Id = convertCellValueToString(row.getCell(12 * 3 + 5));
        String b1Brand = convertCellValueToString(row.getCell(12 * 3 + 6));
        String b1type = convertCellValueToString(row.getCell(12 * 3 + 7));
        String b2Id = convertCellValueToString(row.getCell(13 * 3 + 5));
        String b2Brand = convertCellValueToString(row.getCell(13 * 3 + 6));
        String b2type = convertCellValueToString(row.getCell(13 * 3 + 7));

        if (org.springframework.util.StringUtils.isEmpty(b1Id) ||
            org.springframework.util.StringUtils.isEmpty(b1Brand) ||
            org.springframework.util.StringUtils.isEmpty(b1type)) {
        } else {
          VehicleTireVo tireVo = new VehicleTireVo();
          tireVo.setTireId(b1Id);
          tireVo.setTireBrand(b1Brand);
          tireVo.setTireType(b1type);
          tireVo.setTirePosition(13);
          tires.add(tireVo);
        }
        if (org.springframework.util.StringUtils.isEmpty(b2Id) ||
            org.springframework.util.StringUtils.isEmpty(b2Brand) ||
            org.springframework.util.StringUtils.isEmpty(b2type)) {
        } else {
          VehicleTireVo tireVo = new VehicleTireVo();
          tireVo.setTireId(b2Id);
          tireVo.setTireBrand(b2Brand);
          tireVo.setTirePosition(14);
          tireVo.setTireType(b2type);
          tires.add(tireVo);
        }
        vo.setTires(tires);
        try {
          vehicleService.insertVehicle(vo, user.getUid());
        } catch (Exception e) {
          logger.error("import error for plate: " + vo.getPlate(), e);
          failedRows.add(i + 1);
        }
      }
    }

    ImportResponse resp = new ImportResponse();
    resp.setFailedRows(failedRows);
    resp.setInvalidRows(invalidRows);
    if(failedRows.isEmpty() && invalidRows.isEmpty()) {
      return Result.ok(resp);
    }
    return Result.ng(resp);
  }

  private Map<String, Integer> getVehicleTypeIdAndTireNumMap() {
    List<VehicleTypeEntity> vehicleTypeEntityList = vehicleTypeService.listVehicleTypes();
    Map<String, Integer> idAndTireNumMap = new HashMap<>();
    vehicleTypeEntityList.forEach(e -> {
      idAndTireNumMap.put(String.valueOf(e.getCode()).concat(e.getType()), e.getTireNum());
    });
    return idAndTireNumMap;
  }

  private List<Integer> validateVehicleSheet(HSSFSheet sheet) {
    Map<String, Integer> idAndTireNumMap = getVehicleTypeIdAndTireNumMap();
    List<Integer> failedRows = new ArrayList<Integer>();
    if (sheet == null) {
      failedRows.add(0);
      return failedRows;
    }
    int lastRowNum = sheet.getLastRowNum();
    if (lastRowNum == 0) {
      failedRows.add(1);
      return failedRows;
    }
    Map<String, List<Integer>> plateRowNumMap = new HashMap<>();

    for (int i = 1; i <= lastRowNum; i++) {
      HSSFRow row = sheet.getRow(i);
      String platei = convertCellValueToString(row.getCell(0));
      if (plateRowNumMap.get(platei) == null) {
        List<Integer> rowList = new ArrayList<>();
        rowList.add(i);
        plateRowNumMap.put(platei, rowList);
      } else {
        plateRowNumMap.get(platei).add(i);
      }
      if (org.springframework.util.StringUtils.isEmpty(platei)) {
        failedRows.add(i);
        continue;
      }
      String typei = "";
      HSSFCell celli = row.getCell(1);
      if (celli.getCellType() == Cell.CELL_TYPE_NUMERIC) {
        typei = String.valueOf((int) celli.getNumericCellValue());
      } else if(celli.getCellType() == Cell.CELL_TYPE_STRING){
        typei = convertCellValueToString(celli);
      }else{
    	  break;
      }
      if (org.springframework.util.StringUtils.isEmpty(typei)) {
        failedRows.add(i);
        continue;
      }
      
      String model="";
      HSSFCell cellmodel = row.getCell(2);
      if (cellmodel.getCellType() == Cell.CELL_TYPE_NUMERIC) {
    	  model = String.valueOf((int) cellmodel.getNumericCellValue());
      } else if(cellmodel.getCellType() == Cell.CELL_TYPE_STRING){
    	  model = convertCellValueToString(cellmodel);
      }else{
    	  break;
      }
      if (org.springframework.util.StringUtils.isEmpty(model)) {
        failedRows.add(i);
        continue;
      }
      //型号加编码决定轮胎的数量
      Integer tireNum = idAndTireNumMap.get(model.concat(typei));
      if (tireNum == null || tireNum <= 0) {
        failedRows.add(i);
        continue;
      }
      //车辆信息和轮胎数据必填项校验
      for (int j = 0; j < tireNum * 3 + 5; j++) {
        if (org.springframework.util.StringUtils
            .isEmpty(convertCellValueToString(row.getCell(j)))) {
          failedRows.add(i);
          break;
        }
      }
    }

    for (List<Integer> rowNumList : plateRowNumMap.values()) {
      if (rowNumList.size() > 1) {
        failedRows.addAll(rowNumList);
      }
    }
    failedRows = new ArrayList<>(new HashSet(failedRows));
    return failedRows;
  }


  String convertCellValueToString(HSSFCell cell) {
    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
      return String.valueOf((int) cell.getNumericCellValue());
    }
    return cell.getStringCellValue();
  }

  @RequestMapping(value = "/mileages", method = RequestMethod.GET)
  @Permission(permissionTypes = {PermissionEnum.ADMIN})
  public Pager<List<VehicleMileageVo>> listVehicleMileages(
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      @RequestParam(value = "monthStart", required = false) String monthStart,
      @RequestParam(value = "monthEnd", required = false) String monthEnd,
      @RequestParam(value = "plateLike", required = false) String plateLike) {
	  SimpleDateFormat bartDateFormat =  new SimpleDateFormat("yyyy-MM-dd"); 
    Map<String, Object> parameters = new HashMap<>();
    Integer offset = null;
    Integer limit = null;
    if (page == null || page < 1) {
      page = 1;
    }
    if (size == null || size < 1) {
      size = pageDefaultLimit;
    }
    offset = (page - 1) * size;
    limit = size;
    parameters.put("offset", offset);
    parameters.put("limit", limit);
    if(monthStart != null) {
      try {
        //parameters.put("monthStart", new Date(Long.parseLong(monthStart)));
        
        parameters.put("monthStart", bartDateFormat.parse(monthStart));
        
      } catch (Exception e) {
        logger.error("parse date error, [" + monthStart + "]", e);
      }
    }
    if(monthEnd != null) {
      try {
        //parameters.put("monthEnd", new Date(Long.parseLong(monthEnd)));
        parameters.put("monthEnd", bartDateFormat.parse(monthEnd));
      } catch (Exception e) {
        logger.error("parse date error, [" + monthEnd + "]", e);
      }
    }
    if (plateLike != null && plateLike.length() == 7) {
      parameters.put("plate", plateLike);
    } else {
      parameters.put("plateLike", plateLike);
    }
    List<VehicleMileageVo> vehicleMileageVoList = vehicleMileageService
        .getVehicleMileage(parameters);
    Long rowCount = vehicleMileageService.countVehicleMileage(parameters);
    Pagination pagination = new Pagination(page, limit, rowCount.intValue());
    Pager p = new Pager(vehicleMileageVoList, pagination);
    return p;
  }

  @SuppressWarnings("resource")
  @RequestMapping(value = "/mileage/import", method = RequestMethod.POST)
  @Permission(permissionTypes = {PermissionEnum.ADMIN})
  public Result<ImportResponse> importFile(@RequestParam("file") MultipartFile file,
      @CurrentUser UserVo user)
      throws IllegalStateException, IOException, ParseException {
    HSSFSheet sheet = saveFileAndOpenSheet(file);

    List<Integer> failedRows = new ArrayList<Integer>();
    List<Integer> invalidRows = validateMileageSheet(sheet);
    if (invalidRows.size() == 0) {
      int lastRowNum = sheet.getLastRowNum();
      for (int i = 1; i <= lastRowNum; i++) {
        try {
          HSSFRow row = sheet.getRow(i);
          VehicleMileageVo vo = new VehicleMileageVo();
          vo.setPlate(row.getCell(1).getStringCellValue().substring(0, 7));
          String monthStr = row.getCell(2).getStringCellValue();
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
          vo.setMonth(formatter.parse(monthStr));
          vo.setMile(BigDecimal.valueOf(row.getCell(3).getNumericCellValue()));
          vo.setOperatorId(user.getUid());
          //行驶时间
          vo.setPeriod(row.getCell(4).getStringCellValue());
          //平均时速
          vo.setAverageSpeed(ExcelUtils.convertCellValueToString(row.getCell(5)));
          //开始里程
          if(row.getCell(6).getCellType()==Cell.CELL_TYPE_NUMERIC){
        	  vo.setBegMile(String.valueOf(row.getCell(6).getNumericCellValue()));
          }else if(row.getCell(6).getCellType()==Cell.CELL_TYPE_STRING){
        	  vo.setBegMile(row.getCell(6).getStringCellValue());
          }else{
        	  
          }
          //结束里程
          if(row.getCell(7).getCellType()==Cell.CELL_TYPE_NUMERIC){
        	  vo.setEndMile(String.valueOf(row.getCell(7).getNumericCellValue()));
          }else if(row.getCell(7).getCellType()==Cell.CELL_TYPE_STRING){
        	  vo.setEndMile(row.getCell(7).getStringCellValue());
          }else{
        	  
          }
          
          vehicleMileageService.addVehicleMileage(vo);
        } catch (Exception e) {
          failedRows.add(i + 1);
        }
      }
    }

    ImportResponse resp = new ImportResponse();
    resp.setFailedRows(failedRows);
    resp.setInvalidRows(invalidRows);
    if(failedRows.isEmpty() && invalidRows.isEmpty()) {
      return Result.ok(resp);
    }
    return Result.ng(resp);
  }

  private HSSFSheet saveFileAndOpenSheet(MultipartFile file)
      throws IOException {
    File destFile = new File(
        System.getProperty("user.dir") + File.separator + (System.currentTimeMillis() / 1000L) + "-"
            + file.getOriginalFilename());
    file.transferTo(destFile);
    FileInputStream fileInputStream = new FileInputStream(destFile);
    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
    POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
    HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
    return workbook.getSheetAt(0);
  }

  private List<Integer> validateMileageSheet(HSSFSheet sheet) {
    List<Integer> failedRows = new ArrayList<Integer>();
    if (sheet == null) {
      failedRows.add(0);
      return failedRows;
    }
    int lastRowNum = sheet.getLastRowNum();
    if (lastRowNum == 0) {
      failedRows.add(1);
      return failedRows;
    }
    Map<String, List<Integer>> plateRowNumMap = new HashMap<>();

    for (int i = 1; i <= lastRowNum; i++) {
      HSSFRow row = sheet.getRow(i);
      // check plate
      String platei = convertCellValueToString(row.getCell(1));
      if (org.springframework.util.StringUtils.isEmpty(platei) || platei.length() < 7) {
        failedRows.add(i);
        continue;
      }
      platei = platei.substring(0, 7);
      // check month
      String monthi = "";
      HSSFCell monthCelli = row.getCell(2);
      monthi = convertCellValueToString(monthCelli);
      //2017年08月
      if (org.springframework.util.StringUtils.isEmpty(monthi) || !Pattern
          .matches("^[2-9]{1}[0-9]{3}年(0[1-9]|1[1-2])月$", monthi)) {
        failedRows.add(i);
        continue;
      }
      // check mileage
      String mileage = "";
      HSSFCell mileCelli = row.getCell(3);
      mileage = convertCellValueToString(mileCelli);
      if (org.springframework.util.StringUtils.isEmpty(mileage)) {
        failedRows.add(i);
        continue;
      } else {
        try {
          Double milesi = Double.valueOf(mileage);
          if (milesi.compareTo(Double.valueOf(0)) < 0) {
            failedRows.add(i);
            continue;
          }
        } catch (Exception e) {
          failedRows.add(i);
          continue;
        }
      }

      if (plateRowNumMap.get(platei + monthi) == null) {
        List<Integer> rowList = new ArrayList<>();
        rowList.add(i);
        plateRowNumMap.put(platei + monthi, rowList);
      } else {
        plateRowNumMap.get(platei + monthi).add(i);
      }
    }

    for (List<Integer> rowNumList : plateRowNumMap.values()) {
      if (rowNumList.size() > 1) {
        failedRows.addAll(rowNumList);
      }
    }
    failedRows = new ArrayList<>(new HashSet(failedRows));
    return failedRows;
  }
}