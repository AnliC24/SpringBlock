package tern.block.web.mqMessage;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {
    
	String Input = "myMessage";
	
	@Input(Input)
	SubscribableChannel input();
	
	@Output(Input)
	MessageChannel output();
}
