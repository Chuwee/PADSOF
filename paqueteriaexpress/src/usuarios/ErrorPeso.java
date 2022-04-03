package usuarios;

public class ErrorPeso extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1622004599056371989L;

	public ErrorPeso() {
		super("El peso del producto supera el umbral establecido por la empresa.\n");
	}
}
