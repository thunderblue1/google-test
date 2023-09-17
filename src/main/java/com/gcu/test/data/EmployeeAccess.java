package com.gcu.test.data;

import java.util.List;

public interface EmployeeAccess<T> {
	public T findEmployee(Long id);
	public List<T> findEmployeesAsList(String searchcriteria);
	public T findEmployeeByUsername(String searchcriteria);
	public List<T> getEmployees();
	public void createEmployee(T obj);
	public void deleteEmployee(T obj);
}
