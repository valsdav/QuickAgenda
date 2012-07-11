package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.ElementType;

/**
 * Classe che rappresenta l'eccezione che viene lanciata quando non viene
 * trovato un elemento nei dati.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class ElementNotFoundException extends Exception {

	private static final long serialVersionUID = -6341177253222623699L;
	protected ElementType type;
	protected Object object;
	protected String hash = "";

	public ElementNotFoundException(ElementType _type, Object _object) {
		super("L'elemento di tipo :" + _type.toString()
				+ " non è stato trovato nei dati.");
		// si memorizza il tipo
		type = _type;
		// si memorizza l'oggetto
		object = _object;
	}

	/** Costruttore dell'eccezione con messaggio fornito dall'utente */
	public ElementNotFoundException(ElementType _type, Object _object,
			String mess) {
		super(mess);
		// si memorizza il tipo
		type = _type;
		// si memorizza l'oggetto
		object = _object;
	}

	/** Costruttore che non richiede l'oggetto ma l'hash*/
	public ElementNotFoundException(ElementType _type, String _hash) {
		super("L'elemento con hash: " + _hash + " non è stato trovato nei dati.");
		// si memorizza il tipo
		type = _type;
		// si memorizza l'oggetto
		object = null;
		//si memorizza l'hash
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
