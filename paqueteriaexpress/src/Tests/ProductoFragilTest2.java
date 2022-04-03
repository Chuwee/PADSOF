package Tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;

import Prods.ProductoFragil;
import sistema.SistemaAplicacion;

import static org.junit.Assert.*;

public class ProductoFragilTest2 {
	SistemaAplicacion sist=new SistemaAplicacion();
	ProductoFragil pf=new ProductoFragil(sist, true, 12, 3, 0, "avenida de America 24", "Taza", 1, 12, 14, 10);
	
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
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


}
