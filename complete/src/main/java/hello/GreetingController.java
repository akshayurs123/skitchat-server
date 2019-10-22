package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@Autowired 
	UserRepository userRepository ;

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
}
