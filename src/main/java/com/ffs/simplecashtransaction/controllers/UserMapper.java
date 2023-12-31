package com.ffs.simplecashtransaction.controllers;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.ffs.simplecashtransaction.domain.user.RoleType;
import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.domain.user.UserType;
import com.ffs.simplecashtransaction.dtos.UserRequestDTO;
import com.ffs.simplecashtransaction.dtos.UserResponseDTO;

public class UserMapper {
	
	public static User updateEntityFromDTO(UserRequestDTO dto, User user) {
		final String firstName = StringUtils.hasText(dto.firstName()) ? dto.firstName() : user.getFirstName();
		final String lastName = StringUtils.hasText(dto.lastName()) ? dto.lastName() : user.getLastName();
		final String document = StringUtils.hasText(dto.document()) ? dto.document() : user.getDocument();
	    final String email = StringUtils.hasText(dto.email()) ? dto.email() : user.getEmail();
	    final RoleType role = !ObjectUtils.isEmpty(dto.role()) ? dto.role() : user.getRole();
	    final UserType userType = !ObjectUtils.isEmpty(dto.userType()) ? dto.userType() : user.getUserType();
	    return new User(user.getId(), firstName, lastName, document, email, user.getPassword(), user.getBalance(), role, userType, null, null);
	  }
	
	public static UserResponseDTO fromEntityToResponseDTO(User user) {
		return user != null ? new UserResponseDTO(
					user.getFirstName(), 
					user.getLastName(), 
					user.getDocument(), 
					user.getBalance(), 
					user.getEmail(), 
					user.getRole(),
					user.getUserType(), 
					user.getUpdatedAt()) : null;
	}	

}
