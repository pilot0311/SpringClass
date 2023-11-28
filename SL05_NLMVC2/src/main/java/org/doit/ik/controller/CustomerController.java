package org.doit.ik.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/customer/*")
public class CustomerController {
	@Autowired
	private NoticeDao noticeDao;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping("/noticeDel.htm")
	public String del(@RequestParam("seq")String seq) throws ClassNotFoundException, SQLException {
		 int row = this.noticeDao.delete(seq);
		 if (row == 1) {
				return "redirect:notice.htm";
		}else {
			return "redirect:noticeDetail.htm?seq="+seq+"&error";
		}
		
		}
		
	
	@PostMapping("/noticeEdit.htm")
	public String noticeEdit(NoticeVO notice) throws ClassNotFoundException, SQLException {
		 int row = this.noticeDao.update(notice);
		 if (row == 1) {
			 return "redirect:noticeDetail.htm?seq="+notice.getSeq();
		}else {
			return "redirect:notice.htm";
		}
		
	}
	
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq")String seq, Model model) throws ClassNotFoundException, SQLException {
		NoticeVO notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice",notice);
		return "noticeEdit.jsp";
	}
	
	
	@PostMapping(value = "/noticeReg.htm")
	public String noticeReg(String title, String content, NoticeVO notice)throws ClassNotFoundException, SQLException{
		
		// 작성자 X 로그인 햐야지만 글작성 가능(spring.sequrity)
		// 세션 사용
		notice.setWriter("pilot");
		int insertCount = this.noticeDao.insert(notice);
		if (insertCount == 1) {
			return "redirect:notice.htm";
		}else {
			return "noticeReg.jsp?error";
		}
		
	}
	
	
	@GetMapping(value = "/noticeReg.htm")
	public String noticeReg() throws ClassNotFoundException, SQLException {
		return "noticeReg.jsp";
	}
	
	@GetMapping(value = "/noticeDetail.htm")
	public String noticeDetail( @RequestParam("seq")String seq, Model model ) throws ClassNotFoundException, SQLException {
		 
		 NoticeVO noticeVO = this.noticeDao.getNotice(seq);
		 model.addAttribute("notice", noticeVO);
		 
		return "noticeDetail.jsp";
	}
	
	@GetMapping(value = "/notice.htm")
	public String notices(@RequestParam(value="page",defaultValue = "1")int page,
			@RequestParam(value = "field",defaultValue = "title")String field,
			@RequestParam(value = "query", defaultValue = "")String query,
			Model model) throws ClassNotFoundException, SQLException {
		
		List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		model.addAttribute("list", list);
		model.addAttribute("name", "hello");
		
		return "notice.jsp";
	}
	
	
	/*
	@GetMapping(value = "/notice.htm")
	public ModelAndView notice(Locale locale, Model model, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		ModelAndView mav = new ModelAndView();
		 
		 String ppage = request.getParameter("page");
		 String pfield = request.getParameter("field");
		 String pquery = request.getParameter("query");
		 
		 int page = 1;
		 String field = "title";
		 String query = "";
		 
		 if (ppage != null && !ppage.equals("")) { page = Integer.parseInt(ppage); }
		 if (pfield != null && !pfield.equals("")) { field =pfield; }
		 if (pquery != null && !pquery.equals("")) { query = pquery; }
			
		 List<NoticeVO> list = this.noticeDao.getNotices(page, field, query);
		 
		 mav.addObject("list", list);
		 mav.addObject("name", "pilot");
		 mav.setViewName("notice.jsp");
		 
		return mav;
	}
	*/
}
