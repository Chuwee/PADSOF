package Prods;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import sistema.SistemaAplicacion;

// Correcto 

public abstract class ProductoAlimentacion extends Producto {
    public ProductoAlimentacion(SistemaAplicacion sist, int id, double peso, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(sist, id, peso, descripcion, unidades, largo, ancho ,alto);
    }
    
    @Override
    public boolean isEstandar() {
    	return false;
    }
    
}
