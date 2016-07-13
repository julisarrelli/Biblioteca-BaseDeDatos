package Plataforma;





import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import BaseDeDatos.AutorDB;
import BaseDeDatos.CategoriaDB;
import BaseDeDatos.LibroDB;
import BaseDeDatos.ReservaDB;
import BaseDeDatos.UsuarioDB;
import Libros.Autor;
import Libros.Ejemplar;
import Libros.Libro;
import Libros.Reserva;
import Usuarios.Usuario;

public class Plataforma {
	
    private static Plataforma INSTANCE;



	private Plataforma() {

	}

    public static Plataforma getInstance() {
    	
    	if (INSTANCE==null){
    		INSTANCE = new Plataforma();
    	}
    	
    		return INSTANCE;
    }
    

    public Libro getLibro (Integer ISBN)
    {
    	
    	return LibroDB.getLibro(ISBN);
    	//return libros.get(ISBN);
    }
    

    
    public void eliminarReservas(int idUsuario)
    {
    	
    	ReservaDB.EliminarReservas(idUsuario);
    }
    
    public void eliminarUsuario(int idUsuario)
    {
    	
    	eliminarReservas(idUsuario);
    	UsuarioDB.EliminarUsuario(idUsuario);
    	
    	
    	
    	
    }
    
    public Reserva reservarLibros (Vector<Libro> libros,Usuario usuario)
    {
    	
    	java.util.Date utilDate = new java.util.Date();

    	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    	
    	
    	Reserva reserva= new Reserva(usuario, sqlDate);
    	
    	Ejemplar ejemplar;
    	boolean flag=false;
    	
    	for(Libro libro:libros){
    		
    		if(LibroDB.getEjemplarDisponible(libro)!=null)
    		{
    			ejemplar=LibroDB.getEjemplarDisponible(libro);
    			
    			reserva.agregarEjemplar(ejemplar);
    			
    			if(!ReservaDB.verificarExistenciaReserva(ejemplar))flag=true;
    			
    			
    		}
    		else{
    			System.out.println("No hay ejemplares disponibles de: "+libro.getTitulo());
    		}
    		
    		}
    	
    	if(flag)
    	{
    	
    	ReservaDB.insertarReserva(reserva, usuario);
    	}
    	
    	
    	
    	if(reserva.getEjemplares()==null){
    		System.out.println("No se encuentran ejemplares disponibles de ningun libro");
    		return null;
    	}
    	else
    		
    		
    	
    	return reserva;
    }

	public void addUsuario(Usuario usuario) {
		UsuarioDB.insertarUsuario(usuario, usuario.getTipo());
		
	}
	
	
	public static Vector<String> dameNombreAutores()
	{
		   
			
		  Vector<String> autores = new Vector<String>();
		   for(String nombre:AutorDB.getNombreAutores())
		   {
			autores.add(nombre); 
			
			
			
			
		   }
		 
		   
		   
		   return autores;
		
	}
	
public static Vector<HashMap<String, Object>> dameLibros()
{
	Vector<HashMap<String, Object>> libros=new Vector<>();
	
		
		for(Libro libro:LibroDB.getLibros())
		{
			HashMap<String,Object> map=new HashMap<>();
				String isbn=String.valueOf(libro.getISBN());
				String titulo=libro.getTitulo();
				String descripcion=libro.getDescripcion();
				
				
				String precio=String.valueOf(libro.getPrecio());
				
				String[] precioaux=precio.split(Pattern.quote("."));
			
				String precioentero=precioaux[0];
				String preciodecimal=precioaux[1];
				
				
				
				Vector<String> autores=new Vector<>();
				
				
				map.put("isbn", isbn);
				map.put("titulo", titulo);
				map.put("descripcion", descripcion);
				map.put("precio",precioentero);
				map.put("preciodecimal",preciodecimal);
				
				if(libro.getAutores().size()<1)autores.add("");
				for(Autor autor:libro.getAutores())
				{
					autores.add(autor.getNombre()+" "+autor.getApellido());
				}
				
				map.put("autores", autores);
				libros.addElement(map);
			
		}
	
		return libros;
	
	
	
}


public static Vector<HashMap<String, String>> dameAutores()
{
	
	Vector<HashMap<String, String>> autores=new Vector<>();
	for(Autor autor: AutorDB.getAutores())
	{
		HashMap<String, String>autoraux=new HashMap<>();
		
		autoraux.put("nombre", autor.getNombre());
		autoraux.put("apellido", autor.getApellido());
		autoraux.put("dni", autor.getDNI());
		
		autores.addElement(autoraux);
	}
	
	return autores;
}

public static Vector<String> dameCategorias()
{
	return CategoriaDB.getCategorias();
}
	


	
    
}
