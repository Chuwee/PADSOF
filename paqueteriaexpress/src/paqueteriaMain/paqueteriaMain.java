package paqueteriaMain;

import java.util.*;
import sistema.SistemaAplicacion;

public class paqueteriaMain {
	private static void MenuCliente() {
		System.out.println("Cliente:\n");
	}
	private static void MenuOperador() {
		System.out.println("Operador:\n");
		
	}
	private static void MenuRepartidor() {
		System.out.println("Repartidor:\n");
		
	}
	
	public static void Main(String args[]) {
		int opcion=0;
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
				MenuCliente();
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
