package it.valsecchi.quickagenda.data.interfaces;

import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.component.exception.WorkAlreadyExistsException;
import it.valsecchi.quickagenda.data.exception.InsufficientDataException;
import java.util.Calendar;

/**
 * Interfaccia che fornisce i metodi per l'aggiunta di un Work. Questa
 * interfaccia verrà implementata dal DataManager e sarà poi utilizzata dalle
 * finestre per l'aggiunta dei Work.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public interface AddWorkInterface {

	/**
	 * Metodo che deve essere implementato per l'aggiunta di un Work ai dati.
	 * 
	 * @param nome
	 *            nome del Work
	 * @param costumerid
	 *            Id del Costumer a cui appartiene il Work
	 * @param indirizzo
	 *            indirizzo del Work
	 * @param iniziolavori
	 *            data di inizio del Work
	 * @param finelavori
	 *            data di fine del Work
	 * @param completed
	 *            indica se il Work è stato completato
	 * @throws InsufficientDataException
	 *             lanciata nel caso di parametri insufficienti
	 * @throws WorkAlreadyExistsException
	 *             lanciata nel caso esista già il Work indicato con questi
	 *             questi parametri
	 */
	public void addWork(String nome, String costumerid, String indirizzo,
			Calendar iniziolavori, Calendar finelavori, boolean completed)
			throws InsufficientDataException, WorkAlreadyExistsException,IDNotFoundException;

}
