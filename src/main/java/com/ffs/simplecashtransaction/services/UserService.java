package com.ffs.simplecashtransaction.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.domain.user.UserType;
import com.ffs.simplecashtransaction.dtos.UserDTO;
import com.ffs.simplecashtransaction.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception {
		if(UserType.ENTERPRISE.equals(sender.getUserType())) {
			throw new Exception("User not alowed.");
		}
		
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Insuficient funds.");
		}
	}
	
	public User findUserById(Long id) throws Exception {
		return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
	}
	
	public User createUser(UserDTO userDTO) {
		User newUser = new User(userDTO);
		saveUser(newUser);
		return newUser;
		
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}

}
