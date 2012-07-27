package it.valsecchi.quickagenda.data.component;

import it.valsecchi.quickagenda.data.Utility;
import it.valsecchi.quickagenda.data.Utility.DateString;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Classe che rappresenta un singolo lavoro (ad esempio un cantiere, un
 * appartamento);
 * 
 * @author Davide Valsecchi
 * @version 1.0
 */
public class Work implements Comparable<Work>{

	/** Codice di 8 cifre */
	private String ID;
	/**
	 * L'hash identifica in modo univoco l'oggetto.Ricavata unendo
	 * (clienteid,indirizzo,iniziolavori, finelavori)
	 */
	private String hash;
	/** ID del cliente a cui appartiene il lavoro */
	private String costumerID;
	/** Nome del lavoro,identificativo */
	private String nome;
	private String indirizzo;
	private Calendar inizioLavori;
	private Calendar fineLavori;
	private boolean completed = false;

	/** Costruttore di Word */
	public Work(String id, String costumerid, String _nome, String _indirizzo,
			Calendar iniziolavori, Calendar finelavori, boolean _completed) {
		ID = id;
		costumerID = costumerid;
		nome = _nome;
		indirizzo = _indirizzo;
		inizioLavori = iniziolavori;
		fineLavori = finelavori;
		completed = _completed;
		// si crea l'hash unendo (clienteid,nome,indirizzo,iniziolavori)
		hash = Utility.getHash(costumerID + nome + indirizzo
				+ Utility.formatCalendarToString(iniziolavori));
	}

	/** Costruttore di Work con Hash */
	public Work(String id, String costumerid, String _nome, String _indirizzo,
			Calendar iniziolavori, Calendar finelavori, boolean _completed,
			String _hash) {
		ID = id;
		hash = _hash;
		costumerID = costumerid;
		nome = _nome;
		indirizzo = _indirizzo;
		inizioLavori = iniziolavori;
		fineLavori = finelavori;
		completed = _completed;
	}

	/**
	 * Metodo statico che restituisce l'hash di un Work identificato dai dati
	 * parametro.
	 */
	public static String calculateWorkHash(String costumereid, String nome,
			String indirizzo, Calendar inizio) {
		return Utility.getHash(costumereid + nome + indirizzo
				+ Utility.formatCalendarToString(inizio));
	}

	/**
	 * Metodo che ricalcola l'hash della Work. La assegna al Work e la
	 * restituisce.
	 * 
	 * @return restituisce la nuova hash del Work
	 */
	public String recalculateWorkHash() {
		this.hash = Utility.getHash(this.costumerID + this.nome
				+ this.indirizzo + this.getInizioLavoriString());
		return hash;
	}

	/** Metodo che calcola l'arco di tempo in cui il lavoro è stato attivo */
	public long getWorkNumberOfDays() {
		long start = inizioLavori.getTimeInMillis();
		long end = fineLavori.getTimeInMillis();
		long dif = end - start;
		return dif / (86400000);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Calendar getInizioLavori() {
		return inizioLavori;
	}

	/** Metodo che restituisce l'InizioLavori come stringa già formattata */
	public String getInizioLavoriString() {
		return Utility.formatCalendarToString(this.inizioLavori);
	}

	/**
	 * Metodo che restituisce l'inizio lavori come oggetto DateString che
	 * possiede capacità di ordinamento
	 */
	public DateString getInizioLavoriDateString() {
		return new Utility.DateString(this.inizioLavori);
	}

	public void setInizioLavori(Calendar inizioLavori) {
		this.inizioLavori = inizioLavori;
	}

	/**
	 * Metodo che imposta l'InizioLavori accettando come parametro una stringa
	 * nel formato "dd/MM/yy".
	 * 
	 * @param inizioLavori
	 *            stringa che rappresenta la data
	 * @throws ParseException
	 */
	public void setInizioLavori(String inizioLavori) throws ParseException {
		this.inizioLavori = Utility.parseStringToCalendar(inizioLavori);
	}

	public Calendar getFineLavori() {
		return fineLavori;
	}

	/** Metodo che restituisce il FineLavori come stringa già formattata */
	public String getFineLavoriString() {
		return Utility.formatCalendarToString(this.fineLavori);
	}

	/**
	 * Metodo che restituisce la fine lavori come oggetto DateString che
	 * possiede capacità di ordinamento
	 */
	public DateString getFineLavoriDateString() {
		return new Utility.DateString(this.fineLavori);
	}

	public void setFineLavori(Calendar fineLavori) {
		this.fineLavori = fineLavori;
	}

	/**
	 * Metodo che imposta il FineLavori accettando come parametro una stringa
	 * nel formato "dd/MM/yy".
	 * 
	 * @param fineLavori
	 *            stringa che rappresenta la data
	 * @throws ParseException
	 */
	public void setFineLavori(String fineLavori) throws ParseException {
		this.fineLavori = Utility.parseStringToCalendar(fineLavori);
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public String getID() {
		return ID;
	}

	public String getHash() {
		return hash;
	}

	public String getCostumerID() {
		return costumerID;
	}

	public void setCostumerID(String costumerID) {
		this.costumerID = costumerID;
	}

	@Override
	/**
	 *  Metodo che compara i Work per l'interfaccia Comparable.
	 * L'ordinamento naturale dei Work è l'inizio dei lavori.
	 */
	public int compareTo(Work arg) {
		return Utility.compareDate(this.getInizioLavori(),arg.getInizioLavori());
	}

}
