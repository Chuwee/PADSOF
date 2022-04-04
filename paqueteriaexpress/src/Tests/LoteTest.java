package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import GlobalVars.TipoPaquete;
import Prods.Lote;
import Prods.Producto;
import Prods.ProductoFragil;
import sistema.SistemaAplicacion;

public class LoteTest {
	SistemaAplicacion sist= new SistemaAplicacion();
	ProductoFragil pf=new ProductoFragil(sist, true, 12, 3, "Taza", 1, 12, 14, 10);
	Producto p = new Producto(sist, 38, 2, "xd", 1, 13, 12, 10);
	Producto p2 = new Producto(sist, 39, 2, "xd", 1, 33, 12, 10);
	Lote l=new Lote(3, 4, 2);
	
	@Before
	public void setup() {
		sist.setAlto(50);
		sist.setAncho(50);
		sist.setLargo(50);
		l.anadirProducto(p);
		l.anadirProducto(pf);
		
	}

	@Test
	public void testValidar() {
		assertFalse(l.getProductos().isEmpty());
		assertTrue(l.getProductos().size()==2);
	}

	@Test
	public void testCalcularPrecio() {
		assertTrue(p.calcularPrecio()==0.6);
		assertTrue(pf.calcularPrecio()==5.6);
		assertTrue(l.calcularPrecio()==p.calcularPrecio()+pf.calcularPrecio());
	}

	@Test
	public void testIsLote() {
		assertTrue(l.isLote());
	}

	@Test
	public void testCalcularPeso() {
		assertTrue(p.getPeso()==2);
		assertTrue(pf.getPeso()==3);
		assertTrue(l.calcularPeso()==5.0);
	}

	@Test
	public void testAnadirProducto() {
		l.anadirProducto(p2);
		assertTrue(l.getProductos().size()==3);
	}

	@Test
	public void testAnadirLote() {
		l.anadirLote(l);
		assertTrue(l.getLotes().size()==1);
	}

	@Test
	public void testEliminarProducto() {
		l.eliminarProducto(pf);
		assertTrue(l.getProductos().size()==1);
	}
	
	@Test
	public void testTipoPaquete() {
		assertTrue(l.getTipoPaquete()==TipoPaquete.FRAGIL);
	}


}
