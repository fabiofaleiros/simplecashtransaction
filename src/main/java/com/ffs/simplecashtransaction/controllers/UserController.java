package com.ffs.simplecashtransaction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.dtos.UserDTO;
import com.ffs.simplecashtransaction.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
		
		User newUser = userService.createUser(userDTO);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<> (userService.getAllUsers(), HttpStatus.OK) ;
	}

}
