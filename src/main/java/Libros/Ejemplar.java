package Libros;

import BaseDeDatos.EjemplarDB;
import Estado.Estado;
import Estado.Estanteria;

import Estado.Reservado;
import Usuarios.Usuario;

public class Ejemplar  {
	private int id;
	private Estado estado;
	private int ISBN;



	public Ejemplar(int ISBN) {
		super();
		this.id = 0;
		this.estado = new Estanteria();
		this.setISBN(ISBN);

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public void mostrarEjemplar()
	{
		if(estado.Disponibilidad())System.out.println("idEjemplar:"+this.id+"    Estado: "+this.estado.getClass().getName() );
		else{
			System.out.println("idEjemplar:"+this.id+
					"    Estado: "+this.estado.getClass().getName() 
					+"    Usuario:"+estado.getUsuario().getNombre()+" "
					+estado.getUsuario().getApellido());
		}


	}

	public void reservar(Usuario usuario)
	{
		this.estado=new Reservado(usuario);
	}

	public void prestar(Usuario usuario)
	{
		EjemplarDB.PrestarEjemplar(this,usuario);
		
	}

	public boolean isDisponible()
	{
		if(estado.Disponibilidad())return true;
		else return false;
	}


	public int getISBN() {
		return ISBN;
	}


	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}



}
