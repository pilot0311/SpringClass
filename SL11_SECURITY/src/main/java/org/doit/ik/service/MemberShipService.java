package org.doit.ik.service;

import java.sql.SQLException;

import org.doit.ik.domain.NoticeVO;

public interface MemberShipService {
	void insertAndPointUpofMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException;
}
