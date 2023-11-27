package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

// 후 advice + 예외 발생 X
public class LogPrintAfterReturningAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		String methodName = method.getName();
		
		Log log = LogFactory.getLog(this.getClass());
		log.info(">"+ methodName +"() LogPrintAfterReturningAdvice 호출됨");
		
	}

}
