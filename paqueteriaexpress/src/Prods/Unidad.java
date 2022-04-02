package Prods;

import java.util.ArrayList;
import java.sql.Date;

import Pedido.Pedido;
import Paquete.Paquete;

public abstract class Unidad {

    private int identificador;
    private double peso;
    private String direccion;
    private boolean empaquetado;
    private Pedido pedido;
    
    public Unidad(int id, double peso, String direccion) {
    	this.identificador=id;
    	this.peso=peso;
    	this.direccion=direccion;
    	this.empaquetado=false;
    }

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

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public String getDireccion() {
    	return this.direccion;
    }
    public void setDireccion(String direccion) {
    	this.direccion=direccion;
    }
    
    public double calcularPrecio() {
    	if (peso < 1) 
    		return 0.4;
    	else if(peso < 5)
    		return 0.6;
    	return 5;
    }
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
    public boolean isAlimentacion() {
    	return false;
    }
    
    
    public void estaEnPedido(Pedido p) {
    	pedido = p;
    }
    public Date getDate() {
    	return pedido.getFecha();
    }
    
}
