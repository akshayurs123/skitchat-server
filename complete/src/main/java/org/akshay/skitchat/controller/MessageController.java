package org.akshay.skitchat.controller;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.akshay.skitchat.ErrorResponse;
import org.akshay.skitchat.InboxData;
import org.akshay.skitchat.entity.MessageEntity;
import org.akshay.skitchat.repository.MessageRepository;
import org.akshay.skitchat.repository.SoulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
		return getErrorCode(200,"Lockdown","Pass");
	}



	@RequestMapping(value= {"/send"},method =RequestMethod.POST)
	public ErrorResponse send(@RequestBody MessageEntity message) {
		message.setMessageId(UUID.randomUUID().toString());
		messageRepository.save(message);
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
	public ErrorResponse getInbox(@RequestParam (value="messageId") String messageId ) {
		messageRepository.deleteMessage(messageId);
		ErrorResponse ri = new ErrorResponse();
		ri.setErrorMessage("Message");
		return ri;
	}


	@RequestMapping(value= {"/read"},method =RequestMethod.GET)
	public ErrorResponse read(@RequestParam (value="messageId") String messageId,@RequestParam (value="readStatus") String readStatus) {
		messageRepository.setRead(messageId,readStatus);
		ErrorResponse ri = new ErrorResponse();
		ri.setErrorMessage("messageId set to read");
		return ri;
	}


	@RequestMapping(value= {"/sentmessage"},method =RequestMethod.GET)
	public ErrorResponse getSentMessage(@RequestParam (value="soulId") String soulId , @RequestParam (value="limit") int limit) {
		messageRepository.getSentMessage(soulId);
		ErrorResponse ri = new ErrorResponse();
		ri.setErrorMessage("messageId set to read");
		return ri;
	}



	public ErrorResponse getErrorCode(int errorCode, String errorType, String errorMessage) {
		ErrorResponse e = new ErrorResponse();
		e.setErrorCode(errorCode);
		e.setErrorType(errorType);
		e.setErrorMessage(errorMessage);
		
		return e;
	}

}
