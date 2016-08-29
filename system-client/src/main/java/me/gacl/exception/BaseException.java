package me.gacl.exception;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2727666743696880015L;

	public BaseException() {
		// TODO Auto-generated constructor stub
		super();
	}
	public BaseException(String message){
		super(message);
	}
	
	public BaseException(Throwable cause){
		super(cause);
	}
	public BaseException(String message,Throwable cause){
		super(message,cause);
	}
}
