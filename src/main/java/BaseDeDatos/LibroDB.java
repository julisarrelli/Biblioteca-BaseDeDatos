package BaseDeDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.sql.StatementEvent;
import javax.swing.JOptionPane;

import Libros.Autor;
import Libros.Ejemplar;
import Libros.Libro;
import Usuarios.Usuario;

public class LibroDB extends DataBase {


	public static void insertarLibro(Libro libro)
	{
		try
		{

			Connection conexion = Conectar();

			CallableStatement funcion1=conexion.prepareCall("{call "
					+ "InsertarLibro('"+libro.getISBN()+"','"+libro.getTitulo()+"','"+libro.getCantPags()+"')}");

			funcion1.execute();






			cerrarConexion(conexion);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	public static void insertarAutorAlibro(Libro libro,Autor autor)
	{
		try
		{

			Connection conexion = Conectar();
			CallableStatement funcion2=conexion.prepareCall("{call AsignarAutorLibro"
					+ "('"+libro.getISBN()+"','"+AutorDB.getIdAutor(autor)+"','"+autor.getDNI()+"')}");
			funcion2.execute();

			cerrarConexion(conexion);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	public static Ejemplar getEjemplarDisponible(Libro libro)
	{

		try {

			Connection conexion=Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("SELECT * FROM Ejemplar WHERE Libro_ISBN='"+libro.getISBN()+"' AND Estado_idEstado=1");

			int id;
			int ISBN;

			while(resultado.next())
			{
				id=resultado.getInt(1);
				ISBN=resultado.getInt(2);
				Ejemplar ejemplar=new Ejemplar(ISBN);
				ejemplar.setId(id);

				return ejemplar;
			}

			return null;



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static Libro getLibro(int ISBN1)
	{

		try {

			Connection conexion=Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("SELECT * FROM Libro WHERE ISBN='"+ISBN1+"'");



			while(resultado.next())
			{
				int ISBN=resultado.getInt(1);
				String titulo=resultado.getString(2);
				String descripcion=resultado.getString(3);
				int cantPags=resultado.getInt(4);
				float precio=resultado.getFloat(6);
				Libro libro=new Libro(ISBN, titulo,descripcion, cantPags,precio);

				return libro;
			}

			return null;



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static Ejemplar getEjemplarYaReservado(Usuario usuario,Libro libro)
	{
		try
		{
			Connection conexion = Conectar();					
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("SELECT * FROM Ejemplar WHERE Libro_ISBN='"+libro.getISBN()+"' AND Estado_idEstado=3 AND Reserva_Usuario_idUsuario='"+UsuarioDB.getIdUsuario(usuario)+"'");

			int id;
			int ISBN;

			while(resultado.next())
			{
				id=resultado.getInt(1);
				ISBN=resultado.getInt(2);
				Ejemplar ejemplar=new Ejemplar( ISBN);
				ejemplar.setId(id);

				return ejemplar;
			}

			return null;



		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		return null;
	}

	public static Vector<String> getLibrosISBN(){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Libro ");


			Vector<String>isbn=new Vector<String>();

			while(resultado.next())
			{
				String ISBN=Integer.toString(resultado.getInt(1)) ;

				isbn.add(ISBN);


			}

			return isbn;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return null;
		}

	}

	public static Vector<String> getLibrosTitulo(){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Libro ");


			Vector<String>titulos=new Vector<String>();

			while(resultado.next())
			{
				String titulo=resultado.getString(2);

				titulos.add(titulo);


			}

			return titulos;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return null;
		}

	}


	public static Vector<String> getLibrosDescripcion(){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Libro ");


			Vector<String>descripciones=new Vector<String>();

			while(resultado.next())
			{
				String descripcion=resultado.getString(3);

				descripciones.add(descripcion);


			}

			return descripciones;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return null;
		}

	}


	public static Vector<Libro> getLibros(){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();
			Statement st1=conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Libro ");




			Vector<Libro>libros=new Vector<Libro>();

			while(resultado.next())
			{
				int ISBN=resultado.getInt(1);
				String titulo=resultado.getString(2);
				String descripcion=resultado.getString(3);
				int cantPags =resultado.getInt(4);
				float precio=resultado.getFloat(6);
				Libro libro=new Libro(ISBN, titulo, descripcion, cantPags,precio);

				ResultSet resultado1=st1.executeQuery("Select * from Libro_has_Autor where Libro_ISBN='"+ISBN+"'");
				
			//	JOptionPane.showMessageDialog(null, titulo);

				while(resultado1.next())
				{

					int idAutor=resultado1.getInt(2);


			
					libro.insertarAutor(AutorDB.getAutor(idAutor));
					
					


				}




				libros.add(libro);




			}

			return libros;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return null;
		}

	}



}
