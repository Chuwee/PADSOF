package Prods;
import GlobalVars.TipoPaquete;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import sistema.SistemaAplicacion;
import usuarios.ErrorAlto;
import usuarios.ErrorAncho;
import usuarios.ErrorLargo;
import usuarios.ErrorPeso;
/**
 * esta es la clase de producto, no es abstracta ya que se corresponde con los estandar
 *
 */
public class Producto extends Unidad {
    private String descripcion;
    private int unidades;
    private double largo;
    private double ancho;
    private double alto;
    private double peso;
    boolean valido;
    SistemaAplicacion sist;
    /**
     * constructor
     * @param sist SistemaAplicacion
     * @param id, int, identificador del producto
     * @param peso, double
     * @param descripcion String
     * @param unidades int
     * @param largo, double
     * @param ancho, double
     * @param alto, double
     */
    public Producto(SistemaAplicacion sist, int id, double peso, String descripcion, int unidades, double largo, double ancho, double alto) {
    	super(id);
    	this.descripcion=descripcion;
    	this.unidades=unidades;
    	this.largo=largo;
    	this.ancho=ancho;
    	this.alto=alto;
        this.peso = peso;
    	this.sist = sist;
    }
    /**
     * este método sirve para validar cada producto y ver que no sobrepasa los límites de las dimensiones
     */
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
    /**
     * calcula el precio teniendo en cuenta el peso, y dependiendo de este devuelve un precio u otro
     */
    @Override
    public double calcularPrecio() {
    	return (this.getPeso()<1?0.4:(this.getPeso()<5?0.6:1));
    }
/**
 * 
 * @return double, largo del producto
 */
    public double getLargo() {
        return this.largo;
    }
/**
 * 
 * @param largo, largo que va a tener nuestro producto ahora
 */
    public void setLargo(double largo) {
        this.largo = largo;
    }
    /**
     * 
     * @return double, ancho del producto
     */
    public double getAncho() {
        return this.ancho;
    }
    /**
     * 
     * @param ancho, ancho que va a tener nuestro producto ahora
     */
    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
    /**
     * 
     * @return double, alto del producto
     */
    public double getAlto() {
        return this.alto;
    }
    /**
     * 
     * @param alto, alto que va a tener nuestro producto ahora
     */
    public void setAlto(double alto) {
        this.alto = alto;
    }
/**
 * 
 * @return descripcion del producto
 */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * 
     * @param desc, descripcion que va a tener nuestro producto ahora
     */
    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }
/**
 * 
 * @return int, numero de unidades del producto
 */
    public int getUnidades() {
        return unidades;
    }
    /**
     * 
     * @param unidades, unidades que va a tener nuestro producto ahora
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
/**
 * 
 * @return array de doubles, con el largo ancho y alto del producto
 */
    public double[] getDimensions() {
        double dimensions[] = {largo, ancho, alto};
        return dimensions;
    }
    /**
     * va a devolver siempre true
     */
    @Override
    public boolean isEstandar() {
    	return true;
    }

/**
 * "return el peso del producto
 */

    public double getPeso() {
        return this.peso;
    }
/**
 * va a devolver un tipo de paquete estándar
 */
    @Override
	public TipoPaquete getTipoPaquete() {
		return TipoPaquete.ESTANDAR;
	}


}