package Paquete;
/**
 * @author Ignacio Ildefonso del Miguel Ruano
 * (autoría de funciones puesta encima de las mismas)
 * 
 */
import java.util.ArrayList;
import java.sql.Date;
import GlobalVars.TipoPaquete;

import Prods.Unidad;
/**
 * 
 * esta es la clase de paquete con sus diferentes métodos y atributos
 *
 */
public class Paquete {
    private int idPaquete;
    private String direccion;
    private ArrayList<Unidad> unidades;
    private boolean entregado;
    private EstadoPaquete estadoPaquete;
    private TipoPaquete tp;
    private boolean tried;
/**
 * 
 * @param idPaquete int, identificador del paquete
 * @param direccion String, dirección a la que va a llegar el paquete
 * @param tp TipoPaquete
 */
	public Paquete(int idPaquete, String direccion, TipoPaquete tp) {
        this.idPaquete = idPaquete;
        this.direccion = direccion;
        this.setTp(tp);
        unidades = new ArrayList<Unidad>();
        this.setEntregado(false);
        estadoPaquete=EstadoPaquete.EnReparto;
        tried = false;
    }
	/**
	 * 
	 * @return EstadoPaquete
	 */
	public EstadoPaquete getEstadoPaquete() {
		return estadoPaquete;
	}
/**
 * cambia el estado del paquete y lo pone al que le pasamos por parámetro
 * @param estadoPaquete
 */
	public void setEstadoPaquete(EstadoPaquete estadoPaquete) {
		this.estadoPaquete = estadoPaquete;
	}
/**
 * 
 * @return el array de unidades del paquete 
 */
    public ArrayList<Unidad> getUnidades() {
        return this.unidades;
    }

    /**
     * 
     * @param newUnit unidad que quieres añadir al paquete
     */
    public void anadirUnidad(Unidad newUnit) {
        this.unidades.add(newUnit);
    }
    /**
     * @author Ignacio Ildefonso de Miguel Ruano
     * @return devuelve DAte, la fecha del oaquete
     */
    public Date getDate() {
    	if(this.unidades == null)
    			return null;
    	Date min = unidades.get(0).getDate();
    	for(Unidad u : unidades) {
    		if(min.before(u.getDate()))
    			min = u.getDate();
    	}
    	return min;
    }
/**
 * 
 * @param Unit unidad que quieres eliminar del Paquete
 */
    public void quitarUnidad(Unidad Unit) {
        this.unidades.remove(Unit);
    }
/**
 * 
 * @return int id; el id del paquete
 */
    public int getIdPaquete() {
        return this.idPaquete;
    }
/**
 * 
 * @param idPaquete id que quieres que tenga ahora el paquete
 */
    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }
/**
 * 
 * @returnString, la direccion del paquete
 */
    public String getDireccion() {
        return this.direccion;
    }
/**
 * 
 * @param direccion, direccion a la que quieres cambiar el Paquete
 */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
/**
 * 
 * @return el peso del paquete, double
 */
    public double getPeso() {
        double pesoTotal = 0;
        for(Unidad u : unidades) {
            pesoTotal+=u.getPeso();
        }
        return pesoTotal;
    }
/**
 * 
 * @return boolean, true si el paauete ha sudo entregado
 */
	public boolean isEntregado() {
		return entregado;
	}
/**
 * 
 * @param entregado, cambia el estado de entregado al valor de entregado que le pasas por parámetro
 */
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
    /**
     * @author Ignacio Ildefonso de Miguel Ruano
     * @return boolean, true si el pedido es urgente
     */
	public boolean isUrgente() {
		for(Unidad u: unidades) {
			if(u.isUrgente())
				return true;
		}
		return false;
	}
/**
 * 
 * @return TipoPaquete
 */
	public TipoPaquete getTp() {
		return tp;
	}
/**
 * 
 * @param tp, cambia el tipo de paquete al que le pasas por parámetro
 */
	public void setTp(TipoPaquete tp) {
		this.tp = tp;
	}
/**
 * @author Ignacio Ildefonso de Miguel Ruano
 * @param p, paquete
 * @return true en caso de que el id del paquete coincida
 */
    public boolean equals(Paquete p) {
        return this.idPaquete == p.getIdPaquete();
    }

    public boolean getTried() {
        return this.tried;
    }
    public void setTried(boolean tried) {
        this.tried = tried;
    }
}
