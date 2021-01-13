package com.telcel.aprovisionamiento.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.telcel.aprovisionamiento.model.ExceptionsVO;

@ControllerAdvice
public class Exceptions {
	private Logger logger = LoggerFactory.getLogger(Exceptions.class);
	
	@ExceptionHandler(value = {RequestExceptions.class})
	public ResponseEntity<ExceptionsVO> handleRequestExceptions(RequestExceptions e){
		
		ExceptionsVO exceptionsVO = new ExceptionsVO(
			e.getMessage(),
			e,
			HttpStatus.BAD_REQUEST,
			ZonedDateTime.now(ZoneId.of("Z"))
			);	
		
		logger.error("Error Peticion: {}",e.getMessage());
		return new ResponseEntity<>(exceptionsVO, HttpStatus.BAD_REQUEST);
	}

}
