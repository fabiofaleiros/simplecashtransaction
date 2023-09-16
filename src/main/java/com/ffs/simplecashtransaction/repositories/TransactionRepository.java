package com.ffs.simplecashtransaction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ffs.simplecashtransaction.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
