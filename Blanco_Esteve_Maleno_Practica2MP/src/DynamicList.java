


class ListNode {
	Fecha fecha;
	String etiqueta;
	String usuario;
	ListNode nextNode;
	String mensaje;

	ListNode(Fecha fecha, String etiqu, String user, String mensaje) {
		this(fecha, etiqu, user, mensaje, null);
	}
	
	ListNode(Fecha fecha, String etiqu, String user,String mensaje, ListNode next) {
		this.fecha = fecha;
		etiqueta = etiqu;
		usuario = user;
		nextNode = next;
		this.mensaje = mensaje;
	}

	/*
	Fecha getFecha() {
		return fecha;
	}
	
	String[] getEtiquetas() {
		return etiquetas;
	}
	
	String getUsuario() {
		return usuario;
	}
	
	void setNext(ListNode next){
		nextNode = next;
	}

	ListNode getNextNode() {
		return nextNode;
	}*/
}



public class DynamicList implements TADEtiquetas{
	private int numElem;
	private ListNode firstNode; 

	
	public DynamicList() {
		numElem = 0;
	}

	
	public void insert(String etiqu, Fecha fecha, String user, String mensaje) {
		if (isEmpty()) {
			firstNode = new ListNode(fecha, etiqu, user, mensaje);
			numElem++;
		}
		else if (!existe(fecha, etiqu, user, mensaje)) {
			ListNode aux = firstNode;
			ListNode anterior = firstNode;
			while ((etiqu.compareTo(aux.etiqueta) >= 1) && (aux.nextNode != null)) { //Mientras el hashtag sea mayor (buscamos igual o menor){
				anterior = aux;
				aux = aux.nextNode;
			}
			ListNode nuevo = new ListNode(fecha, etiqu, user, mensaje, anterior.nextNode);	//Si tienen el mismo hashtag, se inserta antes de el
			anterior.nextNode = nuevo;
			numElem++;
		}
			
	}


	public void removeEtiqueta(String etiqueta){
		if (!isEmpty()) {
			ListNode ant  = firstNode;
			ListNode actual = ant.nextNode;
			
			while (actual != null) {	//Tratamos todos los nodos menos el primero
				if (actual.etiqueta.compareTo(etiqueta) == 0) {
					ant.nextNode = actual.nextNode;
					numElem--;
				}
				if (actual.etiqueta.compareTo(etiqueta) != 0) ant = actual;
				actual = actual.nextNode;
			}	
			//Ahora tratamos el primer nodo
			if (firstNode.etiqueta.compareTo(etiqueta) == 0) {
				firstNode = firstNode.nextNode;
				numElem--;
			}
		}
	}
	
	
	public void removeFecha(Fecha fecha){
		if (!isEmpty()) {
			ListNode ant  = firstNode;
			ListNode actual = ant.nextNode;
			
			while (actual != null) {	//Tratamos todos los nodos menos el primero
				if (actual.fecha.compareTo(fecha) == 0) {
					ant.nextNode = actual.nextNode;
					numElem--;
				}
				if (actual.fecha.compareTo(fecha) != 0) ant = actual;
				actual = actual.nextNode;
			}	
			//Ahora tratamos el primer nodo
			if (firstNode.fecha.compareTo(fecha) == 0) {
				firstNode = firstNode.nextNode;
				numElem--;
			}
		}
	}
	
	public String[] UsuariosEtiqueta(String etiqueta) {	//Retornar lista usuarios de la etiqueta
		if (!isEmpty()) {
			String[] aux = new String[numElem];
			int numElemAux = 0;	//Nº usuarios dentro de la lista de usuarios
			ListNode actual = firstNode;
			while ((actual != null) && (actual.etiqueta.compareTo(etiqueta) <= 0)) {
				if (actual.etiqueta.compareTo(etiqueta) == 0) {
					int i = 0;
					while ((i < numElemAux) && (aux[i].compareTo(actual.usuario) != 0)) {
						i++;
					}
					if (i == numElemAux) {
						aux[i] = actual.usuario;		
						numElemAux++;
					}
				}
				actual = actual.nextNode;
			}
			return aux;
		} else return null;
	}
	
