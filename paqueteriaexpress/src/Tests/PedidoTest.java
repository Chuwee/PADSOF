package Tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Pedido.EstadoPedido;
import Pedido.Pedido;
import Prods.Liquido;
import Prods.Producto;
import Prods.Unidad;
import sistema.SistemaAplicacion;

public class PedidoTest {
	SistemaAplicacion sist= new SistemaAplicacion();
	Pedido p=new Pedido(3, true, "Calle SIlvano 6", 28043, "pedido muy urgente", EstadoPedido.EnConstruccion, new Date(System.currentTimeMillis()));
	Producto p1=new Producto(sist,3, 7, "juguetes", 5, 23, 15, 25);
	Producto pl= new Liquido(sist, 4,6, 0,  "Botellas de agua", 2, 12, 14, 20);
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
		sist.setPesoMaximo(60);
		p.anadirUnidad(p1);
		p.anadirUnidad(pl);
	}

	@Test
	public void testAnadirUnidad() {
		assertTrue(p.getEstado()==EstadoPedido.EnConstruccion);
	}

	@Test
	public void testCalcularPrecio() {
		double precio=p.calcularPrecio();
		assertTrue(precio==7.0);
	}

	@Test
	public void testQuitarUnidad() {
		p.quitarUnidad(p1);
		ArrayList<Unidad> unidades=p.getUnidades();
		int size=unidades.size();
		assertTrue(size==1);
	}

	@Test
	public void testIsUrgente() {
		assertTrue(p.isUrgente());
	}

}
