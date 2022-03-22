import java.util.ArrayList;

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
    public Pedido nuevoPedido(int idPedido, boolean urgente, Date fecha, String direccion, int codigoPostal, String descripcion, Cliente Cliente){
        

    }
    public void empaquetarPedido(){

    }
    public boolean validarPedido(Pedido p){

    }
    public void modificarAlto(Parametros p, double alto){
        p.setAlto(alto);
    }
    public void modificarAncho(Parametros p, double ancho){
        p.setAncho(ancho);
    }
    public void modificarLargo(Parametros p, double largo){
        p.setLargo(largo);
    }
    public void modificarPeso(Parametros p, double peso){
        p.setPesoMaximo(peso);
    }
    public void modificarIntentos(Parametros p, int intentos){
        p.setMaxIntentos(intentos);
    }
    public void modificarDirecciones(Parametros p, int direcciones){
        p.setMaxDirecciones(direcciones);
    }
    public void planificarEnvio(){

    }
    
}
