package com.logaggregator.domain;

import java.time.LocalDateTime;

/**
 * @author dipika
 *
 */
public class Session {

	int sessionId;
	long durationInMinutes;
	LocalDateTime sessionStartTime;
	LocalDateTime sessionLastAccessTime;
	LocalDateTime sessionEndTime;

	/**
	 * @param sessionId
	 * @param sessionStartTime
	 */
	public Session(int sessionId, LocalDateTime sessionStartTime) {
		super();
		this.sessionId = sessionId;
		this.sessionStartTime = sessionStartTime;
		this.sessionLastAccessTime = this.sessionStartTime;
	}

	public LocalDateTime getSessionLastAccessTime() {
		return sessionLastAccessTime;
	}

	public void setSessionLastAccessTime(LocalDateTime sessionLastAccessTime) {
		this.sessionLastAccessTime = sessionLastAccessTime;
	}

	public long getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(long durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public LocalDateTime getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(LocalDateTime sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public LocalDateTime getSessionEndTime() {
		return sessionEndTime;
	}

	public void setSessionEndTime(LocalDateTime sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}

}
