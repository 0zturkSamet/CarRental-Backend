package com.prorent.carrental.controller.vm;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVM {
	@Email(message="Please provide a valid email")
	@NotNull(message="Please provide your email")
	@Size(min=5,max=100,message="Email '${validatedValue}' must be between {min} and {max} chracters long")
	private String email;
	
	@NotNull(message="Please provide your password")
	@NotBlank(message="Please provide your password")
	@Size(min=4,max=60,message="Password '${validatedValue}' must be between {min} and {max} chracters long")
	private String password;

	@Override
	public String toString() {
		return "LoginVM [email=" + email + ", password=" + password + "]";
	}
}
