package org.doit.ik.di2;

import org.doit.ik.di.RecordImpl;
import org.doit.ik.di.RecordViewImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// application-context.xml == 자바 설정 파일
// p 85
@Configuration
public class Config {
	
	@Bean
	public RecordImpl record() {
		return new RecordImpl();
	}

	// setter
	@Bean(name = "rvi")
	public RecordViewImpl getRecoredViewImple() {
		RecordViewImpl rvi = new RecordViewImpl();
		rvi.setRecord(record());
		return rvi;
	}
	
	// 생성자
//	@Bean
//	public RecordViewImpl rvi() {
//		return new RecordViewImpl(record());
//	}
	
	
}
