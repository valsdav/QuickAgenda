package it.valsecchi.quickagenda.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import it.valsecchi.quickagenda.data.exception.CryptographyException;
import it.valsecchi.quickagenda.data.exception.InvalidPasswordException;

import javax.crypto.*;
import javax.crypto.spec.*;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import static it.valsecchi.quickagenda.data.Utility.Log;

/**
 * Classe che si occupa della lettura/scrittura dei dati e delle funzioni di
 * sicurezza con la criptazione
 * 
 * @author Davide Valsecchi
 * @version 2.0
 */
class DataReaderWriter {

	private String path;
	private FileInputStream input;
	private FileOutputStream output;
	private File file;
	public static final int WRITE_MODE = 0;
	public static final int READ_MODE = 1;
	// salt per la criptazione
	private final byte[] SALT0 = { 0x12, 0x45, 0x33, 0x11, 0x2d, 0x3d, 0x14, 0x4f };
	private final byte[] SALT1 = { 0x13, 0x55, 0x23, 0x1, 0x2, 0x3d, 0x44, 0x4f };
	private final byte[] SALT2 = { 0x32, 0x42, 0x3a, 0x1d, 0x23, 0x33, 0x34, 0x3f };
	private final byte[] SALT3 = { 0x12, 0x45, 0x33, 0x11, 0x2d, 0x3d, 0x74, 0x5f };
	private final byte[] SALT4 = { 0x22, 0x31, 0x32, 0x16, 0x25, 0x3, 0x14, 0x4f };
	private final byte[] SALT5 = { 0x3a, 0x67, 0x3, 0x14, 0xd, 0xa, 0x47, 0x1f };
	private final byte[] SALT6 = { 0x11, 0x52, 0x43, 0x1d, 0x25, 0x3a, 0x4, 0x5f };
	private final byte[] SALT7 = { 0x52, 0x5, 0x3, 0x1a, 0x2e, 0x33, 0x54, 0x45 };
	private final byte[] SALT8 = { 0x2, 0x5, 0x36, 0x1b, 0x2b, 0xf, 0x24, 0x46 };
	private final byte[] SALT9 = { 0x12, 0x47, 0x3, 0x1a, 0xd, 0xd, 0x14, 0x6f };
	private final byte[] SALT10 = { 0x22, 0x4a, 0x3b, 0x1c, 0x3d, 0x35, 0x4, 0x2a };
	private final byte[] SALT11 = { 0x1b, 0x45, 0x3c, 0x11, 0xd, 0x32, 0x10, 0x4 };
	private final byte[] SALT12 = { 0x10, 0x40, 0x30, 0x1b, 0x2c, 0x3f, 0x15, 0xf };
	private final byte[] SALT13 = { 0x30, 0x4, 0x3d, 0xa, 0x2, 0x3c, 0x64, 0x4c };
	private final byte[] SALT14 = { 0x15, 0x75, 0x3, 0xc, 0x4d, 0x7d, 0x34, 0x3c };
	private final byte[] SALT15 = { 0x5a, 0x45, 0x23, 0xb, 0x28, 0x38, 0x8, 0x2f };

	/**
	 * Costruttore privato che inizializzo l'inputstream o l'outputstream a
	 * seconda della modalità.
	 * 
	 * @param _path
	 *            path del file
	 * @param mode
	 *            modalità
	 * @throws FileNotFoundException
	 *             eccezione lanciata se non viene trovato il file
	 */
	private DataReaderWriter(String _path, int mode)
			throws FileNotFoundException {
		path = _path;
		// si memorizza il file
		file = new File(path);
		if(!file.exists()){
			Log.error("Errore! File non trovato!");
			throw new FileNotFoundException();
		}
		// viene aperto lo stream
		if (mode == READ_MODE) {
			input = new FileInputStream(path);
			output = null;
			Log.info("creato DataReaderWriter in modalità READ_MODE");
		} else if (mode == WRITE_MODE) {
			input = null;
			output = new FileOutputStream(path);
			Log.info("creato DataReaderWriter in modalità WRITE_MODE");
		}
	}

