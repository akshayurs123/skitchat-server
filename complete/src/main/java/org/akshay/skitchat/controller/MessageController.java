package org.akshay.skitchat.controller;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import org.akshay.skitchat.InboxData;
import org.akshay.skitchat.entity.ErrorResponseEntity;
import org.akshay.skitchat.entity.MessageEntity;
import org.akshay.skitchat.repository.MessageRepository;
import org.akshay.skitchat.repository.SoulRepository;
import org.akshay.skitchat.service.MessageService;
import org.akshay.skitchat.util.FCMInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;


@RestController
public class MessageController {

	private String TAG = "Message";
	@Autowired 
	SoulRepository userRepository ;
	@Autowired 
	MessageRepository messageRepository ;


	@RequestMapping("/greeting")
	public Object greeting() {
		// throw new RuntimeException("Invalid employee id : " + TAG);
		//return TAG;
		sendBlah();
		return getErrorCode(200,"Lockdown","Pass");
	}



	@RequestMapping(value= {"/send"},method =RequestMethod.POST)
	public ErrorResponseEntity send(@RequestBody MessageEntity message) {
		message.setMessageId(UUID.randomUUID().toString());
		messageRepository.save(message);
		new MessageService().sendNotification(message);
		return getErrorCode(200,"Message sent","Success");
	}



	@RequestMapping(value= {"/inbox"},method =RequestMethod.GET)
	public Object getInbox(@RequestParam (value="soulId") String soulId , @RequestParam (value="limit") int limit) {
		InboxData data = new InboxData();
		data.setMessageList(messageRepository.findSoulInbox(soulId));
		if(data.getMessageList().size()>0) {
			return data;
		}else {
			return getErrorCode(400,"No Messages","Warning");
		}

	}

	@RequestMapping(value= {"/messages/delete"},method =RequestMethod.GET)
	public ErrorResponseEntity getInbox(@RequestParam (value="messageId") String messageId ) {
		messageRepository.deleteMessage(messageId);
		ErrorResponseEntity ri = new ErrorResponseEntity();
		ri.setErrorMessage("Message");
		return ri;
	}


	@RequestMapping(value= {"/read"},method =RequestMethod.GET)
	public ErrorResponseEntity read(@RequestParam (value="messageId") String messageId,@RequestParam (value="readStatus") String readStatus) {
		messageRepository.setRead(messageId,readStatus);
		ErrorResponseEntity ri = new ErrorResponseEntity();
		ri.setErrorMessage("messageId set to read");
		return ri;
	}


	@RequestMapping(value= {"/sentmessage"},method =RequestMethod.GET)
	public ErrorResponseEntity getSentMessage(@RequestParam (value="soulId") String soulId , @RequestParam (value="limit") int limit) {
		messageRepository.getSentMessage(soulId);
		ErrorResponseEntity ri = new ErrorResponseEntity();
		ri.setErrorMessage("messageId set to read");
		return ri;
	}



	public ErrorResponseEntity getErrorCode(int errorCode, String errorType, String errorMessage) {
		ErrorResponseEntity e = new ErrorResponseEntity();
		e.setErrorCode(errorCode);
		e.setErrorType(errorType);
		e.setErrorMessage(errorMessage);

		return e;
	}

	private void sendBlah() {

		

	}

}
