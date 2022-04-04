package Prods;
import GlobalVars.TipoPaquete;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import sistema.SistemaAplicacion;
/**
 * 
 * esta clase hereda de la clase abstracta producto de alimentacion
 *
 */
public class Refrigerado extends ProductoAlimentacion {
    private boolean congelado;


/**
 * constructor
 * @param sist SistemaAplicacion
 * @param id int
 * @param peso double
 * @param precio double
 * @param descripcion String
 * @param unidades int
 * @param largo double
 * @param ancho double
 * @param alto double
 * @param congelado boolean
 */
public Refrigerado(SistemaAplicacion sist, int id, double peso, String descripcion, int unidades, double largo, double ancho, double alto, boolean congelado) {
		super(sist, id, peso, descripcion,unidades, largo, ancho, alto);
    	this.congelado=congelado;
    }
    /**
     * true en caso de ser congelado
     */
    @Override
	public boolean isCongelado() {
		return congelado;
	}
/**
 * 
 * @param congelado, cambia el valor de congelado al que le pasamos por parametro
 */
	public void setCongelado(boolean congelado) {
		this.congelado = congelado;
	}
	/**
	 * true en caso de que no sea congelado
	 */
	@Override
	public boolean isRefrigerado() {
    	if(congelado==true) {
    		return false;
    	}
		return true;
    }
/**
 * en caso de ser congeladol, devuelve tipo paquete congelado, refrigerado en caso contrario
 */
	@Override
	public TipoPaquete getTipoPaquete() {
		return congelado?TipoPaquete.CONGELADO:TipoPaquete.REFRIGERADO;
	}
}

