package br.com.ecoleta.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.ecoleta.dto.ValidationMessageDto;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public List<ValidationMessageDto> handler(BindException e) {
		List<ValidationMessageDto> validationMessages = new ArrayList<>();
		e.getBindingResult()
			.getFieldErrors()
			.forEach(exception -> {
				String message = messageSource.getMessage(exception, LocaleContextHolder.getLocale());
				validationMessages.add(new ValidationMessageDto(exception.getField(), message));
			});
		return validationMessages;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public String handler(HttpMediaTypeNotSupportedException e) {
		return e.getLocalizedMessage();
	}
	
}
