package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Prods.Producto;
import Prods.Refrigerado;
import sistema.SistemaAplicacion;

public class ProductoRefrigeradoTest2 {
	SistemaAplicacion sist= new SistemaAplicacion();
	Producto pr= new Refrigerado(sist, 2, 0.5, 0, "pizza", 1, 20, 20, 20, false);
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
	}
	@Test
	public void testIsRefrigerado() {
		assertTrue(pr.isRefrigerado());
	}

	@Test
	public void testIsCongelado() {
		assertFalse(pr.isCongelado());
	}

	@Test
	public void testIsEstandar() {
		assertFalse(pr.isEstandar());
	}

	
	@Test
	public void testValidar() {
		double alto=pr.getAlto();
		double largo=pr.getLargo();
		double ancho=pr.getAncho();
		assertTrue(alto<=sist.getAlto());
		assertTrue(ancho<=sist.getAncho());
		assertTrue(largo<=sist.getLargo());
	}

	@Test
	public void testCalcularPrecio() {
		double precio=pr.calcularPrecio();
		assertTrue(precio==0.40);
}

}
