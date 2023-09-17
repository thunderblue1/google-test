package com.gcu.test.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.test.data.EmployeeDAO;
import com.gcu.test.models.EmployeeModel;


@Controller
public class ProfileController {
	
	@Autowired
	EmployeeDAO theDao;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping("profile")
	public String index(Model model) {
		EmployeeModel user = new EmployeeModel();
		String empid = null;
		if(session.getAttribute("empid")!=null) {		
			empid=session.getAttribute("empid").toString();
		}
		if(empid!=null) {
			if(!empid.matches("false")) {
				Long id = Long.parseLong(empid);
				user = theDao.findEmployee(id);
				if (user==null) {
					session.setAttribute("empid", "false");
				}
			}			
		}
		model.addAttribute("user", user);
		return "profile";
	}
}