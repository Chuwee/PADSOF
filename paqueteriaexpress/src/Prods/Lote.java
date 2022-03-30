package Prods;

import java.util.ArrayList;
import java.util.List;

import GlobalVars.TipoPaquete;

public class Lote extends Unidad {
	private double tamano;
    private int unidades;
    private List<Producto> productos;
    private TipoPaquete tipopaquete;
    
    public Lote(int id, double peso, double precio, String direccion, double tamano, int unidades) {
		super(id, peso, precio, direccion);
		this.tamano=tamano;
		this.unidades=unidades;
		this.productos=new ArrayList<Producto>();
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


	public TipoPaquete getTipopaquete() {
		return tipopaquete;
	}


	public void setTipopaquete(TipoPaquete tipopaquete) {
		this.tipopaquete = tipopaquete;
	}

}
