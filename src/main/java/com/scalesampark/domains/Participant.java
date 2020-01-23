package com.scalesampark.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Participant {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long participantId;
	
	@Column
	private String email;
	
	@Column
	private String nickname;
	
	@Column
	private String password;
	
	@Column
	private java.time.LocalDateTime lastUpdated;
	
	@Column
	private java.time.LocalDateTime createdOn;
}
