package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.repository.CarDb;

/**
 * 
 * @author saffana.dira
 * CarServiceImpl
 *
 */

@Service
@Transactional
public class CarServiceImp implements CarService {
	@Autowired
	private CarDb carDb;
	
	@Override
	public Optional<CarModel> getCarDetailById(long id) {
		return carDb.findById(id);
	}
	
	@Override
	public CarModel addCar(CarModel car) {
		carDb.save(car);
		return car;
	}
	
	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}
	
	@Override
	public List<CarModel> allCars() {
		return carDb.findAll();
	}
}
