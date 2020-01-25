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

import com.scalesampark.dao.MessageDao;
import com.scalesampark.domains.Message;

@Path("/messages")
public class MessagesService {
	MessageDao dao = new MessageDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		try {
			return Response.status(Response.Status.OK).entity(dao.getAll()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Message message) {
		try {
			long id = dao.save(message);
			return Response.status(Response.Status.CREATED).entity(id).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getAllUnseenMessagesByParticipant(@PathParam("id") long participantId) {
		try {
			List<Message> messages = dao.getAllUnseenMessagesByParticipant(participantId);
			if (messages.size() > 0) {
				return Response.status(Response.Status.OK).entity(messages).build();
			} else {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
