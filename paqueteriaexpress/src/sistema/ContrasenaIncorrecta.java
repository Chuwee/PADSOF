package sistema;

public class ContrasenaIncorrecta extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8708035574174757363L;

	public ContrasenaIncorrecta() {
		super("La contrasena es incorrecta.\n");
	}

}
