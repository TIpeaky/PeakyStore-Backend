package com.tipeaky.peakystore.controllers;

import com.tipeaky.peakystore.config.security.TokenService;
import com.tipeaky.peakystore.exceptions.CustomAuthenticationException;
import com.tipeaky.peakystore.model.dtos.TokenDTO;
import com.tipeaky.peakystore.model.entities.Role;
import com.tipeaky.peakystore.model.entities.User;
import com.tipeaky.peakystore.model.forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
//@Profile(value = {"prod", "test"})
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			User user = (User)authentication.getPrincipal();
			UUID id = user.getId();
			String email = user.getEmail();
			String name = user.getName();
			List<Role> roles = user.getRoles();

			return ResponseEntity.ok(new TokenDTO(token, "Bearer", id, email, name, roles));
		} catch (AuthenticationException e) {
			throw new CustomAuthenticationException(e.getMessage());
		}
	}
}
