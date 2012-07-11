package it.valsecchi.quickagenda.data.component.exception;

import it.valsecchi.quickagenda.data.component.ElementType;

/**
 * Classe che rappresenta l'eccezione lanciata quando non viene trovato un ID
 * @author Davide Valsecchi
 * @version 1.0
 *
 */
public class IDNotFoundException extends Exception {

	private static final long serialVersionUID = 5166483647969126836L;
	private ElementType type;
	private String ID;
	
	/** Costruttore che richiede il tipo di elemento e l'id interessato*/
	public IDNotFoundException(ElementType _type, String id){
		super("L'ID del tipo :" + _type.toString()
				+ " non è stato trovato nei dati.");
		type = _type;
		ID = id;
	}
	
	/** Costruttore che richiede anche un messaggio fornito dall'utente*/
	public IDNotFoundException(ElementType _type, String id,String mess){
		super(mess);
		type = _type;
		ID = id;
	}
	
	public ElementType getType() {
		return type;
	}

	public String getID() {
		return ID;
	}
}
