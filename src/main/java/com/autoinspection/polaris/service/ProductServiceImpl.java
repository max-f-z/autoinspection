package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.ProductEntity;
import com.autoinspection.polaris.model.mapper.ProductMapper;
import com.autoinspection.polaris.vo.product.AddProductRequest;
import com.autoinspection.polaris.vo.product.UpdateProductRequest;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<ProductEntity> listAllProducts() {
		return productMapper.listAllProducts();
	}

	@Override
	public ProductEntity getProductById(int id) {
		return productMapper.getById(id);
	}

	@Override
	public int insertProduct(AddProductRequest request, int uid) {
		ProductEntity entity = new ProductEntity();
		entity.setProductCode(request.getProductCode());
		entity.setProductDesc(request.getProductDesc());
		entity.setProductType(request.getProductType());
		entity.setOperatorId(uid);
		entity.setEnable(true);
		productMapper.insertProduct(entity);
		
		return entity.getId();
	}

	@Override
	public int updateProduct(UpdateProductRequest request, int uid) {
		ProductEntity entity = new ProductEntity();
		entity.setId(request.getId());
		entity.setProductCode(request.getProductCode());
		entity.setProductDesc(request.getProductDesc());
		entity.setProductType(request.getProductType());
		entity.setOperatorId(uid);
		entity.setEnable(true);
		
		return productMapper.updateProduct(entity);
	}

	@Override
	public int deleteProduct(int id, int uid) {
		return productMapper.deleteProduct(id, uid);
	}

}
