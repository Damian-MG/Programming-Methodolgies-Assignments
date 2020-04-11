import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LaberintoMain {
	public static void main(String[] args) throws FileNotFoundException {
		
		long tempIni, tempFin;
		int opcion = 0;
		Scanner f = null;
		boolean seleccion = false;
		while (seleccion == false) {
			System.out.println("Indica el mapa a resolver: 1:Valors  2:Lab2 3:Lab3 4:Lab4 ");
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch (opcion){
				case 1: 
					f=new Scanner(new File("Valors.csv"));
					seleccion = true;
				break;
				case 2: 
					f=new Scanner(new File("Laberinto2.csv"));
					seleccion = true;
				break;
				case 3: 
					f=new Scanner(new File("Laberinto3.csv"));
					seleccion = true;
				break;
				case 4: 
					f=new Scanner(new File("Laberinto4.csv"));
					seleccion = true;
				break;
				default: System.out.println("Introduce un valor adecuado"); break;
			}
		}
		System.out.println("Leyendo fichero");
		tempIni = System.currentTimeMillis();
		String linea = f.nextLine();
		StringTokenizer st = new StringTokenizer(linea, ", ");
		int fila = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int xIni = Integer.parseInt(st.nextToken());
		int yIni = Integer.parseInt(st.nextToken());
		int xFin = Integer.parseInt(st.nextToken());
		int yFin = Integer.parseInt(st.nextToken());
		int i = 0, y;
		String[][] tablaValores = new String[fila][col];
		char[][] tablaRecorrido = new char[fila][col];
		while (i<fila) {
			linea = f.nextLine();
			st = new StringTokenizer(linea, ", ");
			y = 0;
			while (st.hasMoreTokens()) {
				String valor = st.nextToken();
				tablaValores[i][y] = valor;
				tablaRecorrido[i][y] = 'N';		//N es el caracter de posición no recorrida
				y++;
			}
			i++;
		}
		tempFin = System.currentTimeMillis();
		System.out.println("Tiempo tardado en construir el laberinto: " + (tempFin-tempIni) + " milisegundos");
		//tablaRecorrido[xIni][yIni] = 'Y';	//Y es el caracter de posición recorrida
		opcion = 0;
		System.out.println("Laberinto seleccionado: ");
		System.out.printf(fila + " " + col + " " + xIni + " " + yFin + " " + xFin + " " + yFin + "\n");
		for (i = 0; i<fila; i++) {
			for (y = 0; y<col; y++) {
				System.out.printf("%s ",tablaValores[i][y]);
			}
			System.out.printf("\n");
		}
		seleccion = false;
		while (seleccion == false) {
			System.out.println("\nIndica que tipo de algoritmo de resolución quieres: 1:Algoritmo ávido  2:Algoritmo exhaustivo");
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch (opcion){
				case 1: 
					tempIni = System.currentTimeMillis();
					LaberintoAvido busquedaAvida = new LaberintoAvido (fila, col, xIni, yIni, xFin, yFin, tablaValores, tablaRecorrido);
					busquedaAvida.solucionar();
					seleccion = true;
				break;
				case 2: 
					tempIni = System.currentTimeMillis();
					Lab_backtraking busquedaExhaustiva = new Lab_backtraking (fila, col, xIni, yIni, xFin, yFin, tablaValores, tablaRecorrido);
					busquedaExhaustiva.solucionar();
					seleccion = true;
				break;
				default: System.out.println("Introduce un valor adecuado"); break;
			}
		}
		tempFin = System.currentTimeMillis();
		f.close();
		System.out.println("Tiempo tardado en recorrer el laberinto: " + (tempFin-tempIni) + " milisegundos");
	}

}
