package usuarios;

import GlobalVars.ColasPrioridad;
import GlobalVars.Vars;
import Paquete.*;
import Transporte.Camion;
import sistema.SistemaAplicacion;

/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */

public class Repartidor extends UsuarioIdentificado{
    private String telefono;
    private Camion camion;
    private SistemaAplicacion sist;

    public Repartidor(SistemaAplicacion sist, String telefono, String NIF, String Nombre, String Usuario, String Contrasena, String email){
        super(NIF, Nombre, Usuario, Contrasena, email);
        this.telefono = telefono;
        this.setCamion(null);
        this.sist=sist;

    }
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String tel) {
        this.telefono = tel;
    }

    public void imprimir_informe(){

    }
    public void ver_paquetes() {
    	int i=0;
    	for(Paquete p :this.camion.getPaquetes()) {
    		i++;
    		System.out.println("Paquete "+i+": " + p.getDireccion() +"\n");
    	}
    }
    public void marcarPaqueteEntregado(Paquete p){
    	p.setEntregado(true);
    	p.setEstadoPaquete(EstadoPaquete.Entregado);
    	sist.getPaquetesEntregados().add(p);

    }
    public void marcarPaqueteNoEntregado(Paquete p){
    	p.setEntregado(false);
    	if(p.getEstadoPaquete().equals(EstadoPaquete.EnReparto)) {
    		p.setEstadoPaquete(EstadoPaquete.PendienteFallido1);
    		if(p.isUrgente()) {
                sist.anadirPaqueteACola(p, Vars.getColaPrioridad(ColasPrioridad.URGENTES));
                return;
            }
            sist.anadirPaqueteACola(p, Vars.getColaPrioridad(ColasPrioridad.DEVUELTOS));
    	}else if(p.getEstadoPaquete().equals(EstadoPaquete.EnRepartoFallido1)) {
    		p.setEstadoPaquete(EstadoPaquete.Fallido);
    		sist.getPaquetesNoEntregados().add(p);
    	}

    }
    public Camion ver_camion_asignado(){
        return null; 
        
    }
	public Camion getCamion() {
		return camion;
	}
	public void setCamion(Camion camion) {
		this.camion = camion;
	}
	
	@Override
	public boolean isRepartidor() {
    	return true;
    }
	public SistemaAplicacion getSist() {
		return sist;
	}
	public void setSist(SistemaAplicacion sist) {
		this.sist = sist;
	}
    
    
}
