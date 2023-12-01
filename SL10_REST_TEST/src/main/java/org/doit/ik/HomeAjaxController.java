package org.doit.ik;

import java.util.List;

import org.doit.ik.domain.DeptDTO;
import org.doit.ik.domain.EmpDTO;
import org.doit.ik.domain.EmpVO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@RestController
@Log4j
//@ResponseBody
public class HomeAjaxController {
	@Setter(onMethod=@__({@Autowired}))
	private MemberMapper memberMapper;
	
	@GetMapping("/idCheck")
	public EmpVO idCheck(String empno) {
		log.info("Ajax.get: "+ empno);
		
		int idCheckResult = this.memberMapper.idCheck(empno);
		return new EmpVO(empno, "hong", idCheckResult);
	}
	
	@PostMapping("/scott/dept/new")
	public ResponseEntity<String> insertDept(@RequestBody DeptDTO dto) {
		log.info("Ajax.post: insertDept");
		int insertResult = this.memberMapper.insertDept(dto);
		return insertResult == 1 ? new ResponseEntity<>("SUCCESS", HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 		
	}
	
	@DeleteMapping(value="/scott/dept/{deptno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delDept(@PathVariable("deptno")int deptno) {
		log.info("Ajax.post: delete");
		int delResult = this.memberMapper.delDept(deptno);
		return delResult == 1 ? new ResponseEntity<>("SUCCESS", HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 		
	}
	
	@GetMapping("/deptEmp/{deptno}")
	public List<EmpDTO> deptEmp(@PathVariable("deptno") int deptno){
		List<EmpDTO> list = this.memberMapper.deptEmp(deptno);
		return list;
	}
	
	/*
	@GetMapping("/idCheck")
	public int idCheck(String empno) {
		log.info("Ajax.get: "+ empno);
		
		return this.memberMapper.idCheck(empno);
	}
	*/
	
}
