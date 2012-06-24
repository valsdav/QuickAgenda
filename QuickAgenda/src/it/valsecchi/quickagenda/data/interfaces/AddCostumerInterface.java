package it.valsecchi.quickagenda.data.interfaces;

import it.valsecchi.quickagenda.data.component.exception.CostumerAlreadyExistsException;
import it.valsecchi.quickagenda.data.exception.InsufficientDataException;

/**
 * Interfaccia che fornisce i metodi per l'aggiunta di un Costumer. Questa
 * interfaccia sarà implementata dal DataManager e utilizzata dai form per
 * l'aggiunta di costumers
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public interface AddCostumerInterface {

	/**
	 * Metodo dell'interfaccia che prevede la possibiltà di aggiungere un
	 * costumer utilizzando i parametri passati. Il nome e il cognome sono
	 * necessari e sufficiente e se non vengono fornite verrà lanciata una
	 * InsufficientDataException.
	 * 
	 * @param nome
	 * @param cognome
	 * @param azienda
	 * @param indirizzo
	 * @param tel
	 * @param email
	 * @throws CostumerAlreadyExistsException
	 * @throws InsufficientDataException
	 */
	public void addCostumer(String nome, String cognome, String azienda,
			String indirizzo, String tel, String email)
			throws CostumerAlreadyExistsException, InsufficientDataException;
}
