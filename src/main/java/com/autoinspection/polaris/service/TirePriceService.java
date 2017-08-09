package com.autoinspection.polaris.service;

import java.util.List;

import com.autoinspection.polaris.model.entity.TirePriceEntity;
import com.autoinspection.polaris.vo.tireprice.AddTirePriceRequest;
import com.autoinspection.polaris.vo.tireprice.UpdateTirePriceRequest;

public interface TirePriceService {
	public TirePriceEntity getTirePriceById(int id);
	public List<TirePriceEntity> listTirePrices();
	public List<TirePriceEntity> findByBrand(String brand);
	public int insertTirePrice(AddTirePriceRequest request, int uid);
	public int updateTirePrice(UpdateTirePriceRequest request, int uid);
	public int deleteTirePrice(int id, int uid);
}
