package com.scalesampark.domains;

import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicUpdate
public class Participant {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long participantId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="nickname")
	private String nickname;
	
	@Column(name="created_on")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdOn;

	@Column(name="last_updated")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastUpdated;
	
	public Participant(){}
	/**
	 * @param participantId
	 * @param email
	 * @param nickname
	 * @param createdOn
	 * @param lastUpdated
	 */
	public Participant(long participantId, String email, String nickname, Calendar createdOn,
			Calendar lastUpdated) {
		super();
		this.participantId = participantId;
		this.email = email;
		this.nickname = nickname;
		this.createdOn = createdOn;
		this.lastUpdated = lastUpdated;
	}

	/**
	 * @return the participantId
	 */
	public long getParticipantId() {
		return participantId;
	}

	/**
	 * @param participantId the participantId to set
	 */
	public void setParticipantId(long participantId) {
		this.participantId = participantId;
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
	 * @return the createdOn
	 */
	public Calendar getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the lastUpdated
	 */
	public Calendar getLastUpdated() {
		return lastUpdated;
	}
	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Calendar lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
