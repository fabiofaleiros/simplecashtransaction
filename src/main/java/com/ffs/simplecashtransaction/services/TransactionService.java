package com.ffs.simplecashtransaction.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ffs.simplecashtransaction.domain.transaction.Transaction;
import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.dtos.TransactionRequestDTO;
import com.ffs.simplecashtransaction.exception.BusinessRuleViolationException;
import com.ffs.simplecashtransaction.repositories.TransactionRepository;

@Service
public class TransactionService {
	
	private final static String AUTORIZADO = "Autorizado";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Transaction createTransaction(TransactionRequestDTO transactionDTO) throws Exception {
		
		User sender = userService.findUserById(transactionDTO.senderID());
		User receiver = userService.findUserById(transactionDTO.receiverID());
		
		userService.validateTransaction(sender, transactionDTO.value());
		
		if(!authorizeTransaction(sender, transactionDTO.value())) {
			throw new BusinessRuleViolationException("Not authorized transaction");
		}
		
		Transaction transaction = new Transaction();
		transaction.setAmount(transactionDTO.value());
		transaction.setSender(sender);
		transaction.setReceiver(receiver);
		transaction.setTimestampTransaction(LocalDateTime.now());
		transaction.setTransactionCode(UUID.randomUUID());
		
		sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
		receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));
		
		transactionRepository.save(transaction);
		userService.saveUser(sender);
		userService.saveUser(receiver);
		
		notificationService.sendNotificationTransaction(transaction);
		
		return transaction;
		
	}
	
	public boolean authorizeTransaction(User sender, BigDecimal value) {
		
		ResponseEntity<Map> authResp = restTemplate.getForEntity("https://run.mocky.io/v3/da51a6c7-9ad8-475d-8c3b-7c406416a5f8", Map.class);
		
		if(HttpStatus.OK == authResp.getStatusCode()) {
			String message = (String) authResp.getBody().get("message");
			return AUTORIZADO.equalsIgnoreCase(message);
		}
		
		return false;
		
	}
	
	public List<Transaction> getAllTransactions(){
		return transactionRepository.findAll();
	}
	

}
