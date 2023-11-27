package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.IpData;

public interface BoardService {

	List<BoardVO> getList();
	void register(BoardVO board);
	BoardVO get(Long bno);
	boolean modify(BoardVO board);
	boolean remove(Long bno);
	// 페이징 토잘
	List<BoardVO> getListWithPaging(Criteria criteria);
	int getTotal(Criteria criteria);
	
	// 로그 기록
	int insertIp(IpData ipData);
}
