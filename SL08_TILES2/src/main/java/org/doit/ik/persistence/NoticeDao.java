package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.List;

import org.doit.ik.domain.NoticeVO;

public interface NoticeDao {
	
	
	// 공지사항 총 갯수를 반환
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	// 공지 사항 목록
	public List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	// 공지사항 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	
	// 공지사항 수정
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException;
	
	// 공지사항 조회
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException;
	
	// 공지사항 추가
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException; 
	
	// 트랜잭션 처리 위한 메서드
	//void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException;
	
	//조회수 증가
	void hitUp(String seq);
	
	// 조회수 가져오기
	int getHit(String seq);
}
