package Prods;

public class DimensionesEspeciales extends Producto {

	public DimensionesEspeciales(int id, double peso, double precio, String direccion, String descripcion, int unidades,
			double largo, double ancho, double alto) {
		super(id, peso, direccion, descripcion, unidades, largo, ancho, alto);
	}
	
	@Override
	public boolean isDimEsp() {
    	return true;
    }
    
}
