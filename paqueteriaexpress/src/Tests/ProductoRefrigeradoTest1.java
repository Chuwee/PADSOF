package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Prods.Producto;
import Prods.Refrigerado;
import sistema.SistemaAplicacion;

public class ProductoRefrigeradoTest1 {
	SistemaAplicacion sist=new SistemaAplicacion();
	Producto pr=new Refrigerado(sist,13, 3, "Pescado", 4, 23, 15, 25, true);
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
		sist.setPesoMaximo(60);
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
		double precio=pr.calcularPrecio();
		assertTrue(precio==0.6);
	}
	@Test
	public void testValidar() {
		double alto=pr.getAlto();
		double largo=pr.getLargo();
		double ancho=pr.getAncho();
		double peso=pr.getPeso();
		assertTrue(alto<=sist.getAlto());
		assertTrue(ancho<=sist.getAncho());
		assertTrue(largo<=sist.getLargo());
		assertTrue(peso<=sist.getPesoMaximo());
	}

}
