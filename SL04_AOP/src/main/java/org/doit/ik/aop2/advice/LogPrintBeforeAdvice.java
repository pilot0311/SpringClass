package org.doit.ik.aop2.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.util.StopWatch;

public class LogPrintBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String methodName = method.getName();
		
		Log log = LogFactory.getLog(this.getClass());
		log.info(">"+ methodName +"() LogPrintBeforeAdvice 호출됨");
	
		
	}

}
