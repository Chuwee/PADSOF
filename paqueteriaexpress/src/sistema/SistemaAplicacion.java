package sistema;
import java.util.*;


import GlobalVars.Vars;
import Paquete.*;
import Pedido.Pedido;
import Transporte.Camion;
import usuarios.*;

public class SistemaAplicacion {
    private List<Camion> camiones;
    private List<UsuarioIdentificado> usuarios;
    private List<Pedido> pedidos;
    private List<Paquete> paquetes;
	private double pesoMaximo;
	private double largo;
    private double ancho;
    private double alto;
    private int maxDirecciones;
    private int maxIntentos;
    private int id_paquetes;
    ArrayList<ColaPrioridadPaquetes> colasPrioridad;
    
    public SistemaAplicacion() {
    	colasPrioridad = new ArrayList<ColaPrioridadPaquetes>();
    	for(int i = 0; i < Vars.getNumColasPrioridad(); i++) {
    		colasPrioridad.add(new ColaPrioridadPaquetes());
    	}
    }
    
    public void anadirPaqueteACola(Paquete p, int index) {
    	colasPrioridad.get(index).addPaquete(p);
    }
    
    public void asignarCamionRepartidor() {
    	List<Repartidor> repartidores=new ArrayList<Repartidor>();
    	List<Camion> camionesValidos=new ArrayList<Camion>();
    	Iterator<Repartidor> itRep=repartidores.iterator();
    	Iterator<Camion> itCam=camionesValidos.iterator();
    	for(UsuarioIdentificado us:usuarios) {
    		if(us.isRepartidor()) {
    			Repartidor r=(Repartidor)us;
    			repartidores.add(r);
    		}
    	}
    	for(Camion c: camiones) {
    		if(!c.getPaquetes().isEmpty()) {
    			camionesValidos.add(c);
    		}
    	}
    	while(itRep.hasNext()&&itCam.hasNext()) {
    		itRep.next().setCamion(itCam.next());
    	}
    	if(itCam.hasNext()) {
    		while(itCam.hasNext()) {
    			for(Paquete p : itCam.next().getPaquetes()) {
    				p.setEstadoPaquete(EstadoPaquete.NoEntregadoFaltaCamiones);
    			}
    		}
    	}
    }
    
    public List<Paquete> getPaquetes(){
    	return this.paquetes;
    }
    public void setPaquetes(List<Paquete> paquetes) {
    	this.paquetes=paquetes;
    }
    public List<Camion> getCamiones() {
        return this.camiones;
    }
    public void setCamiones(List<Camion> camiones) {
        this.camiones = camiones;
    }
    public List<UsuarioIdentificado> getUsuarios() {
        return this.usuarios;
    }
    public void setUsuarios(List<UsuarioIdentificado> usuarios) {
        this.usuarios = usuarios;
    }
    public List<Pedido> getPedidos() {
        return this.pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    public double getPesoMaximo() {
        return this.pesoMaximo;
    }
    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
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

    public int getMaxDirecciones() {
        return this.maxDirecciones;
    }

    public void setMaxDirecciones(int maxDirecciones) {
        this.maxDirecciones = maxDirecciones;
    }

    public int getMaxIntentos() {
        return this.maxIntentos;
    }

    public void setMaxIntentos(int maxIntentos) {
        this.maxIntentos = maxIntentos;
    }

	public int getId_paquetes() {
		return id_paquetes;
	}

	public void setId_paquetes(int id_paquetes) {
		this.id_paquetes = id_paquetes;
	}
	

}
