package it.valsecchi.quickagenda.data.interfaces;

import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.component.exception.SessionAlreadyExistsException;
import it.valsecchi.quickagenda.data.exception.InsufficientDataException;
import java.util.Calendar;
import java.util.List;

/**
 * Interfaccia che fornisce i metodi per l'aggiunta di una Session. Questa
 * interfaccia verrà implementata dal DataManager e sarà poi utilizzata dalle
 * finestre per l'aggiunta delle Session.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public interface AddSessionInterface {

	/**
	 * Metodo definito dall'interfaccia necessario per aggiungere ai dati una
	 * sessione. I dati necessari sono workid, costumerid e sessiondata.
	 * Se non viene fornito almeno uno di questi parametri viene lanciata una
	 * InsufficientDataException.
	 * 
	 * @param workid
	 * @param costumerid
	 * @param calendar
	 * @param hours
	 * @param spesa
	 * @param materiali
	 * @throws SessionAlreadyExistsException
	 * @throws InsufficientDataException
	 * @throws IDNotFoundException 
	 */
	public void addSession(String workid, String costumerid,
			Calendar calendar, int hours, int spesa,
			List<String> materiali) throws SessionAlreadyExistsException,InsufficientDataException, IDNotFoundException;

}
