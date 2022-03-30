package Prods;

public abstract class Unidad {

    private int identificador;
    private double peso;
    private double precio;
    private String direccion;

    public int getIdentificador() {
        return this.identificador;
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
