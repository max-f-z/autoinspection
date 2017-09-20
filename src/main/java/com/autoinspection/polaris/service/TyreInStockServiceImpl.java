package com.autoinspection.polaris.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoinspection.polaris.model.entity.TyreInstockEntity;
import com.autoinspection.polaris.model.mapper.TyreInStockMapper;
import com.autoinspection.polaris.utils.BizException;
import com.autoinspection.polaris.model.mapper.InspectionDetailMapper;
import com.autoinspection.polaris.model.entity.InspectionDetailEntity;

@Service
public class TyreInStockServiceImpl implements TyreInStockService {
	
	
	@Autowired
	private TyreInStockMapper tyreInStockMapper;
	
	@Autowired
	private InspectionDetailMapper inspectionDetailMapper;

	@Override
	public int insertTyreInstock(TyreInstockEntity entity,int uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTyreInstock(TyreInstockEntity entity,int uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTyreInstock(TyreInstockEntity entity) {
		
		return this.tyreInStockMapper.deleteTyreInStock(entity.getId());
	}

	@Override
	public List<TyreInstockEntity> getTyreInstock(TyreInstockEntity entity) {
		
		return this.tyreInStockMapper.getTyreInstock(entity);
	}

	@Override
	public List<TyreInstockEntity> getSearch(TyreInstockEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional( rollbackFor=Exception.class )
	public int insertBatchTyreInstock(List<TyreInstockEntity> list, int operatorId) throws BizException{
		
		if(null != list && list.size()>0){
			int i = 0;
			for(TyreInstockEntity item: list){
				//查看轮胎是否已经被是否
				InspectionDetailEntity entity = new InspectionDetailEntity();
				entity.setTireBrand(item.getTyreBrand());
				entity.setTireId(item.getBarCode());
				entity.setTireType(item.getTyreType());
				List<InspectionDetailEntity> result = this.inspectionDetailMapper.findTyre(entity);
				if(result != null && result.size()>0){
					item.setUsed("1");
				}else{
					item.setUsed("0");
				}
				//
				i = i + this.tyreInStockMapper.insertTyreInStock(item, operatorId);
				
				
			}
			return i;
		}
		return 0;
	}

	@Override
	public List<TyreInstockEntity> getList(Integer skip, Integer pageSize, TyreInstockEntity entity) {
		
		return this.tyreInStockMapper.getList(skip, pageSize, entity);
		

	}

	@Override
	public int getCount(TyreInstockEntity entity) {
		return this.tyreInStockMapper.getCount(entity);
	}

	@Override
	public int updateStockStatus(TyreInstockEntity entity, Integer operatorId) {
		
		return this.updateStockStatus(entity, operatorId);
	}
	
	

}
