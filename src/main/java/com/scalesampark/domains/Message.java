package com.scalesampark.domains;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Message {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long messageId;
	
	@Column(name="participant_id")
	private Long participantId;
	
	@Column(name="message_type_id")
	private Long messageTypeId;
	
	@Column(name="message")
	private String message;
	
	@Column(name="created_on")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdOn;

	/**
	 * @return the messageId
	 */
	public long getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the participantId
	 */
	public Long getParticipantId() {
		return participantId;
	}

	/**
	 * @param participantId the participantId to set
	 */
	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

	/**
	 * @return the messageTypeId
	 */
	public Long getMessageTypeId() {
		return messageTypeId;
	}

	/**
	 * @param messageTypeId the messageTypeId to set
	 */
	public void setMessageTypeId(Long messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	
}
