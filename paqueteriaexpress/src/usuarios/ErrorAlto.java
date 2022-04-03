package usuarios;

/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class ErrorAlto extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4427874546133904145L;

	public ErrorAlto() {
		super("El alto del producto supera el umbral establecido por la empresa.\n");
	}
}
