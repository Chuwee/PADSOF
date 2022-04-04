package Paquete;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import java.util.ArrayList;
import java.sql.Date;
import GlobalVars.TipoPaquete;

import Prods.Unidad;

public class Paquete {
    private int idPaquete;
    private String direccion;
    private ArrayList<Unidad> unidades;
    private boolean entregado;
    private EstadoPaquete estadoPaquete;
    private TipoPaquete tp;

	public Paquete(int idPaquete, String direccion, TipoPaquete tp) {
        this.idPaquete = idPaquete;
        this.direccion = direccion;
        this.setTp(tp);
        unidades = new ArrayList<Unidad>();
        this.setEntregado(false);
        estadoPaquete=EstadoPaquete.EnReparto;
    }
	
	public EstadoPaquete getEstadoPaquete() {
		return estadoPaquete;
	}

	public void setEstadoPaquete(EstadoPaquete estadoPaquete) {
		this.estadoPaquete = estadoPaquete;
	}

    public ArrayList<Unidad> getUnidades() {
        return this.unidades;
    }

    public void anadirUnidad(Unidad newUnit) {
        this.unidades.add(newUnit);
    }
    
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

    public void quitarUnidad(Unidad Unit) {
        this.unidades.remove(Unit);
    }

    public int getIdPaquete() {
        return this.idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPeso() {
        double pesoTotal = 0;
        for(Unidad u : unidades) {
            pesoTotal+=u.getPeso();
        }
        return pesoTotal;
    }

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
    
	public boolean isUrgente() {
		for(Unidad u: unidades) {
			if(u.isUrgente())
				return true;
		}
		return false;
	}

	public TipoPaquete getTp() {
		return tp;
	}

	public void setTp(TipoPaquete tp) {
		this.tp = tp;
	}

    public boolean equals(Paquete p) {
        return this.idPaquete == p.getIdPaquete();
    }
}
