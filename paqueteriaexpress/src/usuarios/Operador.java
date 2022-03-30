package usuarios;
import java.util.ArrayList;

import Paquete.Paquete;
import Pedido.*;
import Prods.*;
import sistema.SistemaAplicacion;

import java.util.*;
import java.io.*;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class Operador extends UsuarioIdentificado{
    private int pedidosDistribuidos;

    public Operador(int pedidosDistribuidos, String NIF, String Nombre, String Usuario, String Contrasena, String email){
        super(NIF, Nombre, Usuario, Contrasena, email);
        this.pedidosDistribuidos = pedidosDistribuidos;

    }

    public int getPedidosDistribuidos() {
        return this.pedidosDistribuidos;
    }

    public void setPedidosDistribuidos(int pedidosDistribuidos) {
        this.pedidosDistribuidos = pedidosDistribuidos;
    }
    public Pedido nuevoPedido(int idPedido, boolean urgente, Date fecha, String direccion, int codigoPostal, String descripcion, Cliente cliente){
        Pedido p=new Pedido(idPedido, urgente, direccion, codigoPostal, descripcion, EstadoPedido.EnConstruccion, fecha);
        cliente.getPedidosUser().add(idPedido);
        return p; 

    }

    public boolean añadirUnidad(Pedido p) {
    	return true;
    	
    }
    public boolean validarPedido(SistemaAplicacion sistema,Pedido p){
        return true; 
    }
    public void empaquetarPedido(SistemaAplicacion sist, Pedido pedido){
    	double maxPeso = sist.getPesoMaximo();
    	int id;
    	if(sist.getPaquetes().isEmpty()) {
    		id=0;
    	}else {
    		id=sist.getPaquetes().size();
    	}
    	for(Unidad u : pedido.getUnidades()) {
    		if(u instanceof ProductoFragil) {
    			Producto prod=(Producto)u;
    			for(int i=0;i<prod.getUnidades();i++) {
    				Paquete p=new Paquete(id, u.getDireccion());
        			p.getUnidades().add(u);
        			p.setPeso(u.getPeso());
        			u.setEmpaquetado(true);
        			sist.getPaquetes().add(p);
        			id++;
    			}
    		}
    	}
    	

    }
    public boolean validarPedido(Pedido p){
    	return true; 
    }
    public void modificarAlto(SistemaAplicacion p, double alto){
        p.setAlto(alto);
    }
    public void modificarAncho(SistemaAplicacion p, double ancho){
        p.setAncho(ancho);
    }
    public void modificarLargo(SistemaAplicacion p, double largo){
        p.setLargo(largo);
    }
    public void modificarPeso(SistemaAplicacion p, double peso){
        p.setPesoMaximo(peso);
    }
    public void modificarIntentos(SistemaAplicacion p, int intentos){
        p.setMaxIntentos(intentos);
    }
    public void modificarDirecciones(SistemaAplicacion p, int direcciones){
        p.setMaxDirecciones(direcciones);
    }
    public void planificarReparto(){

    }
    
}
