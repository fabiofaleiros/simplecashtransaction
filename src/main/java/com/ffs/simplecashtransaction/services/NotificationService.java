package com.ffs.simplecashtransaction.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ffs.simplecashtransaction.domain.user.User;
import com.ffs.simplecashtransaction.dtos.NotificationDTO;

@Service
public class NotificationService {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void sendNotification(User user, String message) throws Exception {
		
		NotificationDTO notificationDTO = new NotificationDTO(user.getEmail(), message);
		
		ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://v4goj.wiremockapi.cloud/notify", notificationDTO, String.class);
		
		if(!(HttpStatus.OK == notificationResponse.getStatusCode())) {
			logger.info("Notification Service offline.");
			throw new Exception("Notification Service offline.");
		}
		
		System.out.println(message);
		
	}

}
