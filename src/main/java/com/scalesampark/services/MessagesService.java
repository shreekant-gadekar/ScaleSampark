package com.scalesampark.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			Map<String, Object> map = new HashMap<String, Object>(1);
			map.put("messages", dao.getAll());
			return Response.status(Response.Status.OK).entity(map).build();
		} catch (Exception e) {
			Map<String, Object> map1 = new HashMap<String, Object>(1);
			map1.put("status","failure");
			map1.put("code","400");
			map1.put("message","Bad request");
			return Response.status(Response.Status.BAD_REQUEST).entity(map1).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Message message) {
		try {
			long id = dao.save(message);
			Map<String, Object> map1 = new HashMap<String, Object>(1);
			map1.put("status","success");
			map1.put("code","201");
			map1.put("message","Message created succefully.");
			map1.put("data",id);
			return Response.status(Response.Status.CREATED).entity(map1).build();
		} catch (Exception e) {
			Map<String, Object> map1 = new HashMap<String, Object>(1);
			map1.put("status","failure");
			map1.put("code","400");
			map1.put("message","Bad request");
			return Response.status(Response.Status.BAD_REQUEST).entity(map1).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getAllUnseenMessagesByParticipant(@PathParam("id") long participantId) {
		try {
			Map<String, Object> map = new HashMap<String, Object>(1);
			List<Message> messages = dao.getAllUnseenMessagesByParticipant(participantId);
			map.put("messages", messages);
			if (messages.size() > 0) {
				return Response.status(Response.Status.OK).entity(map).build();
			} else {
				Map<String, Object> map1 = new HashMap<String, Object>(1);
				map1.put("status","failure");
				map1.put("code","204");
				map1.put("message","No Content");
				return Response.status(Response.Status.NO_CONTENT).entity(map1).build();
			}
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
