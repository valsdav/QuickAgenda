package it.valsecchi.quickagenda.data.component;

import it.valsecchi.quickagenda.data.Utility;

/**
 * Classe che rappresenta un cliente
 * 
 * @author Davide
 * @version 1.0
 */
public class Costumer {

	/** L'id è sempre un codice di 8 cifre */
	private String ID;
	/**
	 * L'hash identifica in modo univoco l'oggetto. Ricavata unendo
	 * nome,cognome, indirizzo, azienda per cercare l'hash
	 */
	private String hash;
	/** Nome del cliente */
	private String nome;
	/** Cognome del cliente */
	private String cognome;
	/** Azienda del cliente */
	private String azienda;
	/** Indirizzo del cliente */
	private String indirizzo;
	/** Telefono del cliente */
	private String tel;
	/** Email del cliente */
	private String email;

	/**
	 * Costruttore di Costumer
	 * 
	 * @param id
	 *            l'id viene fornito dal creatore
	 * @param _nome
	 *            nome del cliente
	 * @param _cognome
	 *            cognome del cliente
	 * @param _azienda
	 *            azienda del cliente
	 * @param _indirizzo
	 *            indirizzo del cliente
	 * @param _tel
	 *            telefono del cliente
	 * @param _email
	 *            email del cliente
	 */
	public Costumer(String id, String _nome, String _cognome, String _azienda,
			String _indirizzo, String _tel, String _email) {
		ID = id;
		nome = _nome;
		cognome = _cognome;
		azienda = _azienda;
		indirizzo = _indirizzo;
		tel = _tel;
		email = _email;
		// si ricava l'hash con l'indirizzo
		hash = Utility.getHash(indirizzo);
	}

	/** Costruttore comprensivo di Hash */
	public Costumer(String id, String _hash, String _nome, String _cognome,
			String _azienda, String _indirizzo, String _tel, String _email) {
		ID = id;
		hash = _hash;
		nome = _nome;
		cognome = _cognome;
		azienda = _azienda;
		indirizzo = _indirizzo;
		tel = _tel;
		email = _email;
	}

	/**
	 * Metodo statico che restituisce l'hash di un cliente identificato dai dati
	 * parametro.
	 */
	public static String calculateCostumerHash(String indirizzo) {
		return Utility.getHash(indirizzo);
	}

	/**
	 * Metodo che ricalcola l'hash del Costumer. La assegna al Costumer e la
	 * restituisce.
	 * 
	 * @return restituisce la nuova hash del Costumer
	 */
	public String recalculateCostumerHash() {
		this.hash = Utility.getHash(this.indirizzo);
		return hash;
	}

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return tel;
	}

	public void setTelefono(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getID() {
		return ID;
	}

	public String getHash() {
		return hash;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}
