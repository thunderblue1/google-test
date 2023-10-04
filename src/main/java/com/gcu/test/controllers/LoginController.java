package com.gcu.test.controllers;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.test.data.EmployeeDAO;
import com.gcu.test.models.EmployeeModel;
import com.gcu.test.models.LoginModel;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	EmployeeDAO theDao;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("login")
	public String login(Model model) {
		logger.info("Method: login, Entering method for route login.");
		model.addAttribute("loginModel",new LoginModel());
		logger.info("Method: login, Exiting method for route login.  Loading login page.");
		return "login";
	}
	
	@PostMapping("/loginAttempt")
	public String loginAttempt(Model model, @Valid LoginModel loginModel, BindingResult bindingResult) {
		logger.info("Method: loginAttempt, Entering method for route loginAttempt.");
		if (bindingResult.hasErrors()) {
			System.out.println("Binding result error for login page.");
			session.setAttribute("success", false);
		} else {
			System.out.println("Binding result for login page has no errors.");
			model.addAttribute("loginModel",new LoginModel());
			EmployeeModel emp = (EmployeeModel)theDao.findEmployeeByUsername(loginModel.getUsername());

			String empid = (emp.getPass().matches(loginModel.getPassword()))? emp.getId().toString():"false";
			session.setAttribute("empid", empid);
			if(!empid.matches("false")) {
				return "redirect:profile";
			}
		}
		logger.info("Method: loginAttempt, Exiting method for route loginAttempt.  Loading login page.");
		return "login";
	}
}