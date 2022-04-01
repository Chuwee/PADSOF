package usuarios;

import Transporte.Camion;

/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */

public class Repartidor extends UsuarioIdentificado{
    private String telefono;

    public Repartidor(String telefono, String NIF, String Nombre, String Usuario, String Contrasena, String email){
        super(NIF, Nombre, Usuario, Contrasena, email);
        this.telefono = telefono;

    }
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String tel) {
        this.telefono = tel;
    }

    public void imprimir_informe(){

    }
    public void marcar_Paquetes_entregados(){

    }
    public Camion ver_camion_asignado(){
        return null; 
        
    }
    
    
}
