package Prods;
import GlobalVars.TipoPaquete;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import sistema.SistemaAplicacion;

/**
 * 
 *esta clase hereda de la clase producto
 *
 */
public class ProductoFragil extends Producto {
	private boolean asegurado;
/**
 * constructor
 * @param sist SistemaAplicacion
 * @param asegurado boolean, true en caso de ser verdad
 * @param id, int identificador del producto
 * @param peso, double
 * @param descripcion, String
 * @param unidades, int numero de unidades del producto
 * @param largo, double
 * @param ancho, double
 * @param alto, double
 */
	public ProductoFragil(SistemaAplicacion sist, boolean asegurado,int id, double peso, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(sist, id, peso, descripcion, unidades, largo, ancho ,alto);
    	this.asegurado=asegurado;
    }
	
	/**
	 * 
	 * @return boolean, true en caso de que el producto si esté asegurado
	 */
	public boolean isAsegurado() {
		return asegurado;
	}
/**
 * 
 * @param asegurado, valor que queremos que tenga asegurado en nuestro producto
 */
	public void setAsegurado(boolean asegurado) {
		this.asegurado = asegurado;
	}
	/**
	 * @return double, precio, una vez aplicados los extras
	 */
	@Override
	public double calcularPrecio() {
		return super.calcularPrecio()+(asegurado?5:2);
	}
	/**
	 * va a devolver siempre true
	 */
	@Override
	public boolean isFragil() {
		return true;
	}
	/**
	 * va a devolver siempre false
	 */
	@Override
    public boolean isEstandar() {
    	return false;
    }
/**
 * va a devolver el tipo de paquete de tipo fragil
 */
	@Override
	public TipoPaquete getTipoPaquete() {
		return TipoPaquete.FRAGIL;
	}
	
}
