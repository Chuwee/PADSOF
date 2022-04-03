package GlobalVars;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import Transporte.TipoCamion;

public class Vars {
	private static TreeMap<TipoPaquete, Integer> maxPesoPaquetes = new TreeMap<TipoPaquete, Integer>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(TipoPaquete.ALIMENTACION, 30);
			put(TipoPaquete.ESTANDAR, 30);
			put(TipoPaquete.FRAGIL, 20);
			put(TipoPaquete.TOTAL, 60);
		}
	};
	
	
	private static List<TipoPaquete> prioridadPaquetes = new ArrayList<TipoPaquete>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(0, TipoPaquete.REFRIGERADO);
			add(1, TipoPaquete.CONGELADO);
			add(2, TipoPaquete.LIQUIDO);
			add(3, TipoPaquete.FRAGIL);
			add(4, TipoPaquete.ESTANDAR);
			add(5, TipoPaquete.DIMESPECIALES);
		}
	};
	
	private static List<ColasPrioridad> colasPrioridad = new ArrayList<ColasPrioridad>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(0, ColasPrioridad.URGENTES);
			add(1, ColasPrioridad.NOENTREGADOSFALTACAMIONES);
			add(2, ColasPrioridad.DEVUELTOS);
			add(3, ColasPrioridad.RESTO);
		}
	};

	public static TipoCamion TipoPaquete_to_TipoCamion(TipoPaquete tp) {
		TipoCamion tipoCamion = null;
		switch(tp) {
			case FRAGIL:
			case ESTANDAR:
				tipoCamion = TipoCamion.Estandar;
				break;
			case REFRIGERADO:
				tipoCamion = TipoCamion.RefrigeradoRefrigerado;
				break;
			case CONGELADO:
				tipoCamion = TipoCamion.RefrigeradoCongelado;
				break;
			case LIQUIDO:
				tipoCamion = TipoCamion.RefrigeradoLiquido;
				break;
			default:
				tipoCamion = TipoCamion.DimEspeciales;
				break;
		}
		return tipoCamion;
	}
	
	public static int getNumColasPrioridad() {
		return colasPrioridad.size();
	}
	
	public static int getMaxPeso_from_type(TipoPaquete arg) {
		return maxPesoPaquetes.get(arg);
	}
	
	public static int getSuplementoAsegurado() {
		return 5;																							
	}
	
	public static int getNumPriorities() {
		return prioridadPaquetes.size();
	}
	
	public static int getPriority_fromType(TipoPaquete tp) {
		return prioridadPaquetes.indexOf(tp);
	}
	
	public static int getColaPrioridad(ColasPrioridad c) {
		return colasPrioridad.indexOf(c);
	}
	
}
