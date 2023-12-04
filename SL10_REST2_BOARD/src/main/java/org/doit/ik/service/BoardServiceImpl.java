package org.doit.ik.service;

import java.util.List;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.IpData;
import org.doit.ik.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor  // 스프링 4.3 버전 부터 생성자 DI에 의해서 자동으로 객체 주입된다
@Log4j
public class BoardServiceImpl implements BoardService{

	//@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("BoardServiceImpl getList");
		return this.boardMapper.getList();
	}

	@Override
	public void register(BoardVO board) {
		log.info("BoardServiceImpl register");
		//this.boardMapper.insert(board);
		this.boardMapper.insertSelectKey(board);
		
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("BoardServiceImpl get");		
		return this.boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("BoardServiceImpl modify");		
		return this.boardMapper.update(board) ==1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("BoardServiceImpl remove");
		return this.boardMapper.delete(bno) ==1;
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria criteria) {
		log.info("BoardServiceImpl getListWithPaging");
		return this.boardMapper.getListWithPaging(criteria);
	}

	@Override
	public int getTotal(Criteria criteria) {
		log.info("BoardServiceImpl getTotal");
		return this.boardMapper.getTotalCount(criteria);
	}

	@Override
	public int insertIp(IpData ipData) {
		// TODO Auto-generated method stub
		return this.boardMapper.insertIp(ipData);
	}

}