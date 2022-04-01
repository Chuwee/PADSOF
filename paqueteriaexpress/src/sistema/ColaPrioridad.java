package sistema;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import GlobalVars.Vars;
import GlobalVars.TipoPaquete;

class Paquete {
	
}

public class ColaPrioridadPaquetes {
	
	private List<LinkedList<Paquete>> Colas;
	
	/* ¿Cuántos niveles de prioridad hay? Debemos usar una cola para cada nivel de prioridad */
	
	public void ColaPrioridad() {
		Colas = new ArrayList<LinkedList<Paquete>>(Vars.getNumPriorities());
		for(int i = 0; i < Vars.getNumPriorities(); i++) {
			Colas.add(new LinkedList<Paquete>());
		}
	}
	
	public void addPaquete(Paquete p, TipoPaquete tp) {
		Colas.get(Vars.getPriority_fromType(tp)).add(p);
	}
	
	public Paquete popPaquete() {
		LinkedList<Paquete> cola;
		for(int i = 0; i < Colas.size(); i++) {
			cola = Colas.get(i);
			if(!cola.isEmpty()) {
				return cola.pop();
			}
		}
		return null;
	}
}
