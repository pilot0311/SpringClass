package org.doit.ik.mapper;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.IpData;

public interface BoardMapper {
	
	List<BoardVO> getList();
	
	void insert(BoardVO board);
	
	void insertSelectKey(BoardVO board);
	
	BoardVO read(Long bno);
	
	int update(BoardVO board);
	
	int delete( Long bno);
	// 페이징 처리
	List<BoardVO> getListWithPaging(Criteria criteria);
	int getTotalCount(Criteria criteria);
	
	
	// 로그 기록
	int insertIp(IpData ipData);
}
