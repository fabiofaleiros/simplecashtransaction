package com.ffs.simplecashtransaction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffs.simplecashtransaction.config.security.TokenService;
import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.dtos.AuthenticationDTO;
import com.ffs.simplecashtransaction.dtos.LoginResponseDTO;
import com.ffs.simplecashtransaction.dtos.RegisterDTO;
import com.ffs.simplecashtransaction.services.AuthencationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AuthencationService authencationService;

	@Autowired
	TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

		UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(
				authenticationDTO.login(), authenticationDTO.password());
		Authentication auth = this.authenticationManager.authenticate(userNamePassword);

		String token = tokenService.generateToken((User) auth.getPrincipal());

		return ResponseEntity.ok(new LoginResponseDTO(token));

	}

	@PostMapping("/register")
	public ResponseEntity<RegisterDTO> login(@RequestBody @Valid RegisterDTO registerDTO) {

		User user = authencationService.findUserByEmail(registerDTO.login());

		String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
		user.setPassword(encryptedPassword);
		user.setRole(registerDTO.role());

		authencationService.save(user);

		return ResponseEntity.ok().build();

	}

}
