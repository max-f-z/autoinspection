package com.autoinspection.polaris.model.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.TyreInstockEntity;

@Mapper
public interface TyreInStockMapper {
	
	int insertTyreInStock(@Param("en") TyreInstockEntity entity, @Param("operatorId") Integer operatorId);
	
	List<TyreInstockEntity> search(@Param("skip") Integer skip, @Param("pageSize") Integer pageSize, @Param("search") String search);
	
	List<TyreInstockEntity> getTyreInstock(@Param("en") TyreInstockEntity entity);
	
	int deleteTyreInStock(@Param("id") BigInteger id);
	
	int updateTyreInStock(@Param("en") TyreInstockEntity entity, @Param("operatorId") Integer operatorId);
	
	List<TyreInstockEntity> getList(@Param("skip") Integer skip, @Param("pageSize") Integer pageSize, @Param("en") TyreInstockEntity entity);
	
	int getCount(@Param("en") TyreInstockEntity entity);
	
	int updateStockStatus(@Param("en") TyreInstockEntity entity, @Param("operatorId") Integer operatorId);
	
	

}
