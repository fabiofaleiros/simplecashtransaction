package com.ffs.simplecashtransaction.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ffs.simplecashtransaction.domain.user.UserType;

public record UserResponseDTO(
			String firstName, 
			String lastName, 
			String document, 
			BigDecimal balance, 
			String email, 
			UserType userType, 
			LocalDateTime updatedAt) {
}
