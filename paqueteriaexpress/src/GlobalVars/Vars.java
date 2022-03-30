package GlobalVars;

import java.util.TreeMap;

public class Vars {
	private static TreeMap<TipoPaquete, Integer> maxPeso_Paquetes = new TreeMap<TipoPaquete, Integer>() {
		{
			put(TipoPaquete.ALIMENTACION, 30);
			put(TipoPaquete.ESTANDAR, 30);
			put(TipoPaquete.FRAGIL, 20);
			put(TipoPaquete.TOTAL, 60);
		}
	};
	
	private static TreeMap<DimensionParam, Double> maxDims_Paquetes = new TreeMap<DimensionParam, Double>();
	
	public static int getMaxPeso_from_type(TipoPaquete arg) {
		return maxPeso_Paquetes.get(arg);
	}
	
	public static int getSuplementoAsegurado() {
		return 5;																							
	}
	
	public double getMaxDim_fromParam(DimensionParam param) {
		return maxDims_Paquetes.get(param);
	}
	
	public void setMaxDim_fromParam(DimensionParam param, Double val) {
		maxDims_Paquetes.put(param, val);
	}
	
}
