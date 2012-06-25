package it.valsecchi.quickagenda.windows.detail;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Costumer;
import it.valsecchi.quickagenda.data.component.Session;
import it.valsecchi.quickagenda.data.component.Work;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import static it.valsecchi.quickagenda.data.Utility.Log;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SessionDetailWindow extends JFrame {

	private static final long serialVersionUID = 7746985618232386389L;
	private Session session;
	private Costumer costumer;
	private Work work;
	private JPanel contentPane;
	private JLabel immagine1;
	private JLabel lblID;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4;
	private JLabel lblData;
	private JLabel lblOre;
	private JLabel lblSpesa;
	private JLabel lblMateriali;
	private JSeparator separator;
	private JButton btnModifica;
	private DataManager manager;
	private String sessionID;
	private JLabel lblDettagliCliente;
	private JLabel lblDettagliLavoro;
	private JLabel label;
	private JLabel label_1;
	private JTextField txtID;
	private JTextField txtLavoro;
	private JTextField txtCliente;
	private JTextField txtData;
	private JTextField txtOre;
	private JTextField txtSpesa;
	private JTextField txtMateriali;
	private JButton btnSalva;
	private JLabel lblIstr;
	private JTextField txtLavoroID;
	private JTextField txtClienteID;
	private JLabel lblIndirizzo;
	private JTextField txtIndirizzo;
	private JLabel lblInizioLavori;
	private JTextField txtInizioLavori;
	private JLabel lblFineLavori;
	private JTextField txtFineLavori;
	private JLabel lblCompletato;
	private JCheckBox cbCompleted;
	private JSeparator separator_1;
	private JButton btnSalvaLavoro;
	private JButton btnModificaLavoro;

	public SessionDetailWindow(String sessionId, DataManager _manager) {
		manager = _manager;
		sessionID = sessionId;
		initComponent();
		initData();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 774);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		immagine1 = new JLabel("Dettagli Sessione");
		immagine1.setFont(new Font("Tahoma", Font.BOLD, 15));
		immagine1.setIcon(new ImageIcon(SessionDetailWindow.class
				.getResource("/ico_small/tools.png")));
		lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblID.setForeground(Color.BLUE);
		lblNewLabel_2 = new JLabel("ID Lavoro:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_4 = new JLabel("ID Cliente:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setForeground(Color.BLUE);
		lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblData.setForeground(Color.BLUE);
		lblOre = new JLabel("N\u00B0 Ore:");
		lblOre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOre.setForeground(Color.BLUE);
		lblSpesa = new JLabel("Spesa:");
		lblSpesa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpesa.setForeground(Color.BLUE);
		lblMateriali = new JLabel("Materiali:");
		lblMateriali.setForeground(Color.BLUE);
		lblMateriali.setFont(new Font("Tahoma", Font.BOLD, 14));

		separator = new JSeparator();

		btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new BtnModificaActionListener());
		btnModifica.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblDettagliCliente = new JLabel("Dettagli Cliente");
		lblDettagliCliente.setIcon(new ImageIcon(SessionDetailWindow.class
				.getResource("/ico_small/users.png")));
		lblDettagliCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDettagliLavoro = new JLabel("Dettagli Lavoro");
		lblDettagliLavoro.setIcon(new ImageIcon(SessionDetailWindow.class
				.getResource("/ico_small/work2.png")));
		lblDettagliLavoro.setFont(new Font("Tahoma", Font.BOLD, 15));

		label = new JLabel("ID:");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));

		label_1 = new JLabel("ID Cliente:");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtLavoro = new JTextField();
		txtLavoro.setEditable(false);
		txtLavoro.setColumns(10);
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setColumns(10);
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtOre = new JTextField();
		txtOre.setEditable(false);
		txtOre.setColumns(10);
		txtSpesa = new JTextField();
		txtSpesa.setEditable(false);
		txtSpesa.setColumns(10);
		txtMateriali = new JTextField();
		txtMateriali.setEditable(false);
		txtMateriali.setColumns(10);
		btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new BtnSalvaActionListener());
		btnSalva.setEnabled(false);
		btnSalva.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblIstr = new JLabel("");

		txtLavoroID = new JTextField();
		txtLavoroID.setEditable(false);
		txtLavoroID.setColumns(10);

		txtClienteID = new JTextField();
		txtClienteID.setEditable(false);
		txtClienteID.setColumns(10);

		lblIndirizzo = new JLabel("Indirizzo:");
		lblIndirizzo.setForeground(Color.BLUE);
		lblIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtIndirizzo = new JTextField();
		txtIndirizzo.setEditable(false);
		txtIndirizzo.setColumns(10);

		lblInizioLavori = new JLabel("Inizio Lavori:");
		lblInizioLavori.setForeground(Color.BLUE);
		lblInizioLavori.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtInizioLavori = new JTextField();
		txtInizioLavori.setEditable(false);
		txtInizioLavori.setColumns(10);

		lblFineLavori = new JLabel("Fine Lavori:");
		lblFineLavori.setForeground(Color.BLUE);
		lblFineLavori.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtFineLavori = new JTextField();
		txtFineLavori.setEditable(false);
		txtFineLavori.setColumns(10);

		lblCompletato = new JLabel("Completato:");
		lblCompletato.setForeground(Color.BLUE);
		lblCompletato.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbCompleted = new JCheckBox("");
		cbCompleted.addItemListener(new CbCompletedItemListener());
		separator_1 = new JSeparator();
		btnSalvaLavoro = new JButton("Salva");
		btnSalvaLavoro.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvaLavoro.setEnabled(false);
		btnModificaLavoro = new JButton("Modifica");
		btnModificaLavoro.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnModifica)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnSalva)
																		.addGap(18)
																		.addComponent(
																				lblIstr))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addGroup(
																								Alignment.LEADING,
																								gl_contentPane
																										.createSequentialGroup()
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addGroup(
																																Alignment.LEADING,
																																gl_contentPane
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblIndirizzo,
																																				GroupLayout.PREFERRED_SIZE,
																																				74,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED,
																																				GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				txtIndirizzo,
																																				GroupLayout.PREFERRED_SIZE,
																																				191,
																																				GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																Alignment.LEADING,
																																gl_contentPane
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblInizioLavori,
																																				GroupLayout.PREFERRED_SIZE,
																																				100,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				txtInizioLavori,
																																				GroupLayout.PREFERRED_SIZE,
																																				191,
																																				GroupLayout.PREFERRED_SIZE)))
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												lblFineLavori,
																												GroupLayout.PREFERRED_SIZE,
																												100,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												txtFineLavori,
																												GroupLayout.PREFERRED_SIZE,
																												191,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								Alignment.LEADING,
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												label,
																												GroupLayout.PREFERRED_SIZE,
																												22,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												txtLavoroID,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(12)
																										.addComponent(
																												label_1,
																												GroupLayout.PREFERRED_SIZE,
																												73,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												txtClienteID,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												248,
																												Short.MAX_VALUE))
																						.addGroup(
																								Alignment.LEADING,
																								gl_contentPane
																										.createSequentialGroup()
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.LEADING,
																																false)
																														.addGroup(
																																gl_contentPane
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblID)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED,
																																				GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				txtID,
																																				GroupLayout.PREFERRED_SIZE,
																																				GroupLayout.DEFAULT_SIZE,
																																				GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																gl_contentPane
																																		.createSequentialGroup()
																																		.addComponent(
																																				lblData)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				txtData,
																																				GroupLayout.PREFERRED_SIZE,
																																				GroupLayout.DEFAULT_SIZE,
																																				GroupLayout.PREFERRED_SIZE)))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																lblOre,
																																GroupLayout.PREFERRED_SIZE,
																																59,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																lblNewLabel_2))
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addGroup(
																																gl_contentPane
																																		.createSequentialGroup()
																																		.addComponent(
																																				txtLavoro,
																																				GroupLayout.PREFERRED_SIZE,
																																				GroupLayout.DEFAULT_SIZE,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addGap(18)
																																		.addComponent(
																																				lblNewLabel_4))
																														.addGroup(
																																gl_contentPane
																																		.createSequentialGroup()
																																		.addComponent(
																																				txtOre,
																																				GroupLayout.PREFERRED_SIZE,
																																				GroupLayout.DEFAULT_SIZE,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addGap(18)
																																		.addComponent(
																																				lblSpesa,
																																				GroupLayout.PREFERRED_SIZE,
																																				60,
																																				GroupLayout.PREFERRED_SIZE)))
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																txtSpesa,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																txtCliente,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)))
																						.addGroup(
																								Alignment.LEADING,
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												lblMateriali,
																												GroupLayout.PREFERRED_SIZE,
																												82,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												txtMateriali))
																						.addComponent(
																								lblDettagliCliente,
																								Alignment.LEADING,
																								GroupLayout.PREFERRED_SIZE,
																								199,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								immagine1,
																								Alignment.LEADING)
																						.addComponent(
																								lblDettagliLavoro,
																								Alignment.LEADING,
																								GroupLayout.PREFERRED_SIZE,
																								199,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblCompletato,
																				GroupLayout.PREFERRED_SIZE,
																				100,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				cbCompleted))
														.addComponent(
																separator,
																GroupLayout.DEFAULT_SIZE,
																618,
																Short.MAX_VALUE)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				btnModificaLavoro,
																				GroupLayout.PREFERRED_SIZE,
																				89,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(7)
																		.addComponent(
																				btnSalvaLavoro,
																				GroupLayout.PREFERRED_SIZE,
																				69,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																separator_1,
																GroupLayout.PREFERRED_SIZE,
																618,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addComponent(immagine1)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblID)
														.addComponent(
																lblNewLabel_2)
														.addComponent(
																txtLavoro,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblNewLabel_4)
														.addComponent(
																txtCliente,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtID,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblData)
														.addComponent(lblOre)
														.addComponent(
																txtData,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtOre,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblSpesa)
														.addComponent(
																txtSpesa,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblMateriali,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtMateriali,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnModifica)
														.addComponent(btnSalva)
														.addComponent(lblIstr))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 6,
												GroupLayout.PREFERRED_SIZE)
										.addGap(1)
										.addComponent(lblDettagliLavoro,
												GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																label,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																label_1,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtLavoroID,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtClienteID,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblIndirizzo,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtIndirizzo,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblInizioLavori,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtInizioLavori,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblFineLavori,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtFineLavori,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblCompletato,
																GroupLayout.PREFERRED_SIZE,
																17,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																cbCompleted))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnModificaLavoro)
														.addComponent(
																btnSalvaLavoro))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator_1,
												GroupLayout.PREFERRED_SIZE, 6,
												GroupLayout.PREFERRED_SIZE)
										.addGap(73)
										.addComponent(lblDettagliCliente,
												GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE)
										.addGap(162)));
		contentPane.setLayout(gl_contentPane);
	}

	private void initData() {
		// si ricava la sessione
		try {
			session = manager.getSession(sessionID);
		} catch (IDNotFoundException e) {
			// non si puo verificare questo errore
			Log.error("id " + e.getID() + ", non trovato");
		}
		txtID.setText(sessionID);
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
		txtData.setText(f.format(session.getSessionData().getTime()));
		StringBuilder b = new StringBuilder();
		for (String t : session.getMateriali()) {
			b.append(t + ",");
		}
		txtMateriali.setText(b.toString());
		// si ricava il costumer
		try {
			costumer = manager.getCostumerFromSession(sessionID);
		} catch (IDNotFoundException e) {
			// non si puo verificare questo errore
			Log.error("id " + e.getID() + ", non trovato");
		}
		txtCliente.setText(costumer.getID());
		txtOre.setText(Integer.toString(session.getHours()));
		txtSpesa.setText(Integer.toString(session.getSpesa()));
		// Si ricava il work
		try {
			work = manager.getWorkFromSession(sessionID);
		} catch (IDNotFoundException e) {
			// non si puo verificare questo errore
			Log.error("id " + e.getID() + ", non trovato");
		}
		txtLavoroID.setText(work.getID());
		txtClienteID.setText(work.getCostumerID());
		txtIndirizzo.setText(work.getIndirizzo());
		txtInizioLavori.setText(f.format(work.getInizioLavori().getTime()));

	}

	private class BtnModificaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// si rendono editabili i campi
			txtData.setEditable(true);
			txtOre.setEditable(true);
			txtSpesa.setEditable(true);
			txtMateriali.setEditable(true);
			// si abilita il pulsante salva
			btnSalva.setEnabled(true);
			btnModifica.setEnabled(false);
		}
	}

	private class BtnSalvaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// si salvano i nuovi dati
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
			try {
				Calendar c = Calendar.getInstance();
				c.setTime(f.parse(txtData.getText()));
				session.setSessionData(c);
			} catch (ParseException e1) {
				// si notifica l'errore
				lblIstr.setText("Inserire la data nel formato giorno/mese/anno");
				return;
			}
			session.setHours(Integer.parseInt(txtOre.getText()));
			session.setSpesa(Integer.parseInt(txtSpesa.getText()));
			List<String> materiali = new ArrayList<>();
			StringTokenizer tok = new StringTokenizer(txtMateriali.getText(),
					",");
			while (tok.hasMoreTokens()) {
				materiali.add(tok.nextToken());
			}
			session.setMateriali(materiali);
			// si disabilitano i pulsanti
			lblIstr.setText("");
			btnModifica.setEnabled(true);
			btnSalva.setEnabled(false);
		}
	}

	private class CbCompletedItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
		}
	}
}
