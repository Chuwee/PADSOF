package Prods;

public class Refrigerado extends ProductoAlimentacion {
    private boolean congelado;

    public Refrigerado(int id, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto, boolean congelado) {
    	super(id, peso, direccion, descripcion,unidades, largo, ancho, alto);
    	this.congelado=congelado;
    }
	public boolean isCongelado() {
		return congelado;
	}

	public void setCongelado(boolean congelado) {
		this.congelado = congelado;
	}
}
