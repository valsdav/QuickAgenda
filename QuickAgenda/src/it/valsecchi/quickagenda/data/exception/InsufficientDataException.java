package it.valsecchi.quickagenda.data.exception;

/**
 * Classe che rappresenta l'eccezione che viene lanciata quando si cerca di
 * utilizzare un metodo non passando abbastanza dati. Es: aggiunta di un
 * Costumer senza fornire almeno nome e cognome.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class InsufficientDataException extends Exception {

	private static final long serialVersionUID = -7955982897178269024L;
	private String method;
	private String param;

	/**
	 * Costruttore che richiede il nome del metodo e il parametro non presente
	 * che hanno provocato l'eccezione. Inoltre si richiede un messaggio
	 * dall'utente.
	 */
	public InsufficientDataException(String _method, String _param, String mess) {
		super(mess);
		method = _method;
		param = _param;
	}

	public String getMethod() {
		return method;
	}

	public String getParam() {
		return param;
	}
}
