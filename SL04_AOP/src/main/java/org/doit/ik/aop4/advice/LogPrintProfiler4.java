package org.doit.ik.aop4.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class LogPrintProfiler4 {
	// 1. aroundadvice p222
	// 2 brforeadvice p 217
	// 3 afteradvuce p221
	
	public void after(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
	      Log log = LogFactory.getLog(this.getClass());
	      log.info(">>> " + methodName + "() : LogPrintProfiler4.after 가 호출됨... ");
	}
	
	public void before(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
	      Log log = LogFactory.getLog(this.getClass());
	      log.info(">>> " + methodName + "() : LogPrintProfiler4.before 가 호출됨... ");
	}
	
	public Object trace(ProceedingJoinPoint joinPoint)throws Throwable{
		
		String methodName = joinPoint.getSignature().toShortString();
	      Log log =  LogFactory.getLog(this.getClass());
	       log.info("> " + methodName +"() start.");
	       StopWatch sw = new StopWatch();
	       sw.start();       
	       // 핵심 관심 사항.
	        Object  result = joinPoint.proceed() ;  // calc.add()        
	       sw.stop();
	       log.info("> " + methodName +"() end.");
	       log.info("> " + methodName +"() 처리 시간 :  " + sw.getTotalTimeMillis() +"ms");
	      return result;
		
	}

}
