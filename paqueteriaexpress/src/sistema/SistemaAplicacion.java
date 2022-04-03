package sistema;
import java.util.ArrayList;
import java.util.List;

import GlobalVars.ColasPrioridad;
import GlobalVars.Vars;
import Paquete.Paquete;
import Pedido.Pedido;
import Transporte.Camion;
import Transporte.EstadoCamion;
import Transporte.PesoCamionException;
import Transporte.TipoCamion;
import usuarios.UsuarioIdentificado;

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
    ArrayList<ColaPrioridadPaquetes> colasPrioridad;
    
    public SistemaAplicacion() {
    	colasPrioridad = new ArrayList<ColaPrioridadPaquetes>();
    	for(int i = 0; i < Vars.getNumColasPrioridad(); i++) {
    		colasPrioridad.add(new ColaPrioridadPaquetes());
    	}
    }

    public int numPaquetesTotal() {
        int counter = 0;
        for(ColaPrioridadPaquetes cola : colasPrioridad) {
            counter+=cola.size();
        }
        return counter;
    }

    private Camion getCamionApropiado_fromTipo(TipoCamion tipo, Paquete p) {
        double peso = p.getPeso();
        boolean lookForRefrigerado = (tipo == TipoCamion.RefrigeradoCongelado)
            || (tipo == TipoCamion.RefrigeradoLiquido) || (tipo == TipoCamion.RefrigeradoRefrigerado);
        
        for(Camion c : camiones) {
            if(c.getEstado()!=EstadoCamion.AVERIADO && 
            (c.getTipo() == tipo || (lookForRefrigerado && c.getTipo() == TipoCamion.RefrigeradoNoAsignado))) {
                if(c.canAdd(peso))
                {
                    c.setTipo(tipo);
                    return c;
                }
            }
        }
        return null;
    }

    public void noHayCamiones(Paquete p) {
        if(p.isUrgente()) {
            anadirPaqueteACola(p, Vars.getColaPrioridad(ColasPrioridad.URGENTES));
            return;
        }
        anadirPaqueteACola(p, Vars.getColaPrioridad(ColasPrioridad.NOENTREGADOSFALTACAMIONES));
    }

    public void planificarReparto(Paquete p) {
        /*Debemos planificar el reparto del paquete p*/
        /**
         * Debemos meterlo en un camión apropiado.
         */
        TipoCamion tipo = Vars.TipoPaquete_to_TipoCamion(p.getTp());
        Camion c = getCamionApropiado_fromTipo(tipo, p);
        if(c!=null)
        {
            try {
                c.anadirPaquete(p);
            }
            catch(PesoCamionException ex) {
                System.out.println("Camión "+c.getMatricula()+" lleno.");
            }
        }
        else
            noHayCamiones(p);
        return;
    }

    public void planificarRepartoGlobal() {
        for(Paquete p: paquetes)
            planificarReparto(p);
    }
    
    public void anadirPaqueteACola(Paquete p, int index) {
    	colasPrioridad.get(index).addPaquete(p);
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
	

}
