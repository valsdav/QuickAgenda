package it.valsecchi.quickagenda.data.interfaces;

import it.valsecchi.quickagenda.data.component.Work;

/**
 * Interfaccia che fornisce i metodi per l'evento di selezione di un Work
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public interface WorkSelectionListener {

	/**
	 * Metodo che verrà chiamato dalla sorgente dell'evento e che dovrà essere
	 * implementato per permettere la memorizzazione del Work selezionato
	 * 
	 * @param selected_work work selezionato
	 */
	public void registerSelectedWork(Work selected_work);
}