	/**
	 * Metodo factory che controlla che sia passato il giusto parametro mode, se
	 * non è valido restituisce null.
	 * 
	 * @param path
	 *            path del file
	 * @param mode
	 *            modalità
	 * @return ritorna un'oggetto DataReaderWriter
	 * @throws FileNotFoundException
	 *             eccezione lanciata se non viene trovato il file
	 */
	static DataReaderWriter createDataReaderWriter(String path, int mode)
			throws FileNotFoundException {
		if (mode != WRITE_MODE && mode != READ_MODE) {
			return null;
		}
		return new DataReaderWriter(path, mode);
	}

	/**
	 * Metodo principale che legge i dati di File restituisce un Document jdom,
	 * rappresentante i dati xml.
	 * 
	 * @param path
	 *            path del file da leggere
	 * @param password
	 *            password del file
	 * @return ritorna il document contenente i dati se non sono stase sollevate
	 *         eccezione
	 * @throws IOException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws CryptographyException
	 * @throws JDOMException
	 * @throws InvalidPasswordException
	 */
	 Document readData(char[] _password) throws IOException,
			IllegalBlockSizeException, BadPaddingException,
			CryptographyException, JDOMException, InvalidPasswordException {
		// informazioni di log
		Log.info("inizio processo di lettura");
		//si ricava la password come stringa
		String password = this.getPasswordString(_password);
		Log.debug("controlla password: " + password);
		// prima di tutto si controlla la validità della password
		if (this.checkPassword(password)) {
			Log.info("password corretta");
			// allora si passa alla decrittazione del file
			Log.info("inizio decriptazione dati");
			byte[] fileBytes = this.decryptData(password);
			Log.info("file decriptato");
			// si scrivono i byte su un ByteArray
			ByteArrayInputStream in = new ByteArrayInputStream(fileBytes);
			Log.info("caricamento document");
			// si crea il document caricandolo dal ByteArrayInputStream
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(in);
			Log.info("chiusura degli stream");
			// si chiude lo stream
			in.close();
			// si chiude l'inputStream
			input.close();
			//si cancella l'array di bytes
			fileBytes = null;
			// si restituisce il document
			return doc;
		} else {
			// si lancia una eccezione per la password
			Log.warning("errore password!");
			throw new InvalidPasswordException("Password non valida!");
		}
	}

	/**
	 * Metodo che scrive i dati criptati su file. Il metodo cripta i dati con la
	 * password passata come parametro e aggiunge in coda al file i 16 byte che
	 * memorizzano l'hash della password per il controllo.
	 * 
	 * @param doc
	 *            Document da salvare.
	 * @param password
	 *            password da utilizzare
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws CryptographyException
	 */
	 void writeData(Document doc, char[] _password)
			throws FileNotFoundException, IOException,
			IllegalBlockSizeException, BadPaddingException,
			CryptographyException {
		Log.info("inizio processo di scrittura");
		//si ricava la password come stringa
		String password = this.getPasswordString(_password);
		// si scrive il document su un ByteArray
		XMLOutputter outputter = new XMLOutputter();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		outputter.output(doc, out);
		//si leggono i byte del file dal ByteArrayOutputStream
		byte[] fileBytes = out.toByteArray();
		out.close();
		Log.info("criptazione file");
		//si sceglie casualmente un salt
		Random rnd = new Random();
		int nsalt =  rnd.nextInt(15);
		// si crittano i byte
		byte[] cripted = this.encryptData(password,fileBytes,nsalt);
		//si cancellano i byte in chiaro
		fileBytes = null;
		Log.info("scrittura dei bytes");
		// si scrive l'hash della password
		output.write(this.getPasswordHash(password));
		//si scrive il numero del salt
		output.write((byte)nsalt);
		// si scrivono i dati sull'outputStream
		output.write(cripted);
		// si chiude l'output
		output.close();
		Log.info("chiusura degli stream");
	}

