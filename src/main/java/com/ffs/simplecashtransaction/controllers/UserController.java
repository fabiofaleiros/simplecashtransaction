package com.ffs.simplecashtransaction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.dtos.UserRequestDTO;
import com.ffs.simplecashtransaction.dtos.UserResponseDTO;
import com.ffs.simplecashtransaction.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO){
		
		return new ResponseEntity<>(UserMapper.fromEntityToResponseDTO(userService.createUser(userDTO)), HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
		
		List<User> allUsers = userService.getAllUsers();
		
		if (allUsers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<> (allUsers.stream().map(UserMapper::fromEntityToResponseDTO).toList(), HttpStatus.OK) ;
	}
	
	@PutMapping("{id}")
	public ResponseEntity<UserResponseDTO> editUser(@PathVariable("id") Long id, @RequestBody UserRequestDTO userDTO) throws Exception{
		
		return new ResponseEntity<>(UserMapper.fromEntityToResponseDTO(userService.editUser(id, userDTO)), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Long> deleteUser(@PathVariable("id") Long id) throws Exception{
		
		userService.deleteUser(id);
		
		return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
		
	}

}
