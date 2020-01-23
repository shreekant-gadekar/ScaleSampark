package com.scalesampark.domains;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class MessageType {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long messageTypeId;
	
	@Column
	private String messageTypeName;

	/**
	 * @return the messageTypeId
	 */
	public long getMessageTypeId() {
		return messageTypeId;
	}

	/**
	 * @param messageTypeId the messageTypeId to set
	 */
	public void setMessageTypeId(long messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	/**
	 * @return the messageTypeName
	 */
	public String getMessageTypeName() {
		return messageTypeName;
	}

	/**
	 * @param messageTypeName the messageTypeName to set
	 */
	public void setMessageTypeName(String messageTypeName) {
		this.messageTypeName = messageTypeName;
	}
}
