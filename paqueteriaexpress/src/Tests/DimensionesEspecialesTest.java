package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Prods.DimensionesEspeciales;
import Prods.ProductoFragil;
import sistema.SistemaAplicacion;

public class DimensionesEspecialesTest {
	SistemaAplicacion sist=new SistemaAplicacion();
	DimensionesEspeciales de=new DimensionesEspeciales(sist, 3, 6, 0, "mesa de ping pong", 1, 45, 85, 90);
	
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
		sist.setPesoMaximo(60);
	}

	@Test
	public void testIsEstandar() {
		assertFalse(de.isEstandar());
	}

	@Test
	public void testIsDimEsp() {
		assertTrue(de.isDimEsp());
	}

	@Test
	public void testValidar() {
		double alto=de.getAlto();
		double ancho=de.getAncho();
		double largo=de.getLargo();
		double peso=de.getPeso();		
		assertTrue(alto>sist.getAlto());
		assertTrue(ancho>sist.getAncho());
		assertTrue(largo>sist.getLargo());
		assertTrue(peso<=sist.getPesoMaximo());
	}

	@Test
	public void testCalcularPrecio() {
		double precio = de.calcularPrecio();
		assertTrue(precio==1);
	}

}
