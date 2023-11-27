package org.doit.ik.aop;

public class Ex01 {

	public static void main(String[] args) {
		System.out.println("히히히ㅣ힣헤히히헤");
		// p2204 스프링 AOP
		// 1. Calculator 인터페이스 + - * /
		// 2. CalculatorImpl 클래스
		// 3. Ex01에서 테스트
		
		CalculatorImpl calc = new CalculatorImpl();
		System.out.println(calc.add(1, 3));
		
	}
	
}
