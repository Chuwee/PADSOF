package usuarios;
import java.util.ArrayList;

import java.util.*;
import java.io.*;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class Cliente extends UsuarioIdentificado {
    private int TarjetaDeCredito;
    private String dirContacto;
    private ArrayList<Integer> pedidosUser;

	public Cliente(int TarjetaDeCredito, String dirContacto, ArrayList<Integer> pedidosUser, String NIF, String Nombre, String Usuario, String Contrasena, String email){
        super(NIF, Nombre, Usuario, Contrasena, email);
        this.TarjetaDeCredito = TarjetaDeCredito;
		this.dirContacto=dirContacto;
		this.pedidosUser=pedidosUser;

    }


	public int getTarjetaDeCredito() {
		return this.TarjetaDeCredito;
	}

	public void setTarjetaDeCredito(int TarjetaDeCredito) {
		this.TarjetaDeCredito = TarjetaDeCredito;
	}

	public String getDirContacto() {
		return this.dirContacto;
	}

	public void setDirContacto(String dirContacto) {
		this.dirContacto=dirContacto;
	}

	public ArrayList<Integer> getPedidosUser() {
		return this.pedidosUser;
	}

	public void setPedidosUser(ArrayList<Integer> pedidosUser) {
		this.pedidosUser = pedidosUser;
	}

    public boolean comprobarTarjeta(){
		return true; 

    }
    public void descargarFactura(int id_pedido){

    }
    public void verPedidos(){

    }
    public void logout(){

    
	}
}
