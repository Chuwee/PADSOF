package GlobalVars;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
