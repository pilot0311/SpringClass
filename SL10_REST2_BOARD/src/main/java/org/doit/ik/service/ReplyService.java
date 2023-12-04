package org.doit.ik.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.ReplyPageDTO;
import org.doit.ik.domain.ReplyVO;

public interface ReplyService {

	//int insert(ReplyVO vo);
	int register(ReplyVO vo);
	//ReplyVO read(Long rno);
	ReplyVO get(Long rno);
	//int delete(Long rno);
	int remove(Long rno);
	//int update(ReplyVO reply);
	int modify(ReplyVO reply);
	//List<ReplyVO> getListWithPaging(@Param("cri")Criteria cri, @Param("bno") Long bno);
	ReplyPageDTO getListPage(Criteria cri, Long bno);
	//int getCountByBno(Long bno);
	
}
