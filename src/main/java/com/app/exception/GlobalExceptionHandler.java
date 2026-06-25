package com.app.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		String message = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> error.getField() + " : " + error.getDefaultMessage())
				.findFirst().orElse("Validation failed");

		ErrorResponse response = new ErrorResponse(
				Instant.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Validation Failed",
				message,
				request.getRequestURI());

		return ResponseEntity.badRequest().body(response);
	}
	@ExceptionHandler(BucketAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleBucketAlreadyExists(BucketAlreadyExistsException ex,
			HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.CONFLICT, request.getRequestURI());
	}

	@ExceptionHandler(BucketNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBucketNotFound(BucketNotFoundException ex, HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleObjectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
	}

	@ExceptionHandler(MetadataNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleMetadataNotFound(MetadataNotFoundException ex,
			HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
	}

	@ExceptionHandler(InvalidFileException.class)
	public ResponseEntity<ErrorResponse> handleInvalidFile(InvalidFileException ex, HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getRequestURI());
	}
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getRequestURI());
	}

	@ExceptionHandler(FileUploadException.class)
	public ResponseEntity<ErrorResponse> handleUpload(FileUploadException ex, HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
	}

	@ExceptionHandler(FileDownloadException.class)
	public ResponseEntity<ErrorResponse> handleDownload(FileDownloadException ex, HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
	}

	@ExceptionHandler(StorageException.class)
	public ResponseEntity<ErrorResponse> handleStorage(StorageException ex, HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getRequestURI());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {

		return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
	}

	private ResponseEntity<ErrorResponse> buildResponse(String message, HttpStatus status, String path) {

		ErrorResponse response = new ErrorResponse(Instant.now(), status.value(), status.getReasonPhrase(), message,
				path);

		return new ResponseEntity<>(response, status);
	}
}