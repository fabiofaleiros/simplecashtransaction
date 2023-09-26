package com.ffs.simplecashtransaction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffs.simplecashtransaction.domain.transaction.Transaction;
import com.ffs.simplecashtransaction.dtos.TransactionRequestDTO;
import com.ffs.simplecashtransaction.dtos.TransactionResponseDTO;
import com.ffs.simplecashtransaction.services.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping
	public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionDTO)
			throws Exception {

		return new ResponseEntity<>(TransactionMapper.fromEntityToResponseDTO(transactionService.createTransaction(transactionDTO)), HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(){
		
		List<Transaction> allTransactions = transactionService.getAllTransactions();
		
		if (allTransactions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<> (allTransactions.stream().map(TransactionMapper::fromEntityToResponseDTO).toList(), HttpStatus.OK) ;
	}

}
