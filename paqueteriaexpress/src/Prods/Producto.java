package Prods;

public class Producto extends Unidad {
    private String descripcion;
    private int unidades;
    private double largo;
    private double ancho;
    private double alto;
    
    public Producto(int id, double peso, double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(id, peso, precio, direccion);
    	this.descripcion=descripcion;
    	this.unidades=unidades;
    	this.largo=largo;
    	this.ancho=ancho;
    	this.alto=alto;
    }
    
    public double calcularPrecio() {
    	return this.getPrecio()*unidades;
    }

    public double getLargo() {
        return this.largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return this.ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return this.alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double[] getDimensions() {
        double dimensions[] = {largo, ancho, alto};
        return dimensions;
    }





}