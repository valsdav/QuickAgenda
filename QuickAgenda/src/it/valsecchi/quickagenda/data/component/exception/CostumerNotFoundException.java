package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.Costumer;

/**
 * Classe che rappresenta l'eccezione che viene lanciata quando non viene
 * trovato il Costumer nei dati.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class CostumerNotFoundException extends ElementNotFoundException {

	private static final long serialVersionUID = -1477665849108240456L;

	/** Costruttore che richiede un oggetto costumer soggetto dell'eccezione*/
	public CostumerNotFoundException(Costumer costumer) {
		// si chiama il costruttore della classe base
		super(ElementType.Costumer, costumer);
	}
	
	/** Costruttore dell'eccezione con messaggio fornito dall'utente */
	public CostumerNotFoundException(Costumer costumer,String mess) {
		// si chiama il costruttore della classe base
		super(ElementType.Costumer, costumer,mess);
	}
	
	/** Costuttore che richiede l'hash, non l'oggetto*/
	public CostumerNotFoundException(String hash) {
		// si chiama il costruttore della classe base
		super(ElementType.Costumer, hash);
		
	}
	/** Metodo che restituisce il costumer soggetto dell'eccezione */
	public Costumer getCostumer(){
		return (Costumer)object;
	}
}
