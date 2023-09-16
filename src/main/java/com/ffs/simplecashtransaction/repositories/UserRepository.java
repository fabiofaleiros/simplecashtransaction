package com.ffs.simplecashtransaction.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffs.simplecashtransaction.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findUserByDocument(String doc);

}
