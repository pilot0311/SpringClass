package org.doit.ik.di3;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordViewImpl3 implements RecordView3{
	//@Autowired(required = false)
	//@Resource(name="record1")
	@Inject
	@Named(value = "record2")
	private RecordImpl3 record = null;
	//private RecordImpl record = new RecordImpl();
	
	@Override
	public void input() {
		try(Scanner scanner = new Scanner(System.in)) {
			System.out.print("> kor, eng, mat");
			int kor = scanner.nextInt();
			int eng = scanner.nextInt();
			int mat = scanner.nextInt();
 
			this.record.setKor(kor);
			this.record.setEng(eng);
			this.record.setMat(mat);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void output() {
		System.out.printf("> kor = %d, eng = %d, mat = %d, tot = %d, avg = %.2f\n",
				this.record.getKor()
				,this.record.getEng()
				,this.record.getMat()
				,this.record.total()
				,this.record.avg());
		
	}

	
}
