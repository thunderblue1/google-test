package com.gcu.test.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.test.models.EmployeeModel;

public class EmployeeRowMapper implements RowMapper<EmployeeModel>{

	@Override
	public EmployeeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new EmployeeModel(rs.getLong("employee_id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"),rs.getString("phone"),rs.getString("position"),rs.getString("bio"),rs.getString("photo"),rs.getString("username"),rs.getString("pass"));
	}

}
