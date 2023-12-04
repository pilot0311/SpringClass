package org.doit.ik.service;

import org.doit.ik.domain.Criteria;
import org.doit.ik.domain.ReplyPageDTO;
import org.doit.ik.domain.ReplyVO;
import org.doit.ik.mapper.ReplyMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	private ReplyMapper replyMapper;
	
	@Override
	public int register(ReplyVO vo) {
		log.info("register");
		return this.replyMapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get");		
		return this.replyMapper.read(rno);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove");
		return this.replyMapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO reply) {
		log.info("modify");
		return this.replyMapper.update(reply);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		log.info("getListPage + count of a Board" + bno);
		return new ReplyPageDTO(this.replyMapper.getListWithPaging(cri, bno),this.replyMapper.getCountByBno(bno));
	}

}
