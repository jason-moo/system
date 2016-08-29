package me.gacl.exception;


public class ServiceException  extends BusinessException{
	
	 
	public ServiceException() {
		// TODO Auto-generated constructor stub
		super();
	}
	public ServiceException(String message) {
		// TODO Auto-generated constructor stub
		super(null,message);
	}
	public ServiceException(String code,String message) {
		// TODO Auto-generated constructor stub
		super(code,message);
	}
	public ServiceException(String code,String message,Throwable cause) {
		// TODO Auto-generated constructor stub
		super(code,message,cause);
		 
	}
	public ServiceException(String message,Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message,cause);
	}
}
