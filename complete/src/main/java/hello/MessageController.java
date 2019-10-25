package hello;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.DbInterface.MessageRepository;
import hello.DbInterface.SoulRepository;
import hello.Entity.MessageEntity;

@RestController
public class MessageController {
	
	private String TAG = "Message";
	@Autowired 
	SoulRepository userRepository ;
	@Autowired 
	MessageRepository messageRepository ;


	@RequestMapping("/greeting")
	public ResponseItem greeting() {
		return getDefaultResponse("Server test","Server responding","Pass");
	}


	@RequestMapping(value= {"/send"},method =RequestMethod.POST)
	public ResponseItem send(@RequestBody MessageEntity message) {
		message.setMessageId(UUID.randomUUID().toString());
		messageRepository.save(message);
		return getDefaultResponse(TAG,"Message sent","Success");
	}

	@RequestMapping(value= {"/inbox"},method =RequestMethod.GET)
	public Object getInbox(@RequestParam (value="soulId") String soulId , @RequestParam (value="limit") int limit) {
		InboxData data = new InboxData();
		data.setMessageList(messageRepository.findSoulInbox(soulId));
		if(data.getMessageList().size()>0) {
			return data;
		}else {
			return getDefaultResponse(TAG,"No Messages","Warning");
		}
		
	}

	@RequestMapping(value= {"/messages/delete"},method =RequestMethod.GET)
	public ResponseItem getInbox(@RequestParam (value="messageId") String messageId ) {
		messageRepository.deleteMessage(messageId);
		ResponseItem ri = new ResponseItem();
		ri.setResponseName("Message");
		return ri;
	}


	@RequestMapping(value= {"/read"},method =RequestMethod.GET)
	public ResponseItem read(@RequestParam (value="messageId") String messageId,@RequestParam (value="readStatus") String readStatus) {
		messageRepository.setRead(messageId,readStatus);
		ResponseItem ri = new ResponseItem();
		ri.setResponseName("messageId set to read");
		return ri;
	}


	@RequestMapping(value= {"/sentmessage"},method =RequestMethod.GET)
	public ResponseItem getSentMessage(@RequestParam (value="soulId") String soulId , @RequestParam (value="limit") int limit) {
		messageRepository.getSentMessage(soulId);
		ResponseItem ri = new ResponseItem();
		ri.setResponseName("messageId set to read");
		return ri;
	}



	public ResponseItem getDefaultResponse(String name ,String value, String type){
		ResponseItem ri = new ResponseItem();
		ri.setResponseName(name);
		ri.setResponseValue(value);
		ri.setResponseType(type);
		return ri;
	}

}
