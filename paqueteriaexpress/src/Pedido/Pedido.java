package Pedido;
/**
 * @author Ignacio Ildefonso del Miguel Ruano
 * 
 */
import java.sql.Date;
import usuarios.Cliente;
import usuarios.ErrorAlto;
import usuarios.ErrorAncho;
import usuarios.ErrorLargo;
import usuarios.ErrorPeso;

import java.util.ArrayList;
import Prods.Unidad;

/**
 * 
 * esta es la clase de pedido, con todos sus atributos privados y sus diferentes métodos
 *
 */
public class Pedido {
    private int idPedido;
    private boolean urgente;
    private double precio;
    private Date fecha;
    private String direccion;
    private int codigoPostal;
    private Cliente cliente;
    private String descripcion;
    private EstadoPedido estado;
    private ArrayList<Unidad> unidades;
/**
 * Constructor del pedido
 * @param idPedido int, identificador del pedido
 * @param urgente boolean, true si el pedido es urgente
 * @param direccion String, dirección del pedido
 * @param codigoPostal int, código postal al que va a ir el pedido
 * @param descripcion String, descripcion del pedido
 * @param estado EstadoPedido
 * @param fecha Date, fecha del pedido
 */
    public Pedido(int idPedido, boolean urgente, String direccion, 
    int codigoPostal, String descripcion,
    EstadoPedido estado, Date fecha) {
        this.idPedido = idPedido;
        this.urgente = urgente;
        this.precio = 0;
        this.fecha = null;
        this.codigoPostal = codigoPostal;
        this.descripcion = descripcion;
        this.estado = estado;
        unidades = new ArrayList<Unidad>();
    }
    /**
     * @author Ignacio Ildefonso de Miguel Ruano
     * Esta función va a cambiar el estado del pedido a validado en caso de que todas las unidades que forman parte del pedido
     * cumplan los límites de dimensiones, en caso contrario entraría en alguna excepcion
     */
    public void validar() {
    	this.estado = EstadoPedido.Construido;
    	for(Unidad u : unidades) {
    		try {
    			u.validar();
    		} catch(ErrorAlto e) {
    			System.out.println("El alto de la unidad con ID: " + u.getIdentificador() + " excede los máximos permitidos.\n");
    			if(u.isDimEsp()) {
    				System.out.println("La unidad con ID: " + u.getIdentificador() + " sera de dimensiones especiales.\n");
    				this.estado = EstadoPedido.Validado;
    			}
    			return;
    		} catch (ErrorAncho e) {
    			System.out.println("El ancho de la unidad con ID: " + u.getIdentificador() + " excede los máximos permitidos.\n");
    			if(u.isDimEsp()) {
    				System.out.println("La unidad con ID: " + u.getIdentificador() + " sera de dimensiones especiales.\n");
    				this.estado = EstadoPedido.Validado;
    			}
    			return;
			} catch (ErrorLargo e) {
				System.out.println("El largo de la unidad con ID: " + u.getIdentificador() + " excede los máximos permitidos.\n");
				if(u.isDimEsp()) {
					System.out.println("La unidad con ID: " + u.getIdentificador() + " sera de dimensiones especiales.\n");
    				this.estado = EstadoPedido.Validado;
    			}
				return;
			} catch (ErrorPeso e) {
				System.out.println("El peso de la unidad con ID: " + u.getIdentificador() + " excede los máximos permitidos.\n");
				return;
			}
    	}
    	this.estado = EstadoPedido.Validado;
    }

    /**
     * @return gets the current Unidades from Paquete
     */
    public ArrayList<Unidad> getUnidades() {
        return this.unidades;
    }

    /**
     * @author Ignacio Ildefonso de Miguel Ruano
     * @return gets number of units
     */
    public int getNumProductos() {
        int n = 0;
        for(Unidad u: unidades) {
            n+=u.getNumUnidades();
        }
        return n;
    }

    /**
     * Añade una Unidad a un Pedido
     * @param newUnit
     */
    public void anadirUnidad(Unidad newUnit) {
    	newUnit.estaEnPedido(this);
        this.unidades.add(newUnit);
    }
    
    /**
     * @author Ignacio Ildefonso de Miguel Ruano
     * @return precio double, el precio del pedido una vez que ya hemos aplicado los extras. 
     */
    public double calcularPrecio() {
    	double precio = 0;
    	for(Unidad u: unidades) {
    		precio+=u.calcularPrecio();
    	}
    	if(this.urgente) {
    		precio=precio+5; 
    	}
    	return precio;
    }
/**
 *
 * @param Unit, unidad que queremos eliminar del pedido
 */
    public void quitarUnidad(Unidad Unit) {
        this.unidades.remove(Unit);
    }
/**
 * 
 * @return int, el identificador del pedido
 */
    public int getIdPedido() {
        return this.idPedido;
    }
/**
 * 
 * @param idPedido, esta función cambia el id del pedido, y le pone el que le pasamos por parámetro
 */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
/**
 * 
 * @return boolean, true si el pedido es urgente, false en caso contrario
 */
    public boolean isUrgente() {
        return this.urgente;
    }
/**
 * 
 * @param urgente, boolean al que queremos cambiar nuestro pedido
 */
    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }
/**
 * 
 * @return double, el precio del pedido
 */
    public double getPrecio() {
        return this.precio;
    }
/**
 * esta función cambia el precio del pedido y le asigna el que le estamos pasando por parámetro
 * @param precio, el precio que queremos poner al pedido
 */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
/**
 * 
 * @return Date, la fecha del pedido
 */
    public Date getFecha() {
        return this.fecha;
    }
/**
 * Esta función cambia la fecha del pedido y le pone la que le pasamos por parámetro al método
 * @param fecha, Date, la fecha que va a tener ahora nuestro pedido
 */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
/**
 * 
 * @return String, la dirección del pedido
 */
    public String getDireccion() {
        return this.direccion;
    }
/**
 * Esta función cambia la dirección del pedido y le asigna la que le pasamos como parámetro
 * @param direccion, direccion que queremos que tenga ahora nuestro pedido
 */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
/**
 * 
 * @return int, código postal del pedido
 */
    public int getCodigoPostal() {
        return this.codigoPostal;
    }
/**
 *  Esta función cambia el código postal del pedido y le asigna la que le pasamos como parámetro
 * @param codigoPostal, int, código que va a tener ahora nuestro pedido
 */
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /* public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    } */
/**
 * 
 * @return String, la descripcion del pedido
 */
    public String getDescripcion() {
        return this.descripcion;
    }
/**
 *  Esta función cambia la descripcion del pedido y le asigna la que le pasamos como parámetro
 * @param descripcion, descripcion que va a tener ahora nuestro pedido
 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
/**
 * 
 * @return EstadoPedido, el estado en el que se encuentra el pedido
 */
    public EstadoPedido getEstado() {
        return this.estado;
    }
/**
 *  Esta función cambia el estadp del pedido y le asigna el que le pasamos como parámetro
 * @param estado, estado en el que se va a encontrar ahora nuestro pedido
 */
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
/**
 * 
 * @return CLiente, el cliente que ha hecho el pedido
 */
	public Cliente getCliente() {
		return cliente;
	}
/**
 *  Esta función cambia el cliente del pedido y le asigna el que le pasamos como parámetro
 * @param cliente, cliente que vamos a poner ahora que ha hecho el pedido
 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
