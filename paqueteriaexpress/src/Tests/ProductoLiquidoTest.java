package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Prods.Liquido;
import Prods.Producto;
import sistema.SistemaAplicacion;

public class ProductoLiquidoTest {
	SistemaAplicacion sist= new SistemaAplicacion();
	Producto pl= new Liquido(sist, 4,6, 0,  "Botellas de agua", 2, 12, 14, 20);
	@Before
	public void setup() {
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
	}
	@Test
	public void testIsLiquido() {
		assertTrue(pl.isLiquido());
	}

}
