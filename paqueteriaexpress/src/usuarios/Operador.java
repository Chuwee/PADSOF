package usuarios;
import java.util.ArrayList;

import GlobalVars.ColasPrioridad;
import GlobalVars.TipoPaquete;
import GlobalVars.Vars;
import Paquete.EstadoPaquete;
import Paquete.Paquete;
import Pedido.*;
import Prods.*;
import sistema.SistemaAplicacion;

import java.util.*;
import java.io.*;
import java.sql.Date;


/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class Operador extends UsuarioIdentificado{
    private int pedidosDistribuidos;
    SistemaAplicacion sist;

    public Operador(SistemaAplicacion sist, int pedidosDistribuidos, String NIF, String Nombre, String Usuario, String Contrasena, String email){
        super(NIF, Nombre, Usuario, Contrasena, email);
        this.pedidosDistribuidos = pedidosDistribuidos;
        this.sist = sist;
    }

    public int getPedidosDistribuidos() {
        return this.pedidosDistribuidos;
    }

    public void setPedidosDistribuidos(int pedidosDistribuidos) {
        this.pedidosDistribuidos = pedidosDistribuidos;
    }
    public Pedido nuevoPedido(int idPedido, boolean urgente, String direccion, int codigoPostal, String descripcion, Cliente cliente, Date fecha){
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
    	Producto prod=new Producto(sist,idProducto, peso, direccion, descripcion, unidades, largo, ancho, alto);
    	p.getUnidades().add(prod);
    	return true;
    	
    }
    public boolean añadirProductoAlimentacionLiquido(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) 
    
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
    	Producto prod= new Liquido(sist,idProducto, peso, precio, direccion, descripcion, unidades, largo, ancho, alto); 
    	p.getUnidades().add(prod);
    	return true;
    	
    }
public boolean añadirProductoAlimentacionRefrigerado(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto, boolean congelado) 
    
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
    	Producto prod= new Refrigerado(sist,idProducto, peso, precio, direccion, descripcion, unidades, largo, ancho, alto, congelado); 
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
    	Producto prod=new ProductoFragil(sist,asegurado, idProducto, peso, precio,direccion, descripcion, unidades, largo, ancho, alto); 
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
    		if(u.isFragil()) {
    			Producto prod=(Producto)u;
    			for(int i=0;i<prod.getUnidades();i++) {
    				Paquete p=new Paquete(id, u.getDireccion(), TipoPaquete.FRAGIL);
        			this.empaquetar(u, p);
        			this.anadirPaqueteACola(p);
        			id++;
        			num_empaquetado++;
    			}
    		}else if(u.isLote()) {
    			Lote lote=(Lote)u;
    				if(lote.getTipopaquete().equals(TipoPaquete.FRAGIL)) {
    					Paquete p=new Paquete(id, u.getDireccion(), TipoPaquete.FRAGIL);
            			this.empaquetar(u, p);
            			this.anadirPaqueteACola(p);
            			id++;
            			num_empaquetado++;
            			
    			}
    		}
    	}
    	while(num_empaquetado!=pedido.getUnidades().size()) {
    		Paquete p_estandar = new Paquete(id, pedido.getDireccion(), TipoPaquete.ESTANDAR);
    		id++;
    		Paquete p_congelado = new Paquete(id, pedido.getDireccion(), TipoPaquete.CONGELADO);
    		id++;
    		Paquete p_refrigerado = new Paquete(id, pedido.getDireccion(), TipoPaquete.REFRIGERADO);
    		id++;
    		Paquete p_dim_esp = new Paquete(id, pedido.getDireccion(), TipoPaquete.DIMESPECIALES);
    		id++;
    		Paquete p_alimentacion = new Paquete(id, pedido.getDireccion(), TipoPaquete.ALIMENTACION);
    		id++;
    		for(Unidad u : pedido.getUnidades()) {
    			if(u.getEmpaquetado()==false) {
    			if(u.isRefrigerado()) {
    				Refrigerado r= (Refrigerado)u;
    				if(r.isCongelado()) {
						num_empaquetado=this.empaquetar(u, p_congelado, maxPeso, num_empaquetado);
    				}else {
						num_empaquetado=this.empaquetar(u, p_refrigerado, maxPeso, num_empaquetado);	
    				}
    			}else if(u.isEstandar()) {
    					num_empaquetado=this.empaquetar(u, p_estandar, maxPeso, num_empaquetado);	
    			}else if(u.isDimEsp()) {
    					num_empaquetado=this.empaquetar(u, p_dim_esp, maxPeso, num_empaquetado);
    			}else if(u.isAlimentacion()) {
    					num_empaquetado=this.empaquetar(u, p_alimentacion,maxPeso, num_empaquetado);
    			}else if(u.isLote()) {
    				Lote lote=(Lote)u;
    				switch(lote.getTipopaquete()) {
    				case ESTANDAR:
    					num_empaquetado=this.empaquetar(u, p_estandar, maxPeso, num_empaquetado);
    				case CONGELADO:
    					num_empaquetado=this.empaquetar(u, p_congelado, maxPeso, num_empaquetado);
    				case REFRIGERADO:
    					num_empaquetado=this.empaquetar(u, p_refrigerado, maxPeso, num_empaquetado);
    				case ALIMENTACION:
    					num_empaquetado=this.empaquetar(u, p_alimentacion, maxPeso, num_empaquetado);
    				default:
    					num_empaquetado=this.empaquetar(u, p_dim_esp, maxPeso, num_empaquetado);
    				}
    				
    			}
    			}
    		}
    		this.anadirPaqueteACola(p_alimentacion);
    		this.anadirPaqueteACola(p_estandar);
    		this.anadirPaqueteACola(p_congelado);
    		this.anadirPaqueteACola(p_refrigerado);
    		this.anadirPaqueteACola(p_dim_esp);
    	}  	

    }
    
    private int empaquetar(Unidad u, Paquete p, double maxPeso, int num_empaquetado) {
    	if(p.getPeso()+u.getPeso()<=maxPeso) { 
    	p.getUnidades().add(u);
		p.setPeso(u.getPeso()+p.getPeso());
		u.setEmpaquetado(true);	
		num_empaquetado++;
    	}
    	return num_empaquetado;
    }
    
    private void empaquetar(Unidad u, Paquete p) {
    	p.getUnidades().add(u);
		p.setPeso(u.getPeso()+p.getPeso());
		u.setEmpaquetado(true);	
    }
    
    private int asignarCola(Paquete p) {
    	
    	if(p.isUrgente())
    		return Vars.getColaPrioridad(ColasPrioridad.URGENTES);
    	
    	EstadoPaquete ep = p.getEstadoPaquete();
    	
    	switch(ep) {
	    	case NoEntregadoFaltaCamiones:
	    		return Vars.getColaPrioridad(ColasPrioridad.NOENTREGADOSFALTACAMIONES);
	    	case PendienteFallido1:
	    		return Vars.getColaPrioridad(ColasPrioridad.DEVUELTOS);
	    	default:
	    		return Vars.getColaPrioridad(ColasPrioridad.RESTO);
    	}
    }
    
    private void anadirPaqueteACola(Paquete p) {
    	int cola = asignarCola(p);
    	sist.anadirPaqueteACola(p, cola);
    }
    
    public boolean validarPedido(Pedido p){
    	p.validar();
    	if(p.getEstado() == EstadoPedido.Validado)
    		return true;
    	return false;
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
