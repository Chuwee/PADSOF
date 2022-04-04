package Tests;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import Paquete.Paquete;
import Prods.ProductoFragil;
import sistema.SistemaAplicacion;
import usuarios.Operador;
import Prods.Producto;
import Pedido.EstadoPedido;
import Pedido.Pedido;


public class EmpaquetarPedidoTest {
	
	SistemaAplicacion sist=new SistemaAplicacion();
	ProductoFragil pf=new ProductoFragil(sist, true, 12, 3, "Taza", 1, 12, 14, 10);
	Producto p = new Producto(sist, 38, 2, "xd", 1, 13, 12, 10);
	Producto p2 = new Producto(sist, 39, 2, "xd", 1, 33, 12, 10);
	Pedido ped = new Pedido(1, false, "avenida de America 24", 2822, "hola", EstadoPedido.Construido, new Date(System.currentTimeMillis()));
	Pedido ped2 = new Pedido(2, true, "avenida de America 24", 4144, "hola", EstadoPedido.Construido, new Date(System.currentTimeMillis()));
	Operador op = new Operador(sist, 1, "", "Menganito", "Menganito10",
			 "MenganitoElCrack", "Menganito@uam.es");
			
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
		sist.setPesoMaximo(50);
	}
	
	@Test
	public void testEmpaquetarPedido() {
		ped.anadirUnidad(p);
		ped2.anadirUnidad(pf);
		ped.validar();
		ped2.validar();
		op.empaquetarPedido(ped);
		op.empaquetarPedido(ped2);
		Paquete p = sist.popCola();
		assertTrue(p.isUrgente() == true);
		assertTrue(p.getUnidades().get(0).getIdentificador() == 12);
		p = sist.popCola();
		assertFalse(p==null);
		assertTrue(p.isUrgente() == false);
		assertTrue(p.getUnidades().get(0).getIdentificador() == 38);
		assertTrue(sist.popCola() == null);
	}
	
	/*@Test
	public void testValidarPedido2() {
		ped2.anadirUnidad(p);
		ped2.anadirUnidad(pf);
		ped2.anadirUnidad(p2);
		ped2.validar();
		assertTrue(ped.getEstado()==EstadoPedido.Construido);
	}*/


}
