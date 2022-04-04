package Prods;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import java.sql.Date;

import GlobalVars.TipoPaquete;
import Pedido.Pedido;
import usuarios.ErrorAlto;
import usuarios.ErrorAncho;
import usuarios.ErrorLargo;
import usuarios.ErrorPeso;

public abstract class Unidad {

    private int identificador;
    private boolean empaquetado;
    private Pedido pedido;
    
    public Unidad(int id) {
    	this.identificador=id;
    	this.empaquetado=false;
    }
    
    public abstract void validar() throws ErrorAlto, ErrorAncho, ErrorLargo, ErrorPeso;

    public int getIdentificador() {
        return this.identificador;
    }
    public boolean getEmpaquetado() {
    	return this.empaquetado;
    }
    public void setEmpaquetado(boolean b) {
    	this.empaquetado=b;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public abstract double getPeso();
    
    public abstract double calcularPrecio();
    
    public boolean isFragil() {
    	return false;
    }
    public boolean isLote() {
    	return false;
    }
    public boolean isRefrigerado() {
    	return false;
    }
    public boolean isEstandar() {
    	return false;
    }
    public boolean isDimEsp() {
    	return false;
    }
    public boolean isLiquido() {
    	return false;
    }
    public boolean isCongelado() {
		return false;
	}
    public void estaEnPedido(Pedido p) {
    	pedido = p;
    }
    public Date getDate() {
    	return pedido.getFecha();
    }
    public boolean isUrgente() {
    	return pedido.isUrgente();
    }

    public String getDireccion() {
        return this.pedido.getDireccion();
    }

    public abstract TipoPaquete getTipoPaquete();


	
    
}
