package tern.block.demo.exception;

import tern.block.demo.dto.ExceptionResult;

public class ExeistsNodeException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	private String vaildCode;
	private ExceptionResult result;
	
	
	public ExeistsNodeException(ExceptionResult result) {
		this.result = result;
	}


	public String getVaildCode() {
		return vaildCode;
	}


	public void setVaildCode(String vaildCode) {
		this.vaildCode = vaildCode;
	}


	public ExceptionResult getResult() {
		return result;
	}


	public void setResult(ExceptionResult result) {
		this.result = result;
	}
	
	
	
	
	
    
}
