package com.ffs.simplecashtransaction.dtos;

import com.ffs.simplecashtransaction.domain.user.RoleType;

public record RegisterDTO(String login, String password, RoleType role) {

}
