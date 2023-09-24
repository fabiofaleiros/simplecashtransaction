package com.ffs.simplecashtransaction.controllers;

import com.ffs.simplecashtransaction.domain.transaction.Transaction;
import com.ffs.simplecashtransaction.dtos.TransactionResponseDTO;

public class TransactionMapper {
	
	public static TransactionResponseDTO fromEntityToResponseDTO(Transaction transaction) {
		return new TransactionResponseDTO(transaction.getAmount(), transaction.getSender().getId(), transaction.getReceiver().getId());
	}	

}
