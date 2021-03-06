package sistema;
/**
 * @author Ignacio Ildefonso del Miguel Ruano
 * 
 */
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
	/**
	 * 
	 * @param p, paquete que vamos a anadir a la cola de prioridad
	 */
	public void addPaquete(Paquete p) {
		Colas.get(Vars.getPriority_fromType(p.getTp())).add(p);
	}
	/**
	 * este metodo elimina un paquete de la cola de prioridad
	 */
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

	public Paquete peekPaquete() {
		PriorityQueue<Paquete> cola;
		Paquete p;
		for(int i = 0; i < Colas.size(); i++) {
			cola = Colas.get(i);
			p = cola.peek();
			if(p != null) {
				return p;
			}
		}
		return null;
	}

	public int size() {
		int size=0;
		for(int i = 0; i < Vars.getNumPriorities(); i++) {
			size+=Colas.get(i).size(); // Testear esto 
		}
		return size;
	}
}
