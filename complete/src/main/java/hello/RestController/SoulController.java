package hello.RestController;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.InboxData;
import hello.ResponseItem;
import hello.DbInterface.MessageRepository;
import hello.DbInterface.SoulRepository;
import hello.Entity.LoginEntity;
import hello.Entity.MessageEntity;
import hello.Entity.SoulEntity;

@RestController
public class SoulController {

	private String TAG = "Soul";
	private String LOGTAG = "SoulController";
	@Autowired
	SoulRepository soulRepository;

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public Object register(@RequestBody SoulEntity soul) {
		soul.setSoulId(UUID.randomUUID().toString());
		Object validateObj = validateNewSoulData(soul);
		soulRepository.save(soul);
		return getDefaultResponse(TAG, "soulId", soul.getSoulId());
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public Object login(@RequestBody LoginEntity loginEntity) {

		
		
		  SoulEntity soulEntity = soulRepository.authenticateSoul(loginEntity.getUsername(),loginEntity.getPassword());
		  if(soulEntity == null) {
			  return getDefaultResponse("No user","soulId","Nil");
		  }
		  return getDefaultResponse("Found user","soulId",soulEntity.getSoulId());
		  
		
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

	public ResponseItem getDefaultResponse(String name, String value, String type) {
		ResponseItem ri = new ResponseItem();
		ri.setResponseName(name);
		ri.setResponseValue(value);
		ri.setResponseType(type);
		return ri;
	}

	private Object validateNewSoulData(SoulEntity soul) {
		// TODO Auto-generated method stub
		return null;

	}

}
