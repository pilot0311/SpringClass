package org.doit.ik.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.MemberVO;



public interface MemberMapper {
	// 회원 정보 반환
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException;
	
	//회원 가입
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	
	// 회원 정보 + 권한 정보를 얻어 오는 메서드
	public MemberVO read(@Param("userid")String userid);
}
