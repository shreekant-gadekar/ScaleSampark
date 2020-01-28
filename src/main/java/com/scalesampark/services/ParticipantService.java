package com.scalesampark.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;

import com.scalesampark.dao.ParticipantDao;
import com.scalesampark.domains.Participant;
import com.scalesampark.dto.ParticipantDto;
import com.scalesampark.validator.ValidatorUtil;

@Path("/participants")
public class ParticipantService {
	ParticipantDao dao = new ParticipantDao();
	String badRequest = "{\"status\": \"failure\",\"code\": \"400\",\"message\": \"Bad request\"}";
	Map<String, Object> defaultResMap = new HashMap<String, Object>(){
		{		
		put("status","failure");
		put("code","400");
		put("message","Bad request");
		}
	};
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response get(@PathParam("id") long id) {
        try {
        	Map<String, Object> map = new HashMap<>(1);
        	map.put("participant", convertToDto(dao.get(id)));
//        	return new ResponseBuilder("participants", convertToDto(dao.get(id))).getResponse();
			return Response.status(Response.Status.OK).entity(map).build();
		} catch (Exception e) {
			Map badReq = defaultResMap;
			badReq.put("status","failure");
			badReq.put("code","400");
			badReq.put("message","Bad request");
//			return badReq;
			return Response.status(Response.Status.BAD_REQUEST).entity(badReq).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
		public Response getAll() {
		List<ParticipantDto> list = dao.getAll().stream().map(this::convertToDto).collect(Collectors.toList());
		Map<String, Object> map = new HashMap<>(1);
    	map.put("participants", list);
		return Response.status(Response.Status.OK).entity(map).build();
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
					Map<String, Object> map = new HashMap<>(1);
			    	map.put("participantUuid", id);
					return Response.status(Response.Status.OK).entity(map).build();
				} else {
					Map<String, Object> badRequest = defaultResMap;
					badRequest.put("status", "failure");
					badRequest.put("code", "204");
					badRequest.put("message", "Participant already present");
					return Response.status(Response.Status.CONFLICT).entity(badRequest).build();
				}
			} else {
				Map<String, Object> badRequest1 = defaultResMap;
				badRequest1.put("code", "201");
				badRequest1.put("status", "failure");
				badRequest1.put("message", "No such Employee");
				return Response.status(Response.Status.BAD_REQUEST).entity(badRequest1).build();
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
		Map<String, Object> map = defaultResMap;
		map.put("status", "success");
		map.put("code", "201");
		
		if(rowCount > 0) {
			map.put("message", "Deleted successfully");
		} else {
			map.put("message", "Not Deleted");
		}
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	private ParticipantDto convertToDto(Participant participant) {
		ModelMapper modelMapper = new ModelMapper();
		ParticipantDto dto = modelMapper.map(participant, ParticipantDto.class);
		
		dto.setLastSeen(convertDateToTimestampString(participant.getLastSeen()));
		return dto;
	}
	
	private String convertDateToTimestampString(Calendar date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date.getTime());
	}
}