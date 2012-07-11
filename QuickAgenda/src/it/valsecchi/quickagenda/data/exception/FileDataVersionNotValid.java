package it.valsecchi.quickagenda.data.exception;

/**
 * Eccezione che viene lanciata quando il programma tenta di aprire un file dati
 * avente versione posteriore a quella corrente del programma.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class FileDataVersionNotValid extends Exception {

	private static final long serialVersionUID = -1596563025061872904L;
	private int fileVersion;

	/**
	 * Costruttore che richiede la versione del file incompatibile*/
	public FileDataVersionNotValid(int _fileVersion){
		fileVersion = _fileVersion;
	}

	public int getFileVersion() {
		return fileVersion;
	}
}
