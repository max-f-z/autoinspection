package com.autoinspection.polaris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoinspection.polaris.model.entity.TirePriceEntity;
import com.autoinspection.polaris.model.mapper.TirePriceMapper;
import com.autoinspection.polaris.vo.tireprice.AddTirePriceRequest;
import com.autoinspection.polaris.vo.tireprice.UpdateTirePriceRequest;

@Service
public class TirePriceServiceImpl implements TirePriceService {
	
	@Autowired
	private TirePriceMapper tirePriceMapper;

	@Override
	public TirePriceEntity getTirePriceById(int id) {
		return tirePriceMapper.getById(id);
	}

	@Override
	public List<TirePriceEntity> listTirePrices() {
		return tirePriceMapper.listTirePrices();
	}

	@Override
	public List<TirePriceEntity> findByBrand(String brand) {
		return tirePriceMapper.findByTireBrand(brand);
	}

	@Override
	public int insertTirePrice(AddTirePriceRequest request, int uid) {
		TirePriceEntity entity = new TirePriceEntity();
		entity.setBrand(request.getBrand());
		entity.setDescription(request.getDescription());
		entity.setPrice(request.getPrice());
		entity.setStripe(request.getStripe());
		entity.setEnable(true);
		entity.setOperatorId(uid);
		tirePriceMapper.insertTirePrice(entity);
		
		return entity.getId();
	}

	@Override
	public int updateTirePrice(UpdateTirePriceRequest request, int uid) {
		TirePriceEntity entity = new TirePriceEntity();
		entity.setId(request.getId());
		entity.setBrand(request.getBrand());
		entity.setDescription(request.getDescription());
		entity.setPrice(request.getPrice());
		entity.setStripe(request.getStripe());
		entity.setOperatorId(uid);
		
		return tirePriceMapper.updateTirePrice(entity);
	}

	@Override
	public int deleteTirePrice(int id, int uid) {
		return tirePriceMapper.deleteTirePrice(id, uid);
	}

}
