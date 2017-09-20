package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.TirePriceEntity;

@Mapper
public interface TirePriceMapper {
	TirePriceEntity getById(@Param("id") Integer id);
	List<TirePriceEntity> listTirePrices();
	List<TirePriceEntity> findByTireBrand(@Param("brand") String brand, @Param("search") String search);
	int insertTirePrice(TirePriceEntity entity);
	int updateTirePrice(TirePriceEntity entity);
	int deleteTirePrice(@Param("id") Integer id, @Param("operatorId") Integer operatorId);
}
