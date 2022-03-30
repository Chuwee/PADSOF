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

    public boolean añadirProductoStandard(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto)
    				throws ErrorPeso, ErrorAlto, ErrorAncho, ErrorLargo{
    	if(peso>sist.getPesoMaximo()) {
    		throw new ErrorPeso(); 
    	}
    	if(largo>sist.getAlto()) {
    		throw new ErrorLargo();
    	}
    	if(ancho>sist.getAncho()) {
    		throw new ErrorAncho(); 
    	}
    	if(alto>sist.getLargo()) {
    		throw new ErrorLargo(); 
    	}
    	Producto prod=new Producto(idProducto, peso, precio, direccion, descripcion, unidades, largo, ancho, alto);
    	p.getUnidades().add(prod);
    	return true;
    	
    }
    public boolean añadirProductoAlimentacion(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) 
    
    		throws ErrorPeso, ErrorAlto, ErrorAncho, ErrorLargo		{
    	if(peso>sist.getPesoMaximo()) {
    		throw new ErrorPeso();  
    	}
    	if(largo>sist.getAlto()) {
    		throw new ErrorAlto(); 
    	}
    	if(ancho>sist.getAncho()) {
    		throw new ErrorAncho();  
    	}
    	if(alto>sist.getLargo()) {
    		throw new ErrorLargo();  
    	}
    	Producto prod= new ProductoAlimentacion(idProducto, peso, precio, direccion, descripcion, unidades, largo, ancho, alto); 
    	p.getUnidades().add(prod);
    	return true;
    	
    }
    public boolean añadirProductoFragil(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto, boolean asegurado)
    		throws ErrorPeso, ErrorAlto, ErrorAncho, ErrorLargo		{
    	if(peso>sist.getPesoMaximo()) {
    		throw new ErrorPeso();  
    	}
    	if(largo>sist.getAlto()) {
    		throw new ErrorAlto(); 
    	}
    	if(ancho>sist.getAncho()) {
    		throw new ErrorAncho();  
    	}
    	if(alto>sist.getLargo()) {
    		throw new ErrorLargo();  
    	}
    	Producto prod=new ProductoFragil(asegurado, idProducto, peso, precio, direccion, descripcion, unidades, largo, ancho, alto); 
    	p.getUnidades().add(prod);
    	return true;
    	
    }
    public boolean añadirLote(Pedido p, int idLote, double peso, double precio, String direccion, double tam, int unidades) {
    	return true;
    }
    public boolean validarPedido(SistemaAplicacion sistema,Pedido p){
        return true; 
    }
    public void empaquetarPedido(SistemaAplicacion sist, Pedido pedido){
    	double maxPeso = sist.getPesoMaximo();
    	int id;
    	int num_empaquetado=0;
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
        			p.getUnidades().add(prod);
        			p.setPeso(u.getPeso());
        			u.setEmpaquetado(true);
        			sist.getPaquetes().add(p);
        			id++;
        			num_empaquetado++;
    			}
    		}else if(u instanceof Lote) {
    			Lote lote=(Lote)u;
    			for(Producto prod : lote.getProductos()) {
    				if(prod instanceof ProductoFragil) {
    					Paquete p=new Paquete(id, u.getDireccion());
            			p.getUnidades().add(lote);
            			p.setPeso(u.getPeso());
            			u.setEmpaquetado(true);
            			sist.getPaquetes().add(p);
            			id++;
            			num_empaquetado++;
            			break;
    				}
    			}
    		}
    	}
    	while(num_empaquetado!=pedido.getUnidades().size()) {
    		Paquete p_estandar = new Paquete(id, pedido.getDireccion());
    		id++;
    		Paquete p_congelado = new Paquete(id, pedido.getDireccion());
    		id++;
    		Paquete p_refrigerado = new Paquete(id, pedido.getDireccion());
    		id++;
    		Paquete p_dim_esp = new Paquete(id, pedido.getDireccion());
    		id++;
    		Paquete p_alimentacion = new Paquete(id, pedido.getDireccion());
    		id++;
    		for(Unidad u : pedido.getUnidades()) {
    			if(u instanceof Refrigerado) {
    				Refrigerado r= (Refrigerado)u;
    				if(r.isCongelado()) {
    					if(p_congelado.getPeso()+r.getPeso()<=maxPeso) {
    						p_congelado.getUnidades().add(r);
    						p_congelado.setPeso(r.getPeso()+p_congelado.getPeso());
    						r.setEmpaquetado(true);
    						num_empaquetado++;
    				}else {
    					if(p_refrigerado.getPeso()+r.getPeso()<=maxPeso) {
    						p_refrigerado.getUnidades().add(r);
    						p_refrigerado.setPeso(r.getPeso()+p_congelado.getPeso());
    						r.setEmpaquetado(true);
    						num_empaquetado++;
    					}
    				}
    			}
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
