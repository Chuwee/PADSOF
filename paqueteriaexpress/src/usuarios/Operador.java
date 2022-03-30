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

    public boolean añadirProductoStandard(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {
    	if(peso>sist.getPesoMaximo()) {
    		return false; 
    	}
    	if(largo>sist.getAlto()) {
    		return false; 
    	}
    	if(ancho>sist.getAncho()) {
    		return false; 
    	}
    	if(alto>sist.getLargo()) {
    		return false; 
    	}
    	Producto prod=new Producto(idProducto, peso, precio, direccion, descripcion, unidades, largo, ancho, alto);
    	p.getUnidades().add(prod);
    	return true;
    	
    }
    public boolean añadirProductoAlimentacion(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {
    	if(peso>sist.getPesoMaximo()) {
    		return false; 
    	}
    	if(largo>sist.getAlto()) {
    		return false; 
    	}
    	if(ancho>sist.getAncho()) {
    		return false; 
    	}
    	if(alto>sist.getLargo()) {
    		return false; 
    	}
    	Producto p; 
    	return true;
    	
    }
    public boolean añadirProductoFragil(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {
    	if(peso>sist.getPesoMaximo()) {
    		return false; 
    	}
    	if(largo>sist.getAlto()) {
    		return false; 
    	}
    	if(ancho>sist.getAncho()) {
    		return false; 
    	}
    	if(alto>sist.getLargo()) {
    		return false; 
    	}
    	Producto p; 
    	return true;
    	
    }
    public boolean añadirLote(Pedido p, int idLote, double peso, double precio, String direccion, double tam, int unidades) {
    	for (Producto p: )
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
    			Paquete p=new Paquete(id, u.getDireccion());
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
