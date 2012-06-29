package it.valsecchi.quickagenda.windows.addelements;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Costumer;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.component.exception.WorkAlreadyExistsException;
import it.valsecchi.quickagenda.data.exception.InsufficientDataException;
import it.valsecchi.quickagenda.data.interfaces.AddWorkInterface;
import it.valsecchi.quickagenda.data.interfaces.CostumerSelectionListener;
import it.valsecchi.quickagenda.windows.CostumersManagerWindow;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JToolBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;

public class AddWorkWindow extends JFrame implements CostumerSelectionListener {

	private static final long serialVersionUID = -1074813470575197822L;
	private JPanel contentPane;
	private JToolBar toolBar;
	private JButton btnAggiungi;
	private JButton btnEsci;
	private JLabel lblAggiungiUnNuovo;
	private JLabel labelNome;
	private JTextField txtNome;
	private JButton button;
	private JLabel lblIdCliente;
	private JTextField txtIDCliente;
	private JButton btnIDCliente;
	private JLabel lblIndirizzo;
	private JTextField txtIndirizzo;
	private JDateChooser dcInizioLavori;
	private JLabel lblInizioLavori;
	private JLabel lblFineLavori;
	private JDateChooser dcFineLavori;
	private JLabel lblCompletato;
	private JCheckBox checkCompletato;
	private JLabel lblAvviso;
	private JLabel lblImmagine;
	private AddWorkWindow currentWindow;
	private AddWorkInterface workMan;

