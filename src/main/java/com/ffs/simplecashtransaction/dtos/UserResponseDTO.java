package com.ffs.simplecashtransaction.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ffs.simplecashtransaction.domain.user.RoleType;
import com.ffs.simplecashtransaction.domain.user.UserType;

public record UserResponseDTO(
			String firstName, 
			String lastName, 
			String document, 
			BigDecimal balance, 
			String email, 
			RoleType role,
			UserType userType, 
			LocalDateTime updatedAt) {
}
