package BaseDeDatos;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


import Libros.Ejemplar;
import Usuarios.Usuario;


public class EjemplarDB extends DataBase{


	public static void insertarEjemplar(Ejemplar ejemplar)
	{
		try
		{
			
			Connection conexion = Conectar();
		
			int id =getUltimoIdEjemplar(ejemplar.getISBN())+1;
			java.sql.CallableStatement funcion=conexion.prepareCall("{call "
					+ "InsertarEjemplar('"+id+"','"+ejemplar.getISBN()+"')}");


			funcion.execute();

			cerrarConexion(conexion);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}

	}



	public static void PrestarEjemplar(Ejemplar ejemplar,Usuario usuario)
	{
		try
		{

			Connection conexion = Conectar();
			
			
			java.util.Date utilDate = new java.util.Date();

			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			java.sql.CallableStatement funcion=conexion.prepareCall("{call "
					+ "PrestarEjemplar('"+ejemplar.getId()+"','"+ejemplar.getISBN()+"','"+UsuarioDB.getIdUsuario(usuario)+"','"+sqlDate+"')}");

			funcion.execute();

			cerrarConexion(conexion);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "No se pudo insertar el prestamo");
		}
	}






	public boolean verificarEjemplar(Ejemplar ejemplar)
	{
		try
		{

			Connection conexion = Conectar();					
			Statement st = conexion.createStatement();
			CallableStatement funcion=conexion.prepareCall("{call VerificarExistenciaEjemplar('"+ejemplar.getId()+"','"+ejemplar.getISBN()+"')}");
			
			
			funcion.execute();
			

			ResultSet resultado=st.executeQuery("Select * FROM Ejemplar  WHERE idEjemplar='"+ejemplar.getId()+"' AND Libro_ISBN='"+ejemplar.getISBN()+"'");
			if(resultado.next())
			{

				return true;
			}
			cerrarConexion(conexion);
			return false;

		}
		catch (Exception e)
		{

			e.printStackTrace();return false;
		}
	}

	public static boolean verificarEjemplarPrestado(Ejemplar ejemplar)
	{
		try
		{
			Connection conexion = Conectar();					
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Ejemplar  WHERE idEjemplar='"+ejemplar.getId()+"' AND Libro_ISBN='"+ejemplar.getISBN()+"'");
			resultado.next();
			if(resultado.getInt(3)==1)
			{
				return true;
			}

			cerrarConexion(conexion, st);
			return false;

		}
		catch (Exception e)
		{

			e.printStackTrace();return false;
		}
	}
	
	
	public static int getUltimoIdEjemplar(int ISBN)
	{
		try
		{
			Connection conexion = Conectar();					
			Statement st = conexion.createStatement();
			
		ResultSet resultado=st.executeQuery("SELECT * FROM Ejemplar where Libro_ISBN='"+ISBN+"'");
			
			int id = 0;
			
			
			while(resultado.next())
			{
				id=resultado.getInt(1);
				
			}
			
			return id;

		

		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		return 0;
	}

	
	



}
