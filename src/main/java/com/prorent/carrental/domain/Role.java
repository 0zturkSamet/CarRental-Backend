package com.prorent.carrental.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.prorent.carrental.domain.enumaration.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;


	@Enumerated(EnumType.STRING)
	@Column(length=30,nullable = false)
	private UserRole name;
		

	@Override
		public String toString() {
			return "["+name+"]";
		}
}
