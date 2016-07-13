package BaseDeDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Usuarios.Usuario;

public class UsuarioDB extends DataBase {

	
	public static void insertarUsuario(Usuario usuario,int tipo)
	{
		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();
			java.sql.CallableStatement funcion=conexion.prepareCall("{call "
					+ "InsertarUsuario('"+usuario.getNombreUsuario()+"','"+usuario.getNombre()+"','"+usuario.getApellido()+"',"
							+ "'"+usuario.getDni()+"','"+tipo+"')}");


			funcion.execute();

			cerrarConexion(conexion, st);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "No se ha podido insertar el usuario");
		}

	}
	
	
	
	public static int getIdUsuario(Usuario usuario){

		try
		{
			int id = 3;
		
			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select idUsuario FROM Usuario WHERE DNI='"+usuario.getDni()+"'");
			if(resultado.next())
			{
				id=resultado.getInt("IdUsuario");
				
				
			}
			
			return id;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return 0;
		}

	}
	
	
	public static void EliminarUsuario(int idUsuario)
	{
		try
		{

			Connection conexion = Conectar();
			CallableStatement funcion=conexion.prepareCall("{call EliminarUsuario('"+idUsuario+"')}");
			funcion.execute();
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}


	

	
}
