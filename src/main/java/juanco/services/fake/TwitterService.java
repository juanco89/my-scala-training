package juanco.services.fake;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TwitterService {

	public TwitterService() { 
		
	}
	
	
	public List<String> twitterFollowers() throws InterruptedException {
		Random rnd = new Random(System.currentTimeMillis());
		Thread.sleep(rnd.nextInt(3000));  // Latencia de red
		
		return Arrays.asList("Foo", "Bar", "Doe");
	}
}
