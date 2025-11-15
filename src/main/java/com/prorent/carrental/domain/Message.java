package com.prorent.carrental.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_message")
public class Message implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Please provide not blank name")
	@NotNull(message="Please provide your name")
	@Size(min=1,max=15,message="Your name '${validatedValue}' must be between {min} and {max} chracters long")
	@Column(length=15,nullable=false)
	private String name;	
	
	@NotBlank(message="Please provide not blank subject")
	@NotNull(message="Please provide your subject")
	@Size(min=5,max=15,message="Your subject '${validatedValue}' must be between {min} and {max} chracters long")
	@Column(length=15,nullable=false)
	private String subject;
	
	
	
	@NotBlank(message="Please provide not blank body")
	@NotNull(message="Please provide your body")
	@Size(min=20,max=200,message="Your subject '${validatedValue}' must be between {min} and {max} chracters long")
	@Column(length=200,nullable=false)
	private String body;
	

	@NotNull(message="Please provide your email")
	@Size(min=6,max=200,message="Your subject '${validatedValue}' must be between {min} and {max} chracters long")
	@Email
	@Column(length=50,nullable=false)
	private String email;
	
	
}
