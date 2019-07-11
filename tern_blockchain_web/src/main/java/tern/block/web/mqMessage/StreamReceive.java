package tern.block.web.mqMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
public class StreamReceive {
    
	//注册日志log
 	protected Logger LOG = LogManager.getLogger(this.getClass());
	
	@StreamListener(value = StreamClient.Input)
	public void process(Object message)
	{
		LOG.info("StreamReceiver  "+message);
	}
}
