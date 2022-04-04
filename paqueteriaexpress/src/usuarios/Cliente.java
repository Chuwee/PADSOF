package usuarios;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Pedido.Pedido;
import Prods.Unidad;
import sistema.SistemaAplicacion;


package test_invoicesystem;

import java.util.Arrays;
import java.util.List;

import es.uam.eps.padsof.invoices.IInvoiceInfo;
import es.uam.eps.padsof.invoices.IProductInfo;
import es.uam.eps.padsof.invoices.InvoiceSystem;
import es.uam.eps.padsof.invoices.NonExistentFileException;
import es.uam.eps.padsof.invoices.UnsupportedImageTypeException;

class Product implements IProductInfo {	

	Unidad u;

	public Product(Unidad u) {
		this.u = u;
	}
	public String getDescription() { return u.getDesc(); }
	public double getPrice() { return u.calcularPrecio(); }
	public String getPriceDetails() { return u.getPriceDetailString(); }
}

class InvoiceCliente implements IInvoiceInfo {  
	Cliente c;
	Pedido p;

	public InvoiceCliente(Cliente c, Pedido p) {
		this.c = c;
		this.p = p;
	}

	public String getClientCif() { return c.getNIF(); }
	public String getCompanyName() { return "Paquetería Express"; }
	public String getCompanyLogo () { return ""; } // jpg, gif and png formats are supported	
	public double getDiscount() { return (p.getNumProductos()>100?0.1*p.calcularPrecio():0); }
	public double getUrgent() { return p.isUrgente()?5:0; }
	public String getOrderDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String dateString = format.format( p.getFecha()  );
		return dateString;
	 }
	public String getOrderIdentifier() { return "INV"+p.getIdPedido(); }
	public double getPrice() { return p.calcularPrecio(); }
	public List<IProductInfo> getProducts() { 
		ArrayList<Product> productos;
		for(Unidad u : p.getUnidades()) {
			productos.add(new Product(u));
		}	}
}

/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
public class Cliente extends UsuarioIdentificado {
    private String TarjetaDeCredito;
    private String dirContacto;
    private ArrayList<Integer> pedidosUser;

	public Cliente(String TarjetaDeCredito, String dirContacto, String NIF, String Nombre, String Usuario, String Contrasena, String email){
        super(NIF, Nombre, Usuario, Contrasena, email);
        this.TarjetaDeCredito = TarjetaDeCredito;
		this.dirContacto=dirContacto;
		this.pedidosUser=new ArrayList<Integer>();

    }


	public String getTarjetaDeCredito() {
		return this.TarjetaDeCredito;
	}

	public void setTarjetaDeCredito(String TarjetaDeCredito) {
		this.TarjetaDeCredito = TarjetaDeCredito;
	}

	public String getDirContacto() {
		return this.dirContacto;
	}

	public void setDirContacto(String dirContacto) {
		this.dirContacto=dirContacto;
	}

	public ArrayList<Integer> getPedidosUser() {
		return this.pedidosUser;
	}

	public void setPedidosUser(ArrayList<Integer> pedidosUser) {
		this.pedidosUser = pedidosUser;
	}

    public void descargarFactura(int id_pedido){

    }
    public void verPedidos(SistemaAplicacion sist){
    	for(int id : this.pedidosUser) {
    		for(Pedido p :sist.getPedidos()) {
    			if(p.getCliente().equals(this)) {
    				System.out.println("Nº de pedido: "+ p.getIdPedido()+" Estado: "+ p.getEstado()+"\n");
    			}
    		}
    	}
  
    }
    public void logout(){

    
	}
}
