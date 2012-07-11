package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.ElementType;

/**
 * Classe che rappresenta l'eccezione lanciata quando si tenta di utilizzare un
 * id già presente.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class IDAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 5581183550670962768L;
	private ElementType type;
	private String ID;
	private Object object = null;

	/**
	 * Costruttore che richiede il tipo di elemento, l'id e il messaggio
	 * dell'utente
	 */
	public IDAlreadyExistsException(ElementType _type, String id, String mess) {
		super(mess);
		// si memorizza il tipo
		type = _type;
		// si memorizza l'ID
		ID = id;
	}

	/**
	 * Costruttore che non richiede un messaggio dall'utente e usa quello
	 * predefinito
	 */
	public IDAlreadyExistsException(ElementType _type, String id) {
		super("L'ID del tipo :" + _type.toString()
				+ " è già presente nei dati.");
		// si memorizza il tipo
		type = _type;
		// si memorizza l'ID
		ID = id;
	}

	/**
	 * Costruttore che permette anche di salvare l'oggetto che possiede l'id
	 * passato come parametro
	 */
	public IDAlreadyExistsException(ElementType _type, String id, Object _object) {
		super("L'ID del tipo :" + _type.toString()
				+ " è già presente nei dati.");
		// si memorizza il tipo
		type = _type;
		// si memorizza l'ID
		ID = id;
		// si memorizza l'oggetto
		object = _object;
	}

	public ElementType getType() {
		return type;
	}

	public String getID() {
		return ID;
	}

	public Object getObject() {
		return object;
	}
}
