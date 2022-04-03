package Transporte;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import java.util.ArrayList;

import Paquete.Paquete;

public abstract class Camion {
    private double pesoMaximo;
    private double currentPeso;
    private String matricula;
    private EstadoCamion estado;
    private TipoCamion tipo;
    private ArrayList<Paquete> paquetes;

    public Camion(double pesoMaximo, String matricula, EstadoCamion estado, TipoCamion tipo){
        this.pesoMaximo=pesoMaximo; 
        this.matricula=matricula; 
        this.estado=estado; 
        this.paquetes=new ArrayList<Paquete>();
        this.tipo = tipo;
        this.currentPeso = 0;
    }

    public TipoCamion getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoCamion tipo) {
        this.tipo = tipo;
    }

    public double getPesoMaximo() {
        return this.pesoMaximo;
    }

    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public EstadoCamion getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoCamion estado) {
        this.estado = estado;
    }

	public ArrayList<Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(ArrayList<Paquete> paquetes) {
		this.paquetes = paquetes;
	}
    
    public void anadirPaquete(Paquete paquete) throws PesoCamionException {
        if(!canAdd(paquete.getPeso()))
            throw new PesoCamionException();
        this.paquetes.add(paquete);
    }

    public void quitarPaquete(Paquete paquete) {
        this.paquetes.remove(paquete);
    }

    public boolean canAdd(double peso) {
        return (peso+currentPeso)>pesoMaximo?false:true;
    }

}
