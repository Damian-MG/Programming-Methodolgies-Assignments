
public class Fecha implements Comparable<Fecha>{
	private int a�o;
	private int mes;
	private int dia;
	private int hora, min;
	
	public Fecha (int a�o, int mes, int dia, int hora, int min) {
		this.a�o = a�o;
		this.mes = mes;
		this.dia = dia;
		this.hora = hora;
		this.min = min;
	}
	
	public Fecha (int a�o, int mes, int dia) {
		this(a�o, mes, dia, 0, 0);
	}
	
	public Fecha (Fecha fecha) {
		this(fecha.a�o, fecha.mes, fecha.dia, fecha.hora, fecha.min);
	}
	
	public int getA�o() { return a�o; }
	
	public int getMes() { return mes; }
	
	public int getDia() { return dia; }
	
	public int getHora() { return hora; }
	
	public int getMin() { return min; }
	
	public String toString() { return (a�o + "/" + mes + "/" + dia + " " + hora + ":" + min); }
	
	public int compareTo(Fecha comp) {
		if (a�o == comp.a�o) {
			if (mes == comp.mes) {
				if (dia == comp.dia) return 0;
				else {
					if (dia > comp.dia) return 1;
					else return -1;
				}
			}
			else {
				if (mes > comp.mes) return 1;
				else return -1;
			}
		}
		else {
			if (a�o > comp.a�o) return 1;
			else return -1;
		}
	}

	
}
