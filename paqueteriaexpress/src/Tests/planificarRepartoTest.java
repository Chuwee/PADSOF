package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import Paquete.Paquete;
import Pedido.EstadoPedido;
import Pedido.Pedido;
import Prods.Producto;
import Prods.ProductoFragil;
import Prods.Refrigerado;
import Transporte.Camion;
import Transporte.EstadoCamion;
import Transporte.TipoCamion;
import sistema.SistemaAplicacion;
import usuarios.Operador;

public class planificarRepartoTest {
	SistemaAplicacion sist=new SistemaAplicacion();
	ProductoFragil pf=new ProductoFragil(sist, true, 12, 3, "Taza", 1, 12, 14, 10);
	Producto p = new Producto(sist, 38, 2, "xd", 1, 13, 12, 10);
	Producto p2 = new Producto(sist, 39, 2, "xd", 1, 29, 12, 10);
	Producto p3 = new Refrigerado(sist, 40, 2, "xd", 1, 29, 12, 10, true);
	Pedido ped = new Pedido(1, false, "avenida de America 24", 2822, "hola", EstadoPedido.Construido, new Date(System.currentTimeMillis()));
	Pedido ped2 = new Pedido(2, true, "avenida de America 24", 4144, "hola", EstadoPedido.Construido, new Date(System.currentTimeMillis()));
	Operador op = new Operador(sist, 1, "", "Menganito", "Menganito10",
			 "MenganitoElCrack", "Menganito@uam.es");
	Camion c1 = new Camion(1500, "AAA000", EstadoCamion.FUNCIONAL, TipoCamion.Estandar);
	Camion c2 = new Camion(1500, "AAA001", EstadoCamion.FUNCIONAL, TipoCamion.RefrigeradoNoAsignado);
	Camion c3 = new Camion(1500, "AAA002", EstadoCamion.FUNCIONAL, TipoCamion.RefrigeradoLiquido);
	Camion c4 = new Camion(1500, "AAA003", EstadoCamion.FUNCIONAL, TipoCamion.DimEspeciales);
	Camion c5 = new Camion(1500, "AAA004", EstadoCamion.FUNCIONAL, TipoCamion.Estandar);
	
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
		sist.setPesoMaximo(50);
		sist.anadirCamion(c1);
		sist.anadirCamion(c2);
		sist.anadirCamion(c3);
		sist.anadirCamion(c4);
		sist.anadirCamion(c5);
	}
	
	@Test
	public void testEmpaquetarPedido() {
		ped.anadirUnidad(p);
		ped2.anadirUnidad(pf);
		ped.anadirUnidad(p2);
		ped.anadirUnidad(p3);
		ped.validar();
		ped2.validar();
		assertTrue(ped.getEstado() == EstadoPedido.Validado && ped2.getEstado() == EstadoPedido.Validado);
		op.empaquetarPedido(ped);
		op.empaquetarPedido(ped2);
		sist.planificarRepartoGlobal();
		assertEquals(c2.getPaquetes().get(0).getUnidades().get(0).getIdentificador(), 40);
	}
}
