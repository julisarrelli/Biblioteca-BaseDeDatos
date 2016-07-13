package Usuarios;

import java.util.Vector;

import Libros.Ejemplar;
import Libros.Libro;

import Libros.Reserva;



public class Particular extends Usuario {

	private Vector<Ejemplar>ejemplares;
	private Vector<Reserva>reservas;
	public Particular(String nombreUsuario, String pass,
			String nombre, String apellido, String dni) {
		super(nombreUsuario, pass, nombre, apellido, dni);
		this.ejemplares = new Vector<Ejemplar>();
		this.reservas = new Vector<Reserva>();
	}
	public Vector<Ejemplar> getEjemplares() {
		return ejemplares;
	}
	public void setEjemplares(Vector<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}
	public Vector<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(Vector<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	
	public void reservaLibro(Vector<Libro> libros)
	{
		reservas.add(biblioteca.reservarLibros(libros, this));
	}
	
	
	public void addLibro(Ejemplar ejemplar)
	{
		ejemplares.add(ejemplar);
	}
	
	public Vector<Ejemplar> dameEjemplaresReservados()
	{
		Vector<Ejemplar> ejemplaresReservados= new Vector<Ejemplar>();
		
		for(Reserva reserva: reservas)
		{
			for(Ejemplar ejemplar:reserva.getEjemplares())
			{
				ejemplaresReservados.add(ejemplar);
			}
		}
		
		return ejemplaresReservados;
	}
	
	
	public void mostrarReservas()
	{
		System.out.println("Usuario: "+this.getNombre()+" "+this.getApellido());
		for(Reserva reserva:reservas)
		{
			System.out.println("idReserva: "+reserva.getIdReserva());
			for(Ejemplar ejemplar:reserva.getEjemplares())
			{
				
				System.out.println("Titulo de ejemplar: "+biblioteca.getLibro(ejemplar.getISBN()).getTitulo()
						+"  ISBN de ejemplar: "+biblioteca.getLibro(ejemplar.getISBN()).getISBN());
				
				
			}
		}
	}
	
	public void sumarPuntos(int i){}
	@Override
	public int getTipo() {
		// TODO Auto-generated method stub
		return 2;
	}

	
}