package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;

/**
 * 
 * @author saffana.dira
 * CarService
 *
 */
public interface CarService {
	Optional<CarModel> getCarDetailById(long id);
	
	CarModel addCar(CarModel car);
	void deleteCar(CarModel car);
	List<CarModel> allCars();
}