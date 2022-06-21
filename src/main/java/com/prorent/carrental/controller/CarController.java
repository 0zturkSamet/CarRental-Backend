package com.prorent.carrental.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prorent.carrental.domain.Car;
import com.prorent.carrental.service.CarService;
import com.prorent.carrental.service.dto.CarDTO;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;
	
	
	@PostMapping("/admin/{id}/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String, Boolean>> addCar(@PathVariable String id, @Valid @RequestBody Car car){
		carService.addCar(car, id);
		Map<String, Boolean> map = new HashMap<>();
		map.put("Car saved success", true);
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	
	//Everyone sees the cars
	@GetMapping("/visitors/all")
	public ResponseEntity<List<CarDTO>> getAllCars(){
		List<CarDTO> allCars = carService.getAllCars();
		return ResponseEntity.ok(allCars);
	}
	
	@GetMapping("/visitors/{id}")
	public ResponseEntity<CarDTO> getCarById(@PathVariable Long id){
		CarDTO car = carService.findById(id);
		return ResponseEntity.ok(car);
	}
	
}
