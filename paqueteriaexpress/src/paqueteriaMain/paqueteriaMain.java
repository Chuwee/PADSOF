package paqueteriaMain;

import java.sql.Date;
import java.util.*;

import Paquete.Paquete;
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
		sist.setAlto(30);
		sist.setAncho(30);
		sist.setLargo(30);
		sist.setPesoMaximo(60);
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
				leer.nextLine();
				System.out.println("Tarjeta de credito: ");
				TarjetaDeCredito=leer.nextLine();
				System.out.println("Direccion de contacto: ");
				dirContacto=leer.nextLine();
				System.out.println("NIF: ");
				NIF=leer.nextLine();
				System.out.println("Nombre: ");
				Nombre=leer.nextLine();
				System.out.println("Email: ");
				email=leer.nextLine();
				System.out.println("Usuario: ");
				Usuario=leer.nextLine();
				System.out.println("Contrasena: ");
				Contrasena=leer.nextLine();
				
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
					System.out.println("Contrasena incorrecta");
					c=new Cliente("", "", "", "", "", "", "");
				}
				catch(UsuarioNoEncontrado usn) {
					System.out.println("Usuario no encontrado");
					new Cliente("", "", "", "", "", "", "");
				}
				break;
			
			case 3:
				if(c.getNombre().isBlank()) {
					System.out.println("Tiene que iniciar sesion o registrarse: \n");
				}else {
					c.verPedidos(sist);
				}
				break;
				
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
		boolean urgente=false;
		int idProducto;
		double peso;
		int unidades;
		double largo;
		double ancho;
		double alto;
		boolean asegurado = false;
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
				leer.nextLine();
				System.out.println("Descripcion:  ");
				descripcion=leer.nextLine();
				System.out.println("Direccion de envio:  ");
				direccion=leer.nextLine();
				System.out.println("Codigo Postal:");
				codigoPostal=leer.nextInt();
				Pedido pedido= o.nuevoPedido(idPedido, urgente, direccion, codigoPostal, descripcion, 
						sist.getClientes().get(0), new Date(System.currentTimeMillis()));
				flag=0;
				while(opc==1) {
					System.out.println("1.Anadir producto ");
					System.out.println("2.Validar pedido ");
					System.out.println("Opcion: ");
					opc=leer.nextInt();
					if(opc==1) {
						System.out.println("Id del producto: ");
						idProducto=leer.nextInt();
						System.out.println("Peso: ");
						peso=leer.nextDouble();
						System.out.println("Unidades: ");
						unidades=leer.nextInt();
						System.out.println("Largo: ");
						largo=leer.nextDouble();
						System.out.println("Ancho: ");
						ancho=leer.nextDouble();
						System.out.println("Alto: ");
						alto=leer.nextDouble();
						System.out.println("1.Estandar ");
						System.out.println("2.Liquido ");
						System.out.println("3.Refrigerado ");
						System.out.println("4.Congelado ");
						System.out.println("5.Fragil ");
						System.out.println("Opcion: ");
						flag=leer.nextInt();
						switch(flag) {
						case 1:
							o.añadirProductoStandard(sist, pedido, idProducto, peso, 0, pedido.getDireccion(), pedido.getDescripcion(), 
									unidades, largo, ancho, alto);
							break;
						case 2:
							o.añadirProductoLiquido(sist, pedido, idProducto, peso, 0, direccion, descripcion, 
									unidades, largo, ancho, alto);
							break;
						case 3:
							o.añadirProductoRefrigerado(sist, pedido, idProducto, peso, 0, direccion, descripcion,
									unidades, largo, ancho, alto, false);
							break;
						case 4:
							o.añadirProductoCongelado(sist, pedido, idProducto, peso, 0, direccion, descripcion,
									unidades, largo, ancho, alto, true);
							break;
						case 5:
							int flag1=0;
							while(flag1==0) {
								System.out.println("Asegurado(S/N):  ");
								String asegurado1=leer.next();
								if(asegurado1.contentEquals("S")) {
									asegurado=true;
									flag1=1;
								}else if(asegurado1.contentEquals("N")) {
									asegurado=false;
									flag1=1;
								}else {
									System.out.println("Tiene que ser S/N");
								}
							}
							o.añadirProductoFragil(sist, pedido, idProducto, peso, 0, direccion, descripcion, unidades, 
									largo, ancho, alto, asegurado);
							break;
						}
						
					}else if(opc==2) {
						o.validarPedido(pedido);
						
					}
				}
				break;
			case 2:
				for(Pedido pedidos:sist.getPedidos() ) {
					if(pedidos.getEstado().equals(EstadoPedido.Validado)) {
						o.empaquetarPedido(pedidos);
					}
				}
				System.out.println("Empaquetado acabado\n");
				break;
			
			case 3:
				o.planificarRepartos();
				System.out.println("Planificacion acabada\n");
				break;
				
			case 4:
				o.getEstadisticas();
				break;
			
			case 5:
				return ;
			default:
				System.out.println("Opcion no valida\n");
			}
		}
		leer.close();
		
	}
	private static void MenuRepartidor(SistemaAplicacion sist) {
		int opcion=0;
		int idPaquete;
		Repartidor r=sist.getRepartidores().get(0);
		sist.asignarCamionRepartidor();
		Scanner leer=new Scanner(System.in);
		while(opcion!=5) {
			System.out.println("Repartidor:\n");
			System.out.println("1.Ver reparto\n");
			System.out.println("2.Imprimir informe\n");
			System.out.println("3.Marcar paquetes entregados\n");
			System.out.println("4.Salir\n");
			System.out.println("Eliga una opcion: ");
			opcion=leer.nextInt();
			switch(opcion) {
			case 1:
				r.ver_paquetes();
				break;
			case 2:
				r.imprimir_informe();
				break;
			
			case 3:
				int flag=0;
				String ent;
				r.ver_paquetes();
				Paquete paquete=null;
				System.out.println("Indique el id paquete:\n");
				idPaquete=leer.nextInt();
				for(Paquete paq : r.getCamion().getPaquetes()) {
					if(paq.getIdPaquete()==idPaquete) {
						paquete=paq;
					}
				}
				if(paquete!=null) {
					while(flag==0) {
						System.out.println("Entregado? (S/N):  ");
						ent=leer.next();
						if(ent.contentEquals("S")) {
							r.marcarPaqueteEntregado(paquete);
							flag=1;
						}else if(ent.contentEquals("N")) {
							r.marcarPaqueteEntregado(paquete);
							flag=1;
						}else {
							System.out.println("Tiene que ser S/N");
						}
					}
				}else {
					System.out.println("Id paquete no valido:\n");
				}
				
			case 4:
				
				return ;
			default:
				System.out.println("Opcion no valida\n");
			}
		}
		leer.close();
		
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
				MenuRepartidor(sist);
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
