package Prods;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import sistema.SistemaAplicacion;
public class DimensionesEspeciales extends Producto {

	public DimensionesEspeciales(SistemaAplicacion sist, int id, double peso, double precio, String descripcion, int unidades,
			double largo, double ancho, double alto) {
		super(sist, id, peso, descripcion, unidades, largo, ancho, alto);
	}
	
	@Override
	public boolean isDimEsp() {
    	return true;
    }
	@Override
    public boolean isEstandar() {
    	return false;
    }
    
}
