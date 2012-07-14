package it.valsecchi.quickagenda.windows;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Costumer;
import it.valsecchi.quickagenda.data.component.ElementType;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.interfaces.CostumerSelectionListener;
import it.valsecchi.quickagenda.data.interfaces.DataUpdateListener;
import it.valsecchi.quickagenda.windows.addelements.AddCostumerWindow;
import static it.valsecchi.quickagenda.data.Utility.Log;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

/**
 * Finestra che visualizza e gestisce i Costumer.
 * @author Davide Valsecchi
 * @version 1.0
 *
 */
public class CostumersManagerWindow extends JFrame {

	private static final long serialVersionUID = 5532739737947952966L;
	private JPanel contentPane;
	private DataManager data;
	private JTextField txtCerca;
	private JButton btnCerca;
	private JComboBox cbCerca;
	private JButton btnAggiungiCliente;
	private JPanel panel;
	private JTable table;
	public static final int MODE_NORMAL = 0;
	public static final int MODE_SELECTION = 1;
	private int mode;
	private JButton btnConferma;
	private JButton btnTutti;
	/** lista di listener per la selezione dei costumer */
	private List<CostumerSelectionListener> listeners = new ArrayList<>();
	private JButton btnRimuovi;
	private JLabel immagine;
	private JLabel label;

