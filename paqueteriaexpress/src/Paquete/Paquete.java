package Paquete;

import java.util.ArrayList;

import Prods.Unidad;

public class Paquete {
    private int idPaquete;
    private String direccion;
    private double peso;
    private ArrayList<Unidad> unidades;
    private boolean entregado;

    public Paquete(int idPaquete, String direccion) {
        this.idPaquete = idPaquete;
        this.direccion = direccion;
        this.peso = 0;
        unidades = new ArrayList<Unidad>();
        this.setEntregado(false);
    }

    public ArrayList<Unidad> getUnidades() {
        return this.unidades;
    }

    public void anadirUnidad(Unidad newUnit) {
        this.unidades.add(newUnit);
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
        return this.peso;
    }

    public void setPeso(double d) {
        this.peso = d;
    }

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
    
}
