
public class Fecha implements Comparable<Fecha>{
	private int año;
	private int mes;
	private int dia;
	private int hora, min;
	
	public Fecha (int año, int mes, int dia, int hora, int min) {
		this.año = año;
		this.mes = mes;
		this.dia = dia;
		this.hora = hora;
		this.min = min;
	}
	
	public Fecha (int año, int mes, int dia) {
		this(año, mes, dia, 0, 0);
	}
	
	public Fecha (Fecha fecha) {
		this(fecha.año, fecha.mes, fecha.dia, fecha.hora, fecha.min);
	}
	
	public int getAño() { return año; }
	
	public int getMes() { return mes; }
	
	public int getDia() { return dia; }
	
	public int getHora() { return hora; }
	
	public int getMin() { return min; }
	
	public String toString() { return (año + "/" + mes + "/" + dia + " " + hora + ":" + min); }
	
	public int compareTo(Fecha comp) {
		if (año == comp.año) {
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
			if (año > comp.año) return 1;
			else return -1;
		}
	}

	
}
