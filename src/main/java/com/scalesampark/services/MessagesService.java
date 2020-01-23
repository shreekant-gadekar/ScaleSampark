package com.scalesampark.services;

import javax.persistence.EntityTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;

import com.scalesampark.dao.SessionUtil;
import com.scalesampark.domains.Employee;

@Path("/messages")
public class MessagesService {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		Session session = SessionUtil.getSession();
		EntityTransaction tx = null;
		tx = session.beginTransaction();
		Employee employee = new Employee();
		employee.setEmail("shrikant.gadekar@gmail.com");
		employee.setName("Shrikant Gadekar");
		session.save(employee);
		tx.commit();
		return employee.toString();
	}
}
