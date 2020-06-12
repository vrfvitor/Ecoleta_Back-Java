package br.com.ecoleta.dto;

public class ValidationMessageDto {
	
	private String field;
	private String message;

	public ValidationMessageDto(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}	
	
}
