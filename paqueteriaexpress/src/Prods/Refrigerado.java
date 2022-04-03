package Prods;

import sistema.SistemaAplicacion;

// Correcto

public class Refrigerado extends ProductoAlimentacion {
    private boolean congelado;

    public Refrigerado(SistemaAplicacion sist, int id, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto, boolean congelado) {
    	super(sist, id, peso, direccion, descripcion,unidades, largo, ancho, alto);
    	this.congelado=congelado;
    }
    @Override
	public boolean isCongelado() {
		return congelado;
	}

	public void setCongelado(boolean congelado) {
		this.congelado = congelado;
	}
	
	@Override
	public boolean isRefrigerado() {
    	if(congelado==true) {
    		return false;
    	}
		return true;
    }
<<<<<<< HEAD

=======
	/*@Override
    public boolean isAlimentacion() {
    	return false;
    }*/
	
>>>>>>> fffc5a4d49c49eccc172fd462e6a56d9b4be87f5
}
