package org.doit.ik.aop2;

import org.doit.ik.aop.Calculator;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex02 {

	public static void main(String[] args) {
		
		// p2204 스프링 AOP
		// 1. Calculator 인터페이스 + - * /
		// 2. CalculatorImpl 클래스
		// 3. Ex01에서 테스트
		
	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("application-context2.xml");
	//Calculator calc = ctx.getBean("calc",Calculator.class);
	Calculator calc = ctx.getBean("calcProxy",Calculator.class);
	System.out.println(calc.add(3, 4));
	
	
	
	System.out.println("히히히ㅣ힣헤히히헤");
	}
	
}
