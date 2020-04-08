package org.akshay.skitchat.controller;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import org.akshay.skitchat.InboxData;
import org.akshay.skitchat.RestApiException;
import org.akshay.skitchat.entity.ErrorResponseEntity;
import org.akshay.skitchat.entity.LoginEntity;
import org.akshay.skitchat.entity.SoulEntity;
import org.akshay.skitchat.entity.TokenEntity;
import org.akshay.skitchat.repository.SoulRepository;
import org.jboss.logging.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import util.SCConst;

@RestController
public class SoulController {

	private String TAG = "Soul";
	private String LOGTAG = "SoulController";
	@Autowired
	SoulRepository soulRepository;

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public Object register(@RequestBody SoulEntity soul) {
		soul.setSoulId(UUID.randomUUID().toString());
		if( validateNewSoulData(soul)) {
			soulRepository.save(soul);
			soul.setSuccessful(true);
			soul.setResponseMessage("Successful");
			return soul;
		}else {
			//throw new RestApiException("Username alredy exist",403,SCConst.USER_NOT_FOUND);
			soul.setSuccessful(false);
			soul.setResponseMessage("Username alredy exist");
			return soul;
		}

	}



	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public Object login(@RequestBody LoginEntity loginEntity) {

		SoulEntity soulEntity = soulRepository.authenticateSoul(loginEntity.getUsername(),loginEntity.getPassword());
		if(soulEntity == null) {
			soulEntity = new SoulEntity();
			soulEntity.setSuccessful(false);
			soulEntity.setResponseMessage("Wrong username/password");
			return soulEntity;
		}
		else{
			soulEntity.setSuccessful(true);
			soulEntity.setResponseMessage("Successful");
			return soulEntity;}	

	}


	@RequestMapping(value = { "/token" }, method = RequestMethod.POST)
	public Object saveToken( @RequestBody TokenEntity tokenEntity) {

		SoulEntity soulEntity = new SoulEntity();
		System.out.println("OOOOOO: "+tokenEntity.getFcmToken());
		System.out.println("OOOOOO: "+tokenEntity.getSoulId());
		int record = soulRepository.saveToken(tokenEntity.getFcmToken(),tokenEntity.getSoulId());

		if(record == 0) { 
			soulEntity.setSuccessful(false);
			soulEntity.setResponseMessage("FCM registration failed, User not found"); 
			return soulEntity;
		}
		else{ 
			soulEntity.setSuccessful(true);
			soulEntity.setResponseMessage("Successful"); 
			return soulEntity;
		}

	}




	@RequestMapping(value = { "/forgotpsw" }, method = RequestMethod.POST)
	public Object getInbox(@RequestParam(value = "messageId") String messageId) {
		/*
		 * messageRepository.deleteMessage(messageId); ResponseItem ri = new
		 * ResponseItem(); ri.setResponseName("Message"); return ri;
		 */
		return null;
	}

	public ErrorResponseEntity getErrorCode(int errorCode, String errorType, String errorMessage) {
		ErrorResponseEntity e = new ErrorResponseEntity();
		e.setErrorCode(errorCode);
		e.setErrorType(errorType);
		e.setErrorMessage(errorMessage);

		return e;
	}

	private boolean validateNewSoulData(SoulEntity soul) {
		// TODO Auto-generated method stub
		if(soulRepository.userNamePresent(soul.getUsername()) == true) {
			return false;
		}else if(soulRepository.emailPresent(soul.getEmail()) == true) {
			return false;
		}
		return true;

	}


	

}
//throw new RestApiException("Wrong usernam/password",404,SCConst.WRONG_CREDENTIAL); 
