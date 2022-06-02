package com.prorent.carrental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prorent.carrental.domain.Role;
import com.prorent.carrental.domain.enumaration.UserRole;

@Repository
public interface  RoleRepository extends JpaRepository<Role,Integer > {
	
	 Optional<Role> findByName(UserRole name);
		
}
