package me.gacl.exception;

public class MsgBase {
	
	/***
	 * 状态码
	 * **/
	private String _code=CodeStatus.SUCCESS;
	
	/**
	 * 提示信息
	 * ***/
	private String message;

	 

	public String get_code() {
		return _code;
	}

	public void set_code(String _code) {
		this._code = _code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
