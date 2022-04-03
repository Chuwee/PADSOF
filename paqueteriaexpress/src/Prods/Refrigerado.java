package Prods;

import sistema.SistemaAplicacion;

// Correcto

public class Refrigerado extends ProductoAlimentacion {
    private boolean congelado;

    public Refrigerado(SistemaAplicacion sist, int id, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto, boolean congelado) {
    	super(sist, id, peso, direccion, descripcion,unidades, largo, ancho, alto);
    	this.congelado=congelado;
    }
	public boolean isCongelado() {
		return congelado;
	}

	public void setCongelado(boolean congelado) {
		this.congelado = congelado;
	}
	
	@Override
	public boolean isRefrigerado() {
    	return true;
    }
	/*@Override
    public boolean isAlimentacion() {
    	return false;
    }*/
}
