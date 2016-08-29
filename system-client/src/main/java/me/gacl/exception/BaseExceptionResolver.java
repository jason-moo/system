package me.gacl.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.gacl.utils.RespUtils;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseExceptionResolver implements HandlerExceptionResolver{

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		return streamException(request, response, handler, ex, null);
	}
	private ModelAndView  streamException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex,ModelAndView modelAndView){
		ObjectMapper objectMapper=new ObjectMapper();
		ErroMsgInfo erroMsgInfo=null;
		if(ex instanceof BusinessException){
			BusinessException businessException=(BusinessException)ex;
			if(StringUtils.isEmpty(businessException.getCode())){
				businessException.setCode(CodeStatus.FAIL);
			}
			erroMsgInfo=ErroMessageInfoHelper.transErroMsg(businessException);
		}else{
			erroMsgInfo=ErroMessageInfoHelper.transErroMsg(ex);
		}
		if(RespUtils.isRespJson(request)){
			if(null==erroMsgInfo){
				erroMsgInfo=ErroMsgInfo.createSysDefaultErro();
			} 
			RespUtils.responseOutWithJson(response, erroMsgInfo);
			modelAndView=new ModelAndView();
		}else{
			modelAndView=new ModelAndView();
			modelAndView.addObject("erroMsgInfo", erroMsgInfo);
			modelAndView.setViewName("error");
		}
		return  modelAndView;
	}
}
