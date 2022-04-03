package usuarios;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */

public abstract class UsuarioIdentificado{
    private String NIF, Nombre, Usuario, Contrasena, Email;

    public UsuarioIdentificado(String NIF, String Nombre, String Usuario, String Contrasena, String email ){
        this.NIF=NIF;
        this.Nombre=Nombre;
        this.Usuario=Usuario;
        this.Contrasena=Contrasena;
        this.Email=email;
    }

    public void logout(){

    }
    public String getNIF(){
        return this.NIF; 
    }
    public String getNombre(){
        return this.Nombre; 
    }
    public String getUsuario(){
        return this.Usuario; 
    }
    public String getContrasena(){
        return this.Contrasena; 
    }
    public String getEmail(){
        return this.Email; 
    }
    public void setNombre(String info){
        this.Nombre=info;
    }
    public void setUsuario(String info){
        this.Usuario=info;
    }
    public void setContrasena(String info){
        this.Contrasena=info;
    }
    public void setEmail(String info){
        this.Email=info;
    }
    public boolean isRepartidor() {
    	return false;
    }
}