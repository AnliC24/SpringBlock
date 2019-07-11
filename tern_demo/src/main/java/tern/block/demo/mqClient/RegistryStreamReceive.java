package tern.block.demo.mqClient;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;

import org.springframework.stereotype.Component;



@Component
@EnableBinding(RegistryStreamClient.class)
public class RegistryStreamReceive {
         
	    //注册日志log
	 	protected Logger LOG = LogManager.getLogger(this.getClass());
	 	
	 	
	
}
