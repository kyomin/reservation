package kr.or.connect.reservation.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.or.connect.reservation.enums.ExceptionLevelLog;
import kr.or.connect.reservation.enums.ResponseMessage;

@RestControllerAdvice
public class ExceptionAdvisor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<?> handleSQLException(SQLException sqlException) {
		logger.error("[{}] : {}", ExceptionLevelLog.SQL_EXCEPTION.getLevel(), ResponseMessage.DB_ERROR.getMessage());
		logger.error("stack trace : {}", sqlException);
		
		return new ResponseEntity<>(ResponseMessage.DB_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		logger.error("[{}] : {}", ExceptionLevelLog.NO_SUCH_ELEMENT_EXCEPTION.getLevel(), ResponseMessage.NOT_FOUND.getMessage());
		logger.error("stack trace : {}", noSuchElementException);
		
		return new ResponseEntity<>(ResponseMessage.NO_SUCH_ELEMENT, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> handleIOException(IOException IOException) {
		logger.error("[{}] : {}", ExceptionLevelLog.IO_EXCEPTION.getLevel(), ResponseMessage.IO_EXCEPTION.getMessage());
		logger.error("stack trace : {}", IOException);
		
		return new ResponseEntity<>(ResponseMessage.IO_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
		logger.error("[{}] : {}", ExceptionLevelLog.NOT_VALID_EXCEPTION.getLevel(), ResponseMessage.BAD_REQUEST.getMessage());
		logger.trace("stack trace : {}", methodArgumentNotValidException);
		String validResponseMessage = methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return new ResponseEntity<>(validResponseMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleMethodException(Exception exception) {
		logger.error("[{}] : {}", ExceptionLevelLog.ETC_EXCEPTION.getLevel(),
			ResponseMessage.INTERNAL_SERVER_ERROR.getMessage());
		logger.error("stack trace : {}", exception);
		return new ResponseEntity<>(ResponseMessage.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}