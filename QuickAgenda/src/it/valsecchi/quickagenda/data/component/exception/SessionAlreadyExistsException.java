package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.ElementType;
import it.valsecchi.quickagenda.data.component.Session;

/**
 * Classe che rappresenta l'eccezione che viene lanciata quando una Session
 * esiste già.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class SessionAlreadyExistsException extends
		ElementAlreadyExistsException {

	private static final long serialVersionUID = -4311994612603169709L;

	/**
	 * Il costruttore dell'eccezione richiede il costumer soggetto
	 * dell'eccezione
	 */
	public SessionAlreadyExistsException(Session session) {
		// si richiama il costruttore della classe padre
		super(ElementType.Session, session);
		// si imposta il messaggio
	}

	/** Costruttore che accetta un messaggio dall'utente */
	public SessionAlreadyExistsException(Session session, String mess) {
		// si richiama il costruttore della classe padre
		super(ElementType.Session, session, mess);
	}

	/** Costruttore che richiede l'hash non l'oggetto*/
	public SessionAlreadyExistsException( String hash) {
		// si richiama il costruttore della classe padre
		super(ElementType.Session, hash);
	}
	
	/** Metodo che restituisce la Session soggetto dell'eccezione */
	public Session getSession() {
		return (Session) object;
	}
}
