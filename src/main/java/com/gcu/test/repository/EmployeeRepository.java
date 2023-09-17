package com.gcu.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.gcu.test.models.EmployeeModel;

public interface EmployeeRepository extends CrudRepository<EmployeeModel,Long>{

}
