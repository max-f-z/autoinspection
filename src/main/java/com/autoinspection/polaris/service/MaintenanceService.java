package com.autoinspection.polaris.service;

import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.vo.Inspection.AddMaintenanceRequest;
import com.autoinspection.polaris.vo.Inspection.MaintenanceVo;
import com.autoinspection.polaris.vo.Inspection.UpdateMaintenanceRequest;

public interface MaintenanceService {
	public MaintenanceVo getMaintenance(@Param("inspectionId") long inspectionId);
	public long insertMaintenance(AddMaintenanceRequest request, int uid);
	public int updateMaintenance(UpdateMaintenanceRequest request, int uid);
}