	public CostumersManagerWindow(DataManager d, int _mode) {
		data = d;
		//si aggiunge il listener per l'aggiornamento dati
		data.addDataUpdateListener(new CostumerUpdateListener(), ElementType.Costumer);
		mode = _mode;
		setTitle("Gestione Clienti");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				CostumersManagerWindow.class
						.getResource("/ico_small/agenda.png")));
		initComponent();
	}

	public void initComponent() {
		setBounds(100, 100, 1167, 867);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		txtCerca = new JTextField();
		txtCerca.setColumns(10);
		btnCerca = new JButton("Cerca...");
		btnCerca.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerca.addActionListener(new BtnCercaActionListener());
		btnCerca.setIcon(new ImageIcon(CostumersManagerWindow.class
				.getResource("/ico_small/search3.png")));
		cbCerca = new JComboBox();
		cbCerca.setModel(new DefaultComboBoxModel(new String[] { "ID", "Nome",
				"Cognome", "Azienda", "Indirizzo", "Telefono", "Email" }));
		btnTutti = new JButton("Visualizza tutti");
		btnTutti.addActionListener(new BtnTuttiActionListener());
		btnTutti.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnAggiungiCliente = new JButton("Aggiungi Cliente");
		btnAggiungiCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAggiungiCliente.setIcon(new ImageIcon(CostumersManagerWindow.class
				.getResource("/ico_small/add1.png")));
		btnAggiungiCliente
				.addActionListener(new BtnAggiungiClienteActionListener());
		panel = new JPanel();

		btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new BtnConfermaActionListener());
		btnConferma.setIcon(new ImageIcon(CostumersManagerWindow.class
				.getResource("/ico_small/check.png")));
		
		btnRimuovi = new JButton("Rimuovi Clienti Selezionati");
		btnRimuovi.addActionListener(new BtnRimuoviActionListener());
		btnRimuovi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRimuovi.setIcon(new ImageIcon(CostumersManagerWindow.class.getResource("/ico_small/deletered.png")));
		
		immagine = new JLabel("");
		panel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable(this.getTableModel("", ""));
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			table.setFont(new Font("Tahoma", Font.PLAIN, 15));
			table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
			table.setAutoCreateRowSorter(true);
			panel.add(table, BorderLayout.CENTER);
			panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		}
		// si imposta in base alla modalità
		if (mode == MODE_NORMAL) {
			btnConferma.setVisible(false);
		} else if (mode == MODE_SELECTION) {
			btnConferma.setVisible(true);
		}
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(CostumersManagerWindow.class.getResource("/ico_small/users.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(8)
									.addComponent(immagine))
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCerca, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTutti))
							.addGap(12)
							.addComponent(cbCerca, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btnCerca)
							.addGap(12)
							.addComponent(btnConferma))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAggiungiCliente)
							.addGap(7)
							.addComponent(btnRimuovi, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)))
					.addGap(12))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(9)
									.addComponent(immagine))
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(txtCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnTutti, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(cbCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnCerca)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(btnConferma)))
					.addGap(23)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAggiungiCliente)
						.addComponent(btnRimuovi))
					.addGap(13))
		);
		contentPane.setLayout(gl_contentPane);
	}

	/** Metodo che aggiunge un listener per l'evento di selezione di un costumer */
	public void addCostumerSelectionListener(CostumerSelectionListener listener) {
		this.listeners.add(listener);
	}

	/** Metodo che rimuove un listener per l'evento di selezione di un costumer */
	public void removeCostumerSelectionListener(
			CostumerSelectionListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Metodo che restituisce il modello dati per la tabella, costruito
	 * dinamicamente grazie alla ricerca con campo e value
	 */
	private TableModel getTableModel(String campo, String value) {
		return new CostumerTableModel(campo, value);
	}

	/** Metodo che mostra tutti i costumer */
	public void showAll() {
		// Si visualizzano tutti i costumer
		CostumerTableModel m = (CostumerTableModel) table.getModel();
		m.changeData("", "");
		table.updateUI();
	}

	private class BtnCercaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// si ricerca
			CostumerTableModel m = (CostumerTableModel) table.getModel();
			m.changeData((String) cbCerca.getSelectedItem(), txtCerca.getText());
			table.updateUI();
		}
	}

	private class BtnTuttiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			showAll();
			txtCerca.setText("");
		}
	}

	private class BtnAggiungiClienteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AddCostumerWindow form = new AddCostumerWindow(data);
			form.setVisible(true);
		}
	}

	private class BtnConfermaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Si invia il costumer selezionato
			int row = table.getSelectedRow();
			Costumer cos = null;
			if (row != -1) {
				String id = (String) table.getValueAt(row, 0);
				// si ricava il costumer
				try {
					cos = data.getCostumerByID(id);
				} catch (IDNotFoundException e1) {
					cos = null;
				}
			}
			// si invia a ogni listener
			for (CostumerSelectionListener l : listeners) {
				l.registerSelectedCostumer(cos);
			}
			// si chiude la finestra
			dispose();
		}
	}
	private class BtnRimuoviActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//Si ricavano le sessioni selezionate
			int[] selected = table.getSelectedRows();
			List<String> ids = new ArrayList<>();
			for(int i :selected){
				ids.add((String) table.getValueAt(i, 0));
			}
			for(String id :ids){
				try {
					data.removeCostumer(id);
				} catch (IDNotFoundException e) {
					//questa eccezione non dovrebbe accadere
					Log.error("Cliente non trovato");
				}
			}
		}
	}

	private class CostumerTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 8399185788960198357L;
		String[] columns = { "ID", "Nome", "Cognome", "Azienda", "Indirizzo",
				"Telefono", "Email" };
		List<Costumer> costumers = new ArrayList<>();

		public CostumerTableModel(String campo, String value) {
			costumers = data.queryCostumerByArg(campo, value);
		}

		public void changeData(String campo, String value) {
			costumers = data.queryCostumerByArg(campo, value);
			if(costumers==null){
				costumers=new ArrayList<>();
			}
		}

		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public int getRowCount() {
			return costumers.size();
		}

		@Override
		public String getColumnName(int column) {
			return columns[column];
		}

		@Override
		public Object getValueAt(int row, int column) {
			switch (column) {
			case 0:
				return costumers.get(row).getID();
			case 1:
				return costumers.get(row).getNome();
			case 2:
				return costumers.get(row).getCognome();
			case 3:
				return costumers.get(row).getAzienda();
			case 4:
				return costumers.get(row).getIndirizzo();
			case 5:
				return costumers.get(row).getTelefono();
			case 6:
				return costumers.get(row).getEmail();
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

	private class CostumerUpdateListener implements DataUpdateListener {
		@Override
		public void DataUpdatePerformed(ElementType type) {
			if (type != ElementType.Costumer) {
				return;
			}
			//si aggiornano i dati mantenendo i parametri di ricerca correnti
			CostumerTableModel m = (CostumerTableModel) table.getModel();
			m.changeData("","");
			table.updateUI();
		}
	}
}
