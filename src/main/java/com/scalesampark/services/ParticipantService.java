package com.scalesampark.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.scalesampark.dao.ParticipantDao;
import com.scalesampark.domains.Participant;
import com.scalesampark.validator.ValidatorUtil;

@Path("/participants")
public class ParticipantService {
	ParticipantDao dao = new ParticipantDao();
	
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Participant> getAll() {
        return dao.getAll();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Participant participant) {
		String message;
		try {
			if (ValidatorUtil.isValidParticipant(participant)) {
				if (! ValidatorUtil.isParticipantAlreadyPresent(participant)) {
					long id = dao.save(participant);
					message = "{\"status\": \"success\",\"code\": \"201\",\"id\": \""+id+"\"}";
					return Response.status(Response.Status.OK).entity(message).build();
				} else {
					message = "{\"status\": \"failure\",\"code\": \"201\",\"message\": \"Participant already present\"}";
					return Response.status(Response.Status.CONFLICT).entity(message).build();
				}
			} else {
				message = "{\"status\": \"failure\",\"code\": \"201\",\"message\": \"No such Employee\"}";
				return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
			}
		} catch (Exception exception) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	/*public long update(Participant t, String[] params) {
		return 0;
	}*/

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response delete(@PathParam("id") long id) {
		int rowCount = dao.delete(id);
		String message;
		if(rowCount >0) {
			message = "{\"status\": \"success\",\"code\": \"201\",\"message\": \"Deleted successfully\"}";
		} else {
			message = "{\"status\": \"success\",\"code\": \"201\",\"message\": \"Not Deleted\"}";
		}
		return Response.status(Response.Status.OK).entity(message).build();
	}
}
