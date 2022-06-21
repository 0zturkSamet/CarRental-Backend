package com.prorent.carrental.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prorent.carrental.domain.Car;
import com.prorent.carrental.domain.ImageFile;
import com.prorent.carrental.exception.BadRequestException;
import com.prorent.carrental.exception.ResourceNotFoundException;
import com.prorent.carrental.repository.CarRepository;
import com.prorent.carrental.repository.ImageFileRepository;
import com.prorent.carrental.service.dto.CarDTO;

@Service
@Transactional
public class CarService {

	@Autowired
	 ImageFileRepository imageFileRepository;
	
	@Autowired
	CarRepository carRepository;
	
	//Car Add
	public void addCar(Car car, String imageId) throws BadRequestException{
		
		ImageFile imageFile = imageFileRepository.findById(imageId).orElseThrow(()->
		new ResourceNotFoundException("Car Not Found id: "+imageId));
		Set<ImageFile> imageFiles = new HashSet<>();
		imageFiles.add(imageFile);
		car.setImage(imageFiles);
		car.setBuiltIn(false);
		carRepository.save(car);
	}
	
	//Get All Cars
	public List<CarDTO> getAllCars(){
		return carRepository.findAllCar();
	}
	
	//Get All Cars By Id
	
	public CarDTO findById(Long id) throws ResourceNotFoundException{
		return carRepository.findCarByCId(id).orElseThrow(()-> new ResourceNotFoundException("Car Not Found id: "+id));
		
	}
	
	
}
