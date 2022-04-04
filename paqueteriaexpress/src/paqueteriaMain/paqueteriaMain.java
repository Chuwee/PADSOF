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
				break;
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
				break;
			
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
				break;
				
			case 5:
				System.out.println("Adios!\n");
				return ;
			default:
				System.out.println("Opcion no valida\n");
			}
		}
		leer.close();
		
	}
	private static void MenuOperador(SistemaAplicacion sist) {
		int opcion=0, opc=1;
		int flag=0;
		Operador o=sist.getOperadores().get(0);
		Scanner leer=new Scanner(System.in);
		int idPedido, codigoPostal;
		boolean urgente;
		String  direccion, descripcion, urgente1;
		while(opcion!=5) {
			System.out.println("Operador:\n");
			System.out.println("1.Nuevo pedido\n");
			System.out.println("2.Empaquetar\n");
			System.out.println("3.Planificar Reparto\n");
			System.out.println("4.Estadisticas\n");
			System.out.println("5.Salir\n");
			System.out.println("Eliga una opcion: ");
			opcion=leer.nextInt();
			switch(opcion) {
			case 1:
				System.out.println("Nuevo pedido\n");
				System.out.println("Id: ");
				idPedido=leer.nextInt();
				flag=0;
				while(flag==0) {
					System.out.println("Urgente(S/N):  ");
					urgente1=leer.next();
					if(urgente1.contentEquals("S")) {
						urgente=true;
						flag=1;
					}else if(urgente1.contentEquals("N")) {
						urgente=false;
						flag=1;
					}else {
						System.out.println("Tiene que ser S/N");
					}
				}
				System.out.println("Direccion de envio:  ");
				direccion=leer.next();
				System.out.println("Codigo Postal:");
				codigoPostal=leer.nextInt();
				System.out.println("Descripcion:  ");
				descripcion=leer.next();
				Pedido pedido= o.nuevoPedido(idPedido, urgente, direccion, codigoPostal, descripcion, 
						sist.getClientes().get(0), new Date(System.currentTimeMillis()));
				flag=0;
				while(opc==1) {
					System.out.println("1.Anadir producto ");
					System.out.println("2.Validar pedido ");
					System.out.println("Opcion: ");
					opc=leer.nextInt();
					if(opc==1) {
						
					}else if(opc==2) {
						o.validarPedido(pedido);
						System.out.println("1.Estandar ");
						System.out.println("2.Liquido ");
						System.out.println("3.Refrigerado ");
						System.out.println("4.Congelado ");
						System.out.println("5.Fragil ");
						System.out.println("Opcion: ");
						flag=leer.nextInt();
						switch(flag) {
						case 1:
							System.out.println("Id del producto: ");
							int idProducto=leer.nextInt();
							System.out.println("Peso: ");
							double peso=leer.nextDouble();
							o.añadirProductoStandard(sist, pedido, idProducto, peso, 0, pedido.getDireccion(), descripcion, 
									unidades, largo, ancho, alto);
						}
						
					}
					
					
				}
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
				break;
			
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
				break;
			
			case 5:
				System.out.println("Adios!\n");
				return ;
			default:
				System.out.println("Opcion no valida\n");
			}
		}
		leer.close();
		
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
			try{
				opcion=leer.nextInt();
			}
			catch(NoSuchElementException e) {
				System.out.println("No se lee");
			}
			switch(opcion) {
			case 1:
				MenuCliente(sist);
				break;
			case 2:
				MenuOperador(sist);
				break;
			case 3:
				MenuRepartidor();
				break;
			case 4:
				System.out.println("Adios!\n");
				break;
			default:
				System.out.println("Opcion no valida\n");
			}
		}
		leer.close();
		
	}

}
