package tern.block.demo.util;

import tern.block.demo.dto.ExceptionResult;
import tern.block.demo.exception.VaildNodeException;

public class ResultUtil {
     
	
	/**
	 * 定义成功操作
	 * */
	public static ExceptionResult success(Object object)
	{
		ExceptionResult result = new ExceptionResult();
		
		return result;
	}
	/**
	 * 定义失败操作
	 * */
	public static ExceptionResult error(Object object)
	{   
		ExceptionResult result = emptyError();
		
		if(object instanceof VaildNodeException)
		{   
			((VaildNodeException) object).setVaildCode("自定义异常处理----校验异常");
			//result = ((VaildNodeException) object).getResult();
			return result;
		}
		
		 return result;
	}
	
	/**
	 * 无自定义异常  返回
	 * */
	public static ExceptionResult emptyError()
	{
		ExceptionResult result = new ExceptionResult();
		result.setCode("无状态码");
		result.setMessage("无返回值");
		result.setNodeError("无节点错误信息");
		return result;
	}
	
}
