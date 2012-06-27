package it.valsecchi.quickagenda;

import java.awt.EventQueue;
import it.valsecchi.quickagenda.windows.StartWindow;
import static it.valsecchi.quickagenda.data.Utility.Log;
import java.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Main {
	public static void main(String[] args) {
		checkProvider();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log.info("apertura finestra di avvio");
					StartWindow form = new StartWindow();
					form.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Metodo che controlla se è presente il provider crittografico necessario
	 * al programma e lo aggiunge in caso negativo.
	 */
	private static void checkProvider() {
		if (Security.getProvider("BouncyCastleProvider") == null) {
			// si aggiunge
			Security.addProvider(new BouncyCastleProvider());
		}
	}
}
