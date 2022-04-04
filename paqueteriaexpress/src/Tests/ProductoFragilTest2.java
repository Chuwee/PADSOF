package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Prods.ProductoFragil;
import sistema.SistemaAplicacion;

public class ProductoFragilTest2 {
	SistemaAplicacion sist=new SistemaAplicacion();
	ProductoFragil pf=new ProductoFragil(sist, true, 12, 3, "Taza", 1, 12, 14, 10);
	
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
		sist.setPesoMaximo(60);
	}
	
	@Test
	public void testCalcularPrecio() {	
		double resultado=pf.calcularPrecio();
		assertTrue(resultado==5.60);
		
	}

	@Test
	public void testIsFragil() {
		assertTrue(pf.isFragil());
		
	}

	@Test
	public void testIsEstandar() {
		assertFalse(pf.isEstandar());
	}
	@Test
	public void testValidar() {
		double alto=pf.getAlto();
		double largo=pf.getLargo();
		double ancho=pf.getAncho();
		double peso=pf.getPeso();
		assertTrue(alto<=sist.getAlto());
		assertTrue(ancho<=sist.getAncho());
		assertTrue(largo<=sist.getLargo());
		assertTrue(peso<=sist.getPesoMaximo());
	}


}
