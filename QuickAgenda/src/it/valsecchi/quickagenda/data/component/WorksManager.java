package it.valsecchi.quickagenda.data.component;

import it.valsecchi.quickagenda.data.Utility;
import it.valsecchi.quickagenda.data.component.exception.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * /** Classe che gestisce, immagazzina e gestisce i Work. La classe fornisce i
 * metodi per aggiungere i Work (Es
 * {@link #addWork(String, GregorianCalendar, GregorianCalendar, boolean, String)}
 * ), per rimuoverli (Es {@link #removeWorkbyID(String)}, per restituili a
 * seconda del loro ID o della loro Hash (Es {@link #getWorkByID(String)},
 * {@link #getWorkByHash(String)}). Inoltre la classe fornisce la possibilità di
 * ricercare i Work grazie a parametri (Es
 * {@link #queryByArguments(String, String, GregorianCalendar, GregorianCalendar, boolean)}
 * .
 * 
 * @author Davide Valsecchi
 * @version 1.0
 */
public class WorksManager {

	/** Mappa che contiene gli oggetti Work associati al loro ID (ID|Work) */
	private Map<String, Work> worksMap;
	/** Mappa che contiene le hash dei work associati al loro ID (Hash|ID) */
	private Map<String, String> hashMap;

	/** Costuttore che inizializza le collezioni */
	public WorksManager() {
		worksMap = new HashMap<>();
		hashMap = new HashMap<>();
	}

	/**
	 * Metodo che aggiunge un work ai dati, controllando prima la sua esistenza.
	 * L'identità del work viene controllata con la sua hash. Se esiste già
	 * viene lanciata una WorkAlreadyExistsException. Viene anche controllata
	 * l'esistenza dell'id e se esiste già si lancia un
	 * IDAlreadyExistsException.
	 * 
	 * @param work
	 *            work da aggiungere
	 * @throws WorkAlreadyExistsException
	 * @throws IDAlreadyExistsException
	 */
	public void addWork(Work work) throws WorkAlreadyExistsException,
			IDAlreadyExistsException {
		// si controlla che il work non sia già presente, controllando l'hash
		if (hashMap.containsKey(work.getHash())) {
			// se esiste già si lancia l'eccezione
			throw new WorkAlreadyExistsException(work);
		} else {
			// si controlla che l'id non esista già
			if (worksMap.containsKey(work.getID())) {
				// se lo contiene già si lancia l'eccezione
				throw new IDAlreadyExistsException(ElementType.Work,
						work.getID(), work);
			} else {
				// si aggiunge
				worksMap.put(work.getID(), work);
				hashMap.put(work.getHash(), work.getID());
			}
		}
	}

	/**
	 * Metodo che aggiunge una lista di Works chiamando {@link #addWork(works)}.
	 * Se un solo Work lancia un'eccezione tutto il processo viene interrotto,
	 * quindi è sempre meglio eseguire aggiunte singole, meno nel caso del
	 * caricamento iniziale in cui si è certi dell'integrità dei dati.
	 * 
	 * @param works
	 *            lista di Work da aggiungere
	 * @throws WorkAlreadyExistsException
	 * @throws IDAlreadyExistsException
	 */
	public void addWorks(List<Work> works) throws WorkAlreadyExistsException,
			IDAlreadyExistsException {
		for (Work w : works) {
			this.addWork(w);
		}
	}

	/**
	 * Metodo che aggiunge ai dati un Work creandolo in base ai dati passati
	 * come parametro. L'esistenza del work viene controllata con l'hash. Se
	 * esiste già si lancia l'eccezione WorkAlreadyExistsException.
	 * 
	 * @param indirizzo
	 *            indirizzo del work
	 * @param iniziolavori
	 *            data di inizio del work
	 * @param finelavori
	 *            data di fine del work
	 * @param completed
	 *            work completato o no
	 * @param costumerid
	 *            id del cliente a cui appartiene il work
	 * @throws WorkAlreadyExistsException
	 *             eccezione che viene lanciata se il work esiste già
	 */
	public void addWork(String nome, String indirizzo, String costumerid,
			Calendar iniziolavori, Calendar finelavori, boolean completed)
			throws WorkAlreadyExistsException {
		// Si ricava un id valido
		String id = this.getValidID();
		// si ricava un'hash valida
		String hash = Work.calculateWorkHash(costumerid, nome, indirizzo,
				iniziolavori);
		// si crea un work
		Work newWork = new Work(id ,costumerid,nome, indirizzo, iniziolavori,
				finelavori, completed,hash);
		// ora si controlla se esiste già con l'hash
		if (hashMap.containsKey(hash)) {
			// si lancia l'eccezione
			throw new WorkAlreadyExistsException(newWork);
		} else {
			// si aggiunge
			worksMap.put(id, newWork);
			hashMap.put(hash, id);
		}
	}

