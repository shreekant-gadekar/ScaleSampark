package com.scalesampark.domains;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicUpdate
public class Participant {
private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="participant_uuid")
	private long participantUuid;
	
	@Column(name="email")
	private String email;
	
	@Column(name="nickname")
	private String nickname;
	
	@Column(name="last_seen")
	@UpdateTimestamp
	private Calendar lastSeen;

	/**
	 * @return the participantUuid
	 */
	public long getParticipantUuid() {
		return participantUuid;
	}

	/**
	 * @param participantUuid the participantUuid to set
	 */
	public void setParticipantUuid(long participantUuid) {
		this.participantUuid = participantUuid;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the lastSeen
	 */
	public Calendar getLastSeen() {
		return lastSeen;
	}

	/**
	 * @param lastSeen the lastSeen to set
	 */
	public void setLastSeen(Calendar lastSeen) {
		this.lastSeen = lastSeen;
	}
}
