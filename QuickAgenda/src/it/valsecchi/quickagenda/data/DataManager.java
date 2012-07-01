package it.valsecchi.quickagenda.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import static it.valsecchi.quickagenda.data.Utility.Log;
import it.valsecchi.quickagenda.data.component.Costumer;
import it.valsecchi.quickagenda.data.component.CostumersManager;
import it.valsecchi.quickagenda.data.component.ElementType;
import it.valsecchi.quickagenda.data.component.Session;
import it.valsecchi.quickagenda.data.component.SessionsManager;
import it.valsecchi.quickagenda.data.component.Work;
import it.valsecchi.quickagenda.data.component.WorksManager;
import it.valsecchi.quickagenda.data.component.exception.CostumerAlreadyExistsException;
import it.valsecchi.quickagenda.data.component.exception.IDAlreadyExistsException;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.component.exception.SessionAlreadyExistsException;
import it.valsecchi.quickagenda.data.component.exception.WorkAlreadyExistsException;
import it.valsecchi.quickagenda.data.exception.CryptographyException;
import it.valsecchi.quickagenda.data.exception.InsufficientDataException;
import it.valsecchi.quickagenda.data.exception.InvalidPasswordException;
import it.valsecchi.quickagenda.data.interfaces.*;

/**
 * Classe che gestisce l'intera banca dati dell'applicazione. Il carimento dei
 * dati, la loro scrittura attraverso DataReaderWriter. Gestisce tutti i
 * componenti grazia a un CostumersManager, un WorksManager e un
 * SessionsManager. Fornisce tutte le funzioni di ricerca, aggiunta ed rimozione
 * dei dati al codice client. Inoltre fornisce un meccanismo di notifica
 * dell'aggiornamento dei dati tramite listeners;
 * 
 * La classe implementa varie interfaccie che stabiliscono tutte le azioni che
 * può compiere: -AddCostumerInterface: funzionalità di aggiunta di un Costumer
 * -AddSessionInterface: funzionalità di aggiunta di un Costumer
 * -AddWorkInterface: funzionalità di aggiunta di un Work
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class DataManager implements AddCostumerInterface, AddSessionInterface,
		AddWorkInterface {

	/** Gestore dei Costumer */
	private CostumersManager costumersMan;
	/** Gestore dei Work */
	private WorksManager worksMan;
	/** Gestore delle Session */
	private SessionsManager sessionsMan;
	// si memorizza password e path
	private char[] password;
	private String path;

	/** Listener per l'evento di aggiornamento dei dati dei Costumers */
	private List<DataUpdateListener> costumersListeners = new ArrayList<>();
	/** Listener per l'evento di aggiornamento dei dati dei Works */
	private List<DataUpdateListener> worksListeners = new ArrayList<>();
	/** Listener per l'evento di aggiornamento dei dati delle Sessions */
	private List<DataUpdateListener> sessionsListeners = new ArrayList<>();

	/**
	 * Costruttore privato della classe che accetta 3 liste con i dati: i
	 * costumers, i works e le sessions. I dati vengono letti nel metodo factory
	 * {@link #loadDataManager(String, String)}. Il costruttore carica solo gli
	 * oggetti manager dei dati.
	 * 
	 * @param _path
	 *            percorso del file del DataManager
	 * @param _password
	 *            char array che contiene la password del DataManager
	 * @param costumers
	 *            lista di costumers
	 * @param works
	 *            lista di works
	 * @param sessions
	 *            lista di sessions
	 */
	private DataManager(String _path, char[] _password,
			List<Costumer> costumers, List<Work> works, List<Session> sessions) {
		// si devono caricare i dati
		Log.info("creazione oggetto DataManager");
		// inizializzazione CostumerManager
		costumersMan = new CostumersManager();
		// aggiunta dati
		Log.info("caricamento vari manager dei dati");
		for (Costumer c : costumers) {
			// si aggiunge
			try {
				costumersMan.addCostumer(c);
			} catch (CostumerAlreadyExistsException | IDAlreadyExistsException e) {
				// in caso si errore si tralascia
				continue;
			}
		}
		// inizializzazione WorksManager
		worksMan = new WorksManager();
		// aggiunta dati
		for (Work w : works) {
			try {
				worksMan.addWork(w);
			} catch (WorkAlreadyExistsException | IDAlreadyExistsException e) {
				// in caso si errore si tralascia
				continue;
			}
		}
		// inizializzazione SessionsManager
		sessionsMan = new SessionsManager();
		// aggiunta dati
		for (Session s : sessions) {
			try {
				sessionsMan.addSession(s);
			} catch (SessionAlreadyExistsException | IDAlreadyExistsException e) {
				// in caso si errore si tralascia
				continue;
			}
		}
		// si imposta path e password
		path = _path;
		password = _password;
		Log.info("caricamento completato!");

	}

	/**
	 * Costruttore che richiede path e password del DataManager. Il costruttore
	 * inizializza i dati vuoti.
	 */
	private DataManager(String _path, char[] _password) {
		// si inizializzano i manager
		costumersMan = new CostumersManager();
		worksMan = new WorksManager();
		sessionsMan = new SessionsManager();
		// si imposta path e password
		path = _path;
		password = _password;
	}

	/**
	 * Metodo factory che legge un file dati con path passata come parametro e
	 * password. Se il caricamento avviene con successo viene inizializzato e
	 * restituito un oggetto DataManager contenente i dati.
	 * 
	 * @param path
	 *            path del file di dati
	 * @param password
	 *            password del file
	 * @return ritorna un oggetto DataManager contenente tutti i dati
	 *         organizzati
	 * @throws IOException
	 *             lanciata per errori di IO
	 * @throws CryptographyException
	 *             lanciata per errori di crittografia
	 * @throws JDOMException
	 *             lanciata per errori nel file xml
	 * @throws InvalidPasswordException
	 *             lanciata in caso di utilizzo di password scorretta
	 * @throws ParseException
	 */
	public static DataManager loadDataManager(String path, char[] password)
			throws IOException, CryptographyException, JDOMException,
			InvalidPasswordException, ParseException {
		Document doc;
		try {
			// si cerca di caricare in dati con DataReaderWriter
			Log.info("creazione del reader per leggere i dati");
			DataReaderWriter reader = DataReaderWriter.createDataReaderWriter(
					path, DataReaderWriter.READ_MODE);
			Log.info("lettura del document");
			doc = reader.readData(password);
			// si elimina il reader
			reader = null;
		} catch (IllegalBlockSizeException e) {
			Log.error("Errore di criptografia generico!");
			throw new CryptographyException("Errore di criptografia generico!");
		} catch (BadPaddingException e) {
			Log.error("Errore di criptografia generico!");
			throw new CryptographyException("Errore di criptografia generico!");
		} catch (IOException e) {
			Log.error("Errore IO!");
			throw e;
		} catch (CryptographyException e) {
			Log.error("Errore di criptografia generico!");
			throw new CryptographyException("Errore di criptografia generico!");
		} catch (JDOMException e) {
			Log.error("Errore di parse XML!");
			throw e;
		} catch (InvalidPasswordException e) {
			Log.error("Errore password!");
			throw e;
		}
		// si leggono i dati
		Log.info("lettura e caricamento singoli oggetti");
		Element root = doc.getRootElement();
		// si ricava lista di Costumers
		List<Element> costumElem = root.getChildren("costumer");
		List<Costumer> costumers = new ArrayList<>();
		for (Element c : costumElem) {
			String id, hash, nome, cognome, azienda, indirizzo, tel, email;
			id = c.getChildText("id");
			hash = c.getChildText("hash");
			nome = c.getChildText("nome");
			cognome = c.getChildText("cognome");
			azienda = c.getChildText("azienda");
			indirizzo = c.getChildText("indirizzo");
			tel = c.getChildText("tel");
			email = c.getChildText("email");
			// creazione del costumer
			Costumer newC = new Costumer(id, hash, nome, cognome, azienda,
					indirizzo, tel, email);
			costumers.add(newC);
		}
		// oggetto per formattare le date
		SimpleDateFormat formatdata = new SimpleDateFormat("dd/MM/yy");
		formatdata.setLenient(false);
		// si ricava lista di Works
		List<Element> workElem = root.getChildren("work");
		List<Work> works = new ArrayList<>();
		for (Element w : workElem) {
			String id, hash, costumerid, indirizzo, nome;
			GregorianCalendar inizio, fine;
			boolean completed;
			id = w.getChildText("id");
			hash = w.getChildText("hash");
			costumerid = w.getChildText("costumerid");
			nome = w.getChildText("nome");
			indirizzo = w.getChildText("indirizzo");
			inizio = (GregorianCalendar) GregorianCalendar.getInstance();
			inizio.setTime(formatdata.parse(w.getChildText("iniziolavori")));
			if (w.getChildText("finelavori").equals("null")) {
				fine = null;
			} else {
				fine = (GregorianCalendar) GregorianCalendar.getInstance();
				fine.setTime(formatdata.parse(w.getChildText("finelavori")));
			}
			completed = Boolean.parseBoolean(w.getChildText("completed"));
			// creazione della work
			Work newWork = new Work(id, costumerid, nome, indirizzo, inizio,
					fine, completed, hash);
			works.add(newWork);
		}
		// si ricava lista di Sessions
		List<Element> sessionElem = root.getChildren("session");
		List<Session> sessions = new ArrayList<>();
		for (Element s : sessionElem) {
			String id, hash, workid, costumerid = "";
			GregorianCalendar sessiondata = null;
			int hours = 0;
			int spesa = 0;
			List<String> materiali = new ArrayList<>();
			id = s.getChildText("id");
			hash = s.getChildText("hash");
			workid = s.getChildText("workid");
			costumerid = s.getChildText("costumerid");
			sessiondata = (GregorianCalendar) GregorianCalendar.getInstance();
			sessiondata
					.setTime(formatdata.parse(s.getChildText("sessiondata")));
			hours = Integer.parseInt("+" + s.getChildText("hours"));
			spesa = Integer.parseInt("+" + s.getChildText("spesa"));
			// StringTokenizer per i materiali
			String mat = s.getChildText("materiali");
			StringTokenizer tok = new StringTokenizer(mat, "#");
			while (tok.hasMoreTokens()) {
				materiali.add(tok.nextToken());
			}
			// creazione della session
			Session newSes = new Session(id, hash, workid, costumerid,
					sessiondata, hours, spesa, materiali);
			sessions.add(newSes);
		}
		// si crea il DataManager e lo si restituisce
		return new DataManager(path, password, costumers, works, sessions);
	}

	/**
	 * Metodo factory che restituisce un nuovo DataManager vuoto.
	 * 
	 * @return restituisce un DataManager vuoto.
	 * @throws IOException
	 *             eccezione lanciata se risulta impossibile creare il file
	 *             vuoto.
	 */
	public static DataManager createVoidDataManager(String path, char[] password)
			throws IOException {
		Log.info("creazione di un DataManager vuoto");
		// si crea il file vuoto
		try {
			Files.createFile(Paths.get(path));
		} catch (IOException e) {
			Log.error("impossibile creare nuovo file");
			throw e;
		}
		return new DataManager(path, password);
	}

	/**
	 * Metodo che salva i dati su file con criptazione utilizzando un'istanza
	 * della classe DataReaderWriter e la path e la password memorizzate nel
	 * DataManager. Il metodo prima costuisce un document formattando tutti i
	 * dati in memoria poi lo passa a
	 * {@link DataReaderWriter#writeData(Document, String)} che lo scrive su
	 * file criptato.
	 * 
	 * @throws CryptographyException
	 * @throws IOException
	 */
	public void saveData() throws CryptographyException, IOException {
		// si crea il document con tutti i dati
		Document doc = new Document();
		doc.setRootElement(new Element("quickagenda_data"));
		// ciclo sui costumer
		for (Costumer c : costumersMan.getAllCostumers()) {
			// nuovo costumer
			Element newC = new Element("costumer");
			// id
			Element id = new Element("id");
			id.setText(c.getID());
			newC.addContent(id);
			// hash
			Element hash = new Element("hash");
			hash.setText(c.getHash());
			newC.addContent(hash);
			// nome
			Element nome = new Element("nome");
			nome.setText(c.getNome());
			newC.addContent(nome);
			// cognome
			Element cognome = new Element("cognome");
			cognome.setText(c.getCognome());
			newC.addContent(cognome);
			// azienda
			Element azienda = new Element("azienda");
			azienda.setText(c.getAzienda());
			newC.addContent(azienda);
			// indirizzo
			Element indirizzo = new Element("indirizzo");
			indirizzo.setText(c.getIndirizzo());
			newC.addContent(indirizzo);
			// tel
			Element tel = new Element("tel");
			tel.setText(c.getTel());
			newC.addContent(tel);
			// email
			Element email = new Element("email");
			email.setText(c.getEmail());
			newC.addContent(email);
			// si aggiunge al document
			doc.getRootElement().addContent(newC);
		}
		// oggetto per formattare le date in stringhe
		SimpleDateFormat formatdata = new SimpleDateFormat("dd/MM/yy");
		formatdata.setLenient(false);
		// ciclo sui work
		for (Work w : worksMan.getAllWorks()) {
			// nuovo work
			Element newW = new Element("work");
			// id
			Element id = new Element("id");
			id.setText(w.getID());
			newW.addContent(id);
			// hash
			Element hash = new Element("hash");
			hash.setText(w.getHash());
			newW.addContent(hash);
			// costumerid
			Element costumerid = new Element("costumerid");
			costumerid.setText(w.getCostumerID());
			newW.addContent(costumerid);
			// nome
			Element nome = new Element("nome");
			nome.setText(w.getNome());
			newW.addContent(nome);
			// indirizzo
			Element indirizzo = new Element("indirizzo");
			indirizzo.setText(w.getIndirizzo());
			newW.addContent(indirizzo);
			// iniziolavori
			Element iniziolavori = new Element("iniziolavori");
			iniziolavori.setText(formatdata.format(w.getInizioLavori()
					.getTime()));
			newW.addContent(iniziolavori);
			// finelavori
			Element finelavori = new Element("finelavori");
			if (w.getFineLavori() != null) {
				finelavori.setText(formatdata.format(w.getFineLavori()
						.getTime()));
			} else {
				finelavori.setText("null");
			}
			newW.addContent(finelavori);
			// completed
			Element completed = new Element("completed");
			completed.setText(Boolean.toString(w.isCompleted()));
			newW.addContent(completed);
			// si aggiunge al document
			doc.getRootElement().addContent(newW);
		}
		// ciclo sulle session
		for (Session s : sessionsMan.getAllSessions()) {
			// nuova session
			Element newS = new Element("session");
			// id
			Element id = new Element("id");
			id.setText(s.getID());
			newS.addContent(id);
			// hash
			Element hash = new Element("hash");
			hash.setText(s.getHash());
			newS.addContent(hash);
			// workid
			Element workid = new Element("workid");
			workid.setText(s.getWorkID());
			newS.addContent(workid);
			// costumerid
			Element costumerid = new Element("costumerid");
			costumerid.setText(s.getCostumerID());
			newS.addContent(costumerid);
			// sessiondata
			Element sessiondata = new Element("sessiondata");
			sessiondata
					.setText(formatdata.format(s.getSessionData().getTime()));
			newS.addContent(sessiondata);
			// hours
			Element hours = new Element("hours");
			hours.setText(Integer.toString(s.getHours()));
			newS.addContent(hours);
			// spesa
			Element spesa = new Element("spesa");
			spesa.setText(Integer.toString(s.getSpesa()));
			newS.addContent(spesa);
			// materiali
			StringBuilder build = new StringBuilder();
			for (String m : s.getMateriali()) {
				build.append(m + "#");
			}
			if (build.length() > 0) {
				build.deleteCharAt(build.length() - 1);
			}
			Element materiali = new Element("materiali");
			materiali.setText(build.toString());
			newS.addContent(materiali);
			// si aggiunge al document
			doc.getRootElement().addContent(newS);
		}

		// ora si inizializza il writer
		Log.info("creazione writer");
		DataReaderWriter writer = DataReaderWriter.createDataReaderWriter(
				this.path, DataReaderWriter.WRITE_MODE);
		try {
			Log.info("scrittura e criptazione dati");
			writer.writeData(doc, this.password);
			Log.info("scrittura dati completata");
			// si elimina il writer
			writer = null;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IllegalBlockSizeException e) {
			throw new CryptographyException("Errore di criptografia generico!");
		} catch (BadPaddingException e) {
			throw new CryptographyException("Errore di criptografia generico!");
		} catch (IOException e) {
			throw e;
		} catch (CryptographyException e) {
			throw new CryptographyException("Errore di criptografia generico!");
		}
	}

	/**
	 * Metodo che imposta la password che il DataManager utilizzerà per le
	 * operazioni di lettura/scrittura dei dati.
	 * 
	 * @param pass
	 *            char array contenente la password
	 */
	public void setPassword(char[] pass) {
		// si imposta la password da usare
		this.password = pass;
	}

	/**
	 * Metodo che imposta il path che il DataManager utilizzerà per salvare i
	 * dati.
	 * 
	 * @param _path
	 *            percorso da utilizzare
	 */
	public void setPath(String _path) {
		this.path = _path;
	}

	/**
	 * Metodo che registra i listeners per gli eventi di aggiornamento dei dati
	 * in base al tipo di dato: Costumer,Work o Session.
	 * 
	 * @param listener
	 *            oggetto listener
	 * @param type
	 *            tipo di listener da registrare
	 */
	public void addDataUpdateListener(DataUpdateListener listener,
			ElementType type) {
		switch (type) {
		case Costumer:
			this.costumersListeners.add(listener);
			break;
		case Work:
			this.worksListeners.add(listener);
			break;
		case Session:
			this.sessionsListeners.add(listener);
			break;
		}
	}

	/**
	 * Metodo che lancia la notifica dell'evento di aggiornamento dei dati ai
	 * listener a seconda del tipo passato come parametro.
	 * 
	 * @param type tipo di dati per il quale lanciare l'aggiornamento
	 */
	public void fireDataUpdatePerformed(ElementType type) {
		switch (type) {
		case Costumer:
			for(DataUpdateListener l:this.costumersListeners){
				l.DataUpdatePerformed(type);
			}
			break;
		case Work:
			for(DataUpdateListener l:this.worksListeners){
				l.DataUpdatePerformed(type);
			}
			break;
		case Session:
			for(DataUpdateListener l:this.sessionsListeners){
				l.DataUpdatePerformed(type);
			}
			break;
		}
	}

	/**
	 * Metodo di collegamento con il costumersManager. Il metodo aggiunge un
	 * 
	 * costumer ai dati, richiedendo come parametri le varie caratteristiche del
	 * costumer.
	 * 
	 * @param nome
	 * @param cognome
	 * @param azienda
	 * @param indirizzo
	 * @param tel
	 * @param email
	 * @throws CostumerAlreadyExistsException
	 * @throws InsufficientDataException
	 *             eccezione lanciata in caso di insufficienza di parametri. Il
	 *             minimo sono nome e cognome.
	 */
	@Override
	public void addCostumer(String nome, String cognome, String azienda,
			String indirizzo, String tel, String email)
			throws CostumerAlreadyExistsException, InsufficientDataException {
		// si controlla che almeno nome cognome non siamo nulli
		if ((nome == null || nome.equals(""))
				|| (cognome == null || cognome.equals(""))) {
			// si lancia l'eccezione
			throw new InsufficientDataException("DataManager.addCostumer",
					"nome/cognome", "parametri insufficiente");

		}
		// si chiama il metodo
		costumersMan.addCostumer(nome, cognome, azienda, indirizzo, tel, email);
	}

	/**
	 * Metodo di collegamento con il costumersManager. Il metodo aggiunge un
	 * costumer ai dati, richiedendo come parametro
	 */
	public void addCostumer(Costumer costumer)
			throws CostumerAlreadyExistsException, IDAlreadyExistsException {
		costumersMan.addCostumer(costumer);
	}

	/**
	 * Metodo di collegamento che restituisce un Costumer per ID
	 * 
	 * @throws IDNotFoundException
	 */
	public Costumer getCostumerByID(String id) throws IDNotFoundException {
		return costumersMan.getCostumerByID(id);
	}

	/**
	 * Metodo che restiuisce il Costumer associato a una Session
	 * 
	 * @param session_id
	 * @return
	 * @throws IDNotFoundException
	 *             eccezione lanciata se l'ID della session non viene trovato
	 */
	public Costumer getCostumerFromSession(String session_id)
			throws IDNotFoundException {
		Session s = sessionsMan.getSessionByID(session_id);
		return costumersMan.getCostumerByID(s.getCostumerID());
	}

	/**
	 * Metodo che restituisce il Work associato a una Session
	 * 
	 * @param session_id
	 * @return
	 * @throws IDNotFoundException
	 *             eccezione lanciata se l'ID della session non viene trovato
	 */
	public Work getWorkFromSession(String session_id)
			throws IDNotFoundException {
		Session s = sessionsMan.getSessionByID(session_id);
		return worksMan.getWorkByID(s.getWorkID());
	}

	/**
	 * Metodo di collegamento che restituisce un Work per id
	 * 
	 * @throws IDNotFoundException
	 */
	public Work getWorkByID(String id) throws IDNotFoundException {
		return worksMan.getWorkByID(id);
	}

	/**
	 * Metodo di collegamento con il sessionsManager. Il metodo ricerca le
	 * Sessions eseguita in una certa data passata come parametro.
	 * 
	 * @param calendar2
	 *            data da ricercare
	 * @return restituisce la lista di sessioni
	 */
	public List<Session> querySessionsByDate(Calendar calendar2) {
		return sessionsMan.queryBySessionData(calendar2);
	}

	/**
	 * Metodo di collegamento per l'aggiunta di una Session ai dati.
	 */
	@Override
	public void addSession(String workid, Calendar sessiondata, int hours,
			int spesa, List<String> materiali)
			throws SessionAlreadyExistsException, InsufficientDataException,
			IDNotFoundException {
		// si controlla che workid,costumerid,sessiondata non siano
		// nulli
		if ((workid == null || workid.equals("")) || (sessiondata == null)) {
			// si lancia una InsufficientDataException
			throw new InsufficientDataException("DataManager.addSession", "",
					"parametri insufficiente");
		} else {
			// si controlla l'esistenza ddel work
			if (!worksMan.exists(workid)) {
				// si lancia l'eccezione
				throw new IDNotFoundException(ElementType.Work, workid);
			}
			// ora si ricava l'id del cliente
			String costumerid = worksMan.getWorkByID(workid).getCostumerID();
			// si chiama il metodo
			sessionsMan.addSession(workid, costumerid, sessiondata, hours,
					spesa, materiali);
		}
	}

	/**
	 * Metodo che ricava una Session dall'ID
	 * 
	 * @param id
	 *            id della session
	 * @return
	 * @throws IDNotFoundException
	 */
	public Session getSession(String id) throws IDNotFoundException {
		return sessionsMan.getSessionByID(id);
	}

	/**
	 * Ricerca i Costumer in base a un campo e a un valore.
	 * 
	 * @param campo
	 *            campo da ricercare (ad esempio: Nome)
	 * @param value
	 *            valore da ricercare
	 * @return ritorna una lista di Costumer che soddisfano il requisito
	 */
	public List<Costumer> queryCostumerByArg(String campo, String value) {
		List<Costumer> found = new ArrayList<>();
		switch (campo) {
		case "ID":
			try {
				Costumer w = costumersMan.getCostumerByID(value);
				found.add(w);
			} catch (IDNotFoundException e) {
				return null;
			}
			break;
		case "Nome":
			found.addAll(costumersMan.queryByNome(value));
			break;
		case "Cognome":
			found.addAll(costumersMan.queryByCognome(value));
			break;
		case "Azienda":
			found.addAll(costumersMan.queryByAzienda(value));
			break;
		case "Indirizzo":
			found.addAll(costumersMan.queryByIndirizzo(value));
			break;
		case "Telefono":
			found.addAll(costumersMan.queryByTel(value));
			break;
		case "Email":
			found.addAll(costumersMan.queryByEmail(value));
			break;
		default:
			// si ritornano tutti
			return costumersMan.getAllCostumers();
		}
		return found;
	}

	/** Ricerca i Work in base a un campo e a un valore */
	public List<Work> queryWorkByArg(String campo, String value)
			throws ParseException {
		List<Work> found = new ArrayList<>();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
		switch (campo) {
		case "ID":
			try {
				Work w = worksMan.getWorkByID(value);
				found.add(w);
			} catch (IDNotFoundException e) {
				return null;
			}
			break;
		case "Nome Lavoro":
			found.addAll(worksMan.queryByNome(value));
			break;
		case "Cognome Cliente":
			// si cerca il cliente
			List<Costumer> cos = costumersMan.queryByCognome(value);
			List<String> ids = new ArrayList<>();
			for (Costumer c : cos) {
				ids.add(c.getID());
			}
			for (String id : ids) {
				found.addAll(worksMan.queryByCostumerID(id));
			}
			break;
		case "Azienda Cliente":
			// si cerca il cliente
			List<Costumer> cos2 = costumersMan.queryByAzienda(value);
			List<String> ids2 = new ArrayList<>();
			for (Costumer c : cos2) {
				ids2.add(c.getID());
			}
			for (String id2 : ids2) {
				found.addAll(worksMan.queryByCostumerID(id2));
			}
			break;
		case "Indirizzo":
			found.addAll(worksMan.queryByIndirizzo(value));
			break;
		case "Inizio Lavori":
			// si formatta la data
			Date d = f.parse(value);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			found.addAll(worksMan.queryByInizioLavori(c));
			break;
		case "Fine Lavori":
			// si formatta la data
			Date d2 = f.parse(value);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(d2);
			found.addAll(worksMan.queryByFineLavori(c2));
			break;
		case "Completato":
			found.addAll(worksMan.queryByCompleted(Boolean.parseBoolean(value)));
			break;
		default:
			return worksMan.getAllWorks();
		}
		return found;
	}

	/**
	 * Metodo di collegamento che aggiunge un Work ai dati. I parametri
	 * necessari sono nome, costumerid e iniziolavori. Se almeno uno di questi è
	 * nullo viene lanciata una InsufficientDataExceptio.
	 * 
	 * @param nome
	 * @param costumerid
	 * @param indirizzo
	 * @param iniziolavori
	 * @param finelavori
	 * @param completed
	 * @throws InsufficientDataException
	 * @throws WorkAlreadyExistsException
	 * @throws IDNotFoundException
	 */
	@Override
	public void addWork(String nome, String costumerid, String indirizzo,
			Calendar iniziolavori, Calendar finelavori, boolean completed)
			throws InsufficientDataException, WorkAlreadyExistsException,
			IDNotFoundException {
		if (nome == null || nome.equals("") && costumerid == null
				|| costumerid.equals("") || iniziolavori == null) {
			// si lancia una eccezione per parametri insufficienti
			throw new InsufficientDataException("DataManager.addWork",
					"nome/costumerid/iniziolavori", "parametri insufficienti");
		} else {
			// si controlla l'esistenza del cliente
			if (!costumersMan.exists(costumerid)) {
				// si lancia l'eccezione
				throw new IDNotFoundException(ElementType.Costumer, costumerid);
			}
			// si aggiunge
			worksMan.addWork(nome, indirizzo, costumerid, iniziolavori,
					finelavori, completed);
		}
	}
}
