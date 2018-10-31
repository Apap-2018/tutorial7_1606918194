package com.apap.tutorial7.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

/**
 * 
 * @author saffana.dira
 * CarController
 *
 */

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@PostMapping
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}
	
	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable ("carId") long carId, Model model) {
		CarModel car = carService.getCarDetailById(carId).get();
		car.setDealer(null);
		
		return car;
	}
	
	@DeleteMapping
	private String deleteCar(@RequestParam("carId") long id, Model model) {
		CarModel car = carService.getCarDetailById(id).get();
		carService.deleteCar(car);
		
		return "Success";
	}
	
	@PutMapping(value = "/{id}")
	private String updateDealerSubmit(
			@PathVariable (value = "id") long id,
			@RequestParam("brand") Optional<String> brand,
			@RequestParam("type") Optional<String> type,
			@RequestParam("price") Optional<Long> price,
			@RequestParam("amount") Optional<Integer> amount,
			@RequestParam("dealerId") Optional<Long> dealerId) {
		CarModel car = (CarModel) carService.getCarDetailById(id).get();
		
		if (car.equals(null)) {
			return "Couldn't find your car";
		}
		
		if (brand.isPresent()) {
			car.setBrand(brand.get());
		}
		
		if (type.isPresent()) {
			car.setType(type.get());
		}
		
		if (price.isPresent()) {
			car.setPrice(price.get());
		}
		
		if (amount.isPresent()) {
			car.setAmount(amount.get());
		}
		
		if (dealerId.isPresent()) {
			DealerModel dealerModel = dealerService.getDealerDetailById(dealerId.get()).get();
			car.setDealer(dealerModel);
		}
		
		carService.addCar(car);
		
		return "update success";
	}
	
	@GetMapping()
	private List<CarModel> viewAllCar(Model model) {
		List<CarModel> listCar = carService.allCars();
		for (CarModel car : listCar) {
			car.setDealer(null);
		}
		
		return listCar;
	}
}