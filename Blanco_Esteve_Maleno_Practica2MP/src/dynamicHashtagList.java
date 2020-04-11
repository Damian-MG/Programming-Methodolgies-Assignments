class hashtagNode {

	String etiqueta;
	int cuantos_tweets;
	hashtagNode nextNode;
	tweetNode firstTweet;
	
	hashtagNode(String etiqueta, tweetNode tweet) {
		this.etiqueta = etiqueta;
		this.cuantos_tweets = 0;
		this.nextNode = null;
		this.firstTweet = tweet;
	}
	
	hashtagNode(String etiqueta, hashtagNode nextNode, tweetNode tweet) {
		this.etiqueta = etiqueta;
		this.cuantos_tweets = 0;
		this.nextNode = nextNode;
		this.firstTweet = tweet;
	}
	
}

class tweetNode {
	
	Fecha fecha;
	String etiqueta;
	String usuario;
	String mensaje;
	tweetNode nextTweetNode;


	tweetNode(Fecha fecha, String etiqueta, String usuario,String mensaje) {
		this.fecha = fecha;
		this.etiqueta = etiqueta;
		this.usuario = usuario;
		this.mensaje = mensaje;
		this.nextTweetNode = null;
	}
	
	tweetNode(Fecha fecha, String etiqueta, String usuario,String mensaje, tweetNode nextTweetNode) {
		this.fecha = fecha;
		this.etiqueta = etiqueta;
		this.usuario = usuario;
		this.mensaje = mensaje;
		this.nextTweetNode = nextTweetNode;
	}
}


public class dynamicHashtagList implements TADEtiquetas {

	
	int numElem;
	hashtagNode firstNode; 

	public dynamicHashtagList() {
		this.firstNode = null;
		this.numElem = 0;
		
	}

	public void insert(String etiqu, Fecha fecha, String user, String mensaje) { //Funciona
		
	if (firstNode == null) {
		tweetNode twNuevo = new tweetNode(fecha, etiqu, user, mensaje);
		firstNode = new hashtagNode(etiqu, twNuevo);
		firstNode.cuantos_tweets++;
		numElem++;
	}
	else {
		hashtagNode current = firstNode;
		if (current.etiqueta.compareTo(etiqu) == 0) {
			tweetNode currentTweet = current.firstTweet;
			if (currentTweet == null) {
				currentTweet = new tweetNode(fecha, etiqu, user, mensaje);
				current.cuantos_tweets++;
			}
			else {
				while(currentTweet.nextTweetNode != null) {
					currentTweet = currentTweet.nextTweetNode;	
				}
				currentTweet.nextTweetNode = new tweetNode(fecha, etiqu, user, mensaje);
				current.cuantos_tweets++;
			}
		}
		else {
			hashtagNode anterior = current;
			while ((current != null) && (current.etiqueta.compareTo(etiqu) != 0)) {
				anterior = current;
				current = current.nextNode;
			}
			if (current == null) {
				anterior.nextNode = new hashtagNode(etiqu, null, null);
				anterior.nextNode.firstTweet = new tweetNode(fecha, etiqu, user, mensaje);
				anterior.nextNode.cuantos_tweets++;
			}
			else {	//Si encuentra el nodo
				tweetNode currentTweet = current.firstTweet;
				if (currentTweet == null) {
					currentTweet = new tweetNode(fecha, etiqu, user, mensaje);
					current.cuantos_tweets++;
				}
				else {
					while(currentTweet.nextTweetNode != null) {
						currentTweet = currentTweet.nextTweetNode;	
					}
					currentTweet.nextTweetNode = new tweetNode(fecha, etiqu, user, mensaje);
					current.cuantos_tweets++;
				}
			}
		}
	}
}


	public void removeEtiqueta(String etiqueta) {    //Funciona
		
		if (firstNode.etiqueta.compareTo(etiqueta) == 0) {
			firstNode = firstNode.nextNode;
		}
		else 
		{
			hashtagNode anterior = firstNode;
			hashtagNode current = anterior.nextNode;
			while (current != null && current.etiqueta.compareTo(etiqueta) != 0) {
				anterior = current;
				current = current.nextNode;
			}
			
			if (current.etiqueta.compareTo(etiqueta) == 0) anterior.nextNode = current.nextNode;
		}
	}


	public void removeFecha(Fecha fecha) {						//Funciona		

		hashtagNode current = firstNode;
		while (current != null) {
				tweetNode Tweet = current.firstTweet;
				tweetNode anterior = null;
				
				while (Tweet != null) {
					if (Tweet.fecha.compareTo(fecha) == 0) {
						if (Tweet == current.firstTweet) {
							current.firstTweet = current.firstTweet.nextTweetNode;
							current.cuantos_tweets--;
						}
						else {
							anterior.nextTweetNode = Tweet.nextTweetNode;
							Tweet = anterior;
							current.cuantos_tweets--;
						}
					}
					anterior = Tweet;
					Tweet = Tweet.nextTweetNode;
				}
				current = current.nextNode;
			}
		}


