package Paquete;

import java.util.ArrayList;

public class Paquete {
    private int idPaquete;
    private String direccion;
    private int peso;
    private ArrayList<Unidad> unidades;

    public Paquete(int idPaquete, String direccion) {
        this.idPaquete = idPaquete;
        this.direccion = direccion;
        this.peso = null;
        unidades = new ArrayList<Unidad>();
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

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
}