	/**
	 * Metodo che rimuove dai dati il Work rappresentato dall'ID passato come
	 * parametro. Se l'Id non è presente viene lanciata l'eccezione
	 * IDNotFoundException
	 * 
	 * @param ID
	 *            id del Work da rimuovere
	 * @throws IDNotFoundException
	 *             eccezione lanciata se l'id non è stato trovato
	 */
	public void removeWorkByID(String ID) throws IDNotFoundException {
		if (worksMap.containsKey(ID)) {
			// si ricava l'hash
			String hash = worksMap.get(ID).getHash();
			// si rimuove il work
			worksMap.remove(ID);
			// se rimuove anche l'hash
			hashMap.remove(hash);
		} else {
			throw new IDNotFoundException(ElementType.Work, ID);
		}
	}

	/**
	 * Metodo che rimuove dai dati il Work rappresentato dall'Hash passato come
	 * parametro. Se l'hash non viene trovato si lancia una
	 * WorkNotFoundExcepion.
	 * 
	 * @param hash
	 *            hash che caratterizza il Work da rimuovere
	 * @throws WorkNotFoundException
	 *             eccezione lanciata se non viene trovato l'hash e quindi il
	 *             Work
	 */
	public void removeWorkByHash(String hash) throws WorkNotFoundException {
		if (hashMap.containsKey(hash)) {
			// si elimina il costumer
			worksMap.remove(hashMap.get(hash));
			// si elimina l'hash
			hashMap.remove(hash);
		} else {
			throw new WorkNotFoundException(hash);
		}
	}

	/**
	 * Metodo che restituisce un Work avente come ID il parametro passato al
	 * metodo. Se il Work non viene trovato viene lanciata l'eccezione
	 * IDNotFoundException
	 * 
	 * @param ID
	 *            id del Work da restituire
	 * @return restituisce il Work richiesto
	 * @throws IDNotFoundException
	 *             lanciata se non viene trovato l'Id
	 */
	public Work getWorkByID(String id) throws IDNotFoundException {
		if (worksMap.containsKey(id)) {
			return worksMap.get(id);
		} else {
			throw new IDNotFoundException(ElementType.Work, id);
		}
	}

	/**
	 * Metodo che restituisce un Work identificato dall'hash passata come
	 * parametro. Se non viene trovata l'hash si lancia una
	 * WorkNotFoundException.
	 * 
	 * @param hash
	 *            hash che caratterizza il work da trovare
	 * @return ritorna il Work richiesta
	 * @throws WorkNotFoundException
	 *             eccezione lanciata se non viene trovata l'hash e quindi in
	 *             Work.
	 */
	public Work getWorkByHash(String hash) throws WorkNotFoundException {
		if (hashMap.containsKey(hash)) {
			String id = hashMap.get(hash);
			// si ritorna il costumer associato
			return worksMap.get(id);
		} else {
			throw new WorkNotFoundException(hash);
		}
	}

	/** Metodo che restituisce tutti gli Work contenuti nei dati */
	public List<Work> getAllWorks() {
		List<Work> all = new ArrayList<>();
		for(Work w:worksMap.values()){
			all.add(w);
		}
		return all;
	}

	/**
	 * Metodo che verifica l'esistenza di un Work identificato dall'id passato
	 * come parametro.
	 * 
	 * @param ID
	 *            id da controllare
	 * @return restituisce True se esiste un Work con l'id passato come
	 *         parametro
	 */
	public boolean exists(String ID) {
		if (worksMap.containsKey(ID)) {
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
			if (!worksMap.containsKey(id)) {
				// se non esiste si esce
				exist = false;
			}
		}
		return id;
	}

	/**
	 * Metodo pubblico che filtra i Work secondo vari parametri. Se non si vuole
	 * uilizzare un parametro impostarlo a null.
	 * 
	 * @param costumerid
	 *            ID del costumer
	 * @param indirizzo
	 *            indirizzo del work
	 * @param iniziolavori
	 *            data di inizio del Work
	 * @param finelavori
	 *            data di fine del Work
	 * @param completed
	 *            stato di completamento
	 * @return ritorna la lista di Work che corrisponde a tutti i parametri
	 */
	public List<Work> queryByArguments(String costumerid, String nome,
			String indirizzo, Calendar iniziolavori, Calendar finelavori,
			boolean completed) {
		// Si ricavano tutti i work tra cui cercare
		List<Work> temp = this.getAllWorks();
		// si ricerca
		if (nome != null && !nome.equals("")) {
			temp = this.queryByNome(nome);
		}
		if (costumerid != null && !costumerid.equals("")) {
			temp = this.queryByCostumerID(costumerid, temp);
		}
		if (indirizzo != null && !indirizzo.equals("")) {
			temp = this.queryByIndirizzo(indirizzo, temp);
		}
		if (iniziolavori != null) {
			temp = this.queryByInizioLavori(iniziolavori, temp);
		}
		if (finelavori != null) {
			temp = this.queryByFineLavori(finelavori, temp);
		}
		temp = this.queryByCompleted(completed, temp);
		// si ritorna il risultato
		return temp;
	}

