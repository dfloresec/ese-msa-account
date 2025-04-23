package ec.com.account.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ec.com.account.exception.InsufficientBalanceException;
import ec.com.account.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class ControlExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	private ResponseEntity<Object> handlerException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()).body("ERROR INTERNO");
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	private ResponseEntity<Object> handlerEntityNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()).body(ex.getMessage());
	}
	
	@ExceptionHandler(InsufficientBalanceException.class)
	private ResponseEntity<Object> handlerInsufficientBalanceException(InsufficientBalanceException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()).body(ex.getMessage());
	}
	
	

}
