package usuarios;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
import java.util.ArrayList;
import java.util.List;

import GlobalVars.ColasPrioridad;
import GlobalVars.TipoPaquete;
import GlobalVars.Vars;
import Paquete.EstadoPaquete;
import Paquete.Paquete;
import Pedido.*;
import Prods.*;
import Transporte.Camion;

import Transporte.EstadoCamion;

import sistema.SistemaAplicacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

import es.uam.eps.padsof.telecard.*;



/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María
 *         del Pinar Sacristán Matesanz
 * 
 */
public class Operador extends UsuarioIdentificado {
	private int pedidosDistribuidos;
	SistemaAplicacion sist;
	private static ArrayList<TipoPaquete> arrPaquetes = new ArrayList<TipoPaquete>() {
		{
			add(0, TipoPaquete.ESTANDAR);
			add(1, TipoPaquete.CONGELADO);
			add(2, TipoPaquete.DIMESPECIALES);
			add(3, TipoPaquete.LIQUIDO);
			add(4, TipoPaquete.REFRIGERADO);
		}
	};

	public Operador(SistemaAplicacion sist, int pedidosDistribuidos, String NIF, String Nombre, String Usuario,
			String Contrasena, String email) {
		super(NIF, Nombre, Usuario, Contrasena, email);
		this.pedidosDistribuidos = pedidosDistribuidos;
		this.sist = sist;
	}

	public int getPedidosDistribuidos() {
		return this.pedidosDistribuidos;
	}

	public void setPedidosDistribuidos(int pedidosDistribuidos) {
		this.pedidosDistribuidos = pedidosDistribuidos;
	}

	public Pedido nuevoPedido(int idPedido, boolean urgente, String direccion, int codigoPostal, String descripcion,
			Cliente cliente, Date fecha) {
		Pedido p = new Pedido(idPedido, urgente, direccion, codigoPostal, descripcion, EstadoPedido.EnConstruccion,
				fecha);
		cliente.getPedidosUser().add(idPedido);
		return p;

	}

