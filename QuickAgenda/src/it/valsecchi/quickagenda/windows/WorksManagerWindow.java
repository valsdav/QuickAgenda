package it.valsecchi.quickagenda.windows;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Work;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.interfaces.WorkSelectionListener;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class WorksManagerWindow extends JFrame {

	private static final long serialVersionUID = 4243070285624901001L;
	private JPanel contentPane;
	private DataManager data;
	private JButton btnTutti;
	private JTextField txtCerca;
	private JComboBox cbCerca;
	private JButton btnCerca;
	private JButton btnAggiungi;
	private JButton btnConferma;
	private JPanel panel;
	private JTable table;
	public static final int MODE_NORMAL = 0;
	public static final int MODE_SELECTION = 1;
	private int mode;
	private List<WorkSelectionListener> listeners = new ArrayList<>();
	private JLabel lblIstr;

	public WorksManagerWindow(DataManager _data, int _mode) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WorksManagerWindow.class.getResource("/ico_small/agenda.png")));
		setTitle("Gestione Lavori");
		data = _data;
		mode = _mode;
		initComponent();
	}

	private void initComponent() {
		setBounds(100, 100, 964, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		btnTutti = new JButton("Visualizza tutti");
		btnTutti.addActionListener(new BtnTuttiActionListener());
		btnTutti.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtCerca = new JTextField();
		txtCerca.setColumns(10);
		cbCerca = new JComboBox();
		cbCerca.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome Lavoro", "Cognome Cliente", "Azienda Cliente", "Indirizzo", "Inizio Lavori", "Fine Lavori", "Completato"}));
		btnCerca = new JButton("Cerca...");
		btnCerca.addActionListener(new BtnCercaActionListener());
		btnCerca.setIcon(new ImageIcon(WorksManagerWindow.class
				.getResource("/ico_small/search3.png")));
		btnCerca.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAggiungi = new JButton("Aggiungi Cliente");
		btnAggiungi.setIcon(new ImageIcon(WorksManagerWindow.class
				.getResource("/ico_small/add1.png")));
		btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new BtnConfermaActionListener());
		btnConferma.setIcon(new ImageIcon(WorksManagerWindow.class
				.getResource("/ico_small/check.png")));
		panel = new JPanel();
		lblIstr = new JLabel("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																912,
																Short.MAX_VALUE)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								txtCerca,
																								GroupLayout.PREFERRED_SIZE,
																								261,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												btnTutti,
																												GroupLayout.PREFERRED_SIZE,
																												145,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												lblIstr)))
																		.addGap(12)
																		.addComponent(
																				cbCerca,
																				GroupLayout.PREFERRED_SIZE,
																				127,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(7)
																		.addComponent(
																				btnCerca,
																				GroupLayout.PREFERRED_SIZE,
																				135,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(28)
																		.addComponent(
																				btnAggiungi,
																				GroupLayout.PREFERRED_SIZE,
																				193,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(7)
																		.addComponent(
																				btnConferma,
																				GroupLayout.PREFERRED_SIZE,
																				141,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				txtCerca,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(13)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnTutti,
																								GroupLayout.PREFERRED_SIZE,
																								36,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblIstr)))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				cbCerca,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(7)
																		.addComponent(
																				btnCerca,
																				GroupLayout.PREFERRED_SIZE,
																				57,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																btnAggiungi,
																GroupLayout.PREFERRED_SIZE,
																73,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnConferma,
																GroupLayout.PREFERRED_SIZE,
																73,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(panel,
												GroupLayout.DEFAULT_SIZE, 505,
												Short.MAX_VALUE)
										.addContainerGap()));
		panel.setLayout(new BorderLayout(0, 0));
		table = new JTable(this.getTableModel("", ""));
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(table, BorderLayout.CENTER);
		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		if (mode == MODE_NORMAL) {
			btnConferma.setVisible(false);
		} else if (mode == MODE_SELECTION) {
			btnConferma.setVisible(true);
		}
		contentPane.setLayout(gl_contentPane);
	}

	/** Metodo che aggiunge un listener per l'evento di selezione di un work */
	public void addWorkSelectionListener(WorkSelectionListener listener) {
		this.listeners.add(listener);
	}

	/** Metodo che rimuove un listener per l'evento di selezione di un work */
	public void removeWorkSelectionListener(WorkSelectionListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Metodo che restituisce il modello dati per la tabella, costruito
	 * dinamicamente grazie alla ricerca con campo e value
	 */
	private TableModel getTableModel(String campo,String value){
		return new WorkTableModel(campo,value);
	}
	
	/** Metodo che mostra tutti i work*/
	private void showAll(){
		WorkTableModel m = (WorkTableModel) table.getModel();
		m.changeData("", "");
		table.updateUI();
	}
	
	private class BtnConfermaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//si invia il costumer selezionato
			int row = table.getSelectedRow();
			String id ="";
			Work w = null;
			if(row!=-1){
				id = (String) table.getValueAt(row,0);
				try {
					w = data.getWorkByID(id);
				} catch (IDNotFoundException e1) {
					w= null;
				}
			}
			//si invia ai listener
			for(WorkSelectionListener l:listeners){
				l.registerSelectedWork(w);
			}
			//si chiude la finestra
			dispose();
		}
	}
	
	private class BtnTuttiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			showAll();
			txtCerca.setText("");
		}
	}
	private class BtnCercaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			WorkTableModel m = (WorkTableModel) table.getModel();
			m.changeData((String)cbCerca.getSelectedItem(), txtCerca.getText());
			table.updateUI();
		}
	}

	private class WorkTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 5909182725751767737L;
		String[] columns = { "ID", "Nome Lavoro", "Cognome Cliente",
				"Azienda Cliente", "Indirizzo", "Inizio Lavori", "Fine Lavori",
				"Completato" };
		List<Work> lavori = new ArrayList<>();

		public WorkTableModel(String campo, String value) {
			try {
				lavori = data.queryWorkByArg(campo, value);
			} catch (ParseException e) {
				lblIstr.setText("inserire la data nel formato giorno/mese/anno");
			}
		}

		public void changeData(String campo, String value) {
			try {
				lavori = data.queryWorkByArg(campo, value);
			} catch (ParseException e) {
				lblIstr.setText("inserire la data nel formato giorno/mese/anno");
			}
		}

		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public int getRowCount() {
			return lavori.size();
		}

		@Override
		public String getColumnName(int column) {
			return columns[column];
		}

		@Override
		public Object getValueAt(int row, int column) {
			switch (column) {
			case 0:
				return lavori.get(row).getID();
			case 1:
				return lavori.get(row).getNome();
			case 2:
				try {
					return data
							.getCostumerByID(lavori.get(row).getCostumerID())
							.getCognome();
				} catch (IDNotFoundException e) {
					// impossibile che si generi questa eccezione
					return null;
				}
			case 3:
				try {
					return data
							.getCostumerByID(lavori.get(row).getCostumerID())
							.getAzienda();
				} catch (IDNotFoundException e) {
					// impossibile che si generi questa eccezione
					return null;
				}
			case 4:
				return lavori.get(row).getIndirizzo();
			case 5:
				return lavori.get(row).getInizioLavori();
			case 6:
				return lavori.get(row).getFineLavori();
			case 7:
				return lavori.get(row).isCompleted();
			default:
				return null;
			}
		}

		@Override
		public boolean isCellEditable(int a, int b) {
			return false;
		}

		@Override
		public Class<?> getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
	}
}
