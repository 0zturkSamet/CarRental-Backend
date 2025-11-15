package com.prorent.carrental.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prorent.carrental.domain.enumaration.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_reservation")
@Entity
public class Reservation implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="car_id",referencedColumnName = "id")
	private Car carId;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User userId;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy HH:mm:ss", timezone="Turkey")
	@NotNull(message="Please Provide pick up time for the reservation")
	@Column(nullable=false)
	private LocalDateTime pickUpTime;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy HH:mm:ss", timezone="Turkey")
	@NotNull(message="Please Provide drop off time for the reservation")
	@Column(nullable=false)
	private LocalDateTime dropOffTime;
	
	@NotNull(message="Please Provide a pickup location for the reservation")
	@Column(length=100,nullable=false)
	private String pickUpLocation;
	
	@NotNull(message="Please Provide a drof off location for the reservation")
	@Column(length=100,nullable=false)
	private String dropOffLocation;
	
	@Enumerated(EnumType.STRING)
	@Column(length=20,nullable=false)
	private ReservationStatus status;
	
	@Column(nullable=false)
	private Double totalPrice;
	
	public Long getTotalHours(LocalDateTime pickUpTime, LocalDateTime dropOffTime) {
		Long hours=ChronoUnit.HOURS.between(pickUpTime,dropOffTime);
		return hours;
	} 
	
}
