package org.doit.ik.aop;

public class CalculatorImpl implements Calculator {

	@Override
	public int add(int x, int y) {
		Long start = System.nanoTime();
		
		int result = x+y;		//핵심 기능
		Long end = System.nanoTime();
		System.out.println(end-start);
		return result;
	}

	@Override
	public int sub(int x, int y) {
		int result = x-y;
		return result;
	}

	@Override
	public int mult(int x, int y) {
		int result = x*y;
		return result;
	}

	@Override
	public int div(int x, int y) {
		int result = x/y;
		return result;
	}

}
// 스프링 3가지 AOP 구현 방식
// 1) 스프링 API를 이용한 AOP 구현
//		org.doit.ik.aop2