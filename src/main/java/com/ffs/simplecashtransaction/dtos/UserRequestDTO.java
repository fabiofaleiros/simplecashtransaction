package com.ffs.simplecashtransaction.dtos;

import java.math.BigDecimal;

import com.ffs.simplecashtransaction.domain.user.RoleType;
import com.ffs.simplecashtransaction.domain.user.UserType;

public record UserRequestDTO(
		String firstName, 
		String lastName, 
		String document, 
		BigDecimal balance, 
		String email, 
		String password,
		RoleType role,
		UserType userType) {

}
