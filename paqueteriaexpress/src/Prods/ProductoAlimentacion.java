package Prods;

public class ProductoAlimentacion extends Producto {
    public ProductoAlimentacion(int id, double peso, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(id,peso,direccion, descripcion, unidades, largo, ancho ,alto);
    }
    
    @Override
    public boolean isAlimentacion() {
    	return true;
    }
    @Override
    public boolean isEstandar() {
    	return false;
    }
}
