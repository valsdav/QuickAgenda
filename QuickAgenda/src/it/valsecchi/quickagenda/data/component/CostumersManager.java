package it.valsecchi.quickagenda.data.component;

import it.valsecchi.quickagenda.data.Utility;
import it.valsecchi.quickagenda.data.component.exception.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe che gestisce, immagazzina e gestisce i Costumer. La classe fornisce i
 * metodi per aggiungere i Costumer (Es
 * {@link #addCostumer(String, String, String, String, String, String)}), per
 * rimuoverli (Es {@link #removeCostumerByID(String)}, per restituili a seconda
 * del loro ID o della loro Hash (Es {@link #getCostumerByID(String)},
 * {@link #getCostumerByHash(String)}). Inoltre la classe fornisce la
 * possibilità di ricercare i Costumer grazie a parametri (Es
 * {@link #querybyArguments(String, String, String, String, String, String)}.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 */
public class CostumersManager {

	/**
	 * Mappa che contiene gli oggetti Costumer associati al loro ID
	 * (ID|Costumer)
	 */
	private Map<String, Costumer> costumersMap;
	/** Mappa che contiene le hash dei costumer associati al loro ID (Hash|ID) */
	private Map<String, String> hashMap;

	/** Costruttore in cui si inizializzano le Map */
	public CostumersManager() {
		costumersMap = new HashMap<>();
		hashMap = new HashMap<>();
	}

	/**
	 * Metodo che aggiunge ai dati un Costumer passato come parametro
	 * controllando che non esista già. L'identità del costumer viene
	 * controllata con l'hash.Se esiste già si lancia
	 * CostumerAlreadyExistsException. Se esiste già solo l'id si lancia
	 * l'eccezione IDAlreadyExistsException.
	 * 
	 * @param costumer
	 *            cliente da aggiungere
	 * @throws CostumerAlreadyExistsException
	 * @throws IDAlreadyExistsException
	 */
	public void addCostumer(Costumer costumer)
			throws CostumerAlreadyExistsException, IDAlreadyExistsException {
		// si aggiunge il cliente se non è gia presente
		// si controlla se c'è l'hash
		if (hashMap.containsKey(costumer.getHash())) {
			// se è già presente si lancia l'eccezione
			throw new CostumerAlreadyExistsException(costumer);
		} else {
			// se non esiste si controlla l'id
			if (costumersMap.containsKey(costumer.getID())) {
				// se esiste già l'id si lancia l'eccezione
				throw new IDAlreadyExistsException(ElementType.Costumer,
						costumer.getID(), costumer);
			}
			// se non esiste si aggiunge
			costumersMap.put(costumer.getID(), costumer);
			// si aggiunge l'hash
			hashMap.put(costumer.getHash(), costumer.getID());
		}
	}

	/**
	 * Metodo che aggiunge una lista si Costumer chiamando
	 * {@link #addCostumer(Costumer)}. Se un solo costumer lancia un'eccezione
	 * tutto il processo viene interrotto, quindi è sempre meglio eseguire
	 * aggiunte singole, meno nel caso del caricamento iniziale in cui si è
	 * certi dell'integrità dei dati.
	 * 
	 * @param costumers
	 *            lista di costumers da aggiungere
	 * @throws CostumerAlreadyExistsException
	 * @throws IDAlreadyExistsException
	 */
	public void addCostumers(List<Costumer> costumers)
			throws CostumerAlreadyExistsException, IDAlreadyExistsException {
		for (Costumer c : costumers) {
			this.addCostumer(c);
		}
	}

	/**
	 * Metodo che aggiunge ai dati un Costumer creandolo in base ai dati passati
	 * come parametro. L'esistenza viene controllata con l'hash.Se esiste già si
	 * lancia CostumerAlreadyExistsException.
	 * 
	 * @param nome
	 *            nome del cliente
	 * @param cognome
	 *            cognome del cliente
	 * @param azienda
	 *            azienda del cliente
	 * @param indirizzo
	 *            indirizzo del cliente
	 * @param tel
	 *            telefono del cliente
	 * @param email
	 *            email del cliente
	 * @throws CostumerAlreadyExistsException
	 */
	public void addCostumer(String nome, String cognome, String azienda,
			String indirizzo, String tel, String email)
			throws CostumerAlreadyExistsException {
		// ora, ricavato un id valido,
		String id = this.getValidID();
		// si ricava un'hash valida
		String hash = Costumer.calculateCostumerHash(nome, cognome, indirizzo,
				azienda);
		// si crea il Costumer
		Costumer newCost = new Costumer(id, hash, nome, cognome, azienda,
				indirizzo, tel, email);
		// si controlla se esiste già con l'hash
		if (hashMap.containsKey(hash)) {
			// se è già presente si lancia l'eccezione
			throw new CostumerAlreadyExistsException(newCost);
		}
		// lo si aggiunge alla lista
		costumersMap.put(id, newCost);
		// si aggiunge l'hash alla lista
		hashMap.put(hash, id);
	}

	/**
	 * Metodo che rimuove dai dati il Costumer rappresentato dall'ID passato
	 * come parametro. Se l'Id non è presente viene lanciata l'eccezione
	 * IDNotFoundException
	 * 
	 * @param ID
	 *            id del Costumer da rimuovere
	 * @throws IDNotFoundException
	 *             eccezione lanciata se l'id non è stato trovato
	 */
	public void removeCostumerByID(String ID) throws IDNotFoundException {
		if (costumersMap.containsKey(ID)) {
			// si ricava l'hash
			String hash = costumersMap.get(ID).getHash();
			// si rimuove il costumer
			costumersMap.remove(ID);
			// se rimuove anche l'hash
			hashMap.remove(hash);
		} else {
			throw new IDNotFoundException(ElementType.Costumer, ID);
		}
	}

	/**
	 * Metodo che rimuove dai dati il Costumer rappresentato dall'Hash passato
	 * come parametro. Se l'hash non viene trovato si lancia una
	 * CostumerNotFoundExcepion.
	 * 
	 * @param hash
	 *            hash che caratterizza il Costumer da rimuovere
	 * @throws CostumerNotFoundException
	 *             eccezione lanciata se non viene trovato l'hash e quindi in
	 *             costumer
	 */
	public void removeCostumerByHash(String hash)
			throws CostumerNotFoundException {
		if (hashMap.containsKey(hash)) {
			// si elimina il costumer
			costumersMap.remove(hashMap.get(hash));
			// si elimina l'hash
			hashMap.remove(hash);
		} else {
			throw new CostumerNotFoundException(hash);
		}
	}

	/**
	 * Metodo che restituisce un Costumer avente come ID il parametro passato al
	 * metodo. Se il Costumer non viene trovato viene lanciata l'eccezione
	 * IDNotFoundException
	 * 
	 * @param ID
	 *            id del Costumer da restituire
	 * @return restituisce il Costumer richiesto
	 * @throws IDNotFoundException
	 *             lanciata se non viene trovato
	 */
	public Costumer getCostumerByID(String ID) throws IDNotFoundException {
		if (costumersMap.containsKey(ID)) {
			return costumersMap.get(ID);
		} else {
			throw new IDNotFoundException(ElementType.Costumer, ID);
		}
	}

	/**
	 * Metodo che restituisce un Costumer identificato dall'hash passata come
	 * parametro. Se non viene trovata l'hash si lancia una
	 * CostumerNotFoundException.
	 * 
	 * @param hash
	 *            hash che caratterizza il costumer da trovare
	 * @return ritorna il Costumer trovato
	 * @throws CostumerNotFoundException
	 *             eccezione lanciata se non viene trovata l'hash e quindi in
	 *             Costumer.
	 */
	public Costumer getCostumerByHash(String hash)
			throws CostumerNotFoundException {
		if (hashMap.containsKey(hash)) {
			String id = hashMap.get(hash);
			// si ritorna il costumer associato
			return costumersMap.get(id);
		} else {
			throw new CostumerNotFoundException(hash);
		}
	}

	/** Metodo che restituisce tutti i Costumers contenuti nei dati */
	public Collection<Costumer> getAllCostumers() {
		return costumersMap.values();
	}

	/**
	 * Metodo che verifica l'esistenza di un Costumer identificato dall'id
	 * passato come parametro.
	 * 
	 * @param ID
	 *            id da controllare
	 * @return restituisce True se esiste un costumer con l'id passato come
	 *         parametro
	 */
	public boolean exists(String ID) {
		if (costumersMap.containsKey(ID)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo che restituisce un ID valido da utilizzare. Il metodo genera ID
	 * finchè non ne trova uno non ancora utilizzato e lo restituisce
	 */
	public String getValidID() {
		// si crea l'id
		boolean exist = true;
		String id = "";
		while (exist) {
			id = Utility.generateID();
			// si controlla che non esista già
			if (!costumersMap.containsKey(id)) {
				// se non esiste si esce
				exist = false;
			}
		}
		return id;
	}

	/**
	 * Metodo che filtra i Costumer a seconda dei parametri passati e li
	 * restituisce. I parametri sono questi:
	 * Nome|Cognome|Azienda|Indirizzo|Tel|Email.
	 * 
	 * @return ritorna la lista di Costumer trovati
	 */
	public Collection<Costumer> querybyArguments(String nome, String cognome,
			String azienda, String indirizzo, String tel, String email) {
		// a seconda dei parametri si ricerca.
		// lista di tutti i costumers
		Collection<Costumer> temp = costumersMap.values();
		if (nome != null && nome != "") {
			// allora si ricerca per nome
			temp = this.queryByNome(nome, temp);
		}
		if (cognome != null && cognome != "") {
			// allora si ricerca per cognome
			temp = this.queryByCognome(cognome, temp);
		}
		if (azienda != null && azienda != "") {
			// allora si ricerca per azienda
			temp = this.queryByAzienda(azienda, temp);
		}
		if (indirizzo != null && indirizzo != "") {
			// allora si ricerca per indirizzo
			temp = this.queryByIndirizzo(indirizzo, temp);
		}
		if (tel != null && tel != "") {
			// allora si ricerca per telefono
			temp = this.queryByTel(tel, temp);
		}
		if (email != null && email != "") {
			// allora si ricerca per email
			temp = this.queryByEmail(email, temp);
		}
		// si ritorna il risultato
		return temp;
	}

	/**
	 * Metodo che filtra i Costumer in base al nome.
	 * 
	 * @param nome
	 *            nome da ricercare
	 * @param set
	 *            lista di costumer tra i quali cercare
	 * @return ritorna i Costumer che corrispondono ai criteri di ricerca
	 */
	private List<Costumer> queryByNome(String nome, Collection<Costumer> set) {
		List<Costumer> found = new ArrayList<>();
		for (Costumer cs : set) {
			if (cs.getNome().contains(nome)) {
				found.add(cs);
			}
		}
		return found;
	}

	/** Metodo che filtra i Costumer in base al nome */
	public List<Costumer> queryByNome(String nome) {
		return this.queryByNome(nome, costumersMap.values());
	}

	/**
	 * Metodo che filtra i Costumer in base al cognome.
	 * 
	 * @param cognome
	 *            cognome da ricercare
	 * @param set
	 *            lista di costumer tra i quali cercare
	 * @return ritorna i Costumer che corrispondono ai criteri di ricerca
	 */
	private List<Costumer> queryByCognome(String cognome,
			Collection<Costumer> set) {
		List<Costumer> found = new ArrayList<>();
		for (Costumer cs : set) {
			if (cs.getCognome().contains(cognome)) {
				found.add(cs);
			}
		}
		return found;
	}

	/** Metodo che filtra i Costumer in base al cognome */
	public List<Costumer> queryByCognome(String cognome) {
		return this.queryByCognome(cognome, costumersMap.values());
	}

	/**
	 * Metodo che filtra i Costumer in base all'azienda.
	 * 
	 * @param azienda
	 *            azienda da ricercare
	 * @param set
	 *            lista di costumer tra i quali cercare
	 * @return ritorna i Costumer che corrispondono ai criteri di ricerca
	 */
	private List<Costumer> queryByAzienda(String azienda,
			Collection<Costumer> set) {
		List<Costumer> found = new ArrayList<>();
		for (Costumer cs : set) {
			if (cs.getAzienda().contains(azienda)) {
				found.add(cs);
			}
		}
		return found;
	}

	/** Metodo che filtra i Costumer in base all'azienda */
	public List<Costumer> queryByAzienda(String azienda) {
		return this.queryByAzienda(azienda, costumersMap.values());
	}

	/**
	 * Metodo che filtra i Costumer in base all'indirizzo
	 * 
	 * @param indirizzo
	 *            indirizzo da ricercare
	 * @param set
	 *            lista di costumer tra i quali cercare
	 * @return ritorna i Costumer che corrispondono ai criteri di ricerca
	 */
	private List<Costumer> queryByIndirizzo(String indirizzo,
			Collection<Costumer> set) {
		List<Costumer> found = new ArrayList<>();
		for (Costumer cs : set) {
			if (cs.getIndirizzo().contains(indirizzo)) {
				found.add(cs);
			}
		}
		return found;
	}

	/** Metodo che filtra i Costumer in base al'indirizzo */
	public List<Costumer> queryByindirizzo(String indirizzo) {
		return this.queryByIndirizzo(indirizzo, costumersMap.values());
	}

	/**
	 * Metodo che filtra i Costumer in base al telefono
	 * 
	 * @param tel
	 *            telefono da ricercare
	 * @param set
	 *            lista di costumer tra i quali cercare
	 * @return ritorna i Costumer che corrispondono ai criteri di ricerca
	 */
	private List<Costumer> queryByTel(String tel, Collection<Costumer> set) {
		List<Costumer> found = new ArrayList<>();
		for (Costumer cs : set) {
			if (cs.getTel().equals(tel)) {
				found.add(cs);
			}
		}
		return found;
	}

	/** Metodo che filtra i Costumer in base al telefono */
	public List<Costumer> queryByTel(String tel) {
		return this.queryByTel(tel, costumersMap.values());
	}

	/**
	 * Metodo che filtra i Costumer in base all'email
	 * 
	 * @param email
	 *            email da ricercare
	 * @param set
	 *            lista di costumer tra i quali cercare
	 * @return ritorna i Costumer che corrispondono ai criteri di ricerca
	 */
	private List<Costumer> queryByEmail(String email, Collection<Costumer> set) {
		List<Costumer> found = new ArrayList<>();
		for (Costumer cs : set) {
			if (cs.getEmail().equals(email)) {
				found.add(cs);
			}
		}
		return found;
	}

	/** Metodo che filtra i Costumer in base all'email */
	public List<Costumer> queryByEmail(String email) {
		return this.queryByEmail(email, costumersMap.values());
	}
}
