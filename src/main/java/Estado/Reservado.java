package Estado;

import Usuarios.Usuario;

public class Reservado implements Estado {

	
	private Usuario usuario;
	
	public Reservado(Usuario usuario) {
		super();
		this.usuario = usuario;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public boolean Disponibilidad() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean Diferencia() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public int getIdEstado() {
		// TODO Auto-generated method stub
		return 2;
	}


	@Override
	public String getEstado() {
		// TODO Auto-generated method stub
		return "Reservado";
	}


}
