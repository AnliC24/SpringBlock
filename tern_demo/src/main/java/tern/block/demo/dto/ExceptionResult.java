package tern.block.demo.dto;

public class ExceptionResult {
	
	    private String code;
	    private String message;
	    private String nodeError; //节点异常信息
	    

	        
		public ExceptionResult() {
			super();
		}



		public ExceptionResult(String code, String message) {
			this.code = code;
			this.message = message;
		}
		
		
		
		public ExceptionResult(String code, String message, String nodeError) {
			super();
			this.code = code;
			this.message = message;
			this.nodeError = nodeError;
		}



		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

		public String getNodeError() {
			return nodeError;
		}

		public void setNodeError(String nodeError) {
			this.nodeError = nodeError;
		}
     
        
}
