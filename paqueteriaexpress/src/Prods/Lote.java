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
/**
 * 
 * Esta clase hereda de la clase abstracta Unidad
 *
 */
public class Lote extends Unidad {
	private double tamano;
    private int unidades;
    private List<Producto> productos;
    private List<Lote> lotes;
    /**
     * constructor de Lote
     * @param id, int, identificador del lote
     * @param tamano, double
     * @param unidades, int
     */
    public Lote(int id, double tamano, int unidades) {
		super(id);
		this.tamano=tamano;
		this.unidades=unidades;
		this.productos=null;
		this.lotes = null;
		productos=new ArrayList<Producto>();
		lotes=new ArrayList<Lote>();
	}
    
    /**
     * este método va a calcular el precio del lote calculando el precio de cada uno de sus productos y de sus lotes
     * @return double precio
     */
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
    /**
     * este método va a calcular el peso del lote calculando el peso de cada uno de sus productos y de sus lotes
     * @return double peso
     */

    
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
/**
 * 
 * devuelve el peso del lote una vez se ha calculado
 * @return peso double
 */
	@Override
	public double getPeso() {
		return calcularPeso();
	}
    /**
     * este método valida un lote, comprobando que las unidades que lo componen no superan los umbrales establecidos
     */
    public void validar() throws ErrorAlto, ErrorAncho, ErrorLargo, ErrorPeso {
    	for(Producto p : productos) {
    		p.validar();
    	}
    	for(Lote l: lotes) {
    		l.validar();
    	}
    }
/**
 * 
 * @return double, el tamano del lote
 */
    public double getTamano() {
        return this.tamano;
    }
/**
 * Este método cambia el tamano del lote y le asigna el que le pasamos por parámetro
 * @param tamano,double, tamano que va a tener ahora nuestro lote
 */
    public void setTamano(double tamano) {
        this.tamano = tamano;
    }
/**
 * 
 * @return int, numero de unidades que hay en el lote
 */
    public int getUnidades() {
        return this.unidades;
    }
/**
 * 
 * @param unidades, numero de unidades que va a tener ahora nuestro lote
 */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

/**
 * 
 * @return lista de todos los productos que hay en el lote
 */
	public List<Producto> getProductos() {
		return productos;
	}
	/**
	 * 
	 * @return lista de todos los lotes que hay en el lote
	 */
	public List<Lote> getLotes() {
		return lotes;
	}

/**
 * 
 * @param p, producto que queremos anadir al lote
 */
	public void anadirProducto(Producto p) {
		if(this.productos == null) {
			this.productos = new ArrayList<Producto>();
		}
		this.productos.add(p);
	}
	/**
	 * 
	 * @param l, lote que queremos anadir al lote
	 */
	public void anadirLote(Lote l) {
		if(this.lotes == null) {
			this.lotes = new ArrayList<Lote>();
		}
		this.lotes.add(l);
	}
	/**
	 * 
	 * @param p, producto que queremos eliminar del lote
	 */
	public void eliminarProducto(Producto p) {
		this.productos.remove(p);
	}
	/**
	 * siempre va a devolver true
	 */
	@Override
	public boolean isLote() {
    	return true;
    }
/**
 * en el momento en que haya algo frágil, el tipo va a ser frágil, en otro caso,
 * el tipo será el del primer elemento
 */
	@Override
	public TipoPaquete getTipoPaquete() {
		for(Producto p:productos) {
			if(p.getTipoPaquete().equals(TipoPaquete.FRAGIL)) {
				return TipoPaquete.FRAGIL;
			}	
		}
		for(Lote l:lotes) {
			if(l.getTipoPaquete().equals(TipoPaquete.FRAGIL)) {
				return TipoPaquete.FRAGIL;
			}
		}
		return productos.get(0).getTipoPaquete();
	}

	

}
