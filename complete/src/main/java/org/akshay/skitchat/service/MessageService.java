package org.akshay.skitchat.service;

import org.akshay.skitchat.entity.MessageEntity;
import org.akshay.skitchat.entity.SoulEntity;
import org.akshay.skitchat.repository.MessageRepository;
import org.akshay.skitchat.repository.SoulRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

public class MessageService {

	@Autowired 
	SoulRepository userRepository ;
	@Autowired 
	MessageRepository messageRepository ;

	public void sendNotification(MessageEntity message) {

		/*
		 * SoulEntity soulEntity =
		 * userRepository.getToken(message.getReceiverUsername()); String fcm_token =
		 * soulEntity.getFcmToken();
		 * 
		 * 
		 * Message fcmMessage = Message.builder() .putData("score", "850")
		 * .putData("time","2:45") .setToken(fcm_token) .build();
		 * 
		 * 
		 * try { String response = FirebaseMessaging.getInstance().send(fcmMessage); }
		 * catch (FirebaseMessagingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}


}
