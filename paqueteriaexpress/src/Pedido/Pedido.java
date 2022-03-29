package Pedido;

import java.util.Date;
import java.util.ArrayList;
import Prods.Unidad;

enum EstadoPedido {
    EnConstruccion,
    EnReparto,
    EnRepartoFallido1,
    Entregado,
    Construido,
    Validado,
    PendienteEmpaquetar,
    NoEntregado
}

public class Pedido {
    private int idPedido;
    private boolean urgente;
    private double precio;
    private Date fecha;
    private String direccion;
    private int codigoPostal;
    /* private Cliente cliente; */
    private String descripcion;
    private EstadoPedido estado;
    private ArrayList<Unidad> unidades;

    public Pedido(int idPedido, boolean urgente, String direccion, 
    int codigoPostal, String descripcion,
    EstadoPedido estado) {
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
        this.unidades.add(newUnit);
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
    public boolean validarPedido(){
        return true; 

    }
    private double calcularPrecio(){
        return 0; 
    }
    private double calcularPrecioUrgente(){
        return 0; 
    }

}
