package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.ElementType;

/**
 * Classe che rappresenta l'eccezione che viene lanciata quando si cerca di
 * aggiungere un elemento che esiste già.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class ElementAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 2298554190876047352L;
	protected ElementType type;
	protected Object object;
	protected String hash;

	/**
	 * L'eccezione richiede il tipo dell'elemento salvato e l'oggetto
	 * 
	 * @param _type
	 * @param object
	 */
	public ElementAlreadyExistsException(ElementType _type, Object _object) {
		// si crea il messaggio
		super("L'elemento di tipo :" + _type.toString()
				+ " è già presente nei dati.");
		// si memorizza il tipo
		type = _type;
		// si memorizza l'oggetto
		object = _object;
	}

	/** Costruttore dell'eccezione con messaggio fornito dall'utente */
	public ElementAlreadyExistsException(ElementType _type, Object _object,
			String mess) {
		super(mess);
		// si memorizza il tipo
		type = _type;
		// si memorizza l'oggetto
		object = _object;
	}

	/** Costruttore che non richiede l'oggetto */
	public ElementAlreadyExistsException(ElementType _type, String _hash) {
		super("L'elemento con hash: " + _hash +" è già presente nei dati.");
		// si memorizza il tipo
		type = _type;
		// si memorizza l'oggetto
		object = null;
		//si imposta l'hash
		hash = _hash;
	}
	
	public Object getObject() {
		return object;
	}

	public ElementType getType() {
		return type;
	}

	public String getHash() {
		return hash;
	}

}
