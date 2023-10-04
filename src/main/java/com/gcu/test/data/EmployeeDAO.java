package com.gcu.test.data;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.test.mapper.EmployeeRowMapper;
import com.gcu.test.models.EmployeeModel;
import com.gcu.test.repository.EmployeeRepository;

@Service
public class EmployeeDAO implements EmployeeAccess<EmployeeModel> {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAO.class);
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void createEmployee (EmployeeModel employee) {
		logger.info("Method createEmployee, Entering method.");
		try {
			empRepo.save(employee);
		} catch (Exception e) {
			logger.error("Method createEmployee, Could not save employee with repository method.");
			e.printStackTrace();
		}
		logger.info("Method createEmployee, Exiting method.");
	}

	@Override
	public EmployeeModel findEmployee(Long id) {
		logger.info("Method findEmployee, Entering method.");
		try {
			Optional<EmployeeModel> gotten = empRepo.findById(id);
			if(gotten.isPresent()) {
				return gotten.get();
			}
		} catch (Exception e) {
			logger.error("Method findEmployee, Could not findById employee with repository method.");
			e.printStackTrace();
		}
		logger.info("Method findEmployee, Exiting method.");
		return null;
	}

	@Override
	public List<EmployeeModel> findEmployeesAsList(String searchcriteria) {
		logger.info("Method findEmployeesAsList, Entering method.");
		String sql = "SELECT * FROM employee WHERE LOWER(first_name) LIKE '%"+searchcriteria.toLowerCase()+"%' || LOWER(last_name) LIKE '%"+searchcriteria.toLowerCase()+"%'|| LOWER(username) LIKE '%"+searchcriteria.toLowerCase()+"%'|| LOWER(email) LIKE '%"+searchcriteria.toLowerCase()+"%'"
				+ "|| LOWER(phone) LIKE '%"+searchcriteria.toLowerCase()+"%'|| LOWER(position) LIKE '%"+searchcriteria.toLowerCase()+"%'"
				+ "|| LOWER(bio) LIKE '%"+searchcriteria.toLowerCase()+"%'";
		List<EmployeeModel> found = jdbcTemplateObject.query(sql, new EmployeeRowMapper());
		logger.info("Method findEmployeesAsList, Exiting method.");
		return found;
	}
	
	@Override
	public EmployeeModel findEmployeeByUsername(String searchcriteria) {
		logger.info("Method findEmployeeByUsername, Entering method.");
		String sql = "SELECT * FROM employee WHERE username LIKE '"+searchcriteria+"'";
		List<EmployeeModel> listFound = (List<EmployeeModel>) jdbcTemplateObject.query(sql, new EmployeeRowMapper());
		EmployeeModel found = new EmployeeModel();
		if(listFound.size()>=1) {
			found = (EmployeeModel)listFound.get(0);	
		}
		logger.info("Method findEmployeeByUsername, Exiting method.");
		return found;
	}

	@Override
	public List<EmployeeModel> getEmployees() {
		logger.info("Method getEmployees, Entering method.");
		List<EmployeeModel> gotten = (List<EmployeeModel>)empRepo.findAll();
		logger.info("Method getEmployees, Exiting method.");
		return gotten;
	}

	@Override
	public void deleteEmployee(EmployeeModel obj) {
		logger.info("Method deleteEmployee, Entering method.");
		try {
			empRepo.delete(obj);
		} catch(Exception e) {
			logger.error("Method deleteEmployee, Could not delete employee using repository");
			e.printStackTrace();
		}
		logger.info("Method deleteEmployee, Exiting method.");
	}
}
