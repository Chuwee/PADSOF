package Tests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import Prods.ProductoFragil;
import sistema.SistemaAplicacion;
import Prods.Producto;
import Pedido.EstadoPedido;
import Pedido.Pedido;


public class validarPedidoTest {
	SistemaAplicacion sist=new SistemaAplicacion();
	ProductoFragil pf=new ProductoFragil(sist, true, 12, 3, 0, "Taza", 1, 12, 14, 10);
	Producto p = new Producto(sist, 38, 2, "xd", 1, 13, 12, 10);
	Producto p2 = new Producto(sist, 39, 2, "xd", 1, 33, 12, 10);
	Pedido ped = new Pedido(1, false, "avenida de America 24", 2822, "hola", EstadoPedido.Construido, new Date(System.currentTimeMillis()));
	Pedido ped2 = new Pedido(1, false, "avenida de America 24", 2822, "hola", EstadoPedido.Construido, new Date(System.currentTimeMillis()));
	
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
	}
	
	@Test
	public void testValidarPedido() {
		ped.anadirUnidad(p);
		ped.anadirUnidad(pf);
		ped.validar();
		assertTrue(ped.getEstado()==EstadoPedido.Validado);
	}
	
	@Test
	public void testValidarPedido2() {
		ped2.anadirUnidad(p);
		ped2.anadirUnidad(pf);
		ped2.anadirUnidad(p2);
		ped2.validar();
		assertFalse(ped.getEstado()==EstadoPedido.Construido);
	}


}
