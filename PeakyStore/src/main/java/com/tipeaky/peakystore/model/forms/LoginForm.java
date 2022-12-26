package com.tipeaky.peakystore.model.forms;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
public class LoginForm {

	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Length(min = 6, max = 50)
	private String password;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}

}
