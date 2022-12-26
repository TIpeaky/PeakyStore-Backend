package com.tipeaky.peakystore.model.dtos;

import com.tipeaky.peakystore.model.entities.Role;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@ToString
public class TokenDTO {

	private final String token;
	private final String tipo;

	private final UUID id;

	private final String email;

	private final String name;

	private final List<Role> roles;

	public TokenDTO(String token, String tipo, UUID id, String email, String name, List<Role> roles) {
		this.token = token;
		this.tipo = tipo;
		this.id = id;
		this.email = email;
		this.name = name;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	public UUID getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public List<Role> getRoles() {
		return roles;
	}
}
