package com.prorent.carrental.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prorent.carrental.domain.Car;
import com.prorent.carrental.domain.Reservation;
import com.prorent.carrental.domain.User;
import com.prorent.carrental.domain.enumaration.ReservationStatus;
import com.prorent.carrental.exception.BadRequestException;
import com.prorent.carrental.exception.ReservationTimeException;
import com.prorent.carrental.exception.ResourceNotFoundException;
import com.prorent.carrental.repository.CarRepository;
import com.prorent.carrental.repository.ReservationRepository;
import com.prorent.carrental.repository.UserRepository;

@Service
@Transactional
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CarRepository carRepository;

	public void addReservation(Reservation reservation, Long id, Long carId) throws BadRequestException {

		// :TODO picupTime will be checked is berfore than now

		LocalDateTime now = LocalDateTime.now();
		if (reservation.getPickUpTime().isBefore(now)) {
			throw new ReservationTimeException("Pickup Time or DropOff Time is not correct");
		}

		boolean isEqual = reservation.getPickUpTime().isEqual(reservation.getDropOffTime()) ? true : false;
		boolean isBefore = reservation.getPickUpTime().isBefore(reservation.getDropOffTime()) ? true : false;

		if (isEqual || !isBefore) {
			throw new ReservationTimeException("Pickup Time or DropOff Time is not correct");
		}

		Car car = carRepository.findById(carId)
				.orElseThrow(() -> new ResourceNotFoundException("Car not found:id" + id));

		boolean checkCarIsNotAvailable = checkCarIsAvailable(car.getId(), reservation.getPickUpTime(),
				reservation.getDropOffTime());

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found id:" + id));

		if (!checkCarIsNotAvailable) {
			reservation.setStatus(ReservationStatus.CREATED);
		} else {
			throw new BadRequestException("Car is already reserved");
		}

		reservation.setCarId(car);
		reservation.setUserId(user);

		Double totalPrice = calcTotalPrice(reservation.getPickUpTime(), reservation.getDropOffTime(), carId);
		reservation.setTotalPrice(totalPrice);

		reservationRepository.save(reservation);
	}
	public boolean checkCarIsAvailable(Long carId, 
			LocalDateTime pickUpTime, LocalDateTime dropOffTime) {
		List<Reservation> reservations = reservationRepository.checkStatus(carId, pickUpTime, dropOffTime, ReservationStatus.DONE, 
				ReservationStatus.CANCELLED);
		return reservations.size()>0;
	}
	public Double calcTotalPrice(LocalDateTime pickUpTime, LocalDateTime dropOffTime, Long carId ) {
		Car car = carRepository.findById(carId).
				orElseThrow(()->new ResourceNotFoundException("Car not found id:"+carId));
		
		Long totalHours = (new Reservation()).getTotalHours(pickUpTime, dropOffTime);
		return car.getPricePerHour()*totalHours;
	}
}
