package it.valsecchi.quickagenda.data.report;

import it.valsecchi.quickagenda.data.component.Session;
import it.valsecchi.quickagenda.data.component.Work;

import java.util.List;

/**
 * Classe che rappresenta un report di integrità del database.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class DataIntegrityReport {

	private int n_errors;
	private int n_tot_elements;
	private int n_costumers;
	private int n_works;
	private int n_sessions;
	private List<Work> work_errors;
	private List<Session> session_errors;

	public DataIntegrityReport(int n_Errors, int n_Tot, int n_Costumers,
			int n_Works, int n_Sessions, List<Work> work_Errors,
			List<Session> session_Errors) {
		n_errors = n_Errors;
		n_tot_elements = n_Tot;
		n_costumers = n_Costumers;
		n_works = n_Works;
		n_sessions = n_Sessions;
		work_errors= work_Errors;
		session_errors = session_Errors;
	}

	public int getN_errors() {
		return n_errors;
	}

	public int getN_tot_elements() {
		return n_tot_elements;
	}

	public int getN_costumers() {
		return n_costumers;
	}

	public int getN_works() {
		return n_works;
	}

	public int getN_sessions() {
		return n_sessions;
	}

	public List<Work> getWork_errors() {
		return work_errors;
	}

	public List<Session> getSession_errors() {
		return session_errors;
	}
}
