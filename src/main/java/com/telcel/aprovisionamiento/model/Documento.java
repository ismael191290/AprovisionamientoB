package com.telcel.aprovisionamiento.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Documento implements Serializable{

	/** Bean Para Mostrar Respuesrta de los documentos
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String causa;
	private List<String> listError;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}
	
	
	public List<String> getListError() {
		return listError;
	}
	public void setListError(List<String> listError) {
		this.listError = listError;
	}
	@Override
	public String toString() {
		return "{nombre=" + nombre + ", causa=" + causa + "}";
	}
	
	

}
