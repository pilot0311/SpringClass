package org.doit.ik.di3.test;

import org.doit.ik.di.RecordViewImpl;
import org.doit.ik.di3.RecordViewImpl3;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex04 {

	public static void main(String[] args) {
		// Ex02.java 복 붙
		String resourceLocations = "application-context3.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		RecordViewImpl3 rvi = (RecordViewImpl3) ctx.getBean("rvi");
		
		rvi.input();
		rvi.output();
		
		System.out.println("END");
		
	}
	
}
