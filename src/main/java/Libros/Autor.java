package Libros;

import java.util.Vector;

public class Autor {
	private String nombre;
	private String apellido;
	private Vector<Libro> libros;
	private String DNI;
	public Autor(String nombre, String apellido,String DNI) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.libros = new Vector<Libro>();
		this.setDNI(DNI);
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
	public Vector<Libro> getLibros() {
		return libros;
	}
	public void setLibros(Vector<Libro> libros) {
		this.libros = libros;
	}

	public void agregarLibro(Libro libro)
	{
		if(!existeLibro(libro))
		{
			libros.add(libro);
		}

		else
		{
			System.out.println("Ese libro ya esta ingresado en ese autor");
		}
	}

	public boolean existeLibro(Libro libro)
	{
		for(Libro libroActual:libros)
		{
			if(libroActual.getISBN()==libro.getISBN())
			{
				return true;
			}
		}

		return false;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}

}
