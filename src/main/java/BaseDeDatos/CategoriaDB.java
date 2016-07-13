package BaseDeDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class CategoriaDB extends DataBase {
	
	public static Vector<String> getCategorias(){

		try
		{

			Connection conexion = Conectar();
			Statement st = conexion.createStatement();

			ResultSet resultado=st.executeQuery("Select * FROM Categoria ");
			
			
			Vector<String>categorias=new Vector<String>();

			while(resultado.next())
			{
				String categoria=resultado.getString(2);
				categorias.add(categoria);
			}
			
			return categorias;
		}
		catch (Exception e)
		{

			e.printStackTrace();
			return null;
		}

	}

}
