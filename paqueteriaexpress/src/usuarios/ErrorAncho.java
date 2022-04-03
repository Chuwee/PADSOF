package usuarios;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class ErrorAncho extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2778599313366956142L;

	public ErrorAncho() {
		super("El ancho del producto supera el umbral establecido por la empresa.\n");
	}
}
