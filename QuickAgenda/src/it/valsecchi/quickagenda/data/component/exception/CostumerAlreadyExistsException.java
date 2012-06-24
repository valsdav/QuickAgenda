package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.Costumer;

/**
 * Classe che rappresenta l'eccezione che viene lanciata quando un costumer
 * esiste già.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class CostumerAlreadyExistsException extends
		ElementAlreadyExistsException {

	private static final long serialVersionUID = -4980080722500615361L;

	/**
	 * Il costruttore dell'eccezione richiede il costumer soggetto
	 * dell'eccezione
	 */
	public CostumerAlreadyExistsException(Costumer costumer) {
		// si richiama il costruttore della classe padre
		super(ElementType.Costumer, costumer);
	}

	/** Costruttore che accetta un messaggio dall'utente*/
	public CostumerAlreadyExistsException(Costumer costumer,String mess) {
		// si richiama il costruttore della classe padre
		super(ElementType.Costumer, costumer,mess);
	}
	
	/** Costruttore che accetta non l'oggetto ma l'hash*/
	public CostumerAlreadyExistsException(String hash) {
		// si richiama il costruttore della classe padre
		super(ElementType.Costumer,hash);
	}
	
	/** Metodo che restituisce il costumer soggetto dell'eccezione */
	public Costumer getCostumer() {
		return (Costumer) object;
	}

}
