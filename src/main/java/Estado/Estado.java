package Estado;

import Usuarios.Usuario;



public interface Estado {

	public boolean Disponibilidad();
	
	public boolean Diferencia();
	
	public Usuario getUsuario();
	public int getIdEstado();
	public String getEstado();

	
}
