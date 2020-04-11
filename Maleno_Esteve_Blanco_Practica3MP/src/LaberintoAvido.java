
public class LaberintoAvido {
	static String [][] labe;
	static char[][] recorrido;
	static int filaMAX;
	static int colMAX;
	static int filaIni;
	static int colIni;
	static int filaFinal;
	static int colFinal;
	static boolean end=false;
	static boolean no_option=false;

	public LaberintoAvido(int filaMAX, int colMAX,int filaIni, int colIni, int filaFinal, int colFinal, String[][] tablaVal, char[][] tablaMov) {
		LaberintoAvido.filaMAX = filaMAX;
		LaberintoAvido.colMAX = colMAX;
		LaberintoAvido.filaIni = filaIni;
		LaberintoAvido.colIni = colIni;
		LaberintoAvido.filaFinal = filaFinal;
		LaberintoAvido.colFinal = colFinal;
		LaberintoAvido.labe = tablaVal;
		LaberintoAvido.recorrido = tablaMov;
	}
	
	public void solucionar() {
		int puntos = calcular(filaIni, colIni, 0);
		move(filaIni, colIni, puntos);
	}
	
	public static void move(int i, int j, int puntos) {
		while (!end && !no_option) {	
			if(i==filaFinal && j==colFinal) {
				System.out.println("Felicidades, has completado el laberinto");
				System.out.println("Fila final: " + i + " Columna final: " + j);
				System.out.println("Solucion: " + puntos + " puntos");
				recorrido[i][j]='F';
				for (int x = 0; x<LaberintoAvido.filaMAX; x++) {
					for (int y = 0; y<LaberintoAvido.colMAX; y++) {
						System.out.printf("%s ", recorrido[x][y]);
					}
					System.out.println();
				}
				end=true;
			}else {
				int result[]= {-2, -2, -2, -2};
				if (j!=colMAX-1 && labe[i][j+1].compareTo("NA")!=0 && recorrido[i][j+1]=='N') {
					 result[0]=calcular(i, j+1, puntos);
				}
				if(j!=0 && labe[i][j-1].compareTo("NA")!=0 && recorrido[i][j-1]=='N') {
					result[1]=calcular(i, j-1, puntos);
				}
				if(i!=filaMAX-1 && labe[i+1][j].compareTo("NA")!=0 && recorrido[i+1][j]=='N') {
					result[2]=calcular(i+1, j, puntos);
				}
				if(i!=0 && labe[i-1][j].compareTo("NA")!=0 && recorrido[i-1][j]=='N') {
					result[3]=calcular(i-1, j, puntos);
				}
				int max = maximo(result);
				if(!no_option)
					switch (max) {
						case 0: 
							recorrido[i][j]='V';
							move(i, j+1, result[0]);
							no_option=false;
							recorrido[i][j+1]='X';
							break;
						case 1: 
							recorrido[i][j]='V';
							move(i, j-1, result[1]);
							no_option=false;
							recorrido[i][j-1]='X';
							break;
						case 2: 
							recorrido[i][j]='V';
							move(i+1, j, result[2]);
							no_option=false;
							recorrido[i+1][j]='X';
							break;
						case 3: 
							recorrido[i][j]='V';
							move(i-1, j, result[3]);
							no_option=false;
							recorrido[i-1][j]='X';
							break;
						default: end = true;
							break;
					}
			}
		}
	}
	
	private static int calcular(int i, int j, int puntos) {
		char op = labe[i][j].charAt(0);
		switch (op) {
			case '+': puntos += Character.getNumericValue(labe[i][j].charAt(1));
				break;
			case '-': puntos -= Character.getNumericValue(labe[i][j].charAt(1));
				break;
			case '*': puntos *= Character.getNumericValue(labe[i][j].charAt(1));
				break;
			case '/': puntos /= Character.getNumericValue(labe[i][j].charAt(1));
				break;
		}
		if (puntos<0) {
			return -1;
		}else {
			return puntos;
		}
	}
	
	private static int maximo(int[] tabla) {
		int max = 0;
		no_option=true;
		if (tabla[0]>=0) {
			max = 0;
			no_option=false;
		}
		if (tabla[1]>tabla[max]) {
			max = 1;
			no_option=false;
		}
		if(tabla[2]>tabla[max]) {
			max = 2;
			no_option=false;
		}
		if(tabla[3]>tabla[max]) {
			max = 3;
			no_option=false;
		}
		return max;
	}
}
