package Usuarios;

import Plataforma.Plataforma;

public abstract class Usuario {
protected int idUsuario;
protected String nombreUsuario;
protected String pass;
protected String nombre;
protected String apellido;
protected String dni;
protected Plataforma biblioteca;


public int getIdUsuario() {
	return idUsuario;
}


public void setIdUsuario(int idUsuario) {
	this.idUsuario = idUsuario;
}


public String getNombreUsuario() {
	return nombreUsuario;
}


public void setNombreUsuario(String nombreUsuario) {
	this.nombreUsuario = nombreUsuario;
}


public String getPass() {
	return pass;
}


public void setPass(String pass) {
	this.pass = pass;
}


public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public String getApellido() {
	return apellido;
}


public void setApellido(String apellido) {
	this.apellido = apellido;
}


public String getDni() {
	return dni;
}


public void setDni(String dni) {
	this.dni = dni;
	
}


public Usuario(String nombreUsuario, String pass, String nombre,
		String apellido, String dni) {
	super();
	this.biblioteca= Plataforma.getInstance();
	this.nombreUsuario = nombreUsuario;
	this.pass = pass;
	this.nombre = nombre;
	this.apellido = apellido;
	this.dni = dni;
	
	
	
}

public abstract int getTipo();
public abstract void sumarPuntos(int i);





}