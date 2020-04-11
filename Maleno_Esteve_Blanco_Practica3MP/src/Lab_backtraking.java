
public class Lab_backtraking {
	static int[] movFila= {1,0,-1,0};
	static int[] movCol= {0,1,0,-1};
	static int totalCaminos = 0;
	String [][] tabla;
	static char[][] tablaMov;
	static int filaMAX;
	static int colMAX;
	static int filaIni;
	static int colIni;
	static int filaFinal;
	static int colFinal;
	static int numSolucions=0;
	static boolean acabat=false;
	
	public Lab_backtraking(int filaMAX, int colMAX,int filaIni, int colIni, int filaFinal, int colFinal, String[][] tablaVal, char[][] tablaMov) {
		Lab_backtraking.filaMAX = filaMAX;
		Lab_backtraking.colMAX = colMAX;
		Lab_backtraking.filaIni = filaIni;
		Lab_backtraking.colIni = colIni;
		Lab_backtraking.filaFinal = filaFinal;
		Lab_backtraking.colFinal = colFinal;
		this.tabla = tablaVal;
		Lab_backtraking.tablaMov = tablaMov;
	}
	
	public void solucionar() {
		System.out.printf("\nAlgoritmo exhaustivo todos los caminos:\n");
		LabTodosCaminos(filaIni, colIni, tabla, tablaMov, 0);
		System.out.printf("Caminos: " + totalCaminos);
		System.out.printf("\nAlgoritmo exhaustivo un camino:\n");
		LabUnCamino(filaIni, colIni, tabla, tablaMov, 0);
	}

	private static void LabTodosCaminos(int f, int c, String[][] solucio, char[][] movimientos, int puntos) {
		int nf, nc;
		int pos;
		puntos = operacion(puntos, solucio[f][c]);
		movimientos[f][c] = 'V';
		if ((c != Lab_backtraking.colFinal) || (f != Lab_backtraking.filaFinal)) {
			pos=0;
			while (pos<4) {
				nf=f+movFila[pos];
				nc=c+movCol[pos];
				if (esFactible(solucio, movimientos, nf, nc)) {	
					LabTodosCaminos(nf, nc,solucio, movimientos, puntos);
					movimientos[nf][nc]='N';
				}
				pos++;
			}
		} else {
				movimientos[f][c]='F';
				System.out.printf("Solucion posible: " + puntos + "\n");
				for (int i = 0; i<Lab_backtraking.filaMAX; i++) {
					for (int y = 0; y<Lab_backtraking.colMAX; y++) {
						System.out.printf("%s ",movimientos[i][y]);
					}
					System.out.printf("\n");
				}
				totalCaminos++;
		}
	}

	private static void LabUnCamino(int f, int c, String[][] solucio, char[][] movimientos, int puntos) {
		int nf, nc;
		int pos;
		puntos = operacion(puntos, solucio[f][c]);
		movimientos[f][c] = 'V';
		if ((c != Lab_backtraking.colFinal) || (f != Lab_backtraking.filaFinal)) {
			if (!acabat) {
				pos=0;
				while ((pos<4)&&!acabat) {
					nf=f+movFila[pos];
					nc=c+movCol[pos];
					if (esFactible(solucio, movimientos, nf, nc)) {	
						LabUnCamino(nf, nc,solucio, movimientos, puntos);
						movimientos[nf][nc]='N';
					}
					pos++;
				}
			}
		} else {
			if (!acabat) {
				acabat = true;
				movimientos[f][c]='F';
				System.out.printf("Solucion posible: " + puntos + "\n");
				for (int i = 0; i<Lab_backtraking.filaMAX; i++) {
					for (int y = 0; y<Lab_backtraking.colMAX; y++) {
						System.out.printf("%s ",movimientos[i][y]);
					}
					System.out.printf("\n");
				}
				totalCaminos++;
			}
		}
	}
	
	private static boolean esFactible(String[][] solucio, char[][] visitado, int f, int c) {
		if (f>=0 && f<Lab_backtraking.filaMAX && c>=0 && c<Lab_backtraking.colMAX && (solucio[f][c].compareTo("NA") != 0))
			return (visitado[f][c] == 'N' || visitado[f][c] == 'F');
		return false;
	}
	
	private static int operacion (int puntos, String operar) {
		char operador = operar.charAt(0);
		int num = Character.getNumericValue(operar.charAt(1));
		if (operador == '+') puntos = puntos + num;
		else if (operador == '-') puntos = puntos - num;
		else if (operador == '*') puntos = puntos * num;
		else if (operador == '/') puntos = puntos / num;
		return puntos;
	}
}
