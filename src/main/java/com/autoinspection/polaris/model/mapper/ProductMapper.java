package com.autoinspection.polaris.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.autoinspection.polaris.model.entity.ProductEntity;

@Mapper
public interface ProductMapper {
	ProductEntity getById(@Param("id") Integer id);
	List<ProductEntity> listAllProducts();
	int insertProduct(ProductEntity entity);
	int updateProduct(ProductEntity entity);
	int deleteProduct(@Param("id") Integer id, @Param("operatorId") Integer operatorId);
}
