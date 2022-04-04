package Transporte;
import java.text.SimpleDateFormat;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import Paquete.Paquete;

public class Camion {
    private double pesoMaximo;
    private double currentPeso;
    private String matricula;
    private EstadoCamion estado;
    private TipoCamion tipo;
    private ArrayList<Paquete> paquetes;
    private Map<String, Integer> paquetesMensuales;
    private int numpaquetes;
    private int numrepartos;
   

    public Camion(double pesoMaximo, String matricula, EstadoCamion estado, TipoCamion tipo){
        this.pesoMaximo=pesoMaximo; 
        this.matricula=matricula; 
        this.estado=estado; 
        this.paquetes=new ArrayList<Paquete>();
        this.tipo = tipo;
        this.currentPeso = 0;
        this.paquetesMensuales=new LinkedHashMap<String,Integer>();
        this.numpaquetes=0;
        this.numrepartos=0;
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
    public void CalcularPaquetesMensuales() {
    	SimpleDateFormat formater;
    	String fecha;
    	formater=new SimpleDateFormat("MM-yy");
    	for(Paquete p: paquetes) {
    			fecha=formater.format(p.getDate());
    			if(paquetesMensuales.putIfAbsent(fecha, 1)!=null) {
    				paquetesMensuales.put(fecha, 1+ paquetesMensuales.get(fecha));
    			}
    	}
    	this.numpaquetes+=paquetes.size();
    	this.numrepartos++;
    }

	public Map<String, Integer> getPaquetesMensuales() {
		return paquetesMensuales;
	}

	public void setPaquetesMensuales(Map<String, Integer> paquetesMensuales) {
		this.paquetesMensuales = paquetesMensuales;
	}

	public int getNumpaquetes() {
		return numpaquetes;
	}

	public void setNumpaquetes(int numpaquetes) {
		this.numpaquetes = numpaquetes;
	}

	public int getNumrepartos() {
		return numrepartos;
	}

	public void setNumrepartos(int numrepartos) {
		this.numrepartos = numrepartos;
	}
	
	public double getNumPaquetesReparto() {
		return numpaquetes/numrepartos;
	}

}
