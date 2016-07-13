package Plataforma;

import java.util.HashMap;
import java.util.Map;

import BaseDeDatos.LibroDB;
import Plataforma.Plataforma;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;



import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class Main {

	

	public static void main(String[] args) {
		 
		  staticFileLocation("/public");
		  
		  
	    HashMap<String,Object> map = new HashMap<String, Object>();
	    HashMap<String,Object> variablesAutores = new HashMap<String, Object>();
	       
	        
	        map.put("autores", Plataforma.dameNombreAutores());
	        map.put("categorias", Plataforma.dameCategorias());
	        
	    
	        map.put("libros", Plataforma.dameLibros());
//	        map.put("libro_isbn", LibroDB.getLibrosISBN());
//	        map.put("libro_titulo", LibroDB.getLibrosTitulo());
//	        map.put("libro_descripcion", LibroDB.getLibrosDescripcion());
	        
	        
	        
	         variablesAutores.put("autores", Plataforma.dameAutores());
	        
	      
	        	
	        // hello.mustache file is in resources/templates directory
	        get("/", (rq, rs) -> new ModelAndView(map,"inde.html"), new MustacheTemplateEngine());
	        get("/autores", (rq, rs) -> new ModelAndView(variablesAutores,"autores.html"), new MustacheTemplateEngine());
	        
	      
	    }


}
