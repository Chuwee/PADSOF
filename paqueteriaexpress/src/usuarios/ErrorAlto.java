package usuarios;

public class ErrorAlto extends Exception{
	public ErrorAlto() {
		super("El alto del producto supera el umbral establecido por la empresa.\n");
	}
}
