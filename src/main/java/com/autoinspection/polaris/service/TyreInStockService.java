package com.autoinspection.polaris.service;

import java.util.List;


import com.autoinspection.polaris.model.entity.TyreInstockEntity;
import com.autoinspection.polaris.utils.BizException;

public interface TyreInStockService {
	
	public int insertTyreInstock(TyreInstockEntity entity,int uid);
	
	public int updateTyreInstock(TyreInstockEntity entity,int uid);
	
	public int deleteTyreInstock(TyreInstockEntity entity);
	
	public List<TyreInstockEntity> getTyreInstock(TyreInstockEntity entity);
	
	public List<TyreInstockEntity> getSearch( TyreInstockEntity entity);
	
	public int insertBatchTyreInstock(List<TyreInstockEntity> list, int uid)  throws BizException;
	
	public List<TyreInstockEntity> getList(Integer skip,Integer pageSize,TyreInstockEntity entity);
	
	public int getCount(TyreInstockEntity entity);
	
	public int updateStockStatus(TyreInstockEntity entity,Integer operatorId);

}
