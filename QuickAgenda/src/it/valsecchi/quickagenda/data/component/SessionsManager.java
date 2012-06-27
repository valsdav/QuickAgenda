package it.valsecchi.quickagenda.data.component;

import it.valsecchi.quickagenda.data.Utility;
import it.valsecchi.quickagenda.data.component.exception.ElementType;
import it.valsecchi.quickagenda.data.component.exception.IDAlreadyExistsException;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.component.exception.SessionAlreadyExistsException;
import it.valsecchi.quickagenda.data.component.exception.SessionNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionsManager {

	/** Mappa che contiene gli oggetti Session associati al loro ID (ID|Session) */
	private Map<String, Session> sessionsMap;
	/** Mappa che contiene le hash delle session associate al loro ID (Hash|ID) */
	private Map<String, String> hashMap;

	/** Costruttore in cui si inizializzano le Map */
	public SessionsManager() {
		sessionsMap = new HashMap<>();
		hashMap = new HashMap<>();
	}

	/**
	 * Metodo che aggiunge una Session ai dati, controllando prima la sua
	 * esistenza. L'identità della session viene controllata con la sua hash. Se
	 * esiste già viene lanciata una SessionAlreadyExistsException. Viene anche
	 * controllata l'esistenza dell'id e se esiste già si lancia un
	 * IDAlreadyExistsException.
	 * 
	 * @param session
	 *            session da aggiungere
	 * @throws SessionAlreadyExistsException
	 * @throws IDAlreadyExistsException
	 */
	public void addSession(Session session)
			throws SessionAlreadyExistsException, IDAlreadyExistsException {
		// si aggiunge la sessione se non è già presente
		// si controlla l'hash, che identifica in modo univoco una session
		if (hashMap.containsKey(session.getHash())) {
			// si lancia l'eccezione
			throw new SessionAlreadyExistsException(session);
		} else {
			// si controlla se l'id della sessione esiste già
			if (sessionsMap.containsKey(session.getID())) {
				throw new IDAlreadyExistsException(ElementType.Session,
						session.getID(),session);
			} else {
				// si aggiunge
				sessionsMap.put(session.getID(), session);
				hashMap.put(session.getHash(), session.getID());
			}
		}
	}

	/**
	 * Metodo che aggiunge una lista di Session chiamando
	 * {@link #addSession(Session)}. Se una sola Session lancia un'eccezione
	 * tutto il processo viene interrotto, quindi è sempre meglio eseguire
	 * aggiunte singole, meno nel caso del caricamento iniziale in cui si è
	 * certi dell'integrità dei dati.
	 * 
	 * @param sessions
	 *            lista di Session da aggiungere
	 * @throws SessionAlreadyExistsException
	 * @throws IDAlreadyExistsException
	 */
	public void addSessions(List<Session> sessions)
			throws SessionAlreadyExistsException, IDAlreadyExistsException {
		for (Session s : sessions) {
			this.addSession(s);
		}
	}

	/**
	 * Metodo che aggiunge ai dati una Session creandola in base ai dati passati
	 * come parametro. L'esistenza della Session viene controllata con l'hash.
	 * Se esiste già si lancia l'eccezione SessionAlreadyExistsException.
	 * 
	 * @param workid
	 *            ID del Work a cui appartiene la session
	 * @param costumerid
	 *            ID del Costumer a cui appartiene la session
	 * @param sessiondata
	 *            data della Session
	 * @param hours
	 *            numero di ore della Session
	 * @param spesa
	 *            spesa della Session
	 * @param materiali
	 *            materiali della Session
	 * @throws SessionAlreadyExistsException
	 *             eccezione che viene lanciata quando la session da aggiungere
	 *             esiste già-
	 */
	public void addSession(String workid, String costumerid,
			Calendar sessiondata, int hours, int spesa,
			List<String> materiali) throws SessionAlreadyExistsException {
		// si ricava un id valido
		String id = this.getValidID();
		// si ricava l'hash
		String hash = Session.calculateSessionHash(workid, costumerid,
				sessiondata);
		// si crea la session
		Session newSes = new Session(id, hash, workid, costumerid, sessiondata,
				hours, spesa, materiali);
		// si controlla se esiste già con l'hash
		if (hashMap.containsKey(hash)) {
			// si lancia l'eccezione
			throw new SessionAlreadyExistsException(newSes);
		} else {
			// si aggiunge
			sessionsMap.put(id, newSes);
			hashMap.put(hash, id);
		}
	}

	/**
	 * Metodo che rimuove dai dati la Session rappresentata dall'ID passato come
	 * parametro. Se l'Id non è presente viene lanciata l'eccezione
	 * IDNotFoundException
	 * 
	 * @param ID
	 *            id della Session da rimuovere
	 * @throws IDNotFoundException
	 *             eccezione lanciata se l'id non è stato trovato
	 */
	public void removeWorkByID(String ID) throws IDNotFoundException {
		if (sessionsMap.containsKey(ID)) {
			// si ricava l'hash
			String hash = sessionsMap.get(ID).getHash();
			// si rimuove la Session
			sessionsMap.remove(ID);
			// si rimuove l'hash
			hashMap.remove(hash);
		} else {
			throw new IDNotFoundException(ElementType.Session, ID);
		}
	}

	/**
	 * Metodo che rimuove dai dati la Session rappresentata dall'Hash passato
	 * come parametro. Se l'hash non viene trovato si lancia una
	 * SessionNotFoundExcepion.
	 * 
	 * @param hash
	 *            hash che caratterizza la Session da rimuovere
	 * @throws SessionNotFoundException
	 *             eccezione lanciata se non viene trovato l'hash e quindi la
	 *             Session
	 */
	public void removeSessionByHash(String hash)
			throws SessionNotFoundException {
		if (hashMap.containsKey(hash)) {
			// si elimina il costumer
			sessionsMap.remove(hashMap.get(hash));
			// si elimina l'hash
			hashMap.remove(hash);
		} else {
			throw new SessionNotFoundException(hash);
		}
	}

	/**
	 * Metodo che restituisce la Session avente come ID il parametro passato al
	 * metodo. Se la Session non viene trovata viene lanciata l'eccezione
	 * IDNotFoundException
	 * 
	 * @param ID
	 *            id della Session da restituire
	 * @return restituisce la Session richiesta
	 * @throws IDNotFoundException
	 *             lanciata se non viene trovato l'Id
	 */
	public Session getSessionByID(String id) throws IDNotFoundException {
		if (sessionsMap.containsKey(id)) {
			return sessionsMap.get(id);
		} else {
			throw new IDNotFoundException(ElementType.Session, id);
		}
	}

	/**
	 * Metodo che restituisce la Session identificata dall'hash passata come
	 * parametro. Se non viene trovata l'hash si lancia una
	 * SessionNotFoundException.
	 * 
	 * @param hash
	 *            hash che caratterizza la Session da trovare
	 * @return ritorna la Session richiesta
	 * @throws SessionNotFoundException
	 *             eccezione lanciata se non viene trovata l'hash e quindi la
	 *             session
	 */
	public Session getSessionByHash(String hash)
			throws SessionNotFoundException {
		if (hashMap.containsKey(hash)) {
			String id = hashMap.get(hash);
			// si ritorna il costumer associato
			return sessionsMap.get(id);
		} else {
			throw new SessionNotFoundException(hash);
		}
	}

	/** Metodo che restituisce tutte le Session nei dati*/
	public Collection<Session> getAllSessions(){
		return sessionsMap.values();
	}
	
	/**
	 * Metodo che verifica l'esistenza di una Session identificata dall'id passato come parametro.
	 * @param ID id da controllare
	 * @return restituisce True se esiste la session con l'id passato come parametro
	 */
	public boolean exists(String ID) {
		if (sessionsMap.containsKey(ID)) {
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
			if (!sessionsMap.containsKey(id)) {
				// se non esiste si esce
				exist = false;
			}
		}
		return id;
	}

	/**
	 * Metodo pubblico che filtra le Session secondo vari parametri. Se non si
	 * vuole utilizzare un parametro impostarlo a null;
	 * 
	 * @param workID
	 *            ID del Work a cui è associata la Session
	 * @param costumerID
	 *            ID del Costumer a cui è associata la Session
	 * @param sessionData
	 *            Data della Session
	 * @param hours
	 *            numero di ore della Session
	 * @param param1
	 *            parametro che indica se bisogna cercare le Session con numero
	 *            di ore uguale, maggiore o minore di quelle passate come
	 *            parametro.
	 * @param spesa
	 *            spesa della Session
	 * @param param2
	 *            parametro che indica se bisogna cercare le Session con spesa
	 *            uguale, maggiore o minore di quella passata come parametro.
	 * @return ritorna la lista di Session che soddisfa tutti i parametri.
	 */
	public Collection<Session> queryByArguments(String workID, String costumerID,
			GregorianCalendar sessionData, int hours, int param1, int spesa,
			int param2) {
		// si ricavano tutte le session da cercare
		Collection<Session> set =sessionsMap.values();
		// si ricerca
		if (workID != null && !workID.equals("")) {
			set = this.queryByWorkID(workID, set);
		}
		if (costumerID != null && !costumerID .equals("")) {
			set = this.queryByCostumerID(costumerID, set);
		}
		if (sessionData != null) {
			set = this.queryBySessionData(sessionData, set);
		}
		if (hours != 0) {
			// il param1 indica se bisogna cerca i valori uguali, maggiorni,
			// minori
			// (0=uguali; 1= maggiori; 2= minori);
			if (param1 == 0) {
				set = this.queryByHours(hours, set);
			} else if (param1 == 1) {
				// si cercano quelli maggiori
				set = this.queryByMinHours(hours, set);
			} else if (param1 == 2) {
				// si cercano quelli minori
				set = this.queryByMaxHours(hours, set);
			}
		}
		if (spesa != 0) {
			// il param2 indica se bisogna cerca i valori uguali, maggiorni,
			// minori
			// (0=uguali; 1= maggiori; 2= minori);
			if (param2 == 0) {
				set = this.queryBySpesa(spesa, set);
			} else if (param2 == 1) {
				// si cercano quelli maggiori
				set = this.queryByMinSpesa(spesa, set);
			} else if (param2 == 2) {
				// si cercano quelli minori
				set = this.queryByMaxSpesa(spesa, set);
			}
		}
		// si ritorna il risultato
		return set;
	}

	/**
	 * Metodo che filtra le Session in base al'ID del Work
	 * 
	 * @param workID
	 *            ID del Work da cercare
	 * @param set
	 *            lista di Session in cui cercare
	 * @return ritorna le Session trovate
	 */
	private List<Session> queryByWorkID(String workID, Collection<Session> set) {
		List<Session> temp = new ArrayList<>();
		for (Session ses : set) {
			if (ses.getWorkID().equals(workID)) {
				temp.add(ses);
			}
		}
		return temp;
	}

	/**
	 * Metodo publico che filta le Session in base all'ID del Work e non
	 * richiede la lista in cui cercare.
	 * 
	 * @param workID
	 *            ID del Work da cercare
	 * @return ritorna le Session trovate
	 */
	public List<Session> queryByWorkID(String workID) {
		return this.queryByWorkID(workID, sessionsMap.values());
	}

	/**
	 * Metodo che filtra le Session in base al'ID del Costumer
	 * 
	 * @param costumerID
	 *            ID del Work da cercare
	 * @param set
	 *            lista di Session in cui cercare
	 * @return ritorna le Session trovate
	 */
	private List<Session> queryByCostumerID(String costumerID,Collection<Session> set) {
		List<Session> temp = new ArrayList<>();
		for (Session ses : set) {
			if (ses.getCostumerID().equals(costumerID)) {
				temp.add(ses);
			}
		}
		return temp;
	}

	/**
	 * Metodo publico che filta le Session in base all'ID del Costumer e non
	 * richiede la lista in cui cercare.
	 * 
	 * @param costumerID
	 *            ID del Costumer da cercare
	 * @return ritorna le Session trovate
	 */
	public List<Session> queryByCostumerID(String costumerID) {
		return this.queryByCostumerID(costumerID,sessionsMap.values());
	}

	/**
	 * Metodo che filtra le Session in base alla data della session
	 * 
	 * @param calendar2
	 *            data da cercare
	 * @param set
	 *            lista di Session in cui cercare
	 * @return ritorna le Session trovate
	 */
	private List<Session> queryBySessionData(Calendar calendar2,
			Collection<Session> set) {
		List<Session> temp = new ArrayList<>();
		for (Session ses : set) {
			if (Utility.equalsDate(calendar2, ses.getSessionData())) {
				temp.add(ses);
			}
		}
		return temp;
	}

	/**
	 * Metodo publico che filta le Session in base alla data della session
	 * 
	 * @param calendar2
	 *            data da cercare
	 * 
	 * @return ritorna le Session trovate
	 */
	public List<Session> queryBySessionData(Calendar calendar2) {
		return this.queryBySessionData(calendar2, sessionsMap.values());
	}

	/**
	 * Metodo che filtra le Session in base al numero di ore preciso. Vengono
	 * restituite le Session con numero di ore uguale a hours.
	 * 
	 * @param hours
	 *            numero di ore da cercare
	 * @param set
	 *            lista di Session in cui cercare
	 * @return ritorna le Session trovate
	 */
	private List<Session> queryByHours(int hours, Collection<Session> set) {
		List<Session> temp = new ArrayList<>();
		for (Session ses : set) {
			if (ses.getHours() == hours) {
				temp.add(ses);
			}
		}
		return temp;
	}

	/**
	 * Metodo pubblico che filtra le Session in base al numero di ore preciso.
	 * Vengono restituite le Session con numero di ore uguale a hours.
	 * 
	 * @param hours
	 *            numero di ore da cercare
	 * 
	 * @return ritorna le Session trovate
	 */
	public List<Session> queryByHours(int hours) {
		return this.queryByHours(hours, sessionsMap.values());
	}

	/**
	 * Metodo che filtra le Session in base al numero di ore. Vengono restituite
	 * le Session con numero di ore maggiore o uguale a hours.
	 * 
	 * @param hours
	 *            numero di ore minime da cercare
	 * @param set
	 *            lista di Session in cui cercare
	 * @return ritorna le Session trovate
	 */
	private List<Session> queryByMinHours(int hours, Collection<Session> set) {
		List<Session> temp = new ArrayList<>();
		for (Session ses : set) {
			if (ses.getHours() >= hours) {
				temp.add(ses);
			}
		}
		return temp;
	}

	/**
	 * Metodo pubblico che filtra le Session in base al numero di ore. Vengono
	 * restituite le Session con numero di ore maggiore o uguale a hours.
	 * 
	 * @param hours
	 *            numero di ore minime da cercare
	 * 
	 * @return ritorna le Session trovate
	 */
	public List<Session> queryByMinHours(int hours) {
		return this.queryByMinHours(hours, sessionsMap.values());
	}

	/**
	 * Metodo che filtra le Session in base al numero di ore. Vengono restituite
	 * le Session con numero di ore minore o uguale a hours.
	 * 
	 * @param hours
	 *            numero di ore massime da cercare
	 * @param set
	 *            lista di Session in cui cercare
	 * @return ritorna le Session trovate
	 */
	private List<Session> queryByMaxHours(int hours, Collection<Session> set) {
		List<Session> temp = new ArrayList<>();
		for (Session ses : set) {
			if (ses.getHours() <= hours) {
				temp.add(ses);
			}
		}
		return temp;
	}

	/**
	 * Metodo che filtra le Session in base al numero di ore. Vengono restituite
	 * le Session con numero di ore minore o uguale a hours.
	 * 
	 * @param hours
	 *            numero di ore massime da cercare
	 * 
	 * @return ritorna le Session trovate
	 */
	public List<Session> queryByMaxHours(int hours) {
		return this.queryByMaxHours(hours, sessionsMap.values());
	}

	/**
	 * Metodo che filtra le Session in base alla spesa. Questo metodo ritorna le
	 * Session con spesa uguale a spesa.
	 * 
	 * @param spesa
	 *            spesa da cercare
	 * @param set
	 *            lista in cui cercare
	 * @return ritorna la lista di Session trovate
	 */
	private List<Session> queryBySpesa(int spesa, Collection<Session> set) {
		List<Session> temp = new ArrayList<>();
		for (Session ses : set) {
			if (ses.getSpesa() == spesa) {
				temp.add(ses);
			}
		}
		return temp;
	}

	/**
	 * Metodo pubblico che filtra le Session in base alla spesa. Questo metodo
	 * ritorna le Session con spesa uguale a spesa.
	 * 
	 * @param spesa
	 *            spesa da cercare
	 * 
	 * @return ritorna la lista di Session trovate
	 */
	public List<Session> queryBySpesa(int spesa) {
		return this.queryBySpesa(spesa, sessionsMap.values());
	}

	/**
	 * Metodo che filtra le Session in base alla spesa. Questo metodo ritorna le
	 * Session con spesa minore o uguale a spesa.
	 * 
	 * @param spesa
	 *            spesa massima da cercare
	 * @param set
	 *            lista in cui cercare
	 * @return ritorna la lista di Session trovate
	 */
	private List<Session> queryByMaxSpesa(int spesa, Collection<Session> set) {
		List<Session> temp = new ArrayList<>();
		for (Session ses : set) {
			if (ses.getSpesa() <= spesa) {
				temp.add(ses);
			}
		}
		return temp;
	}

	/**
	 * Metodo pubblico che filtra le Session in base alla spesa. Questo metodo
	 * ritorna le Session con spesa minore o uguale a spesa.
	 * 
	 * @param spesa
	 *            spesa massima da cercare
	 * 
	 * @return ritorna la lista di Session trovate
	 */
	public List<Session> queryByMaxSpesa(int spesa) {
		return this.queryByMaxSpesa(spesa, sessionsMap.values());
	}

	/**
	 * Metodo che filtra le Session in base alla spesa. Questo metodo ritorna le
	 * Session con spesa maggiore o uguale a spesa.
	 * 
	 * @param spesa
	 *            spesa minima da cercare
	 * @param set
	 *            lista in cui cercare
	 * @return ritorna la lista di Session trovate
	 */
	private List<Session> queryByMinSpesa(int spesa, Collection<Session> set) {
		List<Session> temp = new ArrayList<>();
		for (Session ses : set) {
			if (ses.getSpesa() >= spesa) {
				temp.add(ses);
			}
		}
		return temp;
	}

	/**
	 * Metodo pubblico che filtra le Session in base alla spesa. Questo metodo
	 * ritorna le Session con spesa maggiore o uguale a spesa.
	 * 
	 * @param spesa
	 *            spesa minima da cercare
	 * 
	 * @return ritorna la lista di Session trovate
	 */
	public List<Session> queryByMinSpesa(int spesa) {
		return this.queryByMinSpesa(spesa, sessionsMap.values());
	}
	// non si eseguono query per i materiali
}
