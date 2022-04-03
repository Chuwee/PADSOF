package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Prods.Producto;
import Prods.Refrigerado;
import sistema.SistemaAplicacion;

public class ProductoRefrigeradoTest1 {
	SistemaAplicacion sist=new SistemaAplicacion();
	Producto pr=new Refrigerado(sist,13, 3, 0, "Pescado", 4, 23, 15, 25, true);
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
	}
	@Test
	public void testIsRefrigerado() {
		assertFalse(pr.isRefrigerado());
	}

	@Test
	public void testIsCongelado() {
		assertTrue(pr.isCongelado());
	}

	@Test
	public void testCalcularPrecio() {
		double resultado=pr.calcularPrecio();
		assertTrue(resultado==2.40);
	}
	@Test
	public void testValidar() {
		double alto=pr.getAlto();
		double largo=pr.getLargo();
		double ancho=pr.getAncho();
		assertTrue(alto==25);
		assertTrue(ancho==15);
		assertTrue(largo==23);
	}

}
