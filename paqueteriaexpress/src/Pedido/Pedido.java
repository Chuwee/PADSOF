package Pedido;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
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
    
    public void validar() {
    	this.estado = EstadoPedido.Construido;
    	for(Unidad u : unidades) {
    		try {
    			u.validar();
    		} catch(ErrorAlto e) {
    			System.out.println("El alto de la unidad con ID: " + u.getIdentificador() + " excede los máximos permitidos.\n");
    			return;
    		} catch (ErrorAncho e) {
    			System.out.println("El ancho de la unidad con ID: " + u.getIdentificador() + " excede los máximos permitidos.\n");
    			return;
			} catch (ErrorLargo e) {
				System.out.println("El largo de la unidad con ID: " + u.getIdentificador() + " excede los máximos permitidos.\n");
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
     * Añade una Unidad a un Paquete
     * @param newUnit
     */
    public void anadirUnidad(Unidad newUnit) {
    	newUnit.estaEnPedido(this);
        this.unidades.add(newUnit);
    }
    
    public double calcularPrecio() {
    	double precio = 0;
    	for(Unidad u: unidades) {
    		precio+=u.calcularPrecio();
    	}
    	return precio;
    }

    public void quitarUnidad(Unidad Unit) {
        this.unidades.remove(Unit);
    }

    public int getIdPedido() {
        return this.idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public boolean isUrgente() {
        return this.urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /* public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    } */

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoPedido getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
