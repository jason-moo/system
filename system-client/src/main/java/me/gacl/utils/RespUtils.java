package me.gacl.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class RespUtils {
	
	public static void responseOutWithJson(HttpServletResponse response,  
	        Object responseObject) {  
	    //将实体对象转换为JSON Object转换  
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		JsonGenerator jsonGenerator;
		try {
			jsonGenerator = objectMapper.getFactory().createGenerator(response.getOutputStream(),JsonEncoding.UTF8);
			objectMapper.writeValue(jsonGenerator,responseObject);
			jsonGenerator.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}  
	
	/**
	 * 是否响应json数据
	 * ***/
	public  static boolean isRespJson(HttpServletRequest request){
		if(AjaxUtil.isAjax(request)){
			return true;
		}
		return false;
	}
}
