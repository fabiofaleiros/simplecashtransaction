package com.ffs.simplecashtransaction.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.exception.BusinessRuleViolationException;
import com.ffs.simplecashtransaction.repositories.UserRepository;

@Service
public class AuthencationService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDetails findByEmail(String email){
	
		Optional<UserDetails> optUser = userRepository.findByEmail(email);
		
		if(!optUser.isPresent()) {
			throw new BusinessRuleViolationException("User not found. Unable to activate authentication.");
		} 
		
		return optUser.get();
		
	}
	
	public User findUserByEmail(String email) {
		
		Optional<User> optUser = userRepository.findUserByEmail(email);
		
		if(!optUser.isPresent()) {
			throw new BusinessRuleViolationException("User not found. Unable to activate authentication.");
		} 
		
		return optUser.get();
	};
	
	public void save(User user) {
		
		userRepository.save(user);
		
	};

}
