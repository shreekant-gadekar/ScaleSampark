package com.scalesampark.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.scalesampark.dao.EmployeeDAO;
import com.scalesampark.domains.Employee;
 

@Path("/employees")
public class EmployeeService {
	EmployeeDAO dao = new EmployeeDAO();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAll() {
        List<Employee> employees = dao.getAll();
        ResponseBuilder builder = Response.status(Response.Status.OK).entity(employees);
        System.out.println(builder.build());
        return employees;
    }

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response get(@PathParam("id") long id) {
		String message;
		try {
			return Response.status(Response.Status.OK).entity(dao.get(id)).build();
		} catch (Exception e) {
			message = "{\"status\": \"failure\",\"code\": \"400\",\"message\": \"Bad request\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		}
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Employee t) {
		long id = dao.save(t);
		String message = "{\"status\": \"success\",\"code\": \"201\",\"id\": \""+id+"\"}";
		return Response.status(Response.Status.OK).entity(message).build();
	}
}
