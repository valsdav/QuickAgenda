package it.valsecchi.quickagenda.windows.detail;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Costumer;
import it.valsecchi.quickagenda.data.component.ElementType;
import it.valsecchi.quickagenda.data.component.Session;
import it.valsecchi.quickagenda.data.component.Work;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.interfaces.CostumerSelectionListener;
import it.valsecchi.quickagenda.data.interfaces.DataUpdateListener;
import it.valsecchi.quickagenda.windows.CostumersManagerWindow;
import it.valsecchi.quickagenda.windows.addelements.AddSessionWindow;
import static it.valsecchi.quickagenda.data.Utility.Log;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class WorkDetailWindow extends JFrame {

	private static final long serialVersionUID = -4829222952819537156L;
	private JPanel contentPane;
	private DataManager data;
	private String workID;
	private Work work;
	private JLabel immagine1;
	private JLabel lblIDLavoro;
	private JTextField txtIDLavoro;
	private JLabel lblIdCliente;
	private JTextField txtIDCliente;
	private JLabel lblNomeLavoro;
	private JTextField txtNomeLavoro;
	private JTextField txtIndirizzoLavoro;
	private JLabel lblIndirizzoLavoro;
	private JLabel lblInizioLavori;
	private JTextField txtInizioLavori;
	private JTextField txtFineLavori;
	private JLabel lblFineLavori;
	private JLabel lblCompletato;
	private JCheckBox checkCompletato;
	private JButton btnModifica;
	private JButton btnSalva;
	private JSeparator separator;
	private JSeparator separator_1;
	private JButton btnDettagliCliente;
	private JLabel label;
	private JLabel lblSessioniDelLavoro;
	private JPanel panel;
	private JTable table;
	private JButton btnAggiungiSessione;
	private JButton btnRimuoviSessioniSelezionate;
	private JLabel lblIstrLavoro;
	private JButton btnCambiaCliente;
	private JButton btnResocontoLavoro;
	private JButton btnStampaTabellaSessioni;
	private JTextField txtCerca;
	private JComboBox cbCerca;
	private JButton btnCerca;
	private JButton btnVisualizzaTutto;
	private JLabel lblIstr;

	public WorkDetailWindow(String workId, DataManager _data) {
		setTitle("Dettagli Lavoro");
		workID = workId;
		data = _data;
		// Si registrano i listener per gli aggiornamenti dati
		data.addDataUpdateListener(new UpdateListener(), ElementType.Work);
		data.addDataUpdateListener(new UpdateListener(), ElementType.Session);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				WorkDetailWindow.class.getResource("/ico_small/work2.png")));
		initComponent();
		initData();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 918, 870);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		immagine1 = new JLabel("");
		immagine1.setIcon(new ImageIcon(WorkDetailWindow.class
				.getResource("/ico_128/work2.png")));
		lblIDLavoro = new JLabel("ID Lavoro:");
		txtIDLavoro = new JTextField();
		txtIDLavoro.setEditable(false);
		txtIDLavoro.setColumns(10);
		lblIdCliente = new JLabel("ID Cliente:");
		txtIDCliente = new JTextField();
		txtIDCliente.setEditable(false);
		txtIDCliente.setColumns(10);
		lblNomeLavoro = new JLabel("Nome Lavoro:");
		txtNomeLavoro = new JTextField();
		txtNomeLavoro.setEditable(false);
		txtNomeLavoro.setColumns(10);

		txtIndirizzoLavoro = new JTextField();
		txtIndirizzoLavoro.setEditable(false);
		txtIndirizzoLavoro.setColumns(10);

		lblIndirizzoLavoro = new JLabel("Indirizzo Lavoro:");

		lblInizioLavori = new JLabel("Inizio Lavori:");

		txtInizioLavori = new JTextField();
		txtInizioLavori.setEditable(false);
		txtInizioLavori.setColumns(10);

		txtFineLavori = new JTextField();
		txtFineLavori.setEditable(false);
		txtFineLavori.setColumns(10);

		lblFineLavori = new JLabel("Fine Lavori:");

		lblCompletato = new JLabel("Completato:");
		checkCompletato = new JCheckBox("");
		checkCompletato.setEnabled(false);
		btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new BtnModificaActionListener());
		btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new BtnSalvaActionListener());
		btnSalva.setEnabled(false);
		separator = new JSeparator();

		separator_1 = new JSeparator();

		btnDettagliCliente = new JButton("Dettagli Cliente...");

		label = new JLabel("");
		label.setIcon(new ImageIcon(WorkDetailWindow.class
				.getResource("/ico_small/tools.png")));

		lblSessioniDelLavoro = new JLabel("Sessioni di Lavoro");
		lblSessioniDelLavoro.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel = new JPanel();

		btnAggiungiSessione = new JButton("Aggiungi Sessione");
		btnAggiungiSessione
				.addActionListener(new BtnAggiungiSessioneActionListener());
		btnAggiungiSessione.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAggiungiSessione.setIcon(new ImageIcon(WorkDetailWindow.class
				.getResource("/ico_small/add1.png")));

		btnRimuoviSessioniSelezionate = new JButton(
				"Rimuovi Sessioni Selezionate");
		btnRimuoviSessioniSelezionate
				.addActionListener(new BtnRimuoviSessioniSelezionateActionListener());
		btnRimuoviSessioniSelezionate
				.setIcon(new ImageIcon(WorkDetailWindow.class
						.getResource("/ico_small/deletered.png")));
		btnRimuoviSessioniSelezionate
				.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblIstrLavoro = new JLabel("");

		btnCambiaCliente = new JButton("Cambia Cliente...");
		btnCambiaCliente
				.addActionListener(new BtnCambiaClienteActionListener());
		btnCambiaCliente.setEnabled(false);
		btnResocontoLavoro = new JButton("Resoconto Lavoro");
		btnResocontoLavoro.addActionListener(new BtnResocontoLavoroActionListener());
		btnResocontoLavoro.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnStampaTabellaSessioni = new JButton("Stampa tabella sessioni");
		btnStampaTabellaSessioni.addActionListener(new BtnStampaTabellaSessioniActionListener());
		panel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable(new SessionTableModel(workID));
			table.addMouseListener(new TableMouseListener());
			table.setFont(new Font("Tahoma", Font.PLAIN, 15));
			table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
			panel.add(table, BorderLayout.CENTER);
			panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		}
		txtCerca = new JTextField();
		txtCerca.setColumns(10);
		cbCerca = new JComboBox();
		cbCerca.setModel(new DefaultComboBoxModel(new String[] {"ID", "Data", "N\u00B0 Ore", "Minimo N\u00B0 Ore", "Massimo N\u00B0 Ore", "Spesa", "Minima Spesa", "Massima Spesa", "Note"}));
		btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new BtnCercaActionListener());
		btnCerca.setIcon(new ImageIcon(WorkDetailWindow.class.getResource("/ico_small/search3.png")));
		btnVisualizzaTutto = new JButton("Visualizza tutto");
		btnVisualizzaTutto.addActionListener(new BtnVisualizzaTuttoActionListener());
		lblIstr = new JLabel("");
		
		lblIstr = new JLabel("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(immagine1)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblIDLavoro, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(txtIDLavoro, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(lblIdCliente, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(txtIDCliente, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(btnCambiaCliente)
									.addGap(29)
									.addComponent(btnDettagliCliente))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNomeLavoro)
									.addGap(18)
									.addComponent(txtNomeLavoro, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(lblIndirizzoLavoro)
									.addGap(5)
									.addComponent(txtIndirizzoLavoro, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInizioLavori, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(txtInizioLavori, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(lblFineLavori, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(txtFineLavori, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(146)
							.addComponent(lblCompletato, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(checkCompletato))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(btnModifica)
							.addGap(7)
							.addComponent(btnSalva)
							.addGap(18)
							.addComponent(lblIstrLavoro, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
							.addGap(189)
							.addComponent(btnResocontoLavoro))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 865, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblSessioniDelLavoro))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCerca, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(9)
									.addComponent(cbCerca, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
							.addGap(12)
							.addComponent(btnCerca)
							.addGap(12)
							.addComponent(btnVisualizzaTutto)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblIstr, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 865, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(btnAggiungiSessione)
							.addGap(9)
							.addComponent(btnRimuoviSessioniSelezionate, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(btnStampaTabellaSessioni)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(immagine1)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblIDLavoro))
								.addComponent(txtIDLavoro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblIdCliente))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(txtIDCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnCambiaCliente)
								.addComponent(btnDettagliCliente))
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNomeLavoro))
								.addComponent(txtNomeLavoro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblIndirizzoLavoro))
								.addComponent(txtIndirizzoLavoro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblInizioLavori))
								.addComponent(txtInizioLavori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblFineLavori))
								.addComponent(txtFineLavori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCompletato)
						.addComponent(checkCompletato))
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModifica)
						.addComponent(btnSalva)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblIstrLavoro, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnResocontoLavoro))
					.addGap(7)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(16)
									.addComponent(lblSessioniDelLavoro)))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addComponent(btnCerca))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(78)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIstr, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnVisualizzaTutto))))
					.addGap(13)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAggiungiSessione)
						.addComponent(btnRimuoviSessioniSelezionate)
						.addComponent(btnStampaTabellaSessioni)))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void initData() {
		// si inizializzano i dati
		// si ricava il work
		try {
			work = data.getWorkByID(workID);
		} catch (IDNotFoundException e) {
			// non si puo verificare questo errore
			Log.error("id " + e.getID() + ", non trovato");
		}
		txtIDLavoro.setText(work.getID());
		txtIDCliente.setText(work.getCostumerID());
		txtNomeLavoro.setText(work.getNome());
		txtIndirizzoLavoro.setText(work.getIndirizzo());
		txtInizioLavori.setText(work.getInizioLavoriString());
		txtFineLavori.setText(work.getInizioLavoriString());
		checkCompletato.setSelected(work.isCompleted());
	}

	private class SessionTableModel extends AbstractTableModel {
		private static final long serialVersionUID = -8486226384087447085L;
		// dati
		// si recuperano le session
		List<Session> sessions = new ArrayList<>();
		// colonne
		String[] columns = { "ID", "Data", "N° di ore", "Spesa", "Note" };

		public SessionTableModel(String _workID) {
			try {
				sessions = data.querySessionByArg(_workID, "","");
			} catch (ParseException e) {
				//non si può sollevare questa eccezione perchè viene passato il campo "".
			}
		}
		
		public void changeData(String _workID,String campo,String value){
			try {
				sessions = data.querySessionByArg(_workID,campo,value);
			} catch (ParseException e) {
			 lblIstr.setText("Inserire la data nel formato giorno/mese/anno");
			}
		}

		@Override
		public int getRowCount() {
			return sessions.size();
		}

		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public String getColumnName(int c) {
			return columns[c];
		}

		@Override
		public Object getValueAt(int row, int column) {
			switch (column) {
			case 0:
				return sessions.get(row).getID();
			case 1:
				return sessions.get(row).getSessionDataString();
			case 2:
				return sessions.get(row).getHours();
			case 3:
				return sessions.get(row).getSpesa();
			case 4:
				return sessions.get(row).getNote();
			default:
				return null;
			}
		}

		@Override
		public boolean isCellEditable(int a, int b) {
			return false;
		}
	}

	private class UpdateListener implements DataUpdateListener {

		@Override
		public void DataUpdatePerformed(ElementType type) {
			if (type == ElementType.Work) {
				try {
					work = data.getWorkByID(workID);
				} catch (IDNotFoundException e) {
					// vuol dire che il work è stato eliminato quindi si chiude
					// la finestra
					dispose();
				}
				txtIDLavoro.setText(work.getID());
				txtIDCliente.setText(work.getCostumerID());
				txtNomeLavoro.setText(work.getNome());
				txtIndirizzoLavoro.setText(work.getIndirizzo());
				txtInizioLavori.setText(work.getInizioLavoriString());
				txtFineLavori.setText(work.getInizioLavoriString());
				checkCompletato.setSelected(work.isCompleted());
			} else if (type == ElementType.Session) {
				// si aggiorna il table model
				SessionTableModel m = (SessionTableModel) table.getModel();
				m.changeData(workID,"","");
				table.updateUI();
			}
		}
	}

	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg) {
			// si controlla se c'è un doppio click
			if (arg.getClickCount() == 2) {
				// si ricava l'id
				String id = (String) table
						.getValueAt(table.getSelectedRow(), 0);
				// si apre la finestra di dettagli
				SessionDetailWindow detail = new SessionDetailWindow(id, data);
				detail.setVisible(true);
			}
		}
	}

	private class BtnAggiungiSessioneActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// si apre la finestra per l'aggiunta di nuove sessioni
			AddSessionWindow add = new AddSessionWindow(data,Calendar.getInstance());
			add.setWorkID(workID);
			add.setVisible(true);
		}
	}

	private class BtnRimuoviSessioniSelezionateActionListener implements
			ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Si ricavano le sessioni selezionate
			int[] selected = table.getSelectedRows();
			List<String> ids = new ArrayList<>();
			for (int i : selected) {
				ids.add((String) table.getValueAt(i, 0));
			}
			// ora si rimuovono
			for (String id : ids) {
				try {
					data.removeSession(id);
				} catch (IDNotFoundException e2) {
					// questa eccezione non dovrebbe accadere
					Log.error("Sessione non trovata");
				}
			}
		}
	}

	private class BtnModificaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// si rendono editabili i campi
			txtIDCliente.setEditable(true);
			btnCambiaCliente.setEnabled(true);
			txtNomeLavoro.setEditable(true);
			txtInizioLavori.setEditable(true);
			txtFineLavori.setEditable(true);
			checkCompletato.setEnabled(true);
			btnModifica.setEnabled(false);
			btnSalva.setEnabled(true);
		}
	}

	private class BtnSalvaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// si salvano i dati
			if (txtIDCliente.getText().equals("")) {
				lblIstrLavoro.setText("Scegliere un Cliente per il lavoro!");
				return;
			}
			try {
				data.changeWorkCostumerID(work.getID(), txtIDCliente.getText()
						.trim().toUpperCase());
			} catch (IDNotFoundException e1) {
				// non dovrebbe verificarsi questa eccezione
				Log.error("Cliente " + e1.getID() + " non trovato!");
			}
			work.setNome(txtNomeLavoro.getText());
			try {
				work.setInizioLavori(txtInizioLavori.getText());
				work.setFineLavori(txtFineLavori.getText());
			} catch (ParseException e1) {
				lblIstrLavoro
						.setText("Imserire la data nel formato giorno/mese/anno!");
				return;
			}
			work.setCompleted(checkCompletato.isSelected());
			// si lancia l'aggiornamento Work
			data.fireDataUpdatePerformed(ElementType.Work);
			// Si disabilita
			txtIDCliente.setEditable(false);
			btnCambiaCliente.setEnabled(false);
			txtNomeLavoro.setEditable(false);
			txtInizioLavori.setEditable(false);
			txtFineLavori.setEditable(false);
			checkCompletato.setEnabled(false);
			btnModifica.setEnabled(true);
			btnSalva.setEnabled(false);
		}
	}

	private class BtnCambiaClienteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// si apre il costumerManager per la scelta
			CostumersManagerWindow cost = new CostumersManagerWindow(data,
					CostumersManagerWindow.MODE_SELECTION);
			cost.addCostumerSelectionListener(new CostumerSelection());
			cost.setVisible(true);
		}
	}
	private class BtnResocontoLavoroActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	private class BtnStampaTabellaSessioniActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//si stampa la tabella
			try{
			table.print(JTable.PrintMode.FIT_WIDTH);}
			catch(PrinterException e2){
				Log.error("stampa fallita");
			}
		}
	}
	private class BtnCercaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//si aggiornano i dati
			lblIstr.setText("");
			SessionTableModel m = (SessionTableModel) table.getModel();
			m.changeData(workID,(String) cbCerca.getSelectedItem(), txtCerca.getText());
			table.updateUI();
		}
	}
	private class BtnVisualizzaTuttoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//si riinseriscono tutti i dati
			SessionTableModel m = (SessionTableModel) table.getModel();
			m.changeData(workID,"","");
			table.updateUI();
		}
	}

	private class CostumerSelection implements CostumerSelectionListener {
		@Override
		public void registerSelectedCostumer(Costumer selected_costumer) {
			// si memorizza l'id
			txtIDCliente.setText(selected_costumer.getID());
		}
	}
}
