package usuarios;
import java.util.ArrayList;

import Pedido.Pedido;
import sistema.SistemaAplicacion;

/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class Cliente extends UsuarioIdentificado {
    private String TarjetaDeCredito;
    private String dirContacto;
    private ArrayList<Integer> pedidosUser;

	public Cliente(String TarjetaDeCredito, String dirContacto, String NIF, String Nombre, String Usuario, String Contrasena, String email){
        super(NIF, Nombre, Usuario, Contrasena, email);
        this.TarjetaDeCredito = TarjetaDeCredito;
		this.dirContacto=dirContacto;
		this.pedidosUser=new ArrayList<Integer>();

    }


	public String getTarjetaDeCredito() {
		return this.TarjetaDeCredito;
	}

	public void setTarjetaDeCredito(String TarjetaDeCredito) {
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

    public void descargarFactura(int id_pedido){

    }
    public void verPedidos(SistemaAplicacion sist){
    	for(int id : this.pedidosUser) {
    		for(Pedido p :sist.getPedidos()) {
    			if(p.getCliente().equals(this)) {
    				System.out.println("Nº de pedido: "+ p.getIdPedido()+" Estado: "+ p.getEstado()+"\n");
    			}
    		}
    	}
  
    }
    public void logout(){

    
	}
}
