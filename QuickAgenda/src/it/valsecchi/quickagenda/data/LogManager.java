package it.valsecchi.quickagenda.data;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Classe che fornisce i metodi per creare un log utile per il test e il debug
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class LogManager {

	private PrintStream writer;
	private SimpleDateFormat dateFormat=  new SimpleDateFormat ("HH:mm:ss dd/MM/yyyy");

	/**
	 * Il costruttore predefinito imposta lo stream di uscita su quello
	 * predefinito a console
	 */
	public LogManager() {
		writer = System.out;
	}

	/**
	 * Costruttore che imposta lo stream di uscita sul file passato come
	 * parametro.
	 * 
	 * @param path
	 *            path del file su cui scrivere il log.
	 * @throws FileNotFoundException
	 */
	public LogManager(String path) throws FileNotFoundException {
		writer = new PrintStream(path);
	}

	/**
	 * Metodo che scrive nel log un messaggio di debug
	 * 
	 * @param string
	 *            messaggio da scrivere
	 */
	public void debug(String string) {
		// scrive un messaggio contrassegnato come debug
		string = "DEBUG   --- " +dateFormat.format(GregorianCalendar.getInstance().getTime())
				+ " >>> " + string;
		// si scrive nel flusso
		writer.println(string);
		writer.flush();
	}

	/**
	 * Metodo che scrive nel log un messaggio di info
	 * 
	 * @param string
	 *            messaggio da scrivere
	 */
	public void info(String string) {
		// scrive un messaggio contrassegnato come info
		string = "INFO    --- " + dateFormat.format(GregorianCalendar.getInstance().getTime())
				+ " >>> " + string;
		// si scrive nel flusso
		writer.println(string);
		writer.flush();
	}

	/**
	 * Metodo che scrive nel log un messaggio di warning
	 * @param string Messaggio da scrivere
	 */
	public void warning(String string) {
		// scrive un messaggio contrassegnato come warning
		string = "WARNING --- " + dateFormat.format(GregorianCalendar.getInstance().getTime())
				+ " >>> " + string;
		// si scrive nel flusso
		writer.println(string);
		writer.flush();
	}
	
	/**
	 * Metodo che scrive nel log un messaggio di error
	 * @param string Messaggio da scrivere
	 */
	public void error(String string) {
		// scrive un messaggio contrassegnato come error
		string = "ERROR   --- " + dateFormat.format(GregorianCalendar.getInstance().getTime())
				+ " >>> " + string;
		// si scrive nel flusso
		writer.println(string);
		writer.flush();
	}
}