	public String[] UsuariosEtiqueta(String etiqueta) {				//Funciona
		int contador_usuarios = 0;
		String[] usuarios_etiqueta = new String[100];
		
		hashtagNode current = firstNode;
		while ((current.etiqueta.compareTo(etiqueta) != 0) && (current != null)) {
			current = current.nextNode;
		}
		if (current == null) {
			usuarios_etiqueta = null;
		}
		else {
			tweetNode Tweet = current.firstTweet;
			while (Tweet != null) {
				int j=0;
				while (j<contador_usuarios && Tweet.usuario.compareTo(usuarios_etiqueta[j]) != 0) {
					j++;
				}
				if (j == contador_usuarios) {
					usuarios_etiqueta[contador_usuarios] = Tweet.usuario;
					contador_usuarios++;
				}
				Tweet = Tweet.nextTweetNode;
			}
		}
		return usuarios_etiqueta;
	}


	public String[] ListaEtiquetaDia(String etiqueta, Fecha fecha) {			//Funciona
		int contador_tweets = 0;
		String[] tweets_etiqueta_dia = new String[100];
		
		hashtagNode current = firstNode;
		while ((current.etiqueta.compareTo(etiqueta) != 0) && (current != null)) {
			current = current.nextNode;
		}
		if (current == null) {
			tweets_etiqueta_dia = null;
		}
		else {
			tweetNode Tweet = current.firstTweet;
			while (Tweet != null) {
				if (Tweet.fecha.compareTo(fecha) == 0) {
					tweets_etiqueta_dia[contador_tweets] = Tweet.mensaje;
					contador_tweets++;
				}
				Tweet = Tweet.nextTweetNode;
			}
		}
		return tweets_etiqueta_dia;
	}

	public String EtiqMasUsada() {			//Funciona
		int max = 0;
		String mas_usado = "";
		
		hashtagNode current = firstNode;
		while (current != null) {
			if (current.cuantos_tweets > max) {
				max = current.cuantos_tweets;
				mas_usado = current.etiqueta;
			}
			current = current.nextNode;
		}
		return mas_usado;
	}
	
	public String[] EtiquetasUsuario(String usuario) {   //Funciona
		int contador_etiquetas = 0;
		boolean found;
		String[] etiquetas_usuario = new String[100];
		
		hashtagNode current = firstNode;
			while (current != null) {
				found = false;
				tweetNode Tweet = current.firstTweet;
					
				while ((Tweet != null) && (!found)) {
					if (Tweet.usuario.compareTo(usuario) == 0) {
						found = true;
						etiquetas_usuario[contador_etiquetas] = Tweet.etiqueta;
						contador_etiquetas++;
					}
					Tweet = Tweet.nextTweetNode;
				}
				current = current.nextNode;
			}
		return etiquetas_usuario;
	}

	public int TweetsUsuario(String usuario) {
		int contador_mensajes = 0;
		String[] tweets_usuario = new String[100];
		
		hashtagNode current = firstNode;
		while (current != null) {
			tweetNode Tweet = current.firstTweet;
			while ((Tweet != null) ) {
				if (Tweet.usuario.compareTo(usuario) == 0) {
					int j=0;
					while (j<contador_mensajes && Tweet.mensaje.compareTo(tweets_usuario[j]) != 0) {
						j++;
					}
					if (j == contador_mensajes) {
						tweets_usuario[contador_mensajes] = Tweet.mensaje;
						contador_mensajes++;
					}
				}
				Tweet = Tweet.nextTweetNode;
			}
			current = current.nextNode;
		}
		return contador_mensajes;
	}

	public boolean isEmpty() {
		return firstNode == null;
	}
	
	public String toString() {
		String aux = "";
		int i = 0;
		int j = 0;
		hashtagNode current = firstNode;
		
		while (current != null) {
			tweetNode current_tweet = current.firstTweet;
			while (current_tweet != null){
				String aux2 = ("Pos " + j + "," +  i + " Mi etiqueta es: " + current_tweet.etiqueta + ": Fecha: " + current_tweet.fecha +" "+ current_tweet.usuario + " " + current_tweet.mensaje + "\n");
				i++;
				aux = aux + aux2;
				current_tweet = current_tweet.nextTweetNode;
			}
			i = 0;
			j++;
			current = current.nextNode;
		}
		return aux;
	}

	
}
