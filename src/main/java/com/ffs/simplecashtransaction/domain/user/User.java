package com.ffs.simplecashtransaction.domain.user;

import java.math.BigDecimal;

import com.ffs.simplecashtransaction.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String document;

	@Column(unique = true)
	private String email;

	private String password;
	
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	public User(UserDTO from) {
		this.firstName = from.firstName();
		this.lastName = from.lastName();
		this.document = from.document();
		this.email = from.email();
		this.password = from.password();
		this.balance = from.balance();
		this.userType = from.userType();
	}

}
