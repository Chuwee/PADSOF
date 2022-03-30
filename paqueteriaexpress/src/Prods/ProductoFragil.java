package Prods;

public class ProductoFragil extends Producto {
	private boolean asegurado;

	public ProductoFragil(boolean asegurado,int id, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(id,peso,precio,direccion, descripcion, unidades, largo, ancho ,alto);
    	this.asegurado=asegurado;
    }
	public boolean isAsegurado() {
		return asegurado;
	}

	public void setAsegurado(boolean asegurado) {
		this.asegurado = asegurado;
	}
	
}
