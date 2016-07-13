package BaseDeDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import Libros.Autor;
import Libros.Libro;

public  class AutorDB extends DataBase {


	public static void insertarAutor(Autor autor)
	{
		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();
			String nombre=autor.getNombre();
			String apellido=autor.getApellido();
			String DNI=autor.getDNI();

			java.sql.CallableStatement funcion=conexion.prepareCall("{call "
					+ "InsertarAutor('"+nombre+"','"+apellido+"','"+DNI+"')}");


			funcion.execute();


			cerrarConexion(conexion, st);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "No se pudo insertar autor");
		}

	}


	public static boolean verificarExistenciaAutor(Autor autor,Libro libro)
	{
		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Libro_has_Autor  WHERE Autor_idAutor='"+getIdAutor(autor)+"' AND Libro_ISBN='"+libro.getISBN()+"'");
			if(resultado.next())
			{

				return true;
			}

			return false;

		}
		catch (Exception e)
		{

			e.printStackTrace();return false;
		}
	}




	public static int getIdAutor(Autor autor){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Autor WHERE DNI='"+autor.getDNI()+"'");
			while(resultado.next())
			{
				int id=resultado.getInt(1);
				return id;
			}
			return 0;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return 0;
		}

	}
	
	public static Autor getAutor(int idAutor){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Autor WHERE id_Autor='"+idAutor+"'");
			

			while(resultado.next())
			{
				String nombre=resultado.getString(2);
				String apellido=resultado.getString(3);
				String DNI=resultado.getString(4);
				Autor autor=new Autor(nombre, apellido, DNI);
				return autor;
				
			}

			return null;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return null;
		}

	}
	
	
	public static Vector<String> getNombreAutores(){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Autor ");
			
			
			Vector<String>nombres=new Vector<String>();

			while(resultado.next())
			{
				String nombre=resultado.getString(2);
				nombres.add(nombre);
			}
			
			return nombres;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return null;
		}

	}

	public static Vector<Autor> getAutores(){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Autor ");
			
			
			Vector<Autor>autores=new Vector<Autor>();

			while(resultado.next())
			{
				String nombre=resultado.getString(2);
				String apellido=resultado.getString(3);
				String DNI=resultado.getString(4);
				Autor autor=new Autor(nombre, apellido, DNI);
				autores.add(autor);
			}
			
			return autores;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return null;
		}

	}
	/*public static Vector<Autor> getAutores(Libro libro){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select Autor_idAutor FROM Libro where Libro_ISBN='"+libro.getISBN()+"'");
			resultado.next();

			return resultado.getInt(1);
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return 0;
		}

	}

	 */



}
