package com.example.myspring.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResponseObject {
	private String status;
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private Object data;
	private SubErrors subErrors;

	public ResponseObject() {
		super();
	}
	
	public ResponseObject(String status, String message, LocalDateTime timestamp) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public ResponseObject(String status, String message, LocalDateTime timestamp, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.data = data;
	}

	public ResponseObject(String status, String message, LocalDateTime timestamp, SubErrors subErrors) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.subErrors = subErrors;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SubErrors getSubErrors() {
		return subErrors;
	}

	public void setSubErrors(SubErrors subErrors) {
		this.subErrors = subErrors;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	

}
