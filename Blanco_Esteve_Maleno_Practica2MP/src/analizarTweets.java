import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class analizarTweets {
	
	public static void main(String[] args) throws FileNotFoundException {
		int numLinies=7; 	
		long tempIni, tempFin;
		TADEtiquetas lista = null;
		
		int opcion = 0;
		while (lista == null) {
			System.out.println("Indica que implementación quieres: 1:Lista estática  2:Lista dinámica 3:Doble lista dinámica");
			Scanner scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch (opcion){
				case 1: lista = new StaticList(); break;
				case 2: lista = new DynamicList(); break;
				case 3: lista = new dynamicHashtagList(); break;
				default: System.out.println("Introduce un valor adecuado"); break;
			}
			scanner.close();
		}
		
		System.out.println("Leyendo fichero:");
		tempIni = System.currentTimeMillis();
		String[] linies=llegirLiniesFitxer(numLinies);
		for (int i=0; i<linies.length; i++) { 
			StringTokenizer st = new StringTokenizer(linies[i], " ");
				StringTokenizer aux = new StringTokenizer(st.nextToken(), "/");
				int año = Integer.parseInt(aux.nextToken());
				int mes = Integer.parseInt(aux.nextToken());
				int dia = Integer.parseInt(aux.nextToken());
					StringTokenizer horaAux = new StringTokenizer(st.nextToken(), ":");
					int hora = Integer.parseInt(horaAux.nextToken());
					int min = Integer.parseInt(horaAux.nextToken());
				Fecha fechaaux = new Fecha (año, mes, dia, hora, min);
			String user = st.nextToken();
			String palabra;
			String tweet = palabra = st.nextToken();
			String[] etiqueta = new String [72]; 
			int iEtiqu = 0;
			if (palabra.charAt(0) == '#') {
				etiqueta[iEtiqu] = palabra;
				iEtiqu++;
			}
			while (st.hasMoreTokens()) {
				palabra = st.nextToken();
				tweet = tweet.concat(" " + palabra);
				if (palabra.charAt(0) == '#') {
					etiqueta[iEtiqu] = palabra;
					iEtiqu++;
				}
			}		
			
			int numEtiq = 0;
			String a;
			while (numEtiq<iEtiqu) {
				a = etiqueta[numEtiq];
				lista.insert(a, fechaaux, user, tweet);
				numEtiq++;
			}
		}
		tempFin = System.currentTimeMillis();
		System.out.println("Tiempo: " + (tempFin-tempIni) +"ms");
		
		
		System.out.println("\nImprimiendo lista:");
		tempIni = System.currentTimeMillis();
		System.out.println(lista.toString());
		tempFin = System.currentTimeMillis();
		System.out.println("Tiempo: " + (tempFin-tempIni) +"ms");
		
		//Esta parte es el juego de pruebas, descomentar y comentar lo que se quiera/no se quiera comprobar
		
		System.out.println("\nPrueba eliminar etiqueta X15,");
		tempIni = System.currentTimeMillis();
		lista.removeEtiqueta("#etiqX15,");
		System.out.println(lista.toString());
		tempFin = System.currentTimeMillis();
		System.out.println("Tiempo: " + (tempFin-tempIni) +"ms");
		//Se eliminan 2 nodos, los de X15,
		
		System.out.println("\nPrueba eliminar por fecha 10/15");
		tempIni = System.currentTimeMillis();
		lista.removeFecha(new Fecha(2019, 10, 15));
		System.out.println(lista.toString());
		tempFin = System.currentTimeMillis();
		System.out.println("Tiempo: " + (tempFin-tempIni) +"ms");
		//Se eliminan 4 nodos, los del 2019, 10, 15
		
		System.out.println("\nPrueba ver los usuarios que usan una etiqueta X3");
		tempIni = System.currentTimeMillis();
		String[] usuariosEtiqueta = lista.UsuariosEtiqueta("#etiqX3");
		int index = 0;
		while ((index<usuariosEtiqueta.length) && (usuariosEtiqueta[index] != null)) {
			System.out.printf("%s ", usuariosEtiqueta[index]);
			index++;
		}
		tempFin = System.currentTimeMillis();
		System.out.println("\nTiempo: " + (tempFin-tempIni) +"ms");
		//Debería quedar como resultado @user3 y @user1
		
		System.out.println("\nPrueba imprimir tweets con un dia y una etiqueta: X3 y 10/26");
		tempIni = System.currentTimeMillis();
		String[] listaEtiquetaDia = lista.ListaEtiquetaDia("#etiqX3", new Fecha(2019, 10, 26, 0, 0));
		index = 0;
		while ((index<listaEtiquetaDia.length) && (listaEtiquetaDia[index] != null)) {
			System.out.printf("%s\n", listaEtiquetaDia[index]);
			index++;
		}
		tempFin = System.currentTimeMillis();
		System.out.println("Tiempo: " + (tempFin-tempIni) +"ms");
		
		System.out.println("\nPrueba etiqueta mas usada");
		tempIni = System.currentTimeMillis();
		System.out.println(lista.EtiqMasUsada());
		tempFin = System.currentTimeMillis();
		System.out.println("Tiempo: " + (tempFin-tempIni) +"ms");
		
		//System.out.println("\n"+lista.toString());
		
		System.out.println("\nPrueba ver las etiquetas que usa un usuario (user1): (Recordar que se han eliminado el nodos de las etiqueta X15,)");
		tempIni = System.currentTimeMillis();
		String[] etiquetasUsuario = lista.EtiquetasUsuario("@user1");
		index = 0;
		while ((index<etiquetasUsuario.length) && (etiquetasUsuario[index] != null)) {
			System.out.printf("%s ", etiquetasUsuario[index]);
			index++;
		}
		tempFin = System.currentTimeMillis();
		System.out.println("\nTiempo: " + (tempFin-tempIni) +"ms");
		
		System.out.println("\nPrueba ver cuantos tweets tiene un usuario (user1)");
		tempIni = System.currentTimeMillis();
		System.out.println("El usuario 1 tiene " + lista.TweetsUsuario("@user1") + " tweets");
		tempFin = System.currentTimeMillis();
		System.out.println("Tiempo: " + (tempFin-tempIni) +"ms");
		
	}
	
	private static String[] llegirLiniesFitxer(int numLin) throws FileNotFoundException {
		String[] result;
		if (numLin<0) numLin=0; 
		result=new String[numLin];
		Scanner f=new Scanner(new File("Valors.csv"));
		for (int i=0; i<numLin; i++) {
			result[i]=f.nextLine();
		}
		f.close();
		return result;
	}
	
}