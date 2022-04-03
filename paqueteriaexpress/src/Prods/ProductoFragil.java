package Prods;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import sistema.SistemaAplicacion;

// Correcto

public class ProductoFragil extends Producto {
	private boolean asegurado;

	public ProductoFragil(SistemaAplicacion sist, boolean asegurado,int id, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(sist, id, peso, direccion, descripcion, unidades, largo, ancho ,alto);
    	this.asegurado=asegurado;
    }
	
	
	public boolean isAsegurado() {
		return asegurado;
	}

	public void setAsegurado(boolean asegurado) {
		this.asegurado = asegurado;
	}
	
	@Override
	public double calcularPrecio() {
		return super.calcularPrecio()+2+5*(asegurado?1:0);
	}
	
	@Override
	public boolean isFragil() {
		return true;
	}
	
	@Override
    public boolean isEstandar() {
    	return false;
    }
	
}
