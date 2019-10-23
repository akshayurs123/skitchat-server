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
import hello.DbInterface.UserRepository;

@RestController
public class GreetingController {
	
	@Autowired 
	UserRepository userRepository ;
	@Autowired 
	MessageRepository messageRepository ;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    
    @RequestMapping(value= {"/putgreeting"},method =RequestMethod.GET)
    
    public Greeting putgreeting(@RequestParam(value="name", defaultValue="World") String name) {
    	
    	Greeting geeting = new Greeting(1,name);
    	
    	geeting.setContent(name);
    	userRepository.save(geeting);
    	
        return geeting;
    }
    
    @RequestMapping(value= {"/send"},method =RequestMethod.POST)
    public ResponseItem send(@RequestBody MessageEntity message) {
    	
    	message.setMessageId(UUID.randomUUID().toString());
    	messageRepository.save(message);
    	
    	ResponseItem ri = new ResponseItem();
    	ri.setResponseName("test");
    	return ri;
    }
    
    @RequestMapping(value= {"/messages"},method =RequestMethod.GET)
    public InboxData getInbox(@RequestParam (value="soulId") String soulId , @RequestParam (value="limit") int limit) {
    	
    	InboxData data = new InboxData();
    	data.setMessageList(messageRepository.findSoulInbox(soulId));
    	return data;
    }
    
    @RequestMapping(value= {"/messages/delete"},method =RequestMethod.GET)
    public ResponseItem getInbox(@RequestParam (value="messageId") String messageId ) {
    	
    	messageRepository.deleteMessage(messageId);
    	
    	ResponseItem ri = new ResponseItem();
    	ri.setResponseName("Delete Message");
    	return ri;
    }
    
    
    
}
