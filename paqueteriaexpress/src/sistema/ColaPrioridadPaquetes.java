package sistema;

import java.util.List;
import java.util.PriorityQueue;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;

import GlobalVars.Vars;
import Paquete.Paquete;

class ComparePaquetes implements Comparator<Paquete> {
	public int compare(Paquete a, Paquete b) {
		Date aDate = a.getDate();
		Date bDate = b.getDate();
		return aDate.compareTo(bDate);
	}
}

public class ColaPrioridadPaquetes {
	
	private List<PriorityQueue<Paquete>> Colas;
	
	/* ¿Cuántos niveles de prioridad hay? Debemos usar una cola para cada nivel de prioridad */
	
	public ColaPrioridadPaquetes() {
		Colas = new ArrayList<PriorityQueue<Paquete>>(Vars.getNumPriorities());
		for(int i = 0; i < Vars.getNumPriorities(); i++) {
			Colas.add(new PriorityQueue<Paquete>(new ComparePaquetes())); // Testear esto 
		}
	}
	
	public void addPaquete(Paquete p) {
		Colas.get(Vars.getPriority_fromType(p.getTp())).add(p);
	}
	
	public Paquete popPaquete() {
		PriorityQueue<Paquete> cola;
		Paquete p;
		for(int i = 0; i < Colas.size(); i++) {
			cola = Colas.get(i);
			p = cola.poll();
			if(p != null) {
				return p;
			}
		}
		return null;
	}
}
