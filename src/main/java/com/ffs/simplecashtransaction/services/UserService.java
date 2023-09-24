package com.ffs.simplecashtransaction.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffs.simplecashtransaction.controllers.UserMapper;
import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.domain.user.UserType;
import com.ffs.simplecashtransaction.dtos.UserRequestDTO;
import com.ffs.simplecashtransaction.exception.BusinessRuleViolationException;
import com.ffs.simplecashtransaction.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void validateTransaction(User sender, BigDecimal amount) {
		if(UserType.ENTERPRISE.equals(sender.getUserType())) {
			throw new BusinessRuleViolationException("User not alowed.");
		}
		
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new BusinessRuleViolationException("Insuficient funds.");
		}
	}
	
	public User findUserById(Long id) throws Exception {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	
	public User createUser(UserRequestDTO userDTO) {
		User newUser = new User(userDTO);
		saveUser(newUser);
		return newUser;
		
	}
		
	public User editUser(Long id, UserRequestDTO userRequestDTO) throws Exception {
		
		Optional<User> userDBOpt = userRepository.findById(id);
		
		if(userDBOpt.isPresent()) {
			return userRepository.save(UserMapper.updateEntityFromDTO(userRequestDTO, userDBOpt.get()));
		} else {
			throw new EntityNotFoundException();
		}
		
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}

}
