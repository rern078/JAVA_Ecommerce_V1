package com.example.firstProject.ecommerce.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now()));
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()));
	}

	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	public ResponseEntity<ApiError> handleValidation(Exception ex) {
		String message;
		if (ex instanceof MethodArgumentNotValidException validationException) {
			message = validationException.getBindingResult().getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage())
					.collect(Collectors.joining("; "));
		} else if (ex instanceof BindException bindException) {
			message = bindException.getBindingResult().getFieldErrors().stream()
					.map(error -> error.getField() + ": " + error.getDefaultMessage())
					.collect(Collectors.joining("; "));
		} else {
			message = "Validation failed";
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiError(message, HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiError> handleConflict(DataIntegrityViolationException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ApiError("Data conflict: " + ex.getMostSpecificCause().getMessage(),
						HttpStatus.CONFLICT.value(), LocalDateTime.now()));
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ApiError> handleIllegalState(IllegalStateException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()));
	}
}
