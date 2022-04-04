package paqueteriaMain;

import java.sql.Date;
import java.util.*;

import Pedido.EstadoPedido;
import Pedido.Pedido;
import Prods.Producto;
import Prods.ProductoFragil;
import sistema.*;
import usuarios.*;

public class paqueteriaMain {
	private static SistemaAplicacion CrearSistema() {
		SistemaAplicacion sist=new SistemaAplicacion();
		sist.getRepartidores().add(new Repartidor(sist, "678234256","79059922J" ,"Pinar", "pinar04", 
				"pinar04","pinar04@gmail.com"));
		sist.getRepartidores().add(new Repartidor(sist, "678238256","50059022J" ,"Paloma", "paloma01", 
				"paloma01","paloma01@gmail.com"));
		sist.getRepartidores().add(new Repartidor(sist, "628526256","11159022J" ,"Demi", "demi02", 
				"demi02","demi02@gmail.com"));
		sist.getOperadores().add(new Operador(sist,25,"11159022J" ,"Mariano", "mariano98", 
				"mariano1998","mariano98@gmail.com"));
		sist.RegistrarCliente("9472547283382732", "Avenida de la Paz 34", "79059925Y", "Nicolas",
				"nico03", "nico020603", "nico03@gmail.com");
		ProductoFragil pf=new ProductoFragil(sist, true, 12, 3, "Taza", 1, 12, 14, 10);
		Producto p = new Producto(sist, 38, 2, "xd", 1, 13, 12, 10);
		Producto p2 = new Producto(sist, 39, 2, "xd", 1, 3, 12, 10);
		Pedido ped = new Pedido(1, false, "avenida de America 24", 2822, "hola", EstadoPedido.Entregado, new Date(System.currentTimeMillis()));
		Pedido ped2 = new Pedido(2, true, "avenida de America 24", 4144, "hola", EstadoPedido.EnRepartoFallido1, new Date(System.currentTimeMillis()));
		ped.anadirUnidad(pf);
		ped.anadirUnidad(p2);
		ped2.anadirUnidad(p);
		ped.setCliente(sist.getClientes().get(0));
		ped2.setCliente(sist.getClientes().get(0));
		sist.getPedidos().add(ped);
		sist.getPedidos().add(ped2);
		return sist;
		
	}
	private static void MenuCliente(SistemaAplicacion sist) {
		int opcion=0;
		Cliente c;
		c=new Cliente("", "", "", "", "", "", "");
		Scanner leer=new Scanner(System.in);
		String TarjetaDeCredito, dirContacto, NIF, Nombre, Usuario, Contrasena, email;
		while(opcion!=5) {
			System.out.println("Cliente:\n");
			System.out.println("1.Registrase\n");
			System.out.println("2.Iniciar sesion\n");
			System.out.println("3.Ver Estado de Pedidos\n");
			System.out.println("4.Descargar Factura\n");
			System.out.println("5.Salir\n");
			System.out.println("Eliga una opcion: ");
			opcion=leer.nextInt();
			switch(opcion) {
			case 1:
				System.out.println("Tarjeta de credito: ");
				TarjetaDeCredito=leer.next();
				System.out.println("Direccion de contacto: ");
				dirContacto=leer.next();
				System.out.println("NIF: ");
				NIF=leer.next();
				System.out.println("Nombre: ");
				Nombre=leer.next();
				System.out.println("Email: ");
				email=leer.next();
				System.out.println("Usuario: ");
				Usuario=leer.next();
				System.out.println("Contrasena: ");
				Contrasena=leer.next();
				
				c=sist.RegistrarCliente(TarjetaDeCredito, dirContacto, NIF, Nombre, Usuario, Contrasena, email);
			case 2:
				try {
					System.out.println("Usuario: ");
					Usuario=leer.next();
					System.out.println("Contrasena: ");
					Contrasena=leer.next();
					c=sist.loginCliente(Usuario, Contrasena);
				}
				catch(ContrasenaIncorrecta cinc) {
					
				}
				catch(UsuarioNoEncontrado usn) {
					
				}
			
			case 3:
				if(c.getNombre().isBlank()) {
					System.out.println("Tiene que iniciar sesion o registrarse: \n");
				}else {
					c.verPedidos(sist);
				}
				
			case 4:
				if(c.getNombre().isBlank()) {
					System.out.println("Tiene que iniciar sesion o registrarse: \n");
				}else {
					c.verPedidos(sist);
					System.out.println("Elija un pedido (id): \n");
					c.descargarFactura(leer.nextInt());
				}
				
			case 5:
				System.out.println("Adios!\n");
			default:
				System.out.println("Opcion no valida\n");
			}
		}
		leer.close();
		
	}
	private static void MenuOperador() {
		System.out.println("Operador:\n");
		
	}
	private static void MenuRepartidor() {
		System.out.println("Repartidor:\n");
		
	}
	
	public static void Main(String args[]) {
		int opcion=0;
		SistemaAplicacion sist=CrearSistema();
		Scanner leer=new Scanner(System.in);
		while(opcion!=4) {
			System.out.println("Menu:\n");
			System.out.println("1.Cliente\n");
			System.out.println("2.Operador\n");
			System.out.println("3.Repartidor\n");
			System.out.println("4.Salir\n");
			System.out.println("Eliga una opcion: ");
			opcion=leer.nextInt();
			switch(opcion) {
			case 1:
				MenuCliente(sist);
			case 2:
				MenuOperador();
			case 3:
				MenuRepartidor();
			case 4:
				System.out.println("Adios!\n");
			default:
				System.out.println("Opcion no valida\n");
			}
		}
		leer.close();
		
	}

}
