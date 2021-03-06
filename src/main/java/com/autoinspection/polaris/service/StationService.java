package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.DistrictEntity;
import com.autoinspection.polaris.model.entity.StationEntity;
import com.autoinspection.polaris.vo.station.AddStationRequest;
import com.autoinspection.polaris.vo.station.GetStationRequest;
import com.autoinspection.polaris.vo.station.UpdateStationRequest;

public interface StationService {
	public StationEntity getStationById(int id);
	public List<StationEntity> listAllStations();
	public List<StationEntity> listStations(GetStationRequest request);
	public int insertStation(AddStationRequest request, int uid);
	public int updateStation(UpdateStationRequest request, int uid);
	public int deleteStation(int id, int uid);
	public List<DistrictEntity> listDistricts();
}
