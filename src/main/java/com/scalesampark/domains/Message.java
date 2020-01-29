package com.scalesampark.domains;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Message implements Comparable<Message> {

	@Id
	@GeneratedValue
	@Column(name="message_uuid")
	private Long messageUuid;
	
	@Column(name="participant_uuid")
	private long participantUuid;
	
	@Column(name="message_type_id")
	private Long messageTypeId;
	
	@Column(name="message")
	private String message;
	
	@Column(name="created_on")
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdOn;

	
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

	@Override
	public int compareTo(Message o) {
		return this.messageUuid.compareTo(o.getMessageUuid());
	}

	/**
	 * @return the messageUuid
	 */
	public Long getMessageUuid() {
		return messageUuid;
	}

	/**
	 * @param messageUuid the messageUuid to set
	 */
	public void setMessageUuid(Long messageUuid) {
		this.messageUuid = messageUuid;
	}
}
