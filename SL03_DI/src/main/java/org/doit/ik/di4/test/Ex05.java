package org.doit.ik.di4.test;

import org.doit.ik.di.RecordViewImpl;
import org.doit.ik.di2.Config;
import org.doit.ik.di3.RecordViewImpl3;
import org.doit.ik.di4.RecordViewImpl4;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {
		
		String resourceLocations = "application-context4.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		RecordViewImpl4 rvi = (RecordViewImpl4) ctx.getBean("recordViewImpl4");
		
		rvi.input();
		rvi.output();
		
		System.out.println("END");
		
	}
	
}
