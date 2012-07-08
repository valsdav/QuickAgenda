package it.valsecchi.quickagenda.data.report;

/**
 * Classe che rappresenta un risultato di un report su un Costumer.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class CostumerReportResult {

	private int numberOfWorks;
	private int numberOfSessions;
	private int numberOfHours;
	private int totSpesa;

	/**
	 * Costruttore che richiede il numero totale di lavori, il numero totale di
	 * sessioni, il numero totale di ore e la spesa totale.
	 */
	public CostumerReportResult(int works, int sessions, int hours, int spesa) {
		numberOfWorks = works;
		numberOfSessions = sessions;
		numberOfHours = hours;
		totSpesa = spesa;
	}

	public int getNumberOfWorks() {
		return numberOfWorks;
	}

	public int getNumberOfSessions() {
		return numberOfSessions;
	}

	public int getNumberOfHours() {
		return numberOfHours;
	}

	public int getTotSpesa() {
		return totSpesa;
	}

}
