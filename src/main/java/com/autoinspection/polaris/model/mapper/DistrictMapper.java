package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.autoinspection.polaris.model.entity.DistrictEntity;

@Mapper
public interface DistrictMapper {
	public List<DistrictEntity> listDistricts();
}
