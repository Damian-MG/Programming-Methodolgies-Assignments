
class StaticNode {
	Fecha fecha;
	String etiqueta;
	String usuario;
	String mensaje;
	
	StaticNode(Fecha fecha, String etiqu, String user, String mensaje){
		this.fecha = fecha;
		etiqueta = etiqu;
		usuario = user;
		this.mensaje = mensaje;
	}	
	
	StaticNode(StaticNode aux){
		this.fecha = new Fecha(aux.fecha);
		this.etiqueta = aux.etiqueta;
		this.usuario = aux.usuario;
		this.mensaje = aux.mensaje;
	}	
	
	public StaticNode copy() {
		
		return (new StaticNode(this));
	}
}


public class StaticList implements TADEtiquetas {
	
	private final int MAX = 10;
	StaticNode[] lista;
	int numElems;
	
	public StaticList() {
		lista=new StaticNode[MAX];
		numElems=0;
	}

	
	public void insert(String etiqu, Fecha fecha, String user, String mensaje) {
		if (!isFull()) {		//si la lista no esta llena
			lista[numElems] = new StaticNode(fecha,etiqu,user,mensaje);	//añadir nueva etiqueta
			numElems++;
		}
		else  {	//si esta llena, redimensionar la lista
			StaticNode[] listaAux = new StaticNode[lista.length+10];	//lista auxiliar para redimensionar
			for (int i=0 ; i<numElems; i++)
				listaAux[i] = lista[i].copy();	//copiamos toda la lista
			lista = listaAux;	//cambiar puntero a la variable
			lista[numElems] = new StaticNode(fecha,etiqu,user,mensaje);	//añadir nueva etiqueta
			numElems++;
		}
		
	}

	public void removeEtiqueta(String etiqueta) {
		if (!isEmpty()) {	//si la lista no está vacia
			while(posicionEtiqueta(etiqueta)!=-1) {
				if (numElems == 1) numElems=0;	//si solo hay 1 elemento, ponemos a 0 el numero de elementos
				else {
					int EtiqActual = posicionEtiqueta(etiqueta);	//obtenemos posicion a borrar
					for(int i = EtiqActual;i<numElems-1;i++)		
						lista[i] = lista[i+1].copy();		//copiamos los valores de la posicion siguiente en la posicion anterior
					numElems--;		//por ultimo decrementamos numero de elementos
				}		
			}
		}
	}

	public void removeFecha(Fecha fecha) {
		if (!isEmpty()) {	//si no esta vacia
			for(int i = 0;i<numElems;i++) {		
				if(lista[i].fecha.compareTo(fecha)==0) {	//si un tweet coincide con la fecha
					for(int j=i; j<numElems-1; j++) {
						lista[j]=lista[j+1];
					}
					i--;
					numElems--;
				}
			}
		}
	}

	public String[] UsuariosEtiqueta(String etiqueta) {
		String[] ret=new String[numElems]; 
		int cont=0;
		for (int i=0; i<numElems;i++) {
			if(lista[i].etiqueta.compareTo(etiqueta)==0) {
				int j = 0;
				while(j<cont && ret[j].compareTo(lista[i].usuario)!=0) j++;
				if(j==cont) {
					ret[cont]=lista[i].usuario;
					cont++;
				}
			}
		}
		return ret;
	}

	public String[] ListaEtiquetaDia(String etiqueta, Fecha fecha) {
		if(isEmpty()) {
			return null;
		}
		else {
			String[] ret=new String[numElems]; 
			int cont=0;
			for (int i=0; i<numElems;i++)
				if(lista[i].fecha.compareTo(fecha)==0 && lista[i].etiqueta.compareTo(etiqueta)==0) {
					int j = 0;
					while(j<cont && ret[j].compareTo(lista[i].mensaje)!=0) j++;
					if(j==cont) {
						ret[cont]=lista[i].mensaje;
						cont++;
					}
				}	
			return ret;
		}
	}

	public String EtiqMasUsada() {
		if(isEmpty()) {
			return null;
		}
		else {
			String[] aux=new String[numElems]; int cont=0;
			int[] rep=new int[numElems]; for(int x : rep) rep[x]=0;
			
			for(int i=0; i<numElems;i++) {
				int j=0;
				while(j<cont && aux[j].compareTo(lista[i].etiqueta) != 0) j++;
				if(j==cont) {
					aux[cont]=lista[i].etiqueta;
					rep[cont]++;cont++;
				}
				else {rep[j]++;}
			}
			int max=rep[0];int pos=0;
			for(int i=1;i<cont;i++) {
				if(rep[i]>max) {
					max=rep[i];
					pos=i;
				}
			}
			return aux[pos];
		}
	}

	public String[] EtiquetasUsuario(String usuario) {
		String[] ret=new String[numElems]; 
		int cont=0;
		for (int i=0; i<numElems;i++)
			if(lista[i].usuario.compareTo(usuario)==0) {
				int j = 0;
				while(j<cont && ret[j].compareTo(lista[i].etiqueta)!=0) j++;
				if(j==cont) {
					ret[cont]=lista[i].etiqueta;
					cont++;
				}
			}	
		return ret;
	}

	public int TweetsUsuario(String usuario) { 
		String[] ret=new String[numElems]; 
		int cont=0;
		for (int i=0; i<numElems;i++)
			if(lista[i].usuario.compareTo(usuario)==0) {
				int j = 0;
				while(j<cont && ret[j].compareTo(lista[i].mensaje)!=0) j++;
				if(j==cont) {
					ret[cont]=lista[i].mensaje;
					cont++;
				}
			}	
		return cont;
	}
	/*
	 * Comprueba en qué posicion se encuetra la etiqueta
	 * param: La etiqueta a buscar
	 * return: -1 si no se encuentra; la posicion si se encuentra
	 */
	public int posicionEtiqueta(String etiq) {		
		int i = 0;
		while ((i<numElems) && (etiq.compareTo(lista[i].etiqueta)!=0)) {
			i++;
		}
		if (i==numElems) return -1;
		else return i;
	}

	public boolean isEmpty() {
		return numElems == 0;
	}
	
	public boolean isFull() {
		return numElems == lista.length;
	}
	
	public String toString() {
		String ret="";
		for(int i = 0;i<numElems;i++) {
			String aux = ("Posición " + i + 
					" Mi etiqueta es: " + lista[i].etiqueta + 
					": Fecha: " + lista[i].fecha +" "+ 
					lista[i].usuario + " " + 
					lista[i].mensaje + "\n");
			ret = ret + aux; 
		}
		return ret;
	}

}



