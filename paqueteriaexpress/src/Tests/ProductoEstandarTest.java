package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Prods.Producto;
import sistema.SistemaAplicacion;

public class ProductoEstandarTest {
	SistemaAplicacion sist=new SistemaAplicacion();
	Producto p=new Producto(sist,3, 7, "juguetes", 5, 23, 15, 25);
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
		sist.setPesoMaximo(60);
	}

	@Test
	public void testValidar() {
		double alto=p.getAlto();
		double largo=p.getLargo();
		double ancho=p.getAncho();
		double peso=p.getPeso();
		assertTrue(alto<=sist.getAlto());
		assertTrue(ancho<=sist.getAncho());
		assertTrue(largo<=sist.getLargo());
		assertTrue(peso<=sist.getPesoMaximo());
	}

	@Test
	public void testCalcularPrecio() {
		double precio=p.calcularPrecio();
		assertTrue(precio==1);
	}

	@Test
	public void testIsEstandar() {
		assertTrue(p.isEstandar());
	}

}
