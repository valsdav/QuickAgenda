package it.valsecchi.quickagenda.data.report;

import java.util.List;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Session;
import it.valsecchi.quickagenda.data.component.Work;

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
	 * @return restituisce un oggetto WorkReportResult con tutti i dati del
	 *         report.
	 */
	public static WorkReportResult performWorkReport(String workID,
			DataManager data) {
		if (data == null) {
			return null;
		}
		// si ricavano tutte le sessioni
		List<Session> sessions = data.getSessionsFromWorkID(workID);
		// si calcola il numero di sessionio
		int nSessioni = sessions.size();
		// si calcola la spesa e ore
		int spesa = 0, ore = 0;
		for (Session s : sessions) {
			spesa += s.getSpesa();
			ore += s.getHours();
		}
		// si restituisce il report
		return new WorkReportResult(nSessioni, spesa, ore);
	}

	/**
	 * Metodo che calcola i dati per il report di un Costumer. Il report viene
	 * restituito come oggetto WorkReportResult che contiene i dati.
	 * 
	 * @param costumerID
	 *            ID del Costumer di cui calcolare il report.
	 * @param data
	 *            DataManager che contiene i dati da elaborare.
	 * @return restituisce un oggetto CostumerReportResult con tutti i dati del
	 *         report.
	 */
	public static CostumerReportResult performCostumerReport(String costumerID,
			DataManager data) {
		if (data == null) {
			return null;
		}
		int nworks, nsessions = 0, spesa = 0, hours = 0;
		// si ricavano tutti gli work
		List<Work> works = data.getWorksByCostumerID(costumerID);
		nworks = works.size();
		for (Work w : works) {
			List<Session> sessions = data.getSessionsFromWorkID(w.getID());
			for (Session s : sessions) {
				spesa += s.getSpesa();
				hours += s.getHours();
			}
			nsessions += sessions.size();
		}
		// si restituisce il report
		return new CostumerReportResult(nworks, nsessions, hours, spesa);
	}
}
