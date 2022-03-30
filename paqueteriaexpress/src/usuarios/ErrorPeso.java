package usuarios;

public class ErrorPeso extends Exception{
	
	public ErrorPeso() {
		super("El peso del producto supera el umbral establecido por la empresa.\n");
	}
	
	
}
