package it.valsecchi.quickagenda.data.exception;

/**
 * Classe che rappresenta l'eccezione lanciata quando la password fornita
 * dall'utente non è valida
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class InvalidPasswordException extends Exception {

	private static final long serialVersionUID = -3788577655824405557L;

	/** Costruttore che richiede un messaggio dall'utente */
	public InvalidPasswordException(String mess) {
		super(mess);
	}

	/** Costruttore che non richiede messaggio */
	public InvalidPasswordException() {
		super("Password non valida!");
	}
}
