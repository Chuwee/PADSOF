package Prods;

public class Lote extends Unidad {
	private double tamano;
    private int unidades;
    
    public Lote(int id, double peso, double precio, String direccion, double tamano, int unidades) {
		super(id, peso, precio, direccion);
		this.tamano=tamano;
		this.unidades=unidades;
	}


    public double getTamano() {
        return this.tamano;
    }

    public void setTamano(double tamano) {
        this.tamano = tamano;
    }

    public int getUnidades() {
        return this.unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

}