	/**
	 * Metodo che decripta i dati da leggere del file, utilizzando la password
	 * passata come parametro. Il metodo inoltre esclude i bytes in fondo al
	 * file corrispondenti all'hash della password.
	 * 
	 * @param password
	 * @return
	 * @throws CryptographyException
	 * @throws IOException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private byte[] decryptData(String password) throws CryptographyException,
			IOException, IllegalBlockSizeException, BadPaddingException {
		// si legge il salt utilizzato
		int nsalt = this.readSaltCodeFormFile();
		// si richiede un cipher per la decrittazione
		Cipher cip = this.getCipher(password, nsalt, READ_MODE);
		// ora si passano al cipher i byte da leggere
		byte[] toRead = new byte[(int) (file.length() - 17)];
		input.read(toRead, 0, toRead.length);
		// si decrittano
		return cip.doFinal(toRead);
	}

	/**
	 * Metodo che cripta i dati di un file passati come parametro, utilizzando
	 * la password passata come parametro e come salt quello con numero indicato in nsalt
	 * 
	 * @param password
	 *            password da utilizzare
	 * @param fileBytes
	 *            byte da criptare
	 * @param nsalt
	 *            numero del salt da utilizzare
	 * @return ritorna i bytes del file criptati
	 * @throws CryptographyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private byte[] encryptData(String password, byte[] fileBytes, int nsalt)
			throws CryptographyException, IllegalBlockSizeException,
			BadPaddingException {
		// si richiede un cipher per la crittazione
		Cipher cip = this.getCipher(password, nsalt, WRITE_MODE);
		// si passano al cipher i bytes da crittare
		return cip.doFinal(fileBytes);
	}

	/**
	 * Metodo che calcola l'hash della password da inserire nel file
	 * 
	 * @param password
	 *            password di cui calcolare l'hash
	 * @return ritorna l'hash della password sotto forma di un array di bytes di
	 *         16 byte, da inserire nel file
	 */
	private byte[] getPasswordHash(String password) {
		// si calcola l'hash della password ripetuta 500 volte
		return Utility.getHash(password, 500);
	}

	/**
	 * Metodo che trasforma una password da array di caratteri a stringa.
	 * @param cha char array da processare
	 * @return ritorna la password in formato stringa
	 */
	private String getPasswordString(char[] cha){
		StringBuilder builder = new StringBuilder();
		for(char c : cha){
			builder.append(c);
		}
		return builder.toString();
	}
	
