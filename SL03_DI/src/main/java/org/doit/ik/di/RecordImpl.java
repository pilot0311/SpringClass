package org.doit.ik.di;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordImpl implements Record{
	
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
