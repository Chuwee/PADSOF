package sistema;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class UsuarioNoEncontrado extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -11487936203791678L;

	public UsuarioNoEncontrado() {
		super("El usuario no se ha encontrado\n");
	}

}
