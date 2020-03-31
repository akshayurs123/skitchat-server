package org.akshay.skitchat.controller;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.akshay.skitchat.ErrorResponse;
import org.akshay.skitchat.InboxData;
import org.akshay.skitchat.RestApiException;
import org.akshay.skitchat.entity.LoginEntity;
import org.akshay.skitchat.entity.SoulEntity;
import org.akshay.skitchat.repository.SoulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

			return soul;
		}else {
			throw new RestApiException("Username alredy exsit",400);
			//return getErrorCode(400,"Invalid operation","Username/email already exsits");
		}

	}



	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public Object login(@RequestBody LoginEntity loginEntity) {

		SoulEntity soulEntity = soulRepository.authenticateSoul(loginEntity.getUsername(),loginEntity.getPassword());
		if(soulEntity == null) {
			throw new RestApiException("Wrong usernam/password",500);
		}
		else{return soulEntity;}	


		// validateSoul(soulEntity); To confirm thru mail



	}

	@RequestMapping(value = { "/forgotpsw" }, method = RequestMethod.POST)
	public Object getInbox(@RequestParam(value = "messageId") String messageId) {
		/*
		 * messageRepository.deleteMessage(messageId); ResponseItem ri = new
		 * ResponseItem(); ri.setResponseName("Message"); return ri;
		 */
		return null;
	}

	public ErrorResponse getErrorCode(int errorCode, String errorType, String errorMessage) {
		ErrorResponse e = new ErrorResponse();
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
