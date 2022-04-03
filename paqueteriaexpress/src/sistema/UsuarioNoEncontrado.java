package sistema;

public class UsuarioNoEncontrado extends Exception{
	public UsuarioNoEncontrado() {
		super("El usuario no se ha encontrado\n");
	}

}
