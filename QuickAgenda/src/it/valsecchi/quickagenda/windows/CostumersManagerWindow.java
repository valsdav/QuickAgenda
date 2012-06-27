package it.valsecchi.quickagenda.windows;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Costumer;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.interfaces.CostumerSelectionListener;
import it.valsecchi.quickagenda.windows.addelements.AddCostumerWindow;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CostumersManagerWindow extends JFrame {

	private static final long serialVersionUID = 5532739737947952966L;
	private JPanel contentPane;
	private DataManager data;
	private JTextField txtCerca;
	private JButton btnCerca;
	private JComboBox cbCerca;
	private JButton btnNewButton;
	private JButton btnAggiungiCliente;
	private JPanel panel;
	private JTable table;
	public static final int MODE_NORMAL = 0;
	public static final int MODE_SELECTION = 1;
	private int mode;
	private JButton btnConferma;
	/** lista di listener per la selezione dei costumer*/
	private List<CostumerSelectionListener> listeners = new ArrayList<>();

	public CostumersManagerWindow(DataManager d, int _mode) {
		data = d;
		mode = _mode;
		setTitle("Gestione Clienti");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				CostumersManagerWindow.class
						.getResource("/ico_small/agenda.png")));
		initComponent();
	}

	public void initComponent() {
		setBounds(100, 100, 972, 759);
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
		btnNewButton = new JButton("Visualizza tutti");
		btnNewButton.addActionListener(new BtnNewButtonActionListener());
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));

		btnAggiungiCliente = new JButton("Aggiungi Cliente");
		btnAggiungiCliente.setIcon(new ImageIcon(CostumersManagerWindow.class
				.getResource("/ico_small/add1.png")));
		btnAggiungiCliente
				.addActionListener(new BtnAggiungiClienteActionListener());
		panel = new JPanel();
		
		btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new BtnConfermaActionListener());
		btnConferma.setIcon(new ImageIcon(CostumersManagerWindow.class.getResource("/ico_small/check.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtCerca, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cbCerca, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnNewButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCerca)
							.addGap(28)
							.addComponent(btnAggiungiCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnConferma)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbCerca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnConferma, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnCerca)
									.addComponent(btnAggiungiCliente, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(new BorderLayout(0, 0));
		{
			table = new JTable(this.getTableModel("", ""));
			table.setFont(new Font("Tahoma", Font.PLAIN, 15));
			table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 15));
			table.setAutoCreateRowSorter(true);
			panel.add(table, BorderLayout.CENTER);
			panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		}
		//si imposta in base alla modalità
		if(mode ==MODE_NORMAL){
			btnConferma.setVisible(false);
		}else if(mode ==MODE_SELECTION){
			btnConferma.setVisible(true);
		}
		contentPane.setLayout(gl_contentPane);
	}

	public void addCostumerSelectionListener(CostumerSelectionListener listener){
		this.listeners.add(listener);
	}
	
	private AbstractTableModel getTableModel(String campo, String value) {
		return new CostumerTableModel(campo, value);
	}

	public void ShowAll() {
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

	private class BtnNewButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ShowAll();
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
			//Si invia il costumer selezionato
			int column = table.getSelectedColumn();
			int row = table.getSelectedRow();
			String id =(String) table.getValueAt(row, column);
			Costumer cos = null;
			//si ricava il costumer
			try {
				cos =data.getCostumerByID(id);
			} catch (IDNotFoundException e1) {
				cos =null;
			}
			//si invia a ogni listener
			for(CostumerSelectionListener l :listeners){
				l.registerSelectedCostumer(cos);
			}
		}
	}

	private class CostumerTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 8399185788960198357L;
		String[] columns = { "ID", "Nome", "Cognome", "Azienda", "Indirizzo",
				"Telefono", "Email" };
		List<Costumer> costumers = new ArrayList<>();

		public void changeData(String campo, String value) {
			costumers = data.queryCostumerByArg(campo, value);
		}

		public CostumerTableModel(String campo, String value) {
			costumers = data.queryCostumerByArg(campo, value);
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
				return costumers.get(row).getTel();
			case 6:
				return costumers.get(row).getEmail();
			default:
				return null;
			}
		}

		@Override
		public String getColumnName(int column) {
			return columns[column];
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
