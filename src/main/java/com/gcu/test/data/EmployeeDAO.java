package com.gcu.test.data;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.test.mapper.EmployeeRowMapper;
import com.gcu.test.models.EmployeeModel;
import com.gcu.test.repository.EmployeeRepository;

@Service
public class EmployeeDAO implements EmployeeAccess<EmployeeModel> {

	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void createEmployee (EmployeeModel employee) {
		try {
			empRepo.save(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public EmployeeModel findEmployee(Long id) {
		Optional<EmployeeModel> gotten = empRepo.findById(id);
		if(gotten.isPresent()) {
			return gotten.get();
		} else {
			return null;	
		}
	}

	@Override
	public List<EmployeeModel> findEmployeesAsList(String searchcriteria) {
		String sql = "SELECT * FROM employee WHERE LOWER(first_name) LIKE '%"+searchcriteria.toLowerCase()+"%' || LOWER(last_name) LIKE '%"+searchcriteria.toLowerCase()+"%'|| LOWER(username) LIKE '%"+searchcriteria.toLowerCase()+"%'|| LOWER(email) LIKE '%"+searchcriteria.toLowerCase()+"%'"
				+ "|| LOWER(phone) LIKE '%"+searchcriteria.toLowerCase()+"%'|| LOWER(position) LIKE '%"+searchcriteria.toLowerCase()+"%'"
				+ "|| LOWER(bio) LIKE '%"+searchcriteria.toLowerCase()+"%'";
		List<EmployeeModel> found = jdbcTemplateObject.query(sql, new EmployeeRowMapper());
		return found;
	}
	
	@Override
	public EmployeeModel findEmployeeByUsername(String searchcriteria) {
		String sql = "SELECT * FROM employee WHERE username LIKE '"+searchcriteria+"'";
		List<EmployeeModel> listFound = (List<EmployeeModel>) jdbcTemplateObject.query(sql, new EmployeeRowMapper());
		EmployeeModel found = new EmployeeModel();
		if(listFound.size()>=1) {
			found = (EmployeeModel)listFound.get(0);	
		}
		return found;
	}

	@Override
	public List<EmployeeModel> getEmployees() {
		List<EmployeeModel> gotten = (List<EmployeeModel>)empRepo.findAll();
		return gotten;
	}

	@Override
	public void deleteEmployee(EmployeeModel obj) {
		try {
			empRepo.delete(obj);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
