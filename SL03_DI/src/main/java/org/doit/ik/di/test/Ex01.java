package org.doit.ik.di.test;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;

public class Ex01 {

	public static void main(String[] args) {
		
		System.out.println("Hello World");
		// 스프링 컨테이너
		// 1. org.doit.ik.di 패키지
		//		ㄴ Record.java 인터페이스
		//		ㄴ RecordImpl.java 클래스
		//		ㄴ RecordView.java 인터페이스
		//		ㄴ RecordViewImpl.java 클래스
		
		RecordImpl record = new RecordImpl();
		// 생성자를 통한 DI
		//RecordViewImpl rvi = new RecordViewImpl(record);
		//Setter를 통한 DI
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record);
		
		rvi.input();
		rvi.output();
		
		System.out.println("END");
		
		// 스프링에서 처럼 설정파일 + 스프링 컨텍스트 : 객체생성 + 조립
		
	}

}
