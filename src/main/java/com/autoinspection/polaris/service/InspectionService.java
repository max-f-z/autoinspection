package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.InspectionEntity;
import com.autoinspection.polaris.vo.Inspection.AddInspectionRequest;
import com.autoinspection.polaris.vo.Inspection.DeleteInspectionRequest;
import com.autoinspection.polaris.vo.Inspection.InspectionVo;
import com.autoinspection.polaris.vo.Inspection.UpdateInspectionRequest;

public interface InspectionService {
	public List<InspectionVo> listInspections(int skip, int pageSize, String plate);
	public InspectionEntity getById(long id);
	public long insertInspection(AddInspectionRequest request, int uid);
	public int updateInspection(UpdateInspectionRequest request, int uid);
	public int deletInspection(DeleteInspectionRequest request);
	public List<InspectionEntity> search(int skip, int pageSize, String search);
}
