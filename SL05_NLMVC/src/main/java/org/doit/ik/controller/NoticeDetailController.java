package org.doit.ik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


//  /customer/notice.htm?seq=1
public class NoticeDetailController implements Controller{

	private NoticeDao noticeDao;
	
	public NoticeDetailController(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ModelAndView mav = new ModelAndView("noticeDetail.jsp");
		 
		 String seq = request.getParameter("seq");
		 NoticeVO noticeVO = this.noticeDao.getNotice(seq);
		 mav.addObject("notice", noticeVO);
		 
		return mav;
	}

}
