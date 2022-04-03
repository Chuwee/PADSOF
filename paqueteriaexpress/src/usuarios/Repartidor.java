package usuarios;

import Paquete.*;
import Transporte.Camion;

/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */

public class Repartidor extends UsuarioIdentificado{
    private String telefono;
    private Camion camion;

    public Repartidor(String telefono, String NIF, String Nombre, String Usuario, String Contrasena, String email){
        super(NIF, Nombre, Usuario, Contrasena, email);
        this.telefono = telefono;
        this.setCamion(null);

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

    }
    public void marcarPaqueteNoEntregado(Paquete p){
    	p.setEntregado(false);
    	if(p.getEstadoPaquete().equals(EstadoPaquete.EnReparto)) {
    		p.setEstadoPaquete(EstadoPaquete.PendienteFallido1);
    	}else if(p.getEstadoPaquete().equals(EstadoPaquete.EnRepartoFallido1)) {
    		p.setEstadoPaquete(EstadoPaquete.Fallido);
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
    
    
}
