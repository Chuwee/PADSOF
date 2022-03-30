package usuarios;
import java.util.ArrayList;

import Pedido.Pedido;
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
        Pedido p=new Pedido(idPedido, urgente, direccion, codigoPostal, descripcion, EnConstruccion, fecha);
        cliente.getPedidosUser().add(p);
        return p; 

    }
    public boolean añadirUnidad(Pedido p) {
    	p.
    	
    	
    }
    public boolean validarPedido(SistemaAplicacion sistema,Pedido p){
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
