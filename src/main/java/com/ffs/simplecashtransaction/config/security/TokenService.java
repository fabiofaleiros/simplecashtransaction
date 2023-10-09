package com.ffs.simplecashtransaction.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.exception.BusinessRuleViolationException;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secretKey;
	
	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(user.getUsername())
					.withExpiresAt(genExpirationDate())
					.sign(algorithm);
			
			return token;
			
		} catch (JWTCreationException e) {
			throw new BusinessRuleViolationException("Error while generating token");
		}
	}
	
	public String validateToken(String token) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException e) {
			throw new BusinessRuleViolationException("Error while validating token");
		}
		
	}
	
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("+00:00"));
	}

}
