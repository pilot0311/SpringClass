package org.doit.ik.aop3.advice;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Component
public class LogPrintBeforeAdvice3 implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String methodName = method.getName();
		
		Log log = LogFactory.getLog(this.getClass());
		log.info(">"+ methodName +"() LogPrintBeforeAdvice 호출됨");
	
		
	}

}
