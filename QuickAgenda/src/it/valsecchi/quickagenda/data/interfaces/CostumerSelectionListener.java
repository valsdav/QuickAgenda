package it.valsecchi.quickagenda.data.interfaces;

import it.valsecchi.quickagenda.data.component.Costumer;

/**
 * Interfaccia che fornisce i metodi per un'evento di selezione di un Costumer.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public interface CostumerSelectionListener {

	/**
	 * Metodo che verrà chiamato dall'oggetto sorgente dell'evento e che dovrà
	 * essere implementato per permettere la memorizzazione del Costumer
	 * selezionato.
	 * 
	 * @param selected Costumer selezionato
	 */
	public void registerSelectedCostumer(Costumer selected_costumer);

}
