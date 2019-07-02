package com.uptc.desingMatch.util;

public class RestResponse {

	private Integer responseCode;
	private String message;
	
	public RestResponse(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public RestResponse(Integer responseCode, String message) {
		this.message = message;
		this.responseCode = responseCode;
	}
	
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
