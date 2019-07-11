package tern.block.demo.exception;

import tern.block.demo.dto.ExceptionResult;

public class VaildNodeException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	

	private String vaildCode;
	private ExceptionResult result;
	
	

	public VaildNodeException(ExceptionResult result) {
		super();
		this.result = result;
	}
    
	public VaildNodeException(String message) {
	
		this.vaildCode = message;
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
