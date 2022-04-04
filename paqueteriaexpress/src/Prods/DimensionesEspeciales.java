package Prods;
import GlobalVars.TipoPaquete;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import sistema.SistemaAplicacion;
/**
 * 
 * esta clase hereda de la clase Producto
 *
 */
public class DimensionesEspeciales extends Producto {
/**
 * Constructor de un producto de este tipo
 * @param sist SistemaAicacion
 * @param id, int, el identificador del producto
 * @param peso, double, peso del producto
 * @param precio, double, precio del producto
 * @param descripcion, String, descripcion del producto
 * @param unidades, int, número de unidades del producto
 * @param largo, double
 * @param ancho, double
 * @param alto, double
 */
	public DimensionesEspeciales(SistemaAplicacion sist, int id, double peso, double precio, String descripcion, int unidades,
			double largo, double ancho, double alto) {
		super(sist, id, peso, descripcion, unidades, largo, ancho, alto);
	}
	/**
	 * Esta funcion va a devolver true, el producto es de dimensiones especiales
	 */
	@Override
	public boolean isDimEsp() {
    	return true;
    }
	/**
	 * siemore va a devolver false, ya que no es de tiepo estandar
	 */
	@Override
    public boolean isEstandar() {
    	return false;
    }
	/**
	 * va a devolver el tipo de paquete dimensiones especiales, ya que el producto es de ese tipo
	 */
	@Override
	public TipoPaquete getTipoPaquete() {
		return TipoPaquete.DIMESPECIALES;
	}
    
}
