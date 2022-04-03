package Prods;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import sistema.SistemaAplicacion;
import usuarios.ErrorAlto;
import usuarios.ErrorAncho;
import usuarios.ErrorLargo;
import usuarios.ErrorPeso;

public class Producto extends Unidad {
    private String descripcion;
    private int unidades;
    private double largo;
    private double ancho;
    private double alto;
    private double peso;
    boolean valido;
    SistemaAplicacion sist;
    
    public Producto(SistemaAplicacion sist, int id, double peso, String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(id, direccion);
    	this.descripcion=descripcion;
    	this.unidades=unidades;
    	this.largo=largo;
    	this.ancho=ancho;
    	this.alto=alto;
        this.peso = peso;
    	this.sist = sist;
    }
    
    public void validar() throws ErrorAlto, ErrorAncho, ErrorLargo, ErrorPeso {
    	if (this.largo > sist.getLargo()) {
    		throw new ErrorLargo();
    	}
    	
    	if(this.ancho > sist.getAncho()) {
    		throw new ErrorAncho();
    	}
    	
    	if(this.largo > sist.getAlto()) {
    		throw new ErrorAlto();
    	}
    	
    	
    	// TODO: pesos?
    	
    }
    
    @Override
    public double calcularPrecio() {
    	return this.getPeso()<1?0.4:(this.getPeso()<5?0.6:5);
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
    
    @Override
    public boolean isEstandar() {
    	return true;
    }


    @Override
    public double getPeso() {
        return this.peso;
    }


}