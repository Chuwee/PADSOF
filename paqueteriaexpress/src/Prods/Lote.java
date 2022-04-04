package Prods;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
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
    
    public Lote(int id, double tamano, int unidades) {
		super(id);
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
	public List<Lote> getLotes() {
		return lotes;
	}


	public void anadirProducto(Producto p) {
		if(this.productos == null) {
			this.productos = new ArrayList<Producto>();
		}
		this.productos.add(p);
	}
	public void anadirLote(Lote l) {
		if(this.lotes == null) {
			this.lotes = new ArrayList<Lote>();
		}
		this.lotes.add(l);
	}
	
	public void eliminarProducto(Producto p) {
		this.productos.remove(p);
	}
	@Override
	public boolean isLote() {
    	return true;
    }

	@Override
	public TipoPaquete getTipoPaquete() {
		return productos.get(0).getTipoPaquete();
	}

}
