package sistema;

import java.text.SimpleDateFormat;

/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import java.util.*;


import GlobalVars.ColasPrioridad;
import GlobalVars.Vars;
import Paquete.*;
import Pedido.*;
import Transporte.Camion;

import usuarios.*;

import Transporte.EstadoCamion;
import Transporte.PesoCamionException;
import Transporte.TipoCamion;

import es.uam.eps.padsof.telecard.*;


public class SistemaAplicacion {
    private List<Camion> camiones;
    private List<Cliente> clientes;
    private List<Repartidor> repartidores;
    private List<Operador> operadores;
    private List<Pedido> pedidos;
    private List<Paquete> paquetesEntregados;
    private List<Paquete> paquetesNoEntregados;
	private double pesoMaximo;
	private double largo;
    private double ancho;
    private double alto;
    private int maxDirecciones;
    private int maxIntentos;
    private int id_paquetes;
    private Map<String, Double> ingresosMensuales;
    private Map<String, Integer> pedidosMensuales;
    private double costeMedioPedido;
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

    private Paquete popCola() {
        Paquete p;
        for(int i = 0; i < colasPrioridad.size(); i++) {
            p = colasPrioridad.get(i).popPaquete();
            if(p != null)
                return p;
        }
        return null;
    }

    public void planificarRepartoGlobal() {
        Paquete p = popCola();
        while(p != null) {
            planificarReparto(p);
        }
    }
    
    public void anadirPaqueteACola(Paquete p, int index) {
    	colasPrioridad.get(index).addPaquete(p);
    }
    
    public void asignarCamionRepartidor() {
    	List<Camion> camionesValidos=new ArrayList<Camion>();
    	Iterator<Repartidor> itRep=repartidores.iterator();
    	Iterator<Camion> itCam=camionesValidos.iterator();
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
    				noHayCamiones(p);
    			}
    		}
    	}
    }
    public void RegistrarCliente(String TarjetaDeCredito, String dirContacto, String NIF, String Nombre, String Usuario, String Contrasena, String email) {
		Cliente c=new Cliente(TarjetaDeCredito, dirContacto, NIF, Nombre, Usuario, Contrasena, email);
		clientes.add(c);
	}
    public UsuarioIdentificado login(String usuario, String contrasena) throws ContrasenaIncorrecta, UsuarioNoEncontrado{
    	for(Cliente c: clientes) {
    		if(c.getUsuario().equals(usuario)) {
    			if(c.getContrasena().equals(contrasena)) {
    				return c;
    			}else {
    				throw new ContrasenaIncorrecta();
    			}
    		}
    	}
    	for(Operador o: operadores) {
    		if(o.getUsuario().equals(usuario)) {
    			if(o.getContrasena().equals(contrasena)) {
    				return o;
    			}else {
    				throw new ContrasenaIncorrecta();
    			}
    		}
    	}
    	for(Repartidor r: repartidores) {
    		if(r.getUsuario().equals(usuario)) {
    			if(r.getContrasena().equals(contrasena)) {
    				return r;
    			}else {
    				throw new ContrasenaIncorrecta();
    			}
    		}
    	}
    	throw new UsuarioNoEncontrado();
    }
    
    private void calcularIngresosMensuales() {
    	ingresosMensuales=new HashMap<String, Double>();
    	SimpleDateFormat formater;
    	String fecha;
    	formater=new SimpleDateFormat("MM-yy");
    	for(Pedido p: pedidos) {
    		if(!p.getEstado().equals(EstadoPedido.Validado)&&!p.getEstado().equals(EstadoPedido.EnConstruccion)
    				&&!p.getEstado().equals(EstadoPedido.Construido)) {
    			fecha=formater.format(p.getFecha());
    			if(ingresosMensuales.putIfAbsent(fecha, p.getPrecio())!=null) {
    				ingresosMensuales.put(fecha, p.getPrecio()+ ingresosMensuales.get(fecha));
    			}
    		}
    	}
    }
    
    private void calcularPedidosMensuales() {
    	pedidosMensuales= new HashMap<String, Integer>();
    	SimpleDateFormat formater;
    	String fecha;
    	formater=new SimpleDateFormat("MM-yy");
    	for(Pedido p: pedidos) {
    			fecha=formater.format(p.getFecha());
    			if(pedidosMensuales.putIfAbsent(fecha, 1)!=null) {
    				ingresosMensuales.put(fecha, 1+ ingresosMensuales.get(fecha));
    			}
    	}
    	
    }
    
    
    public List<Paquete> getPaquetesEntregados(){
    	return this.paquetesEntregados;
    }
    public void setPaquetes(List<Paquete> paquetes) {
    	this.paquetesEntregados=paquetes;
    }
    public List<Camion> getCamiones() {
        return this.camiones;
    }
    public void setCamiones(List<Camion> camiones) {
        this.camiones = camiones;
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

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Repartidor> getRepartidores() {
		return repartidores;
	}

	public void setRepartidores(List<Repartidor> repartidores) {
		this.repartidores = repartidores;
	}

	public List<Operador> getOperadores() {
		return operadores;
	}

	public void setOperadores(List<Operador> operadores) {
		this.operadores = operadores;
	}
	public boolean comprobarTarjeta(String numTarjeta) throws InvalidCardNumberException {
		if(TeleChargeAndPaySystem.isValidCardNumber(numTarjeta)==true) {
			return true; 
		}
		throw new InvalidCardNumberException(numTarjeta); 
	}

	public List<Paquete> getPaquetesNoEntregados() {
		return paquetesNoEntregados;
	}

	public void setPaquetesNoEntregados(List<Paquete> paquetesNoEntregados) {
		this.paquetesNoEntregados = paquetesNoEntregados;
	}
	public Map<String, Double> getIngresosMensuales() {
		calcularIngresosMensuales();
		return ingresosMensuales;
	}

	public void setIngresosMenuslaes(Map<String, Double> ingresosMensuales) {
		this.ingresosMensuales = ingresosMensuales;
	}

	public Map<String, Integer> getPedidosMensuales() {
		this.calcularPedidosMensuales();
		return pedidosMensuales;
	}

	public void setPedidosMensuales(Map<String, Integer> pedidosMensuales) {
		this.pedidosMensuales = pedidosMensuales;
	}

	public double getCosteMedioPedido() {
		double sum=0;
		for(Pedido p: pedidos) {
			sum+=p.getPrecio();
		}
		costeMedioPedido=sum/pedidos.size();
		return costeMedioPedido;
	}

	public void setCosteMedioPedido(double costeMedioPedido) {
		this.costeMedioPedido = costeMedioPedido;
	}
	

}
