package Libros;

import java.util.Vector;



import BaseDeDatos.AutorDB;
import BaseDeDatos.EjemplarDB;
import BaseDeDatos.LibroDB;


import Usuarios.Usuario;

public class Libro {
	private int ISBN;
	private String titulo;
	private String descripcion;
	private int cantPags;
	private Vector<Autor>autores;
	private float precio;
	public Libro(int iSBN, String titulo,String descripcion, int cantPags,float precio) {
		super();	
		ISBN = iSBN;
		this.titulo = titulo;
		this.cantPags = cantPags;
		this.autores=new Vector<Autor>();
		this.setDescripcion(descripcion);
		this.setPrecio(precio);
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getCantPags() {
		return cantPags;
	}
	public void setCantPags(int cantPags) {
		this.cantPags = cantPags;
	}

	
	
	
	public Ejemplar reservarEjemplar(Usuario usuario)
	{
		
			if(LibroDB.getEjemplarDisponible(this)!=null)
			{
				
				return LibroDB.getEjemplarDisponible(this);
			}
		
		return null;
	}
	
	public void prestarEjemplar(Usuario usuario)
	{
		
		if(LibroDB.getEjemplarYaReservado(usuario, this)!=null)
		{		
			LibroDB.getEjemplarYaReservado(usuario, this).prestar(usuario);
		}
	
			if(LibroDB.getEjemplarDisponible(this)!=null){
			LibroDB.getEjemplarDisponible(this).prestar(usuario);
			}
				
		
			else{
		
		
		System.out.println("No hay ejemplares del libro "+titulo+"\n");
			}
		
		
	}
	public boolean ejemplaresDisponible()
	{
			if(LibroDB.getEjemplarDisponible(this)!=null) return true;
			else return false;
	}
	
	public void addAutores(Autor autor)
	
	{
		if(!AutorDB.verificarExistenciaAutor(autor, this))
		{
		LibroDB.insertarAutorAlibro(this, autor);
		}
		else
		{
			
			System.out.println("Ese autor ya esta ingresado en el libro");
		}
	}
	
	public void addEjemplar()
	{
		
		//JOptionPane.showMessageDialog(null, EjemplarDB.getUltimoIdEjemplar());
		Ejemplar ejemplar=new Ejemplar(this.ISBN);
		
		EjemplarDB.insertarEjemplar(ejemplar);
		
		
		
		
	}
	public Vector<Autor> getAutores() {
		
		return autores;
		
		
		
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void insertarAutor(Autor autor)
	
	{
		autores.addElement(autor);
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}





}
