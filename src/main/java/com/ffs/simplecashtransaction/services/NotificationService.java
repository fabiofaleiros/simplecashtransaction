package com.ffs.simplecashtransaction.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ffs.simplecashtransaction.domain.transaction.Transaction;
import com.ffs.simplecashtransaction.dtos.NotificationDTO;

@Service
public class NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

	@Autowired
	private RestTemplate restTemplate;

	public void sendNotificationTransaction(Transaction transaction) throws Exception {

		String senderMessage = transaction.getSender().getFirstName() + " " + transaction.getSender().getLastName()
				+ " sent " + transaction.getAmount() + " to " + transaction.getReceiver().getFirstName() + " "
				+ transaction.getReceiver().getLastName() + ". Control code: " + transaction.getTransactionCode();

		NotificationDTO senderNotificationDTO = new NotificationDTO(transaction.getSender().getEmail(), senderMessage);

		ResponseEntity<String> senderNotificationResponse = restTemplate
				.postForEntity("https://v4goj.wiremockapi.cloud/notify", senderNotificationDTO, String.class);

		if (!(HttpStatus.OK == senderNotificationResponse.getStatusCode())) {
			logger.info("Notification Service offline.");
			throw new Exception("Notification Service offline.");
		}
		
		logger.info(senderMessage);

		String receiverMessage = transaction.getReceiver().getFirstName() + " "
				+ transaction.getReceiver().getLastName() + " received " + transaction.getAmount() + " from "
				+ transaction.getSender().getFirstName() + " " + transaction.getSender().getLastName() + ". Control code: " + transaction.getTransactionCode();

		NotificationDTO receiverNotificationDTO = new NotificationDTO(transaction.getReceiver().getEmail(),
				receiverMessage);

		ResponseEntity<String> receiverNotificationResponse = restTemplate
				.postForEntity("https://v4goj.wiremockapi.cloud/notify", receiverNotificationDTO, String.class);

		if (!(HttpStatus.OK == receiverNotificationResponse.getStatusCode())) {
			logger.info("Notification Service offline.");
			throw new Exception("Notification Service offline.");
		}
		
		logger.info(receiverMessage);

	}

}
