package it.valsecchi.quickagenda.data.report;

import java.util.ArrayList;
import java.util.List;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Session;

/**
 * Classe che gestisce la creazione di reports dei dati contenuti in
 * DataManager. Si possono creare reports di Work o Costumer.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class ReportsManager {

	/**
	 * Metodo che calcola i dati per il report di un Work. Il report viene
	 * restituito come oggetto WorkReportResult che contiene i dati.
	 * 
	 * @param workID
	 *            ID del Work di cui calcolare il report
	 * @param data
	 *            DataManager che contiene i dati da elaborare.
	 * @return
	 */
	public static WorkReportResult performWorkReport(String workID,
			DataManager data) {
		// si ricavano tutte le sessioni
		List<Session> sessions = new ArrayList<>();
		sessions.addAll(data.getSessionsFromWorkID(workID));
		// si calcola il numero di sessionio
		int nSessioni = sessions.size();
		// si calcola la spesa e ore
		int spesa = 0, ore = 0;
		for (Session s : sessions) {
			spesa += s.getSpesa();
			ore += s.getHours();
		}
		return new WorkReportResult(nSessioni, spesa, ore);
	}
}
