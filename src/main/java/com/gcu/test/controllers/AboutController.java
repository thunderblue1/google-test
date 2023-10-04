package com.gcu.test.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
	private static final Logger logger = LoggerFactory.getLogger(AboutController.class);
	
	@RequestMapping({"/","about"})
	public String index() {
		logger.info("Method: index, Entering method for route / and about. Loading about page.");
		logger.debug("Method: index, Testing Debug level for logger.");
		logger.warn("Method: index,  Testing Warn level for logger.");
		logger.error("Method: index,  Testing Error level for logger.");
		logger.info("Method: index, Exiting method for route / and about. Loading about page.");
		return "about.html";
	}
}
