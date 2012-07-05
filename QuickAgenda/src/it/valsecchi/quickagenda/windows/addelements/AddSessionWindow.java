package it.valsecchi.quickagenda.windows.addelements;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.ElementType;
import it.valsecchi.quickagenda.data.component.Work;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.component.exception.SessionAlreadyExistsException;
import it.valsecchi.quickagenda.data.exception.InsufficientDataException;
import it.valsecchi.quickagenda.data.interfaces.*;
import it.valsecchi.quickagenda.windows.WorksManagerWindow;
import static it.valsecchi.quickagenda.data.Utility.Log;
import java.awt.Font;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JTextPane;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

/**
 * Classe che fornisce la GUI per l'inserimento dei dati per un nuovo cliente.
 * La classe richiede un oggetto che implementi l'interfaccia
 * AddSessionInterface e che quindi fornisca i metodi per l'aggiunta dei
 * costumers.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 */
public class AddSessionWindow extends javax.swing.JFrame implements
		WorkSelectionListener {

	private static final long serialVersionUID = 2240305864691419492L;
	/** Variabile di tipo AddCostumerInterface che gestisce l'aggiunta dei dati */
	private AddSessionInterface sessionsMan;

	/**
	 * Costruttore della finestra che richiede un oggetto che implementa
	 * l'interfaccia AddCostumerInterface, per l'aggunta dei costumer.
	 * 
	 * @param _costsMan
	 *            oggetto che gestisce l'aggiunta dei costumers.
	 * @wbp.parser.constructor
	 */
	public AddSessionWindow(AddSessionInterface _sessionsMan) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				AddSessionWindow.class.getResource("/ico_small/add1.png")));
		setTitle("Aggiungi Sessione");
		initComponents();
		sessionsMan = _sessionsMan;
		Log.info("finestra inizializzata");
	}

	public AddSessionWindow(AddSessionInterface _sessionsMan, Calendar cal) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				AddSessionWindow.class.getResource("/ico_small/add1.png")));
		setTitle("Aggiungi Sessione");
		initComponents();
		dateChooser.setCalendar(cal);
		sessionsMan = _sessionsMan;
		Log.info("finestra inizializzata");
	}

	/**
	 * Metodo che inizializza i componenti dell'interfaccia grafica.
	 */
	private void initComponents() {
		currentWindow = this;
		txtIDLavoro = new javax.swing.JTextField();
		lblIDLavoro = new javax.swing.JLabel();
		lblData = new javax.swing.JLabel();
		lblOre = new javax.swing.JLabel();
		txtOre = new javax.swing.JTextField();
		txtSpesa = new javax.swing.JTextField();
		lblSpesa = new javax.swing.JLabel();
		lblMateriali = new javax.swing.JLabel();
		lblImmagine = new JLabel("");
		jToolBar = new javax.swing.JToolBar();
		jToolBar.setFloatable(false);
		addButton = new javax.swing.JButton();
		addButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		addButton.addActionListener(new AddButtonActionListener());
		addButton.setIcon(new ImageIcon(AddSessionWindow.class
				.getResource("/ico_small/add2.png")));
		exitButton = new javax.swing.JButton();
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// si chiude
				dispose();
			}
		});
		exitButton.setIcon(new ImageIcon(AddSessionWindow.class
				.getResource("/ico_small/shutdown_box_red.png")));
		lblIstruzioni = new javax.swing.JLabel();
		lblIstruzioni.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblAvviso = new javax.swing.JLabel();
		lblAvviso.setFont(new Font("SansSerif", Font.BOLD, 13));

		lblImmagine.setIcon(new ImageIcon(AddSessionWindow.class
				.getResource("/ico_small/edit.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		lblIDLavoro.setText("ID Lavoro*:");
		lblIDLavoro.setName("txtNome");

		lblData.setText("Data*:");
		lblData.setName("txtAzienda");

		lblOre.setText("N\u00B0 Ore:");
		lblOre.setName("txtIndirizzo");

		lblSpesa.setText("Spesa:");
		lblSpesa.setName("txtTelefono");

		lblMateriali.setText("Materiali:");

		jToolBar.setRollover(true);

		addButton.setText("AGGIUNGI");
		addButton.setFocusable(false);
		addButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		addButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar.add(addButton);

		exitButton.setText("ESCI");
		exitButton.setFocusable(false);
		exitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		exitButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar.add(exitButton);

		lblIstruzioni
				.setText("Aggiungi una nuova sessione inserendo i dati sottostanti.");

		lblAvviso
				.setText("N.B.: i campi contrassegnati con l'asterisco* sono obbligatori");
		txtMateriali = new JTextPane();
		txtMateriali.setToolTipText("");

		btnIDLavoro = new JButton("...");
		btnIDLavoro.addActionListener(new BtnIDLavoroActionListener());
		dateChooser = new JDateChooser();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(200)
								.addComponent(lblImmagine,
										GroupLayout.PREFERRED_SIZE, 79,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(239, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup().addContainerGap()
								.addComponent(lblAvviso)
								.addContainerGap(123, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				Alignment.TRAILING)
																				.addComponent(
																						lblIstruzioni,
																						Alignment.LEADING,
																						GroupLayout.DEFAULT_SIZE,
																						362,
																						Short.MAX_VALUE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										lblData,
																										GroupLayout.PREFERRED_SIZE,
																										70,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										dateChooser,
																										GroupLayout.DEFAULT_SIZE,
																										287,
																										Short.MAX_VALUE)))
																.addGap(144))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				Alignment.TRAILING)
																				.addGroup(
																						Alignment.LEADING,
																						layout.createSequentialGroup()
																								.addComponent(
																										lblIDLavoro,
																										GroupLayout.PREFERRED_SIZE,
																										70,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										txtIDLavoro,
																										GroupLayout.PREFERRED_SIZE,
																										219,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										btnIDLavoro))
																				.addGroup(
																						Alignment.LEADING,
																						layout.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																								.addGroup(
																										Alignment.LEADING,
																										layout.createSequentialGroup()
																												.addComponent(
																														lblMateriali,
																														GroupLayout.PREFERRED_SIZE,
																														70,
																														GroupLayout.PREFERRED_SIZE)
																												.addPreferredGap(
																														ComponentPlacement.RELATED)
																												.addComponent(
																														txtMateriali))
																								.addGroup(
																										layout.createSequentialGroup()
																												.addComponent(
																														lblSpesa,
																														GroupLayout.PREFERRED_SIZE,
																														70,
																														GroupLayout.PREFERRED_SIZE)
																												.addPreferredGap(
																														ComponentPlacement.RELATED)
																												.addComponent(
																														txtSpesa))
																								.addGroup(
																										Alignment.LEADING,
																										layout.createSequentialGroup()
																												.addComponent(
																														lblOre,
																														GroupLayout.PREFERRED_SIZE,
																														70,
																														GroupLayout.PREFERRED_SIZE)
																												.addPreferredGap(
																														ComponentPlacement.RELATED)
																												.addComponent(
																														txtOre,
																														287,
																														287,
																														287))))
																.addContainerGap(
																		144,
																		Short.MAX_VALUE))))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jToolBar,
										GroupLayout.PREFERRED_SIZE, 334,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jToolBar,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblIstruzioni,
										GroupLayout.PREFERRED_SIZE, 38,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblIDLavoro,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtIDLavoro,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnIDLavoro))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(
														lblData,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														dateChooser,
														GroupLayout.PREFERRED_SIZE,
														25,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblOre,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtOre,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblSpesa,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtSpesa,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(12)
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														lblMateriali,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtMateriali,
														GroupLayout.DEFAULT_SIZE,
														59, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblAvviso).addGap(18)
								.addComponent(lblImmagine).addContainerGap()));
		getContentPane().setLayout(layout);
		pack();
	}

	private javax.swing.JButton addButton;
	private javax.swing.JButton exitButton;
	private javax.swing.JLabel lblIDLavoro;
	private javax.swing.JLabel lblData;
	private javax.swing.JLabel lblOre;
	private javax.swing.JLabel lblSpesa;
	private javax.swing.JLabel lblMateriali;
	private javax.swing.JLabel lblIstruzioni;
	private javax.swing.JLabel lblAvviso;
	private javax.swing.JTextField txtIDLavoro;
	private javax.swing.JTextField txtOre;
	private javax.swing.JTextField txtSpesa;
	private javax.swing.JToolBar jToolBar;
	private javax.swing.JLabel lblImmagine;
	private JTextPane txtMateriali;
	private JButton btnIDLavoro;
	private JDateChooser dateChooser;
	private AddSessionWindow currentWindow;

	public void setWorkID(String workID){
		txtIDLavoro.setText(workID);
	}
	
	/** Classe interna che gestisce il click del pulsante add */
	private class AddButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// si aggiunge la session
			// si aggiunge un costumer
			try {
				List<String> materiali = new ArrayList<>();
				// si ricavano i materiali
				if (!txtMateriali.getText().equals("")) {
					StringTokenizer tok = new StringTokenizer(
							txtMateriali.getText(), ",");
					while (tok.hasMoreTokens()) {
						materiali.add(tok.nextToken());
					}
				}
				int nore, spesa;
				if (txtSpesa.getText().equals("")) {
					spesa = 0;
				} else {
					spesa = Integer.parseInt(txtSpesa.getText());
				}
				if (txtOre.getText().equals("")) {
					nore = 0;
				} else {
					nore = Integer.parseInt(txtOre.getText());
				}
				sessionsMan.addSession(txtIDLavoro.getText().toUpperCase(),
						dateChooser.getCalendar(), nore, spesa, materiali);
				// si svuotano i campi
				lblAvviso.setText("Sessione aggiunta con successo!");
				lblImmagine.setIcon(new ImageIcon(AddSessionWindow.class
						.getResource("/ico_small/check.png")));
				txtIDLavoro.setText("");
				dateChooser.cleanup();
				txtOre.setText("");
				txtSpesa.setText("");
				txtMateriali
						.setText("Inserire i materiali separati da virgola");
			} catch (SessionAlreadyExistsException exc) {
				// si avvisa
				lblAvviso.setText("Session già presente nei dati!");
				lblImmagine.setIcon(new ImageIcon(AddSessionWindow.class
						.getResource("/ico_small/cancel2.png")));
				return;
			} catch (InsufficientDataException exc) {
				// si avvisa
				// si cambia l'icona
				lblImmagine.setIcon(new ImageIcon(AddSessionWindow.class
						.getResource("/ico_small/attention.png")));
				// si cambia l'avviso
				lblAvviso.setText("Inserire ID Lavoro e Data!");
				return;
			} catch (IDNotFoundException e2) {
				// eccezione generata se non vengono trovati gli id di
				// riferimento
				if (e2.getType().equals(ElementType.Costumer)) {
					lblImmagine.setIcon(new ImageIcon(AddSessionWindow.class
							.getResource("/ico_small/attention.png")));
					// si cambia l'avviso
					lblAvviso.setText("Cliente non trovato!");
				}
				if (e2.getType().equals(ElementType.Work)) {
					lblImmagine.setIcon(new ImageIcon(AddSessionWindow.class
							.getResource("/ico_small/attention.png")));
					// si cambia l'avviso
					lblAvviso.setText("Lavoro non trovato!");
				}
				return;
			}
		}
	}

	/**
	 * Metodo che salva il costumer selezionato
	 */
	@Override
	public void registerSelectedWork(Work selected_work) {
		// se è null non si fa niente
		if (selected_work != null) {
			// si imposta il suo id nel campo
			txtIDLavoro.setText(selected_work.getID());
		}
	}

	private class BtnIDLavoroActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Si apre la finestra di selezione
			WorksManagerWindow w = new WorksManagerWindow(
					(DataManager) sessionsMan,
					WorksManagerWindow.MODE_SELECTION);
			w.addWorkSelectionListener(currentWindow);
			w.setVisible(true);
		}
	}

}