	public AddWorkWindow(AddWorkInterface workman) {
		workMan = workman;
		setTitle("Aggiungi Lavoro");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				AddWorkWindow.class.getResource("/ico_small/add1.png")));
		initComponent();
	}

	private void initComponent() {
		currentWindow = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 466, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		lblAggiungiUnNuovo = new JLabel();
		lblAggiungiUnNuovo
				.setText("Aggiungi un nuovo lavoro inserendo i dati sottostanti.");
		lblAggiungiUnNuovo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		labelNome = new JLabel();
		labelNome.setText("Nome*:");
		labelNome.setName("txtNome");
		txtNome = new JTextField();
		button = new JButton("...");
		lblIdCliente = new JLabel();
		lblIdCliente.setText("ID Cliente*:");
		lblIdCliente.setName("txtNome");
		txtIDCliente = new JTextField();
		btnIDCliente = new JButton("...");
		btnIDCliente.addActionListener(new BtnIDClienteActionListener());
		lblIndirizzo = new JLabel();
		lblIndirizzo.setText("Indirizzo:");
		lblIndirizzo.setName("txtNome");
		txtIndirizzo = new JTextField();
		dcInizioLavori = new JDateChooser();
		lblInizioLavori = new JLabel();
		lblInizioLavori.setText("Inizio Lavori*:");
		lblInizioLavori.setName("txtNome");
		lblFineLavori = new JLabel();
		lblFineLavori.setText("Fine Lavori:");
		lblFineLavori.setName("txtNome");
		dcFineLavori = new JDateChooser();
		lblCompletato = new JLabel();
		lblCompletato.setText("Completato:");
		lblCompletato.setName("txtNome");
		checkCompletato = new JCheckBox("");
		lblAvviso = new JLabel();
		lblAvviso
				.setText("N.B.: i campi contrassegnati con l'asterisco* sono obbligatori");
		lblAvviso.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblImmagine = new JLabel("");
		lblImmagine.setIcon(new ImageIcon(AddWorkWindow.class
				.getResource("/ico_small/edit.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 334,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblAggiungiUnNuovo,
												GroupLayout.PREFERRED_SIZE,
												362, GroupLayout.PREFERRED_SIZE))
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																labelNome,
																GroupLayout.PREFERRED_SIZE,
																70,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblIdCliente,
																GroupLayout.PREFERRED_SIZE,
																70,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblIndirizzo,
																GroupLayout.PREFERRED_SIZE,
																70,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblInizioLavori))
										.addGap(12)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(txtNome)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								txtIndirizzo,
																								GroupLayout.PREFERRED_SIZE,
																								298,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addComponent(
																												txtIDCliente,
																												GroupLayout.PREFERRED_SIZE,
																												241,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(18)
																										.addComponent(
																												btnIDCliente))
																						.addComponent(
																								dcInizioLavori,
																								GroupLayout.PREFERRED_SIZE,
																								314,
																								GroupLayout.PREFERRED_SIZE))))
										.addGap(217)
										.addComponent(button,
												GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE))
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblFineLavori,
												GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(dcFineLavori,
												GroupLayout.PREFERRED_SIZE,
												314, GroupLayout.PREFERRED_SIZE))
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblCompletato,
												GroupLayout.PREFERRED_SIZE, 81,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(checkCompletato))
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblAvviso,
												GroupLayout.PREFERRED_SIZE,
												383, GroupLayout.PREFERRED_SIZE))
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(174)
										.addComponent(lblImmagine,
												GroupLayout.PREFERRED_SIZE, 79,
												GroupLayout.PREFERRED_SIZE)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addComponent(toolBar,
												GroupLayout.PREFERRED_SIZE, 93,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblAggiungiUnNuovo,
												GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(4)
																		.addComponent(
																				txtNome,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																labelNome,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(16)
																		.addComponent(
																				button))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblIdCliente,
																								GroupLayout.PREFERRED_SIZE,
																								31,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnIDCliente)
																						.addComponent(
																								txtIDCliente,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblIndirizzo,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtIndirizzo,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																dcInizioLavori,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblInizioLavori,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																lblFineLavori,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																dcFineLavori,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblCompletato,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																checkCompletato))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblAvviso,
												GroupLayout.PREFERRED_SIZE, 18,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(lblImmagine,
												GroupLayout.PREFERRED_SIZE, 64,
												GroupLayout.PREFERRED_SIZE)
										.addGap(4)));
		{
			btnAggiungi = new JButton("AGGIUNGI");
			btnAggiungi.addActionListener(new BtnAggiungiActionListener());
			btnAggiungi.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnAggiungi.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnAggiungi.setHorizontalTextPosition(SwingConstants.CENTER);
			btnAggiungi.setIcon(new ImageIcon(AddWorkWindow.class
					.getResource("/ico_small/add2.png")));
			toolBar.add(btnAggiungi);
		}
		{
			btnEsci = new JButton("ESCI");
			btnEsci.setIcon(new ImageIcon(AddWorkWindow.class
					.getResource("/ico_small/shutdown_box_red.png")));
			btnEsci.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnEsci.setHorizontalTextPosition(SwingConstants.CENTER);
			btnEsci.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnEsci.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// si chiude la finestra
					dispose();
				}
			});
			toolBar.add(btnEsci);
		}
		contentPane.setLayout(gl_contentPane);
	}

	@Override
	public void registerSelectedCostumer(Costumer selected_costumer) {
		// se non è null si scrive l'id
		if (selected_costumer != null) {
			txtIDCliente.setText(selected_costumer.getID());
		}
	}

	private class BtnAggiungiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// si controllano e aggiungono i dati
			try {
				workMan.addWork(txtNome.getText(), txtIDCliente.getText()
						.toUpperCase(), txtIndirizzo.getText(), dcInizioLavori
						.getCalendar(), dcFineLavori.getCalendar(),
						checkCompletato.isSelected());

				// si svuotano i campi
				lblAvviso.setText("Lavoro aggiunto con successo!");
				lblImmagine.setIcon(new ImageIcon(AddWorkWindow.class
						.getResource("/ico_small/check.png")));
				txtNome.setText("");
				txtIDCliente.setText("");
				txtIndirizzo.setText("");
				dcInizioLavori.cleanup();
				dcFineLavori.cleanup();
				checkCompletato.setSelected(false);
			} catch (WorkAlreadyExistsException e1) {
				// si avvisa
				lblAvviso.setText("Lavoro già presente nei dati!");
				lblImmagine.setIcon(new ImageIcon(AddWorkWindow.class
						.getResource("/ico_small/cancel2.png")));
				return;
			} catch (InsufficientDataException e1) {
				// si avvisa
				// si cambia l'icona
				lblImmagine.setIcon(new ImageIcon(AddWorkWindow.class
						.getResource("/ico_small/attention.png")));
				// si cambia l'avviso
				lblAvviso.setText("Inserire Nome, ID Cliente e Inizio Lavori!");
				return;
			} catch (IDNotFoundException e2) {
				lblImmagine.setIcon(new ImageIcon(AddSessionWindow.class
						.getResource("/ico_small/attention.png")));
				// si cambia l'avviso
				lblAvviso.setText("Cliente non trovato!");
				return;
			}

		}
	}

	private class BtnIDClienteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// si apre la finestra per la scelta
			CostumersManagerWindow w = new CostumersManagerWindow(
					(DataManager) workMan,
					CostumersManagerWindow.MODE_SELECTION);
			// si registra il listener
			w.addCostumerSelectionListener(currentWindow);
			w.setVisible(true);
		}
	}

}
