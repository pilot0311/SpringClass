package org.doit.ik.domain;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDTO {

	private int empno;
    private String ename;
    private String job;
    private int mgr;
    private String hiredate;
    private double sal;
    private double comm;
    private int deptno;
	
}
