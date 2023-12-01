package org.doit.ik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@RequestMapping("/city/*")
public class CityController {
	
	@GetMapping("/london")
	public String london() {
		log.info("/city/london: londonController");
		return "city/london.tiles";
	}
	
	@GetMapping("/paris")
	public String paris() {
		log.info("/city/paris: parisController");
		return "city/paris.tiles";
	}
		
	@GetMapping("/seoul")
	public String seoul() {
		log.info("/city/seoul:seoulController");
		return "city/seoul.tiles";
	}
	
}
