package com.scalesampark.dto;

import java.io.Serializable;

public class ParticipantDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String participantUuid;
//	private String email;
	private String nickname;
	private String lastSeen;
	
	/**
	 * @return the participantUuid
	 */
	public String getParticipantUuid() {
		return participantUuid;
	}
	/**
	 * @param participantUuid the participantUuid to set
	 */
	public void setParticipantUuid(String participantUuid) {
		this.participantUuid = participantUuid;
	}
	/**
	 * @return the email
	 *//*
	public String getEmail() {
		return email;
	}
	*//**
	 * @param email the email to set
	 *//*
	public void setEmail(String email) {
		this.email = email;
	}*/
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
	public String getLastSeen() {
		return lastSeen;
	}
	/**
	 * @param lastSeen the lastSeen to set
	 */
	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}
	
	@Override
	public String toString() {
		return "[participantUuid=" + participantUuid + ", nickname=" + nickname
				+ ", lastSeen=" + lastSeen + "]";
	}
}
