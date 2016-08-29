package me.gacl.exception;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

public class ErroMessageInfoHelper {
		public static ErroMsgInfo transErroMsg(BusinessException exception){
			ErroMsgInfo erroMsgInfo=new ErroMsgInfo();
			erroMsgInfo.set_code(exception.getCode());
			erroMsgInfo.setMessage(exception.getMessage());
			erroMsgInfo.setErro_level(ErroLevel.SERVEICE_ERR);
			erroMsgInfo.setErro_mark("业务异常");
			return erroMsgInfo;
		}
		public static ErroMsgInfo transErroMsg(Exception exception){
			SysException sysException=createSysException(exception);
			ErroMsgInfo erroMsgInfo=new ErroMsgInfo();
			erroMsgInfo.set_code(sysException.getCode());
			erroMsgInfo.setMessage(sysException.getMessage());
			erroMsgInfo.setErro_level(ErroLevel.SYS_ERR);
			erroMsgInfo.setErro_mark("系统异常");
			return erroMsgInfo;
		}
		public static SysException createSysException(Exception ex){
			if (ex.getClass().equals(DataAccessException.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "数据库操作失败");
			} else if (ex.getClass().toString().equals(NullPointerException.class.toString())) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "调用了未经初始化的对象或者是不存在的对象");
			} else if (ex.getClass().equals(IOException.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "io异常");
			} else if (ex.getClass().equals(ClassNotFoundException.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "指定的类不存在");
			} else if (ex.getClass().equals(ArithmeticException.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "数学运算异常");
			} else if (ex.getClass().equals(ArrayIndexOutOfBoundsException.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "数组下标越界");
			} else if (ex.getClass().equals(IllegalArgumentException.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "方法的参数错误");
			} else if (ex.getClass().equals(ClassCastException.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "类型强制转换错误");
			} else if (ex.getClass().equals(SecurityException.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "违背安全原则异常");
			} else if (ex.getClass().equals(SQLException.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "操作数据库异常");
			} else if (ex.getClass().equals(NoSuchMethodError.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "方法末找到异常");
			} else if (ex.getClass().equals(OutOfMemoryError.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "java虚拟机内存溢出");
			} else if (ex.getClass().equals(InternalError.class)) {
				ex.printStackTrace();
				return new SysException(CodeStatus.FAIL, "java虚拟机发生了内部错误");
			} else {
				ex.printStackTrace();
				//return new SysException(ex.getMessage());			
				return new SysException(CodeStatus.FAIL,"系统后台发生异常");
			}
		}
}
