package it.valsecchi.quickagenda.data.report;

/**
 * Classe che rappresenta un risultato di un report su un Work.
 *
 * @author Davide Valsecchi
 * @version 1.0
 *
 */
public class WorkReportResult {

	private int numberOfSessions;
	private int totSpesa;
	private int totHours;
	
	public WorkReportResult(int numberofSessions,int spesa,int ore){
		setNumberOfSessions(numberofSessions);
		setTotSpesa(spesa);
		setTotHours(ore);
	}

	public int getNumberOfSessions() {
		return numberOfSessions;
	}

	public void setNumberOfSessions(int numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}

	public int getTotSpesa() {
		return totSpesa;
	}

	public void setTotSpesa(int totSpesa) {
		this.totSpesa = totSpesa;
	}

	public int getTotHours() {
		return totHours;
	}

	public void setTotHours(int totHours) {
		this.totHours = totHours;
	}
	
}
