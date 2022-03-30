package usuarios;

public class ErrorLargo extends Exception{
	public ErrorLargo() {
		super("El largo del producto supera el umbral establecido por la empresa.\n");
	}

}
