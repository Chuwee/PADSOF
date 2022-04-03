package Prods;

import java.util.ArrayList;
import java.util.List;

import GlobalVars.TipoPaquete;
import usuarios.ErrorAlto;
import usuarios.ErrorAncho;
import usuarios.ErrorLargo;
import usuarios.ErrorPeso;

public class Lote extends Unidad {
	private double tamano;
    private int unidades;
    private List<Producto> productos;
    private List<Lote> lotes;
    private TipoPaquete tipopaquete;
    
    public Lote(int id, String direccion, double tamano, int unidades) {
		super(id, direccion);
		this.tamano=tamano;
		this.unidades=unidades;
		this.productos=null;
		this.lotes = null;
	}
    
    @Override
    public double calcularPrecio() {
    	double counter = 0;
    	for(Producto p : productos) {
    		counter+=p.calcularPrecio();
    	}
    	for(Lote l: lotes) {
    		counter+=l.calcularPrecio();
    	}
    	return counter;
    }
    
    public double calcularPeso(){
    	double counter = 0;
    	for(Producto p: productos) {
    		counter+=p.getPeso();
    	}
    	for(Lote l: lotes) {
    		counter+=l.getPeso();
    	}
    	return counter;
    }

	@Override
	public double getPeso() {
		return calcularPeso();
	}
    
    public void validar() throws ErrorAlto, ErrorAncho, ErrorLargo, ErrorPeso {
    	for(Producto p : productos) {
    		p.validar();
    	}
    	for(Lote l: lotes) {
    		l.validar();
    	}
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


	public void anadirProducto(Producto p) {
		if(this.productos == null) {
			this.productos = new ArrayList<Producto>();
		}
		this.productos.add(p);
	}
	
	public void eliminarProducto(Producto p) {
		this.productos.remove(p);
	}


	public TipoPaquete getTipopaquete() {
		return tipopaquete;
	}


	public void setTipopaquete(TipoPaquete tipopaquete) {
		this.tipopaquete = tipopaquete;
	}
	
	@Override
	public boolean isLote() {
    	return true;
    }

}
