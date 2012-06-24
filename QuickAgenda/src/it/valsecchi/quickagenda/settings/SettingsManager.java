package it.valsecchi.quickagenda.settings;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static it.valsecchi.quickagenda.data.Utility.Log;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Classe che gestisce le impostazioni dell'applicazione, memorizzate in un file
 * settings.xml al percorso ./settings.xml
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class SettingsManager {

	private static Document doc;

	static {
		// si legge il file di preferenze
		doc = new Document();
		if (Files.exists(Paths.get("QuickAgenda_settings.xml"))) {
			// solo se esiste si legge se no si lascia così
			SAXBuilder build = new SAXBuilder();
			try {
				doc = build.build("QuickAgenda_settings.xml");
			} catch (JDOMException | IOException e) {
				// si scrive l'eccezione
				Log.error("Il file settings.xml non è stato trovato!");
			}
		} else {
			// si inizializzano i dati
			doc.setRootElement(new Element("settings"));
			doc.getRootElement().addContent(new Element("data_path"));
		}
	}

	public static String[] getDataPaths() {
		Element dati = doc.getRootElement().getChild("data_path");
		List<String> _results = new ArrayList<>();
		for (Element e : dati.getChildren("path")) {
			if (Files.exists(Paths.get(e.getText()))) {
				_results.add(e.getText());
			}
		}
		if (_results.size() == 0) {
			return new String[] { "" };
		}
		String[] results = new String[_results.size()];
		for (int i = 0; i < _results.size(); i++) {
			results[i] = _results.get(i);
		}
		return results;
	}

	public static void addDataPath(String path) {
		Element e = new Element("path");
		e.setText(path);
		doc.getRootElement().getChild("data_path").addContent(e);
	}

	public static void writeSettings() {
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
		try {
			FileOutputStream output = new FileOutputStream(
					"QuickAgenda_settings.xml");
			outputter.output(doc, output);
		} catch (FileNotFoundException e) {
			Log.error("impossibile trovare file settings");
		} catch (IOException e) {
			Log.error("impossibile scrivere file settings");
		}
	}

}
