package com.scalesampark.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.scalesampark.dao.MessageDao;
import com.scalesampark.domains.MessageType;

@Path("/messagetypes")
public class MessageTypeService {

	MessageDao dao = new MessageDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		try {
			return Response.status(Response.Status.OK).entity(dao.getAllMessageTypes()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(MessageType messageType) {
		try {
			long id = dao.save(messageType);
			return Response.status(Response.Status.CREATED).entity(id).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}