	public String[] ListaEtiquetaDia(String etiqueta, Fecha fecha) {	//Listar los tweets de un dia con una etiqueta
		if (!isEmpty()) {
			String[] aux = new String[numElem];
			int numElemAux = 0;	//Nº usuarios dentro de la lista de usuarios
			ListNode actual = firstNode;
			while ((actual != null) && (actual.etiqueta.compareTo(etiqueta) <= 0)) {
				if ((actual.etiqueta.compareTo(etiqueta) == 0) && (actual.fecha.compareTo(fecha) == 0)) {
					aux[numElemAux] = actual.mensaje;
					numElemAux++;
				}
				actual = actual.nextNode;
			}
			return aux;
		} else return null;
	}
	
	public String EtiqMasUsada() {
		if (!isEmpty()) {
			String etiquetaAnt;
			String max = etiquetaAnt = firstNode.etiqueta; 
			ListNode actual = firstNode.nextNode;
			int numRep = 1, numRepMax = 1;			//Se empieza ya contabilizando la primera etiqueta
			while (actual != null) {
				if (actual.etiqueta.compareTo(etiquetaAnt) == 0) {	//Esta ordenado por hashtag, si el anterior es diferente del actual, dejar de contar para ese hash
					numRep++;
				}
				else {	//Si cambia la etiqueta, contabilizamos el numero de repeticiones de la etiqueta anterior
					if (numRep > numRepMax) {
						numRepMax = numRep;
						max = etiquetaAnt;
					}
					numRep = 1;
				}
				etiquetaAnt = actual.etiqueta;
				actual = actual.nextNode;
			}
			if (numRep > numRepMax) {
				numRepMax = numRep;
				max = etiquetaAnt;
			}
			return max;
		}
		else return null;
	}
	
	public String[] EtiquetasUsuario(String usuario) {	//Retorna las etiquetas que ha usado un usuario
		if (!isEmpty()) {
			String[] aux = new String[numElem];
			int numElemAux = 0;	//Nº usuarios dentro de la lista de usuarios
			ListNode actual = firstNode;
			while (actual != null) {
				if (actual.usuario.compareTo(usuario) == 0) {
					int i = 0;
					while ((i < numElemAux) && (aux[i].compareTo(actual.etiqueta) != 0)) {
						i++;
					}
					if (i == numElemAux) {
						aux[i] = actual.etiqueta;		
						numElemAux++;
					}
				}
				actual = actual.nextNode;
			}
			return aux;
		} else return null;
	}
	
	public int TweetsUsuario(String usuario) {
		if (!isEmpty()) {
			String[] auxLista = new String[numElem];
			int aux = 0;
			int numElemAux = 0;	//Nº usuarios dentro de la lista de tweets del usuario, si se repite el tweet no se cuenta
			ListNode actual = firstNode;
			while (actual != null) {
				if (actual.usuario.compareTo(usuario) == 0) {
					int i = 0;
					while ((i < numElemAux) && (auxLista[i].compareTo(actual.mensaje) != 0)) {
						i++;
					}
					if (i == numElemAux) {
						auxLista[i] = actual.mensaje;		
						numElemAux++;
						aux++;
					}
				}
				actual = actual.nextNode;
			}
			return aux;
		} else return 0;
	}
	
	public boolean existe(Fecha fecha, String etiqu, String user, String mensaje) {
		boolean existe = false;
		ListNode actual = firstNode;
		while ((actual != null) && (!existe)) {
			if ((actual.fecha.compareTo(fecha) == 0) && (actual.etiqueta.compareTo(etiqu) == 0) && (actual.usuario.compareTo(user) == 0) && (actual.mensaje.compareTo(mensaje) == 0)) {
				existe = true;
			}
			actual = actual.nextNode;
		}
		return existe;
	}
	
	
	public boolean isEmpty() {
		return firstNode == null;
	}
	
	public String toString() {
		String aux = "";
		ListNode a = firstNode;
		int i = 0;
		while (a != null) {
			String aux2 = ("Posición " + i + " Mi etiqueta es: " + a.etiqueta + ": Fecha: " + a.fecha +" "+ a.usuario + " " + a.mensaje + "\n");
			i++;
			aux = aux + aux2;
			a = a.nextNode;
		}
		return aux;
	}
	
}
