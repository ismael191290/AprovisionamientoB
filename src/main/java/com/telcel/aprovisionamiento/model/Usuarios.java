package com.telcel.aprovisionamiento.model;

import java.io.Serializable;

public class Usuarios  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombre;
	private String apellido;
	private int age;
	
	public Usuarios() {
		
	}
	public Usuarios(long id, String nombre, String apellido, int age) {
		
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.age = age;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	
	
}
