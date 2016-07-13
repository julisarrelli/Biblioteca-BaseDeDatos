package Usuarios;





public class Administrador extends Bibliotecario {
	
	

	public Administrador(String nombreUsuario, String pass,
			String nombre, String apellido, String dni) {
		super(nombreUsuario, pass, nombre, apellido, dni);
		// TODO Auto-generated constructor stub
	}
	
	
	public void addUsuario(Usuario usuario)
	{
		this.biblioteca.addUsuario(usuario);
		
		
		
	}

	public void removeUsuario(int idUsuario)
	{
		biblioteca.eliminarReservas(idUsuario);
		biblioteca.eliminarUsuario(idUsuario);
	}
	

	public void upgrade(Particular usuario)
	{
		Premium usuarionuevo= new Premium(usuario.getNombreUsuario(), usuario.getPass(), usuario.getNombre(), usuario.getApellido(), usuario.getDni());
		usuarionuevo.setIdUsuario(usuario.getIdUsuario());
		
		usuarionuevo.setEjemplares(usuario.getEjemplares());
		usuarionuevo.setReservas(usuario.getReservas());
		
		biblioteca.eliminarUsuario(usuario.getIdUsuario());
		biblioteca.addUsuario(usuarionuevo);
	}
	
	public int getTipo()
	{
		return 0;
	}
	
	
	
}
