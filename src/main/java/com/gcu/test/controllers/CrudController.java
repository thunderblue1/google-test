package com.gcu.test.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.gcu.test.data.EmployeeAccess;
import com.gcu.test.models.*;

@Controller
public class CrudController {
	
	@Autowired
	EmployeeAccess<EmployeeModel> theDao;
	
	//Get the session
	@Autowired
	HttpSession session;
	
	@RequestMapping("crud")
	public String crud(Model model,  @ModelAttribute("searchform") SearchForm searchform) {
		model.addAttribute("employeeModel", new EmployeeModel());
		
		if(searchform.getSearch()==null) {
			List<EmployeeModel> filled = new ArrayList<EmployeeModel>();
			filled.addAll(theDao.getEmployees());
			session.setAttribute("employees", filled);
		} else {
			session.setAttribute("employees", theDao.findEmployeesAsList(searchform.getSearch()));
		}
		
		return "crud";
	}
	
    @PostMapping("/createEmployee")
    public String createEmployee(@Valid EmployeeModel registerModel, BindingResult bindingResult, Model model)
    {
		if(bindingResult.hasErrors()) {
			System.out.println("Failed");
		} else {
			@SuppressWarnings("unchecked")
			List<EmployeeModel> employeelist = (List<EmployeeModel>) session.getAttribute("employees");
			
			EmployeeModel em = null;			
			if(registerModel.getId()!=null) {
				
				//Get database entry (if exists)
				if(theDao.findEmployee(registerModel.getId())!=null) {
					em = theDao.findEmployee(registerModel.getId()); 				
				}
				
				if(em!=null) {
					//If found an employee display then remove old entry
					for(EmployeeModel emp : employeelist) {
						if(emp.getId() == em.getId()) {
							employeelist.remove(emp);
							break;
						}
					}			
				}
			}

			if(em!=null || registerModel.getId()==null) {
				//Create or Update product if it exists
				theDao.createEmployee(registerModel);			
				
				//Add product to product display
				employeelist.add(registerModel);
			}
			session.setAttribute("employees", employeelist);
	
			model.addAttribute("employeeModel",new EmployeeModel());
		}

		return "crud";
    }
    
    @PostMapping("/updateEmployee")
    public String updateEmployee(@Valid @ModelAttribute("employeeModel") EmployeeModel registerModel,BindingResult bindingResult, Model model)
    {
		if(bindingResult.hasErrors()) {
			System.out.println("Failed");
		} else {
			@SuppressWarnings("unchecked")
			List<EmployeeModel> employeelist = (List<EmployeeModel>) session.getAttribute("employees");
			
			EmployeeModel em = null;			
			if(registerModel.getId()!=null) {
				
				//Get database entry (if exists)
				if(theDao.findEmployee(registerModel.getId())!=null) {
					em = theDao.findEmployee(registerModel.getId()); 				
				}
				
				if(em!=null) {
					//If found an employee display then remove old entry
					for(EmployeeModel emp : employeelist) {
						if(emp.getId() == em.getId()) {
							employeelist.remove(emp);
							break;
						}
					}			
				}
			}

			if(em!=null || registerModel.getId()==null) {
				//Create or Update product if it exists
				theDao.createEmployee(registerModel);			
				
				//Add product to product display
				employeelist.add(registerModel);
			}
			session.setAttribute("employees", employeelist);
	
			model.addAttribute("employeeModel",new EmployeeModel());
		}

		return "crud";
    }
    
	@RequestMapping("update")
	public String updating(@RequestParam(value="employeeid") Long empid, Model model) {
		EmployeeModel gotten = theDao.findEmployee(empid);
		
		if(gotten!=null) {
			model.addAttribute("employeeModel",gotten);
		} else {
			model.addAttribute("employeeModel",new EmployeeModel());			
		}
		System.out.println("Updating: "+empid);
		
		return "crud";
	}
	
	@RequestMapping("delete")
	public String deleting(@RequestParam(value="employeeid") Long empid, Model model) {
	
		//Get database entry
		EmployeeModel em = theDao.findEmployee(empid);
		
		//Remove database entry
		theDao.deleteEmployee(em);

		//Remove from search results
		@SuppressWarnings("unchecked")
		List<EmployeeModel> employeelist = (List<EmployeeModel>) session.getAttribute("employees");
		
		for(EmployeeModel emp : employeelist) {
			if(emp.getId() == em.getId()) {
				employeelist.remove(emp);
				break;
			}
		}
		
		session.setAttribute("employees", employeelist);
		
		model.addAttribute("employeeModel", new EmployeeModel());
		model.addAttribute("updateModel", new EmployeeModel());
		
		System.out.println("Deleting: "+empid);
		
		return "crud";
	}
}
