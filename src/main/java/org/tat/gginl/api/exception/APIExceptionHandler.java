package org.tat.gginl.api.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.tat.gginl.api.dto.ResponseDTO;


@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();

		List<String> details = new ArrayList<>();
		for (FieldError error : fieldError) {
			details.add(error.getDefaultMessage());
		}
		ResponseDTO<Object> responseDTO = ResponseDTO.builder().responseStatus(status.toString())
				.message(generateMessage(fieldError)).responseBody(null).build();
		return ResponseEntity.badRequest().body(responseDTO);

	}

	public String generateMessage(List<FieldError> fieldErrors) {
		String result = "";

		for (FieldError error : fieldErrors) {
			if (!result.isEmpty()) {
				result += ",";
			}
			result += error.getDefaultMessage();
		}
		return result;
	}
	  @ExceptionHandler(ConstraintViolationException.class)
	    public final ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
	        ResponseDTO<Object> responseDTO = ResponseDTO.builder()
	            .responseStatus(HttpStatus.BAD_REQUEST.toString())
	            .message(ex.getMessage()).build();

	        return ResponseEntity.badRequest().body(responseDTO);
	    }
}
