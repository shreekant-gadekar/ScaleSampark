package com.scalesampark.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.scalesampark.daos.EmployeeDAO;
import com.scalesampark.domains.Employee;
 

@Path("/employees")
public class EmployeeService {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public List<Employee> getEmployee() {
        EmployeeDAO dao = new EmployeeDAO();
        List employees = dao.getEmployees();
        return employees;
    }
}
