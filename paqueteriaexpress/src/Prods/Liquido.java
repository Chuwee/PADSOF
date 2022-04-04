package Prods;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import sistema.SistemaAplicacion;

/**
 * 
 *esta clase hereda de la clase abstracta de productoAlimentacion
 *
 */

public class Liquido extends ProductoAlimentacion {
	/**
	 * Constructor
	 * @param sist, Sistemaamplicacion
	 * @param id, int, el identificador del producto
	 * @param peso, double
	 * @param precio double
	 * @param descripcion String
	 * @param unidades, int, las unidades que hay de ese producto
	 * @param largo, double
	 * @param ancho, double
	 * @param alto, double
	 */
	public Liquido(SistemaAplicacion sist, int id, double peso, double precio, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(sist, id, peso, descripcion, unidades, largo, ancho, alto);
    }
	/**
	 * siempre va a devolver true
	 */
    @Override
    public boolean isLiquido() {
    	return true;
    }
}
