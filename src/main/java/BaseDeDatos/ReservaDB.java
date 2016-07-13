package BaseDeDatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Libros.Ejemplar;
import Libros.Reserva;
import Usuarios.Usuario;

public class ReservaDB extends DataBase {


	public static void insertarReserva(Reserva reserva,Usuario usuario)
	{
		try
		{

			Connection conexion =Conectar();
			CallableStatement funcion=conexion.prepareCall("{call InsertarReserva"
					+ "('"+UsuarioDB.getIdUsuario(reserva.getUsuario())+"','"+reserva.getFecha()+"')}");

			funcion.execute();


			for(Ejemplar ejemplar1:reserva.getEjemplares()){
				
				CallableStatement funcion2=conexion.prepareCall("{call AsignarReservaEjemplar"
						+ "('"+reserva.getIdReserva()+"','"+UsuarioDB.getIdUsuario(usuario)+"','"+ejemplar1.getId()+"','"+ejemplar1.getISBN()+"')}");
				funcion2.execute();
			}





			cerrarConexion(conexion);


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public static int getIdReserva(Reserva reserva){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Reserva WHERE Usuario_idUsuario='"+UsuarioDB.getIdUsuario(reserva.getUsuario())+"' AND" +
					" Fecha='"+reserva.getFecha()+"' ");
			resultado.next();

			return resultado.getInt(1);
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return 0;
		}

	}

	public static boolean verificarExistenciaReserva(Ejemplar ejemplar)
	{
		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();



			ResultSet resultado=st.executeQuery("Select * FROM Ejemplar  WHERE Estado_idEstado='"+3+"' AND idEjemplar='"+ejemplar.getId()+"' AND Libro_ISBN='"+ejemplar.getISBN()+"'");

			if(resultado.next())
			{
				return true;
			}
			else return false;
		}
		catch (Exception e)
		{

			e.printStackTrace();return false;
		}
	}
	
	
	public static void EliminarReservas(int idUsuario)
	{
		try
		{

			Connection conexion = Conectar();
			CallableStatement funcion=conexion.prepareCall("{call EliminarReservas('"+idUsuario+"')}");
			funcion.execute();
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}


}
