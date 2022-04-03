package sistema;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class ContrasenaIncorrecta extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8708035574174757363L;

	public ContrasenaIncorrecta() {
		super("La contrasena es incorrecta.\n");
	}

}
