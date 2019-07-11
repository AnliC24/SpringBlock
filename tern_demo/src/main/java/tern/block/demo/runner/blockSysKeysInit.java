package tern.block.demo.runner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import tern.block.demo.util.FileUtil;





@Component
public class blockSysKeysInit implements CommandLineRunner{
	
	private  Logger Log = LogManager.getLogger(this.getClass());

	private String sysPubKey;
	private String sysPriKey;
	
	private static String SYS_PUBLIC_KEY = "SysPubKey.txt";
	private static String SYS_PRIVATE_KEY = "SysPriKey.txt";
	
	@Autowired
	private FileUtil fileUtil;
	
   /**
    * 读取本地root公私钥文件
    * */
	@Override
	public void run(String... strings) throws Exception {
		// TODO Auto-generated method stub
		sysPubKey = fileUtil.readText(SYS_PUBLIC_KEY);
		Log.info("联盟公钥初始化成功...");
		sysPriKey = fileUtil.readText(SYS_PRIVATE_KEY);
		Log.info("联盟私钥初始化成功...");
	}


	public String getSysPubKey() {
		return sysPubKey;
	}

	public String getSysPriKey() {
		return sysPriKey;
	}


	public static String getSYS_PRIVATE_KEY() {
		return SYS_PRIVATE_KEY;
	}


}
