package usuarios;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class ErrorLargo extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -782909850393413502L;

	public ErrorLargo() {
		super("El largo del producto supera el umbral establecido por la empresa.\n");
	}

}
