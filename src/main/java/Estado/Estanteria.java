package Estado;

import Usuarios.Usuario;

public class Estanteria implements Estado  {


	
	private Usuario usuario;
	
	public Estanteria() {
		super();
		this.setUsuario(null);
	}

	public boolean Disponibilidad() {
		// TODO Auto-generated method stub
		return true;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean Diferencia() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getIdEstado() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getEstado() {
		// TODO Auto-generated method stub
		return "Estanteria";
	}

	
	

}
