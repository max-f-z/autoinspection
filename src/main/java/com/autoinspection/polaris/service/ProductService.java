package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.ProductEntity;
import com.autoinspection.polaris.vo.product.AddProductRequest;
import com.autoinspection.polaris.vo.product.UpdateProductRequest;

public interface ProductService {
	public List<ProductEntity> listAllProducts();
	public ProductEntity getProductById(int id);
	public int insertProduct(AddProductRequest request, int uid);
	public int updateProduct(UpdateProductRequest request, int uid);
	public int deleteProduct(int id, int uid);
}