	public boolean añadirProductoStandard(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio,
			String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {

		Producto prod = new Producto(sist, idProducto, peso, descripcion, unidades, largo, ancho, alto);
		p.getUnidades().add(prod);
		return true;

	}

	public boolean añadirProductoLiquido(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio,
			String direccion, String descripcion, int unidades, double largo, double ancho, double alto) {

		Producto prod = new Liquido(sist, idProducto, peso, precio, descripcion, unidades, largo, ancho,
				alto);
		p.getUnidades().add(prod);
		return true;

	}

	public boolean añadirProductoRefrigerado(SistemaAplicacion sist, Pedido p, int idProducto, double peso,
			double precio, String direccion, String descripcion, int unidades, double largo, double ancho, double alto,
			boolean congelado) {

		Producto prod = new Refrigerado(sist, idProducto, peso, precio, descripcion, unidades, largo, ancho,
				alto, congelado);
		p.getUnidades().add(prod);
		return true;

	}

	public boolean añadirProductoCongelado(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio,
			String direccion, String descripcion, int unidades, double largo, double ancho, double alto,
			boolean congelado) {

		Producto prod = new Refrigerado(sist, idProducto, peso, precio, descripcion, unidades, largo, ancho,
				alto, congelado);
		p.getUnidades().add(prod);
		return true;

	}

	public boolean añadirProductoDimEsp(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio,
			String direccion, String descripcion, int unidades, double largo, double ancho, double alto,
			boolean congelado) {

		Producto prod = new DimensionesEspeciales(sist, idProducto, peso, precio, descripcion, unidades,
				largo, ancho, alto);
		p.getUnidades().add(prod);
		return true;

	}

	public boolean añadirProductoFragil(SistemaAplicacion sist, Pedido p, int idProducto, double peso, double precio,
			String direccion, String descripcion, int unidades, double largo, double ancho, double alto,
			boolean asegurado) {

		Producto prod = new ProductoFragil(sist, asegurado, idProducto, peso, precio, descripcion, unidades,
				largo, ancho, alto);
		p.getUnidades().add(prod);
		return true;
	}

	private void tratarLote(Lote l, List<Producto> lista, TipoPaquete tp) {
		l.setTipopaquete(tp);
		for (Producto producto : lista) {
			l.anadirProducto(producto);
		}
	}

	public boolean añadirLote(Pedido p, int idLote, double peso, double precio, String direccion, double tam,
			int unidades, List<Producto> prod, List<Lote> lot) {
		Lote l = new Lote(idLote, tam, unidades);
		int flag = 0;
		tratarLote(l, prod, prod.get(0).getTipoPaquete());
		for (Producto producto : prod) {
			if (producto.isFragil()) {
				flag = 1;
				l.setTipopaquete(TipoPaquete.FRAGIL);
				l.anadirProducto(producto);
			}
		}
		if (flag == 0) {
			l.setTipopaquete(TipoPaquete.ESTANDAR);
			for (Producto producto : prod) {
				l.anadirProducto(producto);
			}
		}
		if (lot.get(0).isDimEsp()) {
			l.setTipopaquete(TipoPaquete.DIMESPECIALES);
			for (Lote l1 : lot) {
				l.anadirLote(l1);
			}
		} else if (lot.get(0).isRefrigerado()) {
			l.setTipopaquete(TipoPaquete.REFRIGERADO);
			for (Lote l1 : lot) {
				l.anadirLote(l1);
				;
			}
		} else if (lot.get(0).isCongelado()) {
			l.setTipopaquete(TipoPaquete.CONGELADO);
			for (Lote l1 : lot) {
				l.anadirLote(l1);
				;
			}
		} else if (lot.get(0).isLiquido()) {
			l.setTipopaquete(TipoPaquete.LIQUIDO);
			for (Lote l1 : lot) {
				l.anadirLote(l1);
			}
		}
		for (Lote l1 : lot) {
			if (l1.isFragil()) {
				flag = 1;
				l.setTipopaquete(TipoPaquete.FRAGIL);
				l.anadirLote(l1);
			}
		}
		if (flag == 0) {
			l.setTipopaquete(TipoPaquete.ESTANDAR);
			for (Lote l1 : lot) {
				l.anadirLote(l1);
			}
		}
		p.getUnidades().add(l);
		return true;
	}

	public boolean comprobarCodigoPostal(Pedido p) throws IOException {
		int codigo = p.getCodigoPostal();
		try {
			File fil = new File("codigos.txt");
			BufferedReader buffer = new BufferedReader(new FileReader(fil));
			String linea;
			int lineaInteger;
			while ((linea = buffer.readLine()) != null) {
				lineaInteger = Integer.parseInt(linea);
				if (lineaInteger == codigo) {
					buffer.close();
					return true;
				}
			}
			buffer.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se ha encontrado\n");
		} catch (NumberFormatException e) {
			System.out.println("" + e);
		} catch (IOException e) {
			System.out.println("" + e);
		}
		return false;

	}

	public void empaquetarPedido(Pedido pedido) {
		double maxPeso = sist.getPesoMaximo();
		int id = sist.getId_paquetes();
		int index = 0;
		int num_empaquetado = 0;
		for (Unidad u : pedido.getUnidades()) {
			if (u.getTipoPaquete() == TipoPaquete.FRAGIL){
				Paquete p = new Paquete(id, u.getDireccion(), TipoPaquete.FRAGIL);
				empaquetar(u, p);
				anadirPaqueteACola(p);
				id++;
				num_empaquetado++;
			}
		}

		while (num_empaquetado != pedido.getUnidades().size()) {
			ArrayList<Paquete> paquetes = new ArrayList<Paquete>(5);
			for(int i = 0; i < 5; i++) {
				paquetes.add(new Paquete(id, pedido.getDireccion(), arrPaquetes.get(i)));
				id++;
			}
			for (Unidad u : pedido.getUnidades()) {
				if (u.getEmpaquetado() == false)
				{
					index = arrPaquetes.indexOf(u.getTipoPaquete());
					num_empaquetado = empaquetar(u, paquetes.get(index), maxPeso, num_empaquetado);
				}
			}
			for(Paquete p : paquetes)
				anadirPaqueteACola(p);
		}
		sist.setId_paquetes(id);

	}

	private int empaquetar(Unidad u, Paquete p, double maxPeso, int num_empaquetado) {
		if (p.getPeso() + u.getPeso() <= maxPeso) {
			p.getUnidades().add(u);
			u.setEmpaquetado(true);
			num_empaquetado++;
		}
		return num_empaquetado;
	}

	private void empaquetar(Unidad u, Paquete p) {
		p.getUnidades().add(u);
		u.setEmpaquetado(true);
	}

	private int asignarCola(Paquete p) {

		if (p.isUrgente())
			return Vars.getColaPrioridad(ColasPrioridad.URGENTES);

		EstadoPaquete ep = p.getEstadoPaquete();

		switch (ep) {
			case NoEntregadoFaltaCamiones:
				return Vars.getColaPrioridad(ColasPrioridad.NOENTREGADOSFALTACAMIONES);
			case PendienteFallido1:
				return Vars.getColaPrioridad(ColasPrioridad.DEVUELTOS);
			default:
				return Vars.getColaPrioridad(ColasPrioridad.RESTO);
		}
	}

	private void anadirPaqueteACola(Paquete p) {
		int cola = asignarCola(p);
		sist.anadirPaqueteACola(p, cola);
	}

	private void planificarRepartos() {
		sist.planificarRepartoGlobal();
	}

	public boolean validarPedido(Pedido p) {
		p.validar();
	
		if (p.getEstado() == EstadoPedido.Validado) {
			return true;
		}
		return false;
	}
	

	public void modificarAlto(SistemaAplicacion p, double alto) {
		p.setAlto(alto);
	}

	public void modificarAncho(SistemaAplicacion p, double ancho) {
		p.setAncho(ancho);
	}

	public void modificarLargo(SistemaAplicacion p, double largo) {
		p.setLargo(largo);
	}

	public void modificarPeso(SistemaAplicacion p, double peso) {
		p.setPesoMaximo(peso);
	}

	public void modificarIntentos(SistemaAplicacion p, int intentos) {
		p.setMaxIntentos(intentos);
	}

	public void modificarDirecciones(SistemaAplicacion p, int direcciones) {
		p.setMaxDirecciones(direcciones);
	}

	public void planificarReparto(Paquete p) {
		sist.planificarReparto(p);
	}

	public void marcarCamionAveriado(Camion c) {
		c.setEstado(EstadoCamion.AVERIADO);
	}

	public void marcarCamionFuncional(Camion c) {
		c.setEstado(EstadoCamion.FUNCIONAL);
	}
	public void cobrarPedido( Pedido p) throws FailedInternetConnectionException, OrderRejectedException{
		double precio=p.calcularPrecio();
		TeleChargeAndPaySystem.charge(p.getCliente().getTarjetaDeCredito(), "p.getIdPedido()", precio);
	}

}
