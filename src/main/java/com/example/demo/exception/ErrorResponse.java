package com.example.demo.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	private LocalDateTime timestamp;
	private String error;
	private String message;
	private int status;
	
	public ErrorResponse(String err, String msg, int status){
		timestamp = LocalDateTime.now();
		this.error = err;
		this.message = msg;
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

}
