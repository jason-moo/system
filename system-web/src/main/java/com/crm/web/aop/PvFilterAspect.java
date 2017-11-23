package com.crm.web.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.Advice;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PvFilterAspect {
	
	@Before("execution(* com.crm.web.controller..*.*(..))")
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Method method;
		try {
			method = joinPoint.getTarget().getClass().getMethod(methodName,
					((MethodSignature) joinPoint.getSignature()).getParameterTypes());
			PvDetail pvDetail = method.getAnnotation(PvDetail.class);
			if (pvDetail != null) {
				System.out.println("dsaddsa");
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}