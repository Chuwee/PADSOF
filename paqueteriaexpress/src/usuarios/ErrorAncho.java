package usuarios;

public class ErrorAncho extends Exception{
	public ErrorAncho() {
		super("El ancho del producto supera el umbral establecido por la empresa.\n");
	}
}
