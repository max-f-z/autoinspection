package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.MaintenanceEntity;
import com.autoinspection.polaris.model.entity.OrderPayEntity;
import com.autoinspection.polaris.vo.payment.PaymentSearchRequest;

@Mapper
public interface MaintenanceMapper {
	int insertMaintenance(@Param("en") MaintenanceEntity entity, @Param("operatorId") Integer operatorId);
	int updateMaintenance(@Param("en") MaintenanceEntity entity, @Param("operatorId") Integer operatorId);
	MaintenanceEntity getByInspectionId(@Param("inspectionId") Long inspectionId);
	List<OrderPayEntity> search(@Param("en") PaymentSearchRequest entity, @Param("skip") Integer skip, @Param("pageSize") Integer pageSize);
	int count(@Param("en") PaymentSearchRequest entity);
	int updatePayStatusById(@Param("payStatus") Integer payStatus, @Param("operatorId") Integer operatorId, @Param("id") Long id);
	OrderPayEntity getOrderById(@Param("id") Long id);
}
