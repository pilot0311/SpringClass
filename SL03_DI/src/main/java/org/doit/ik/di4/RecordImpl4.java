package org.doit.ik.di4;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component	//recordImpl4 빈객체 ID(이름)
public class RecordImpl4 implements Record4{
	
	private int kor;
	private int eng;
	private int mat;
	
	@Override
	public int total() {
		// TODO Auto-generated method stub
		return kor + eng + mat;
	}
	@Override
	public double avg() {
		// TODO Auto-generated method stub
		return total()/3.0;
	}

}
