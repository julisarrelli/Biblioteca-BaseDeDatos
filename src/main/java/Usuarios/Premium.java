package Usuarios;




public class Premium extends Particular {

	
	private int puntos;

	public Premium(String nombreUsuario, String pass,
			String nombre, String apellido, String dni) {
		super(nombreUsuario, pass, nombre, apellido, dni);
		this.puntos = 0;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
		
	}
	
	
	public void sumarPuntos(int i){
		puntos+=i;
	}
	

	public int getTipo()
	{
		return 3;
	}

	

}
