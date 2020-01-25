package com.scalesampark.validator;

import java.util.List;

import com.scalesampark.dao.EmployeeDAO;
import com.scalesampark.dao.ParticipantDao;
import com.scalesampark.domains.Employee;
import com.scalesampark.domains.Participant;

public class ValidatorUtil {
	public static boolean isValidParticipant(Participant participant) {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List<Employee> employees = employeeDAO.getAll();
		long count = employees.stream().filter(e -> e.getEmail().equalsIgnoreCase(participant.getEmail())).count();
		return (count > 0);
	}
	
	public static boolean isParticipantAlreadyPresent(Participant participant) {
		ParticipantDao participantDao = new ParticipantDao();
		List<Participant> participants = participantDao.getAll();
		long count = participants.stream().filter(e -> e.getEmail().equalsIgnoreCase(participant.getEmail())).count();
		return (count > 0);
	}
}
