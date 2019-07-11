package tern.block.mqReceive;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RegistryStreamClient {
    
	String Input = "nodeRegistryMessage";
	
	@Input(Input)
	SubscribableChannel input();
	
	@Output(Input)
	MessageChannel output();
}
