package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.Session;

/**
 * Classe che rappresenta l'eccezione che viene lanciata quando non viene
 * trovata una session nei dati.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class SessionNotFoundException extends ElementNotFoundException {

	private static final long serialVersionUID = 6170844877380378786L;

	/** Costruttore che richiede un oggetto Session soggetto dell'eccezione */
	public SessionNotFoundException(Session session) {
		// Si chiama il costruttore della classe base
		super(ElementType.Session, session);
		// si imposta il messaggio
	}
	
	/** Costruttore dell'eccezione con messaggio fornito dall'utente*/
	public SessionNotFoundException(Session session, String mess){
		// si chiama il costruttore della classe base
		super(ElementType.Session,session,mess);
	}
	
	/** Costruttore che richiede l'hash non l'oggetto*/
	public SessionNotFoundException(String hash){
		// si chiama il costruttore della classe base
		super(ElementType.Session,hash);
	}
	
	/** Metodo che restituisce la sessione soggetto dell'eccezione*/
	public Session getSession(){
		return (Session)object;
	}
}
