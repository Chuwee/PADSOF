package Prods;

public abstract class Unidad {

    private int identificador;
    private double peso;
    private double precio;
    private String direccion;
    private boolean empaquetado;
    
    public Unidad(int id, double peso, double precio, String direccion) {
    	this.identificador=id;
    	this.peso=peso;
    	this.precio=precio;
    	this.direccion=direccion;
    	this.empaquetado=false;
    }

    public int getIdentificador() {
        return this.identificador;
    }
    public boolean getEmpaquetado() {
    	return this.empaquetado;
    }
    public void setEmpaquetado(boolean b) {
    	this.empaquetado=b;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getDireccion() {
    	return this.direccion;
    }
    public void setDireccion(String direccion) {
    	this.direccion=direccion;
    }
    
}