	/**
	 * Metodo che filtra i Work in base all'ID del Costumer
	 * 
	 * @param costumerID
	 *            ID del Costumer da ricercare
	 * @param set
	 *            lista di work tra i quali cercare
	 * @return ritorna i Work che corrispondono ai criteri di ricerca
	 */
	private List<Work> queryByCostumerID(String costumerID,List<Work> set) {
		List<Work> found = new ArrayList<>();
		for (Work wk : set) {
			if (wk.getCostumerID().equals(costumerID)) {
				found.add(wk);
			}
		}
		return found;
	}

	/** Metodo pubblico che filtra gli work con l'ID del Costumer */
	public List<Work> queryByCostumerID(String costumerID) {
		// Si ricavano tutti i work tra cui cercare
		return this.queryByCostumerID(costumerID,this.getAllWorks());
	}

	/**
	 * Metodo che filtra i Work in base all'indirizzo
	 * 
	 * @param indirizzo
	 *            indirizzo da ricercare
	 * @param set
	 *            lista di work tra i quali cercare
	 * @return ritorna i Work che corrispondono ai criteri di ricerca
	 */
	private List<Work> queryByIndirizzo(String indirizzo, List<Work> set) {
		List<Work> found = new ArrayList<>();
		for (Work wk : set) {
			if (wk.getIndirizzo().contains(indirizzo)) {
				found.add(wk);
			}
		}
		return found;
	}

	/** Metodo pubblico che filtra gli work con l'indirizzo */
	public List<Work> queryByIndirizzo(String indirizzo) {
		// Si ricavano tutti i work tra cui cercare
		return this.queryByIndirizzo(indirizzo, this.getAllWorks());
	}

	/** Metodo privato che filtra gli Work per il nome */
	private List<Work> queryByNome(String nome, List<Work> set) {
		List<Work> found = new ArrayList<>();
		for (Work wk : set) {
			if (wk.getNome().contains(nome)) {
				found.add(wk);
			}
		}
		return found;
	}

	/** Metodo pubblico che filtra gli Work per il nome */
	public List<Work> queryByNome(String nome) {
		return this.queryByNome(nome, this.getAllWorks());
	}

	/**
	 * Metodo che filtra i Work in base all'inizio dei lavori
	 * 
	 * @param iniziolavori
	 *            data da ricercare
	 * @param set
	 *            lista di work tra i quali cercare
	 * @return ritorna i Work che corrispondono ai criteri di ricerca
	 */
	private List<Work> queryByInizioLavori(Calendar iniziolavori,
			List<Work> set) {
		List<Work> found = new ArrayList<>();
		for (Work wk : set) {
			if (Utility.equalsDate(wk.getInizioLavori(), iniziolavori)) {
				found.add(wk);
			}
		}
		return found;
	}

	/** Metodo pubblico che filtra gli work con l'inizio lavori */
	public List<Work> queryByInizioLavori(Calendar iniziolavori) {
		// Si ricavano tutti i work tra cui cercare
		return this.queryByInizioLavori(iniziolavori, this.getAllWorks());
	}

	/**
	 * Metodo che filtra i Work in base alla fine dei lavori
	 * 
	 * @param finelavori
	 *            data da ricercare
	 * @param set
	 *            lista di work tra i quali cercare
	 * @return ritorna i Work che corrispondono ai criteri di ricerca
	 */
	private List<Work> queryByFineLavori(Calendar finelavori,
			List<Work> set) {
		List<Work> found = new ArrayList<>();
		for (Work wk : set) {
			if (Utility.equalsDate(wk.getFineLavori(), finelavori)) {
				found.add(wk);
			}
		}
		return found;
	}

	/** Metodo pubblico che filtra gli Work con il fine lavori */
	public List<Work> queryByFineLavori(Calendar finelavori) {
		// Si ricavano tutti i work tra cui cercare
		return this.queryByFineLavori(finelavori, this.getAllWorks());
	}

	/**
	 * Metodo che filtra i Work in base allo stato del work (completato o no)
	 * 
	 * @param completed
	 *            stato da ricercare
	 * @param set
	 *            lista di work tra i quali cercare
	 * @return ritorna i Work che corrispondono ai criteri di ricerca
	 */
	private List<Work> queryByCompleted(boolean completed, List<Work> set) {
		List<Work> found = new ArrayList<>();
		for (Work wk : set) {
			if (wk.isCompleted() == completed) {
				found.add(wk);
			}
		}
		return found;
	}

	/** Metodo pubblico che filtra gli Work con lo stato */
	public List<Work> queryByCompleted(boolean completed) {
		// Si ricavano tutti i work tra cui cercare
		return this.queryByCompleted(completed,this.getAllWorks());
	}
}
