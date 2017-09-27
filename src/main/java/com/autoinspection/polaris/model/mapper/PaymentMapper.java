package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.autoinspection.polaris.model.entity.PaymentEntity;

@Mapper
public interface PaymentMapper {
	public int insertPayment(@Param("en") PaymentEntity en);
	public int updatePayment(@Param("payStatus") Integer payStatus, @Param("id") Long id);
	public List<PaymentEntity> getByOrderId(@Param("orderId") Long orderId);
	public PaymentEntity getByFlowNo(@Param("flowNo") String flowNo);
}
