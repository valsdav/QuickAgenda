package it.valsecchi.quickagenda.data.interfaces;

import it.valsecchi.quickagenda.data.component.ElementType;

/**
 * Interfaccia che dovrà essere implementata per eseguire operazioni in seguito
 * a un aggiornamento dei dati.
 * 
 * @author Davide
 * 
 */
public interface DataUpdateListener {

	/**
	 * Il metodo indica con il parametro type il tipo di elemento che è stato aggiornato
	 * @see ElementType
	 * @param type
	 */
	public void DataUpdatePerformed(ElementType type);

}
