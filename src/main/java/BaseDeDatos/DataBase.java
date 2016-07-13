package BaseDeDatos;

import java.sql.*;


import javax.swing.JOptionPane;

public class DataBase {

	static Connection conexion = null;
	Statement comando = null;
	ResultSet registro;





	@SuppressWarnings("finally")
	public static Connection Conectar() {

		try {
		
			Class.forName("com.mysql.jdbc.Driver");
		
			String servidor = "jdbc:mysql://localhost:3306/Biblioteca";
			
			String usuario = "root";
			String pass = "";
			
			conexion = DriverManager.getConnection(servidor, usuario, pass);

		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex, "Error en la conexión a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
			conexion = null;
		}
		finally {
		
			return conexion;
		}
	}
	
	
	public static void cerrarConexion(Connection conexion,Statement st)
	{
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cerrarConexion(Connection conexion)
	{
		
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
	

	


	


	
	
	
	

	
	

	
	

	


	
	
	
	
	
	
	
	
	


	



	


}



