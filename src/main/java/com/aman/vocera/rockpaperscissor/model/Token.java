package com.aman.vocera.rockpaperscissor.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOKENS")
public class Token {
	@Id
	@Column(name = "generated_token", nullable = false)
	private String token;
	@Column(name = "status")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Token() {
		super();
		this.message = "READY";
		Instant instant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
		this.token = Base64.getEncoder().encodeToString(String.valueOf(instant.toEpochMilli()).getBytes());
	}

}
