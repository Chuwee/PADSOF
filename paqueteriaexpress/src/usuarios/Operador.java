package usuarios;
import java.util.ArrayList;

import GlobalVars.TipoPaquete;
import GlobalVars.Vars;
import Paquete.Paquete;
import Pedido.*;
import Prods.*;
import sistema.SistemaAplicacion;

import java.util.*;
import java.io.*;

abstract class CodigoPostal {
	public static int getSize() {
		return 16;
	}
}

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
    	if(peso>Vars.getMaxPeso_from_type(TipoPaquete.ESTANDAR)) {
    		throw new ErrorPeso(); 
    	}
    	if(alto>sist.getAlto()) {
    		throw new ErrorAlto();
    	}
    	if(ancho>sist.getAncho()) {
    		throw new ErrorAncho(); 
    	}
    	if(largo>sist.getLargo()) {
    		throw new ErrorLargo(); 
    	}
    	Producto prod=new Producto(idProducto, peso, precio, direccion, descripcion, unidades, largo, ancho, alto);
    	p.getUnidades().add(prod);
    	return true;
    	
    }
    public boolean añadirProductoAlimentacion(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) 
    
    		throws ErrorPeso, ErrorAlto, ErrorAncho, ErrorLargo		{
		if(peso>Vars.getMaxPeso_from_type(TipoPaquete.ALIMENTACION)) {
    		throw new ErrorPeso();  
    	}
    	if(alto>sist.getAlto()) {
    		throw new ErrorAlto(); 
    	}
    	if(ancho>sist.getAncho()) {
    		throw new ErrorAncho();  
    	}
    	if(largo>sist.getLargo()) {
    		throw new ErrorLargo();  
    	}
    	Producto prod= new ProductoAlimentacion(idProducto, peso, precio, direccion, descripcion, unidades, largo, ancho, alto); 
    	p.getUnidades().add(prod);
    	return true;
    	
    }
    public boolean añadirProductoFragil(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto, boolean asegurado)
    		throws ErrorPeso, ErrorAlto, ErrorAncho, ErrorLargo		{
    	if(peso>Vars.getMaxPeso_from_type(TipoPaquete.FRAGIL)) {
    		throw new ErrorPeso();  
    	}
    	if(alto>sist.getAlto()) {
    		throw new ErrorAlto(); 
    	}
    	if(ancho>sist.getAncho()) {
    		throw new ErrorAncho();  
    	}
    	if(largo>sist.getLargo()) {
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
    public boolean comprobarCodigoPostal(Pedido p) throws IOException{
    	boolean contiene=false; 
    	int codigo;
    	codigo=p.getCodigoPostal();
    	try {
            File fil= new File("codigos.txt");
    		BufferedReader buffer=new BufferedReader(new FileReader(fil));
        	String linea; 
    		while((linea=buffer.readLine())!=null) {
        		int linea1=Integer.parseInt(linea);
        		if(linea1==codigo) {
        			contiene=true;
        			return contiene; 
        		}
        		else {
        			contiene=false;
        		}
        	}
    		buffer.close();
    	}catch(FileNotFoundException e) {
    		System.out.println("El fichero no se ha encontrado\n");
    	}catch(NullPointerException e) {
    		System.out.println("No se ha seleccionado ningún archivo\n");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return contiene;
    	
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
    			
    				if(lote.getTipopaquete().equals(TipoPaquete.ESTANDAR)) {
    					Paquete p=new Paquete(id, u.getDireccion());
            			p.getUnidades().add(lote);
            			p.setPeso(u.getPeso());
            			u.setEmpaquetado(true);
            			sist.getPaquetes().add(p);
            			id++;
            			num_empaquetado++;
            			
   
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
    			if(u.getEmpaquetado()==false) {
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
    			}else if(u instanceof Producto) {
    				if(p_estandar.getPeso()+u.getPeso()<=maxPeso) {
						p_estandar.getUnidades().add(u);
						p_estandar.setPeso(u.getPeso()+p_estandar.getPeso());
						u.setEmpaquetado(true);
						num_empaquetado++;
    				}
    			}else if(u instanceof DimensionesEspeciales) {
    				if(p_dim_esp.getPeso()+u.getPeso()<=maxPeso) {
    					p_dim_esp.getUnidades().add(u);
    					p_dim_esp.setPeso(u.getPeso()+p_dim_esp.getPeso());
    					u.setEmpaquetado(true);
    					num_empaquetado++;
    				}
    			}else if(u instanceof ProductoAlimentacion) {
    				if(p_alimentacion.getPeso()+u.getPeso()<=maxPeso) {
    					p_alimentacion.getUnidades().add(u);
    					p_alimentacion.setPeso(u.getPeso()+p_dim_esp.getPeso());
    					u.setEmpaquetado(true);
    					num_empaquetado++;
    				}
    			}else if(u instanceof Lote) {
    				Lote lote=(Lote)u;
    				if(lote.getTipopaquete().equals(TipoPaquete.ESTANDAR)) {
    					if(p_estandar.getPeso()+u.getPeso()<=maxPeso) {
    						p_estandar.getUnidades().add(u);
    						p_estandar.setPeso(u.getPeso()+p_estandar.getPeso());
    						u.setEmpaquetado(true);
    						num_empaquetado++;
        				}
    					
    					
    				}else if(lote.getTipopaquete().equals(TipoPaquete.CONGELADO)) {
    					if(p_congelado.getPeso()+u.getPeso()<=maxPeso) {
    						p_congelado.getUnidades().add(u);
    						p_congelado.setPeso(u.getPeso()+p_congelado.getPeso());
    						u.setEmpaquetado(true);
    						num_empaquetado++;
        				}
    					
    					
    				}else if(lote.getTipopaquete().equals(TipoPaquete.REFRIGERADO)) {
    					if(p_refrigerado.getPeso()+u.getPeso()<=maxPeso) {
    						p_refrigerado.getUnidades().add(u);
    						p_refrigerado.setPeso(u.getPeso()+p_refrigerado.getPeso());
    						u.setEmpaquetado(true);
    						num_empaquetado++;
        				}
    					
    					
    				}else if(lote.getTipopaquete().equals(TipoPaquete.ALIMENTACION)) {
    					if(p_alimentacion.getPeso()+u.getPeso()<=maxPeso) {
    						p_alimentacion.getUnidades().add(u);
    						p_alimentacion.setPeso(u.getPeso()+p_alimentacion.getPeso());
    						u.setEmpaquetado(true);
    						num_empaquetado++;
        				}
    					
    				}
    				
    			}
    			}
    		}
    		
    		
    	}
    		sist.getPaquetes().add(p_alimentacion);
    		sist.getPaquetes().add(p_estandar);
    		sist.getPaquetes().add(p_congelado);
    		sist.getPaquetes().add(p_refrigerado);
    		sist.getPaquetes().add(p_dim_esp);
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
