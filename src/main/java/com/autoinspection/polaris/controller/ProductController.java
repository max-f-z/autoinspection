package com.autoinspection.polaris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autoinspection.polaris.interceptor.Permission;
import com.autoinspection.polaris.interceptor.PermissionEnum;
import com.autoinspection.polaris.model.entity.ProductEntity;
import com.autoinspection.polaris.resolver.CurrentUser;
import com.autoinspection.polaris.service.ProductService;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.utils.ErrorCode;
import com.autoinspection.polaris.vo.UserVo;
import com.autoinspection.polaris.vo.product.AddProductRequest;
import com.autoinspection.polaris.vo.product.AddProductResponse;
import com.autoinspection.polaris.vo.product.DeleteProductRequest;
import com.autoinspection.polaris.vo.product.DeleteProductResponse;
import com.autoinspection.polaris.vo.product.UpdateProductRequest;
import com.autoinspection.polaris.vo.product.UpdateProductResponse;

@RestController
@RequestMapping(value="${api.path}/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/products", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public List<ProductEntity> getProducts() {
		return productService.listAllProducts();
	}
	
	@RequestMapping(value="/products/{id}", method = RequestMethod.GET)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public ProductEntity getProduct(@PathVariable Integer id) throws BizException {
		ProductEntity product = productService.getProductById(id);
		if (product == null) {
			throw new BizException(ErrorCode.NOT_FOUND);
		}
		return product;
	}
	
	@RequestMapping(value="/products/add", method = RequestMethod.POST) 
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public AddProductResponse insertProduct(@RequestBody AddProductRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		
		int id = productService.insertProduct(request, user.getUid());
		AddProductResponse resp = new AddProductResponse();
		if (id != 0) {
			resp.setId(id);
		}
		return resp;
	}
	
	@RequestMapping(value="/products/update", method = RequestMethod.PUT)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public UpdateProductResponse updateProduct(@RequestBody UpdateProductRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = productService.updateProduct(request, user.getUid());
		UpdateProductResponse resp = new UpdateProductResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
	
	@RequestMapping(value="/products/delete", method = RequestMethod.DELETE)
	@Permission( permissionTypes = { PermissionEnum.ADMIN })
	public DeleteProductResponse deleteProduct(@RequestBody DeleteProductRequest request, @CurrentUser UserVo user) throws BizException {
		if (request == null) {
			throw new BizException(ErrorCode.INVALID_PARAM);
		}
		int rows = productService.deleteProduct(request.getId(), user.getUid());
		DeleteProductResponse resp = new DeleteProductResponse();
		resp.setAffectedRows(rows);
		return resp;
	}
}
