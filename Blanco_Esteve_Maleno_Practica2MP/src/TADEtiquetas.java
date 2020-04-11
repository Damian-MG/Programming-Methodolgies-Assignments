/*
 * Interfaz para definir la colecci�n de etiquetas
 */
public interface TADEtiquetas {
	
	/*
	 * A�ade un nuevo elemento al conjunto
	 * @param String etiqueta, Fecha fecha, String usuario, String mensaje
	 * @return la lista modificada con la nueva etiqueta a�adida
	 */
	void insert(String etiqu, Fecha fecha, String user, String mensaje);
	
	/*
	 * Elimina los datos de una etiqueta
	 * @param String etiqueta
	 * @return lista modificada sin la etiqueta
	 */
	void removeEtiqueta(String etiqueta);
	
	/*
	 * Borra los tweets que se han hecho un dia concreto
	 * @param Fecha fecha
	 * @return lista modificada sin los tweets en la fecha indicada
	 */
	void removeFecha(Fecha fecha);
	
	/*
	 * Retorna la lista de usuarios que han utilizado la etiqueta
	 * @param String etiqueta
	 * @return Una String[] con los usuarios que han usado la etiqueta
	 */
	String[] UsuariosEtiqueta(String etiqueta);
	
	/*
	 * Retorna una lista de tweets que han utilizado la etiqueta en un dia concreto
	 * @param String etiqueta, Fecha fecha
	 * @return Una String[] con los tweets hechos en la fecha indicada y que contienen la etiqueta
	 */
	String[] ListaEtiquetaDia(String etiqueta, Fecha fecha);
	
	/*
	 * Retorna la etiqueta m�s usada en todos los tweets
	 * @param
	 * @return la etiqueta que m�s se repite
	 */
	String EtiqMasUsada();
	
	/*
	 * Retorna una lista con todas las etiquetas que ha utilizado un usuario
	 * @param String usuario
	 * @return Una String[] con todas las etiquetas que ha usado el usuario
	 */
	String[] EtiquetasUsuario(String usuario);
	
	/*
	 * Retorna el numero de tweets que ha escrito un usuario
	 * @param String usuario
	 * @return Un int con el n�mero de veces que un usuario ha tweeteado
	 */
	int TweetsUsuario(String usuario);
	
	/*
	 * Retorna un booleano seg�n si el conjunto est� vacio o no
	 * @param 
	 * @return True si la lista est� vacia, False si no
	 */
	boolean isEmpty();
}