	/**
	 * Metodo che verifica la validità della password rispetto al file. L'hash
	 * della password viene memorizzata in coda al file criptato.
	 * 
	 * @param password
	 *            password da verificare
	 * @return restituisce un boolean che indica se la pass è valida o no
	 * @throws IOException
	 *             eccezione lanciata in caso di problemi di IO.
	 */
	private boolean checkPassword(String password) throws IOException {
		// si calcola l'hash della password ripetuta 500 volte
		byte[] currentHash = Utility.getHash(password, 500);
		// ora si ricava quella memorizzata sul file
		byte[] onFile = this.readPasswordHashFromFile();
		// si confrontano
		if (this.areEqual(currentHash, onFile)) {
			// si ritorna true
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo che legge i 16 bytes relativi all'hash della password,memorizzata
	 * nel file
	 * 
	 * @return ritorna un array di 16 byte che contiene l'hash della password
	 * @throws IOException
	 */
	private byte[] readPasswordHashFromFile() throws IOException {
		// si leggono i byte dell'hash che sono gli ultimi 16
		byte[] hashBytes = new byte[16];
		input.read(hashBytes, 0, 16);
		return hashBytes;
	}

	/**
	 * Metodo che restituisce il numero del salt utilizzato per la criptazione.
	 * 
	 * @return
	 * @throws IOException
	 */
	private int readSaltCodeFormFile() throws IOException {
		// si legge il 17esimo byte del file che indica il salt utilizzato
		byte[] saltbyte = new byte[1];
		input.read(saltbyte);
		return (int) saltbyte[0];
	}

	/**
	 * Metodo che crea un Cipher in base alla password passata come parametro,
	 * in base al salt richiesto e in base alla modalità. Se mode =
	 * {@link #WRITE_MODE}, si restituisce un cipher in modalità encrypt, se
	 * mode = {@link #READ_MODE}, si restituisce un cipher in modalità decrypt.
	 * 
	 * @param pass
	 *            password da utilizzare
	 * @param nsalt
	 *            numero del salt da utilizzare
	 * @param mode
	 *            modalità
	 * @return ritorna un Cipher per le operazione crittografiche
	 * @throws CryptographyException
	 *             eccezione lanciata in caso di errori generici nella creazione
	 *             del cipher
	 */
	private Cipher getCipher(String pass, int nsalt, int mode)
			throws CryptographyException {
		// si inizializza chiave
		PBEKeySpec keyspec = new PBEKeySpec(pass.toCharArray());
		SecretKeyFactory keyfactory;
		try {
			keyfactory = SecretKeyFactory.getInstance(
					"PBEWithSHAAnd3KeyTripleDES", "BC");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			throw new CryptographyException("Errore di crittografia");
		}
		SecretKey secretkey;
		try {
			secretkey = keyfactory.generateSecret(keyspec);
		} catch (InvalidKeySpecException e) {
			throw new CryptographyException("Errore di crittografia");
		}

		// si aggiungono salt e ripetizioni
		byte[] salt;
		switch (nsalt) {
		case 0:
			salt = SALT0;
			break;
		case 1:
			salt = SALT1;
			break;
		case 2:
			salt = SALT2;
			break;
		case 3:
			salt = SALT3;
			break;
		case 4:
			salt = SALT4;
			break;
		case 5:
			salt = SALT5;
			break;
		case 6:
			salt = SALT6;
			break;
		case 7:
			salt = SALT7;
			break;
		case 8:
			salt = SALT8;
			break;
		case 9:
			salt = SALT9;
			break;
		case 10:
			salt = SALT10;
			break;
		case 11:
			salt = SALT11;
			break;
		case 12:
			salt = SALT12;
			break;
		case 13:
			salt = SALT13;
			break;
		case 14:
			salt = SALT14;
			break;
		case 15:
			salt = SALT15;
			break;
		default:
			salt = SALT0;
		}
		int ripetizioni = 1000;
		PBEParameterSpec parameterspec = new PBEParameterSpec(salt, ripetizioni);

		// creazione cipher
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("PBEWithSHAAnd3KeyTripleDES", "BC");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| NoSuchProviderException e1) {
			throw new CryptographyException("Errore di crittografia");
		}
		// inizializzazione
		if (mode == WRITE_MODE) {
			try {
				cipher.init(Cipher.ENCRYPT_MODE, secretkey, parameterspec);
				return cipher;
			} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
				throw new CryptographyException("Errore di crittografia");
			}
		} else if (mode == READ_MODE) {
			try {
				cipher.init(Cipher.DECRYPT_MODE, secretkey, parameterspec);
				return cipher;
			} catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
				throw new CryptographyException("Errore di crittografia");
			}
		} else {
			return null;
		}
	}

	/**
	 * Metodo che confronta due array di bytes per stabilire se sono identici o
	 * no. Se i due array hanno lunghezze diverse si ritorna subito false
	 * 
	 * @param a
	 *            primo array
	 * @param b
	 *            secondo array
	 * @return ritorna True se sono identici, False se anche un solo byte è
	 *         diverso
	 */
	private boolean areEqual(byte[] a, byte[] b) {
		if (a.length != b.length) {
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				// se uno è diverso si ritorna false
				return false;
			}
		}
		// se sono tutti uguali si ritorna true,
		return true;
	}
}
