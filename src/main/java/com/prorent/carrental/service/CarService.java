package com.prorent.carrental.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.prorent.carrental.domain.Car;
import com.prorent.carrental.domain.ImageFile;
import com.prorent.carrental.exception.BadRequestException;
import com.prorent.carrental.exception.ResourceNotFoundException;
import com.prorent.carrental.helper.PaginationUtil;
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
	
	public void updateCar(Long id,Car car, String imageId) throws BadRequestException{
		car.setId(id);

		ImageFile imageFile=imageFileRepository.findById(imageId).
				orElseThrow(()->new ResourceNotFoundException("Image not found:"+imageId));
		
		Car existCar = carRepository.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Car not found with id:"+id));
		
		if(existCar.getBuiltIn()) {
			throw new BadRequestException("Yo dont have permission to update this car:"+id);
		}
		
		car.setBuiltIn(false);
		
		Set<ImageFile> imageFiles=new HashSet<>();
		imageFiles.add(imageFile);
		car.setImage(imageFiles);
		
		carRepository.save(car);
	}
	public void removeById(Long id) throws ResourceNotFoundException{
		Car car = carRepository.findById(id).orElseThrow(()->
			new ResourceNotFoundException("Car not found id:"+id));
		
		if(car.getBuiltIn()) {
			throw new BadRequestException("Yo dont have permission to update this car:"+id);
		}
		
		
		carRepository.deleteById(id);
	}
	public Page<CarDTO> getCarPage(Pageable pageable){
		return carRepository.findCarPage(pageable);
	}
	
	
	
}
