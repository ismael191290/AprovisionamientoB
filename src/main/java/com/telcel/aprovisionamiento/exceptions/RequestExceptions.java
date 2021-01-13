package com.telcel.aprovisionamiento.exceptions;

public class RequestExceptions extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestExceptions (String mensaje) {
		super(mensaje);
		}
	
	public RequestExceptions(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
