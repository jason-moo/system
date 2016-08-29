package me.gacl.exception;


public class ErroMsgInfo extends MsgBase {
	
	private String erro_level;
	
	private String erro_mark;

	public String getErro_level() {
		return erro_level;
	}

	public void setErro_level(String erro_level) {
		this.erro_level = erro_level;
	}

	public String getErro_mark() {
		return erro_mark;
	}

	public void setErro_mark(String erro_mark) {
		this.erro_mark = erro_mark;
	}
	public static ErroMsgInfo createSysDefaultErro(){
		ErroMsgInfo erroMsgInfo=new ErroMsgInfo();
		erroMsgInfo.set_code(CodeStatus.FAIL);
		erroMsgInfo.setErro_level(ErroLevel.SYS_ERR);
		erroMsgInfo.setMessage(" message is empty ! ");
		return erroMsgInfo;
	}
}
