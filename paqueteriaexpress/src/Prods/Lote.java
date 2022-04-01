package Prods;

import java.util.ArrayList;
import java.util.List;

public class Lote extends Unidad {
	private double tamano;
    private int unidades;
    private List<Producto> productos;
    
    public Lote(int id, double precio, String direccion, double tamano, int unidades) {
		super(id, precio, direccion);
		this.tamano=tamano;
		this.unidades=unidades;
		this.productos=new ArrayList<Producto>();
	}
    @Override
    public double calcularPrecio() {
    	double counter = 0;
    	for(Producto p : productos) {
    		counter+=p.calcularPrecio();
    	}
    	return counter;
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


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
