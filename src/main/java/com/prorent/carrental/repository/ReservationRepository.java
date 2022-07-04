package com.prorent.carrental.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prorent.carrental.domain.Reservation;
import com.prorent.carrental.domain.enumaration.ReservationStatus;



@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("SELECT r FROM Reservation r "+
	         "INNER JOIN Car cd on r.carId.id=cd.id WHERE "+
				"(cd.id=:carId and r.status<>:done and r.status<>:cancel and :pickUpTime BETWEEN"+ 
	         " r.pickUpTime and r.dropOffTime) or "
	         + "(cd.id=:carId and r.status<>:done and r.status<>:cancel and :dropOffTime BETWEEN"+ 
	         " r.pickUpTime and r.dropOffTime)"
				
				)
		
		List<Reservation> checkStatus(@Param("carId") Long carId,@Param("pickUpTime") LocalDateTime pickUpTime, 
				@Param("dropOffTime") LocalDateTime dropOffTime,
				@Param ("done") ReservationStatus done, @Param ("cancel")ReservationStatus cancelled);
	}
	

