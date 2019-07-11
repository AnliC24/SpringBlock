package tern.block.node.runner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class VaildBlockRunner implements CommandLineRunner{
   
	
	//注册日志log
 	protected Logger LOG = LogManager.getLogger(this.getClass());
	
	//用来统计验证返回结果
	public static int vaildBlockResult;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		LOG.info("初始化区块验证记录集...");
		VaildBlockRunner.vaildBlockResult=0;
	}

}
