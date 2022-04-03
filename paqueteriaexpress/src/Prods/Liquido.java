package Prods;

import sistema.SistemaAplicacion;

// Correcto 

public class Liquido extends ProductoAlimentacion {
	
	public Liquido(SistemaAplicacion sist, int id, double peso, double precio, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(sist, id, peso, descripcion, unidades, largo, ancho, alto);
    }
    @Override
    public boolean isLiquido() {
    	return true;
    }
}
