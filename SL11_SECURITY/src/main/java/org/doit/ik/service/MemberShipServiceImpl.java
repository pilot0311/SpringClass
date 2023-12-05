package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberShipServiceImpl implements MemberShipService {
	@Autowired
	private NoticeMapper noticeDao;
	// 6. 전파 방식 설명하기 위해 insertAndPointUpofMember 수정
	@Override
	//@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		this.noticeDao.insert(notice);

		//notice.setTitle(notice.getTitle() + "-2");
		//this.noticeDao.insert(notice);
	}
}
