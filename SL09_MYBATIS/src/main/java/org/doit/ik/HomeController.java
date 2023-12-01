package org.doit.ik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@RequestMapping(value = "/", method = RequestMethod.GET)
public class HomeController {
	
	public HomeController() {
		super();
	}
	
	@RequestMapping("index.htm")
	public String home() throws Exception {
		return "home.index";
	}
	
}
