package usuarios;


public class ErrorAlto extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4427874546133904145L;

	public ErrorAlto() {
		super("El alto del producto supera el umbral establecido por la empresa.\n");
	}
}
