package tern.block.demo.Handler;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import tern.block.demo.dto.ExceptionResult;
import tern.block.demo.exception.ExeistsNodeException;
import tern.block.demo.exception.VaildNodeException;
import tern.block.demo.util.ResultUtil;



/**
 * @author WindC~
 * @Time 2019/4/17
 * @version 1.0
 * @Title 统一异常处理机制 -- 区块链底层
 * */


public class NodeExceptionHandler {
	
	@ExceptionHandler(VaildNodeException.class)
    @ResponseBody
	public ExceptionResult VaildNodeExceptionHandler(VaildNodeException e)
	{		
		    System.out.println("123");
			return ResultUtil.error(e);	
	}
	
	@ExceptionHandler(ExeistsNodeException.class)
    @ResponseBody
    public ExceptionResult ExeistsNodeExceptionHandler(ExeistsNodeException e)
    {
		return ResultUtil.error(e);
    }
	
	@ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public String UsernameNotFoundExceptionHandler(UsernameNotFoundException e)
    {
		return e.getMessage();
    }
  
} 
