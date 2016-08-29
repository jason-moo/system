package me.gacl.exception;

public class BusinessException extends BaseException {
	private String code;
	public BusinessException() {
		// TODO Auto-generated constructor stub
		super();
	}
	public BusinessException(String code) {
		// TODO Auto-generated constructor stub
		super();
		this.code=code;
	}
	public BusinessException(String code,String message) {
		// TODO Auto-generated constructor stub
		super(message);
		this.code=code;
	}
	public BusinessException(String code,String message,Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message,cause);
		this.code=code;
	}
	public BusinessException(String message,Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message,cause);
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
