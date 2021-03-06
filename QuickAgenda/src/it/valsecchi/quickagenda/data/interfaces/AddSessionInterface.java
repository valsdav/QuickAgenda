package it.valsecchi.quickagenda.data.interfaces;

import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.exception.InsufficientDataException;
import java.util.Calendar;

/**
 * Interfaccia che fornisce i metodi per l'aggiunta di una Session. Questa
 * interfaccia verr� implementata dal DataManager e sar� poi utilizzata dalle
 * finestre per l'aggiunta delle Session.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public interface AddSessionInterface {

	/**
	 * Metodo definito dall'interfaccia necessario per aggiungere ai dati una
	 * sessione. I dati necessari sono workid e sessiondata. Se non viene
	 * fornito almeno uno di questi parametri viene lanciata una
	 * InsufficientDataException.
	 * 
	 * @param workid
	 * @param calendar
	 * @param hours
	 * @param spesa
	 * @param note
	 * @throws InsufficientDataException
	 * @throws IDNotFoundException
	 */
	public void addSession(String workid, Calendar calendar, int hours,
			int spesa, String note) throws InsufficientDataException,
			IDNotFoundException;

}
