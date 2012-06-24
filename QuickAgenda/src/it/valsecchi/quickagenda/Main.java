package it.valsecchi.quickagenda;

import java.awt.EventQueue;
import it.valsecchi.quickagenda.windows.StartForm;
import static it.valsecchi.quickagenda.data.Utility.Log;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log.info("apertura finestra di avvio");
					StartForm form = new StartForm();
					form.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
