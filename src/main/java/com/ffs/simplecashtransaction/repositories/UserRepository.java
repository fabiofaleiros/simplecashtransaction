package com.ffs.simplecashtransaction.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ffs.simplecashtransaction.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByDocument(String doc);
	
	Optional<User> findUserByEmail(String email);
	
	Optional<UserDetails> findByEmail(String email);

}
