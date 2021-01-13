package com.telcel.aprovisionamiento.model;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ExceptionsVO {

	private final String mensaje;
	private final Throwable throwable;
	private final HttpStatus httpStatus;
	private final ZonedDateTime dateTime;
	
	public ExceptionsVO(String mensaje, Throwable throwable, HttpStatus httpStatus, ZonedDateTime dateTime) {

		this.mensaje = mensaje;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
		this.dateTime = dateTime;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @return the throwable
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @return the dateTime
	 */
	public ZonedDateTime getDateTime() {
		return dateTime;
	}
	
	
	
}
