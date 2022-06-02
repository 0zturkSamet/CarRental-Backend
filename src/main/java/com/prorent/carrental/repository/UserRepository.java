package com.prorent.carrental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prorent.carrental.domain.User;
import com.prorent.carrental.exception.ConflictException;
import com.prorent.carrental.exception.ResourceNotFoundException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//find user with email 
	Optional<User>findByEmail(String email) throws ResourceNotFoundException;
	
	Boolean existsByEmail(String email) throws ConflictException;
}
