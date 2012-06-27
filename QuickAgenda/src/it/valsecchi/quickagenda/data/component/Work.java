package it.valsecchi.quickagenda.data.component;

import it.valsecchi.quickagenda.data.Utility;

import java.util.Calendar;

/**
 * Classe che rappresenta un singolo lavoro (ad esempio un cantiere, un
 * appartamento);
 * 
 * @author Davide Valsecchi
 * @version 1.0
 */
public class Work {

	/** Codice di 8 cifre */
	private String ID;
	/**
	 * L'hash identifica in modo univoco l'oggetto.Ricavata unendo
	 * (clienteid,indirizzo,iniziolavori, finelavori)
	 */
	private String hash;
	/** ID del cliente a cui appartiene il lavoro */
	private String costumerID;
	/**Nome del lavoro,identificativo*/
	private String nome;
	private String indirizzo;
	private Calendar inizioLavori;
	private Calendar fineLavori;
	private boolean completed = false;

	/** Costruttore di Word */
	public Work(String id, String clienteid, String _nome,String _indirizzo,
			Calendar iniziolavori, Calendar finelavori, boolean _completed) {
		ID = id;
		costumerID = clienteid;
		nome = _nome;
		indirizzo = _indirizzo;
		inizioLavori = iniziolavori;
		fineLavori = finelavori;
		completed = _completed;
		// si crea l'hash unendo (id,clienteid,indirizzo,iniziolavori)
		hash = Utility.getHash(clienteid + indirizzo + iniziolavori.toString()
				+ finelavori.toString());
	}

	/** Costruttore di Work con Hash */
	public Work(String id, String _hash, String costumerid, String _nome,String _indirizzo,
			Calendar iniziolavori, Calendar finelavori, boolean _completed) {
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
	public static String calculateWorkHash(String costumereid,String nome,
			String indirizzo, Calendar inizio, Calendar fine) {
		return Utility.getHash(costumereid + nome+ indirizzo + inizio.toString()
				+ fine.toString());
	}

	/** Metodo che calcola l'arco di tempo in cui il lavoro � stato attivo */
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

	public void setInizioLavori(Calendar inizioLavori) {
		this.inizioLavori = inizioLavori;
	}

	public Calendar getFineLavori() {
		return fineLavori;
	}

	public void setFineLavori(Calendar fineLavori) {
		this.fineLavori = fineLavori;
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

}
