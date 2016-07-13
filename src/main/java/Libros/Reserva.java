package Libros;







import java.sql.Date;
import java.util.Vector;
import BaseDeDatos.ReservaDB;
import Usuarios.Usuario;

public class Reserva {
	
	
	
	private Vector<Ejemplar> ejemplares;
	private Usuario usuario;
	private Date fecha;
	//private int idReserva;
	public Reserva(Usuario usuario,Date fecha) {
		super();
		
		this.ejemplares= new Vector<Ejemplar>();
		this.usuario = usuario;
		this.fecha = fecha;
		//this.idReserva=idReserva;
	}

	
	



	public Vector<Ejemplar> getEjemplares() {
		return ejemplares;
	}
	public void setEjemplares(Vector<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void agregarEjemplar(Ejemplar ejemplar)
	{
		ejemplares.add(ejemplar);
		
		
	}
	
	public int getIdReserva()
	{
		
		
		return ReservaDB.getIdReserva(this);
		
	}

}
