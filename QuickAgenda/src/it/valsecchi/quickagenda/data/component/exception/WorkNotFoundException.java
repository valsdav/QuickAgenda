package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.ElementType;
import it.valsecchi.quickagenda.data.component.Work;

/**
 * Classe che rappresenta l'eccezione che viene lanciata quando non viene
 * trovato il Work nei dati.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class WorkNotFoundException extends ElementNotFoundException {

	private static final long serialVersionUID = -210952143679508102L;

	/** Costruttore che richiede un oggetto work soggetto dell'eccezione*/
	public WorkNotFoundException(Work work) {
		// si chiama il costruttore della classe base
		super(ElementType.Work, work);
	}
	
	/** Costruttore dell'eccezione con messaggio fornito dall'utente */
	public WorkNotFoundException(Work work,String mess) {
		// si chiama il costruttore della classe base
		super(ElementType.Work, work,mess);
	}
	
	/** Costruttore che richiede l'hash, non l'oggetto*/
	public WorkNotFoundException(String hash) {
		// si chiama il costruttore della classe base
		super(ElementType.Work, hash);
	}
	
	/** Metodo che restituisce il Work soggetto dell'eccezione */
	public Work getWork(){
		return (Work) object;
	}
}
