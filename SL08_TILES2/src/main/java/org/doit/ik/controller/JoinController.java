package org.doit.ik.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.persistence.MemberDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@RequestMapping(value = "/joinus/*")
public class JoinController {
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/login.htm")
	public String login()throws Exception{
		return "joinus.login";
	}
	
	@GetMapping("join.htm")
	public String join()throws Exception{
		return "joinus.join";
	}
	
	@PostMapping("join.htm")
	public String join(MemberVO member)throws Exception{
		this.memberDao.insert(member);
		return "redirect:../index.htm";
	}
	
}
