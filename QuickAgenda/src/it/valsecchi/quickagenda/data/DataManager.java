package it.valsecchi.quickagenda.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import it.valsecchi.quickagenda.data.component.Session;
import it.valsecchi.quickagenda.data.component.SessionsManager;
import it.valsecchi.quickagenda.data.component.Work;
import it.valsecchi.quickagenda.data.component.WorksManager;
import it.valsecchi.quickagenda.data.component.exception.CostumerAlreadyExistsException;
import it.valsecchi.quickagenda.data.component.exception.IDAlreadyExistsException;
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
 * dei dati al codice client.
 * 
 * La classe implementa varie interfaccie che stabiliscono tutte le azioni che
 * può compiere: -AddCostumerInterface: funzionalità di aggiunta di un Costumer
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class DataManager implements AddCostumerInterface {

	private CostumersManager costumersMan;
	private WorksManager worksMan;
	private SessionsManager sessionsMan;
	private char[] password;
	private String path;

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
			String id, hash, costumerid, indirizzo;
			GregorianCalendar inizio, fine;
			boolean completed;
			id = w.getChildText("id");
			hash = w.getChildText("hash");
			costumerid = w.getChildText("costumerid");
			indirizzo = w.getChildText("indirizzo");
			inizio = (GregorianCalendar) GregorianCalendar.getInstance();
			inizio.setTime(formatdata.parse(w.getChildText("iniziolavori")));
			fine = (GregorianCalendar) GregorianCalendar.getInstance();
			fine.setTime(formatdata.parse(w.getChildText("finelavori")));
			completed = Boolean.parseBoolean(w.getChildText("completed"));
			// creazione della work
			Work newWork = new Work(id, hash, costumerid, indirizzo, inizio,
					fine, completed);
			works.add(newWork);
		}
		// si ricava lista di Sessions
		List<Element> sessionElem = root.getChildren("session");
		List<Session> sessions = new ArrayList<>();
		for (Element s : sessionElem) {
			String id, hash, workid, costumerid;
			GregorianCalendar sessiondata;
			int hours, spesa;
			List<String> materiali = new ArrayList<>();
			id = s.getChildText("id");
			hash = s.getChildText("hash");
			workid = s.getChildText("workid");
			costumerid = s.getChildText("costumerid");
			sessiondata = (GregorianCalendar) GregorianCalendar.getInstance();
			sessiondata
					.setTime(formatdata.parse(s.getChildText("sessiondata")));
			hours = Integer.getInteger(s.getChildText("hours"));
			spesa = Integer.getInteger(s.getChildText("spesa"));
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
	 */
	public static DataManager createVoidDataManager(String path, char[] password) {
		Log.info("creazione di un DataManager vuoto");
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
			// indirizzo
			Element indirizzo = new Element("indirizzo");
			indirizzo.setText(w.getIndirizzo());
			newW.addContent(indirizzo);
			// iniziolavori
			Element iniziolavori = new Element("iniziolavori");
			iniziolavori.setText(formatdata.format(w.getInizioLavori()));
			newW.addContent(iniziolavori);
			// finelavori
			Element finelavori = new Element("finelavori");
			finelavori.setText(formatdata.format(w.getFineLavori()));
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
			sessiondata.setText(formatdata.format(s.getSessionData()));
			newS.addContent(sessiondata);
			// hours
			Element hours = new Element("hours");
			hours.setText(Integer.toString(s.getHours()));
			newS.addContent(hours);
			// spesa
			Element spesa = new Element("spesa");
			spesa.setText(Integer.toString(s.getSpesa()));
			// materiali
			StringBuilder build = new StringBuilder();
			for (String m : s.getMateriali()) {
				build.append(m + "#");
			}
			build.deleteCharAt(build.length() - 1);
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
	 * Metodo di collegamento con il costumersManager. Il metodo aggiunge un
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

}
