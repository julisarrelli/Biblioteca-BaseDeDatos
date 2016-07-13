package Usuarios;
import BaseDeDatos.AutorDB;
import BaseDeDatos.DataBase;
import BaseDeDatos.LibroDB;
import Libros.Autor;

import Libros.Libro;



public class Bibliotecario extends Usuario {

	DataBase db=new DataBase();
	public Bibliotecario(String nombreUsuario, String pass,
			String nombre, String apellido, String dni) {
		super(nombreUsuario, pass, nombre, apellido, dni);
		// TODO Auto-generated constructor stub
	}


	public void agregarLibro(Libro libro)
	{
		LibroDB.insertarLibro(libro);
	}

	public void prestarLibro(Usuario usuario, Libro libro)
	{

		if(((Particular)usuario).getEjemplares().size()<3)
		{
			biblioteca.getLibro(libro.getISBN()).prestarEjemplar(usuario);
			usuario.sumarPuntos(10);
		}

		else
		{
			System.out.println("El usuario "+ usuario.getNombre()+" "+ usuario.getApellido()+" tiene mas de tres libros en posecion");
		}


	}

	public void addEjemplar(Libro libro)
	{

		
		biblioteca.getLibro(libro.getISBN()).addEjemplar();
	}

	public void agregarAutoraLibro(Autor autor, Libro libro)
	{

		if(!AutorDB.verificarExistenciaAutor(autor, libro))
		{
			biblioteca.getLibro(libro.getISBN()).addAutores(autor);

		}
		else
		{

			System.out.println("Ese autor ya esta ingresado en el libro");
		}
	}


	@Override
	public void sumarPuntos(int i) {
		// TODO Auto-generated method stub

	}


	@Override
	public int getTipo() {
		// TODO Auto-generated method stub
		return 1;
	}

	public void agregarAutor(Autor autor)
	{
		AutorDB.insertarAutor(autor);
	}
}
