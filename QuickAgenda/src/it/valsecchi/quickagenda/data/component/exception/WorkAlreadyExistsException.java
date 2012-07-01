package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.ElementType;
import it.valsecchi.quickagenda.data.component.Work;

/**
 * Classe che rappresenta l'eccezione che viene lanciata quando un Work esiste
 * già.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class WorkAlreadyExistsException extends ElementAlreadyExistsException {

	private static final long serialVersionUID = 7012951607564714528L;

	/** Il costruttore dell'eccezione richiede il work soggetto dell'eccezione. */
	public WorkAlreadyExistsException(Work work) {
		super(ElementType.Work, work);
	}

	/** Costruttore che accetta un messaggio dall'utente */
	public WorkAlreadyExistsException(Work work, String mess) {
		// si richiama il costruttore della classe padre
		super(ElementType.Costumer, work, mess);
	}

	/** Costruttore che richiede l'hash non l'oggetto*/
	public WorkAlreadyExistsException(String hash) {
		// si richiama il costruttore della classe padre
		super(ElementType.Costumer,hash);
	}
	
	
	/** Metodo che restituisce il work soggetto dell'eccezione.*/
	public Work geWork(){
		return (Work) object;
	}
}
