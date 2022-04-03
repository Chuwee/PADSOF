package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Prods.Lote;
import sistema.SistemaAplicacion;

public class LoteTest {
	SistemaAplicacion sist= new SistemaAplicacion();
	Lote l=new Lote(3, 4, 2);
	@Before
	public void setup() {
		sist.setAlto(50);
		sist.setAncho(50);
		sist.setLargo(50);
	}

	@Test
	public void testValidar() {
		assertTrue(l.getProductos());
	}

	@Test
	public void testCalcularPrecio() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsLote() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalcularPeso() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnadirProducto() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnadirLote() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarProducto() {
		fail("Not yet implemented");
	}

}
