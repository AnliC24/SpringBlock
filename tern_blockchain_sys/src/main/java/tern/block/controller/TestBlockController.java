package tern.block.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestBlockController extends PublicBaseController{
       
	   /**
	    * 网关测试服务接口
	    * */
	  
	   @RequestMapping(value="/block")
       public Map<String, Object> testBlock(){
    	   Map<String,Object> testMap  = new HashMap<>();
    	   testMap.put("test", "block");
    	   return testMap;
       }
	   
	   /**
	    * 网关测试 不暴露给外部的接口
	    * */
	   @RequestMapping(value = "/createOrigin")
	   public String testIgnore(){
		   return "创建创世块";
	   }
	 
}
