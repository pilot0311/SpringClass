package org.doit.ik.aop4;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		
		// p211 스프링 AOP
		// 1.xml 스키마 기반의 AOP 구현 방식
		// 2. CalculatorImpl 클래스
		// 3. Ex01에서 테스트
		
	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("application-context4.xml");
	//Calculator calc = ctx.getBean("calc",Calculator.class);
	Calculator calc = ctx.getBean("calc4",Calculator.class);
	System.out.println(calc.add(3, 4));
	
	
	
	System.out.println("히히히ㅣ힣헤히히헤");
	}
	
}
