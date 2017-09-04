package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.DistrictEntity;
import com.autoinspection.polaris.model.entity.StationEntity;
import com.autoinspection.polaris.model.mapper.DistrictMapper;
import com.autoinspection.polaris.model.mapper.StationMapper;
import com.autoinspection.polaris.vo.station.AddStationRequest;
import com.autoinspection.polaris.vo.station.GetStationRequest;
import com.autoinspection.polaris.vo.station.UpdateStationRequest;
import com.mysql.jdbc.StringUtils;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private StationMapper stationMapper;
	
	@Autowired
	private DistrictMapper districtMapper;
	
	@Override
	public StationEntity getStationById(int id) {
		return stationMapper.getById(id);
	}
	
	@Override
	public List<StationEntity> listStations(GetStationRequest request) {
		if (StringUtils.isNullOrEmpty(request.getDistrict())) {
			request.setDistrict(null);
		}
		if (StringUtils.isNullOrEmpty(request.getSearch())) {
			request.setSearch(null);
		}
		
		return stationMapper.listStations(request.getDistrict(), request.getSearch());
	}
	
	@Override
	public List<StationEntity> listAllStations() {
		return stationMapper.listAllStations();
	}

	@Override
	public int insertStation(AddStationRequest request, int uid) {
		StationEntity entity = new StationEntity();
		entity.setAddress(request.getAddress());
		entity.setDescription(request.getDescription());
		entity.setLatitude(request.getLatitude());
		entity.setLongitude(request.getLongitude());
		entity.setName(request.getName());
		entity.setPhone(request.getPhone());
		entity.setPrincipal(request.getPrincipal());
		entity.setPrincipalPhone(request.getPrincipalPhone());
		entity.setDistrict(request.getDistrict());
		entity.setEnable(true);
		entity.setOperatorId(uid);
		stationMapper.insertStation(entity);
		
		return entity.getId();
	}

	@Override
	public int updateStation(UpdateStationRequest request, int uid) {
		StationEntity entity = new StationEntity();
		entity.setId(request.getId());
		entity.setName(request.getName());
		entity.setAddress(request.getAddress());
		entity.setLatitude(request.getLatitude());
		entity.setLongitude(request.getLongitude());
		entity.setDescription(request.getDescription());
		entity.setPhone(request.getPhone());
		entity.setPrincipal(request.getPrincipal());
		entity.setPrincipalPhone(request.getPrincipalPhone());
		entity.setOperatorId(uid);
		entity.setDistrict(request.getDistrict());
			
		return stationMapper.updateStation(entity);
	}

	@Override
	public int deleteStation(int id, int uid) {
		return stationMapper.deleteStation(id, uid);
	}

	@Override
	public List<DistrictEntity> listDistricts() {
		return districtMapper.listDistricts();
	}

}
