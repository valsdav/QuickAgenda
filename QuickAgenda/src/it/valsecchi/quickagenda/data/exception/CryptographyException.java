package it.valsecchi.quickagenda.data.exception;

/**
 * Classe che rappresente l'eccezione lanciata quando ci sono problemi generici
 * di crittografia.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class CryptographyException extends Exception {

	private static final long serialVersionUID = 6627740755191903414L;

	/** Costruttore che richiede solo un messaggio dall'utente */
	public CryptographyException(String mess) {
		super(mess);
	}
}
