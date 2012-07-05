package it.valsecchi.quickagenda.data.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import it.valsecchi.quickagenda.data.Utility;

/**
 * La classe rappresenta una singola sessione di lavoro in un certo Work
 * 
 * @author Davide Valsecchi
 * @version 1.0
 */
public class Session {

	/** Codice di 8 cifre */
	private String ID;

	/**
	 * Ricavata unendo (workid,clienteid,sessiondata). L'hash identifica in modo
	 * univoco l'oggetto.
	 */
	private String hash;
	/** ID del lavoro a cui appartiene la sessione */
	private String workID;
	/** ID del cliente a cui appartiene la sessione */
	private String costumerID;
	/** Data della sessione */
	private Calendar sessionData;
	/** Numero di ore della sessione */
	private int hours;
	/** Spese della sessione */
	private int spesa;
	/** Lista dei materiali della sessione */
	private List<String> materiali;
	/** Formattatore per le date*/
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

	/** Costruttore di Session */
	public Session(String id, String workid, String costumerid,
			GregorianCalendar sessiondata, int _hours, int _spesa,
			List<String> _materiali) {
		ID = id;
		workID = workid;
		costumerID = costumerid;
		sessionData = sessiondata;
		hours = _hours;
		spesa = _spesa;
		materiali = _materiali;
		// si ricava l'hash unendo (id,workid,costumerid)
		hash = Utility.getHash(workid + costumerid + sessiondata.toString());
	}

	/** Costruttore di Session con Hash fornita */
	public Session(String id, String _hash, String workid, String costumerid,
			Calendar sessiondata, int _hours, int _spesa,
			List<String> _materiali) {
		ID = id;
		hash = _hash;
		workID = workid;
		costumerID = costumerid;
		sessionData = sessiondata;
		hours = _hours;
		spesa = _spesa;
		materiali = _materiali;
	}

	/**
	 * Metodo statico che restituisce l'hash di una Session identificata dai
	 * dati parametro.
	 */
	public static String calculateSessionHash(String workid, String costumerid,
			Calendar sessiondata2) {
		return Utility.getHash(workid + costumerid + sessiondata2.toString());
	}

	public Calendar getSessionData() {
		return sessionData;
	}
	
	/** Metodo che restituisce la SessionData come stringa già formattata*/
	public String getSessionDataString(){
		return formatter.format(this.sessionData.getTime());
	}

	public void setSessionData(Calendar calendar) {
		sessionData = calendar;
	}
	
	/** 
	 * Metodo che imposta la SessionData accettando come parametro una stringa nel formato "dd/MM/yy".
	 * @param data stringa che rappresenta la data
	 * @throws ParseException
	 */
	public void setSessionData(String data) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(formatter.parse(data));
		this.sessionData= c;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int _hours) {
		hours = _hours;
	}

	public int getSpesa() {
		return spesa;
	}

	public void setSpesa(int _spesa) {
		spesa = _spesa;
	}

	public List<String> getMateriali() {
		return materiali;
	}

	public void setMateriali(List<String> _materiali) {
		materiali = _materiali;
	}

	public String getID() {
		return ID;
	}

	public String getHash() {
		return hash;
	}

	public String getWorkID() {
		return workID;
	}

	public String getCostumerID() {
		return costumerID;
	}

	public void setCostumerID(String costumerID) {
		this.costumerID = costumerID;
	}
}
