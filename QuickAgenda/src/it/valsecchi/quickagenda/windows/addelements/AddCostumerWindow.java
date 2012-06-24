package it.valsecchi.quickagenda.windows.addelements;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import it.valsecchi.quickagenda.data.component.exception.CostumerAlreadyExistsException;
import it.valsecchi.quickagenda.data.exception.InsufficientDataException;
import it.valsecchi.quickagenda.data.interfaces.*;
import static it.valsecchi.quickagenda.data.Utility.Log;
import java.awt.Font;
import javax.swing.JFrame;
import java.awt.Toolkit;

/**
 * Classe che fornisce la GUI per l'inserimento dei dati per un nuovo cliente.
 * La classe richiede un oggetto che implementi l'interfaccia
 * AddCostumerInterface e che quindi fornisca i metodi per l'aggiunta dei
 * costumers.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 */
public class AddCostumerWindow extends javax.swing.JFrame {

	private static final long serialVersionUID = 2240305864691419492L;
	/** Variabile di tipo AddCostumerInterface che gestisce l'aggiunta dei dati */
	private AddCostumerInterface costsMan;

	/**
	 * Costruttore della finestra che richiede un oggetto che implementa
	 * l'interfaccia AddCostumerInterface, per l'aggunta dei costumer.
	 * 
	 * @param _costsMan
	 *            oggetto che gestisce l'aggiunta dei costumers.
	 */
	public AddCostumerWindow(AddCostumerInterface _costsMan) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				AddCostumerWindow.class.getResource("/ico_small/agenda.png")));
		setTitle("Aggiungi Cliente");
		initComponents();
		costsMan = _costsMan;
		Log.info("finestra inizializzata");
	}

	/**
	 * Metodo che inizializza i componenti dell'interfaccia grafica.
	 */
	private void initComponents() {

		txtNome = new javax.swing.JTextField();
		lblNome = new javax.swing.JLabel();
		lblCognome = new javax.swing.JLabel();
		txtCognome = new javax.swing.JTextField();
		lblAzienda = new javax.swing.JLabel();
		txtAzienda = new javax.swing.JTextField();
		lblIndirizzo = new javax.swing.JLabel();
		txtIndirizzo = new javax.swing.JTextField();
		txtTelefono = new javax.swing.JTextField();
		lblTelefono = new javax.swing.JLabel();
		lblEmail = new javax.swing.JLabel();
		txtEmail = new javax.swing.JTextField();
		lblImmagine = new JLabel("");
		jToolBar = new javax.swing.JToolBar();
		addButton = new javax.swing.JButton();
		addButton.addActionListener(new AddButtonClickHandler());
		addButton.setIcon(new ImageIcon(AddCostumerWindow.class
				.getResource("/ico_small/add2.png")));
		exitButton = new javax.swing.JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// si chiude
				dispose();
			}
		});
		exitButton.setIcon(new ImageIcon(AddCostumerWindow.class
				.getResource("/ico_small/shutdown_box_red.png")));
		lblIstruzioni = new javax.swing.JLabel();
		lblIstruzioni.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblAvviso = new javax.swing.JLabel();
		lblAvviso.setFont(new Font("SansSerif", Font.BOLD, 13));

		lblImmagine.setIcon(new ImageIcon(AddCostumerWindow.class
				.getResource("/ico_small/edit.png")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		lblNome.setText("Nome*:");
		lblNome.setName("txtNome");

		lblCognome.setText("Cognome*:");
		lblCognome.setName("txtCognome");

		lblAzienda.setText("Azienda:");
		lblAzienda.setName("txtAzienda");

		lblIndirizzo.setText("Indirizzo:");
		lblIndirizzo.setName("txtIndirizzo");

		lblTelefono.setText("Telefono:");
		lblTelefono.setName("txtTelefono");

		lblEmail.setText("Email:");

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
				.setText("Aggiungi un nuovo cliente inserendo i dati sottostanti.");

		lblAvviso.setText("N.B.: i campi Nome e Cognome sono obbligatori.");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.TRAILING)
				.addComponent(jToolBar, GroupLayout.DEFAULT_SIZE, 432,
						Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblIstruzioni,
										GroupLayout.PREFERRED_SIZE, 312,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(108, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												Alignment.TRAILING, false)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblIndirizzo,
																		GroupLayout.PREFERRED_SIZE,
																		70,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtIndirizzo,
																		287,
																		287,
																		287))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblTelefono,
																		GroupLayout.PREFERRED_SIZE,
																		70,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtTelefono,
																		287,
																		287,
																		287))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblEmail,
																		GroupLayout.PREFERRED_SIZE,
																		70,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtEmail,
																		287,
																		287,
																		287))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblAzienda,
																		GroupLayout.PREFERRED_SIZE,
																		70,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtAzienda))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblCognome,
																		GroupLayout.PREFERRED_SIZE,
																		70,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtCognome))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblNome,
																		GroupLayout.PREFERRED_SIZE,
																		70,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtNome)))
								.addContainerGap(58, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup().addContainerGap()
								.addComponent(lblAvviso)
								.addContainerGap(110, Short.MAX_VALUE))
				.addGroup(
						Alignment.LEADING,
						layout.createSequentialGroup()
								.addGap(167)
								.addComponent(lblImmagine,
										GroupLayout.PREFERRED_SIZE, 79,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(186, Short.MAX_VALUE)));
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
														lblNome,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtNome,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblCognome,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtCognome,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblAzienda,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtAzienda,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
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
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblTelefono,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtTelefono,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblEmail,
														GroupLayout.PREFERRED_SIZE,
														31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtEmail,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblAvviso)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblImmagine)
								.addContainerGap(22, Short.MAX_VALUE)));
		getContentPane().setLayout(layout);
		pack();
	}

	private javax.swing.JButton addButton;
	private javax.swing.JButton exitButton;
	private javax.swing.JLabel lblNome;
	private javax.swing.JLabel lblCognome;
	private javax.swing.JLabel lblAzienda;
	private javax.swing.JLabel lblIndirizzo;
	private javax.swing.JLabel lblTelefono;
	private javax.swing.JLabel lblEmail;
	private javax.swing.JLabel lblIstruzioni;
	private javax.swing.JLabel lblAvviso;
	private javax.swing.JTextField txtNome;
	private javax.swing.JTextField txtCognome;
	private javax.swing.JTextField txtAzienda;
	private javax.swing.JTextField txtIndirizzo;
	private javax.swing.JTextField txtTelefono;
	private javax.swing.JTextField txtEmail;
	private javax.swing.JToolBar jToolBar;
	private javax.swing.JLabel lblImmagine;

	/** Classe interna che gestisce il click del pulsante add */
	private class AddButtonClickHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// si aggiunge un costumer
			try {
				costsMan.addCostumer(txtNome.getText(), txtCognome.getText(),
						txtAzienda.getText(), txtIndirizzo.getText(),
						txtTelefono.getText(), txtEmail.getText());
			} catch (CostumerAlreadyExistsException exc) {
				// si avvisa
				lblAvviso.setText("Cliente già presente nei dati!");
				lblImmagine.setIcon(new ImageIcon(AddCostumerWindow.class
						.getResource("/ico_small/cancel2.png")));
				return;
			} catch (InsufficientDataException exc) {
				// si cambia l'icona
				lblImmagine.setIcon(new ImageIcon(AddCostumerWindow.class
						.getResource("/ico_small/attention.png")));
				// si cambia l'avviso
				lblAvviso
						.setText("Inserire almeno Nome e Cognome del cliente!");
			}
			// si sistema l'icona e l'avviso
			lblAvviso.setText("Cliente aggiunto con successo!");
			lblImmagine.setIcon(new ImageIcon(AddCostumerWindow.class
					.getResource("/ico_small/check.png")));
			// si svuotano i campi
			txtNome.setText("");
			txtCognome.setText("");
			txtAzienda.setText("");
			txtIndirizzo.setText("");
			txtTelefono.setText("");
			txtEmail.setText("");
		}
	}
}
