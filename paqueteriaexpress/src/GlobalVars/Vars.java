package GlobalVars;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Vars {
	private static TreeMap<TipoPaquete, Integer> maxPesoPaquetes = new TreeMap<TipoPaquete, Integer>() {
		{
			put(TipoPaquete.ALIMENTACION, 30);
			put(TipoPaquete.ESTANDAR, 30);
			put(TipoPaquete.FRAGIL, 20);
			put(TipoPaquete.TOTAL, 60);
		}
	};
	
	private static TreeMap<DimensionParam, Double> maxDimsPaquetes = new TreeMap<DimensionParam, Double>();
	
	private static List<TipoPaquete> prioridadPaquetes = new ArrayList<TipoPaquete>() {
		{
			add(0, TipoPaquete.REFRIGERADO);
			add(1, TipoPaquete.CONGELADO);
			add(2, TipoPaquete.LIQUIDO);
			add(3, TipoPaquete.FRAGIL);
			add(4, TipoPaquete.ESTANDAR);
			add(5, TipoPaquete.DIMESPECIALES);
		}
	};
	
	public static int getMaxPeso_from_type(TipoPaquete arg) {
		return maxPesoPaquetes.get(arg);
	}
	
	public static int getSuplementoAsegurado() {
		return 5;																							
	}
	
	public double getMaxDim_fromParam(DimensionParam param) {
		return maxDimsPaquetes.get(param);
	}
	
	public void setMaxDim_fromParam(DimensionParam param, Double val) {
		maxDimsPaquetes.put(param, val);
	}
	
	public static int getNumPriorities() {
		return prioridadPaquetes.size();
	}
	
	public static int getPriority_fromType(TipoPaquete tp) {
		return prioridadPaquetes.indexOf(tp);
	}
	
}
