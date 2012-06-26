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
	private JButton btnModificaSessione;
	private DataManager manager;
	private String sessionID;
	private JLabel lblDettagliCliente;
	private JLabel lblDettagliLavoro;
	private JLabel label;
	private JLabel label_1;
	private JTextField txtSessioneID;
	private JTextField txtLavoroSessione;
	private JTextField txtClienteSessione;
	private JTextField txtDataSessione;
	private JTextField txtOre;
	private JTextField txtSpesa;
	private JTextField txtMateriali;
	private JButton btnSalvaSessione;
	private JLabel lblIstrSessione;
	private JTextField txtLavoroID;
	private JTextField txtClienteID;
	private JLabel lblIndirizzo;
	private JTextField txtIndirizzoLavoro;
	private JLabel lblInizioLavori;
	private JTextField txtInizioLavori;
	private JLabel lblFineLavori;
	private JTextField txtFineLavori;
	private JLabel lblCompletato;
	private JCheckBox cbCompleted;
	private JSeparator separator_1;
	private JButton btnSalvaLavoro;
	private JButton btnModificaLavoro;
	private JLabel lblIstrLavoro;
	private JLabel label_2;
	private JTextField textField;
	private JLabel label_3;
	private JTextField txtIDCliente;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblCognome;
	private JTextField txtCognome;
	private JLabel lblAzienda;
	private JTextField txtAzienda;
	private JLabel lblIndirizzo_1;
	private JTextField txtIndirizzoCliente;
	private JLabel lblTelefono;
	private JTextField txtTelefonoCliente;
	private JLabel lblEmail;
	private JTextField txtEmailCliente;
	private JButton btnModificaCliente;
	private JButton btnSalvaCliente;

	public SessionDetailWindow(String sessionId, DataManager _manager) {
		manager = _manager;
		sessionID = sessionId;
		initComponent();
		initData();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 761);
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

		btnModificaSessione = new JButton("Modifica");
		btnModificaSessione.addActionListener(new BtnModificaActionListener());
		btnModificaSessione.setFont(new Font("Tahoma", Font.BOLD, 14));

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
		txtSessioneID = new JTextField();
		txtSessioneID.setEditable(false);
		txtSessioneID.setColumns(10);
		txtLavoroSessione = new JTextField();
		txtLavoroSessione.setEditable(false);
		txtLavoroSessione.setColumns(10);
		txtClienteSessione = new JTextField();
		txtClienteSessione.setEditable(false);
		txtClienteSessione.setColumns(10);
		txtDataSessione = new JTextField();
		txtDataSessione.setEditable(false);
		txtDataSessione.setColumns(10);
		txtOre = new JTextField();
		txtOre.setEditable(false);
		txtOre.setColumns(10);
		txtSpesa = new JTextField();
		txtSpesa.setEditable(false);
		txtSpesa.setColumns(10);
		txtMateriali = new JTextField();
		txtMateriali.setEditable(false);
		txtMateriali.setColumns(10);
		btnSalvaSessione = new JButton("Salva");
		btnSalvaSessione.addActionListener(new BtnSalvaActionListener());
		btnSalvaSessione.setEnabled(false);
		btnSalvaSessione.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblIstrSessione = new JLabel("");

		txtLavoroID = new JTextField();
		txtLavoroID.setEditable(false);
		txtLavoroID.setColumns(10);

		txtClienteID = new JTextField();
		txtClienteID.setEditable(false);
		txtClienteID.setColumns(10);

		lblIndirizzo = new JLabel("Indirizzo:");
		lblIndirizzo.setForeground(Color.BLUE);
		lblIndirizzo.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtIndirizzoLavoro = new JTextField();
		txtIndirizzoLavoro.setEditable(false);
		txtIndirizzoLavoro.setColumns(10);

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
		btnSalvaLavoro.addActionListener(new BtnSalvaLavoroActionListener());
		btnSalvaLavoro.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvaLavoro.setEnabled(false);
		btnModificaLavoro = new JButton("Modifica");
		btnModificaLavoro.addActionListener(new BtnModificaLavoroActionListener());
		btnModificaLavoro.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblIstrLavoro = new JLabel("");
		
		label_2 = new JLabel("ID:");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setText((String) null);
		textField.setEditable(false);
		textField.setColumns(10);
		
		label_3 = new JLabel("ID:");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtIDCliente = new JTextField();
		txtIDCliente.setEditable(false);
		txtIDCliente.setColumns(10);
		
		lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.BLUE);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtNome = new JTextField();
		txtNome.setEditable(false);
		txtNome.setColumns(10);
		
		lblCognome = new JLabel("Cognome:");
		lblCognome.setForeground(Color.BLUE);
		lblCognome.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtCognome = new JTextField();
		txtCognome.setEditable(false);
		txtCognome.setColumns(10);
		
		lblAzienda = new JLabel("Azienda:");
		lblAzienda.setForeground(Color.BLUE);
		lblAzienda.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtAzienda = new JTextField();
		txtAzienda.setEditable(false);
		txtAzienda.setColumns(10);
		
		lblIndirizzo_1 = new JLabel("Indirizzo:");
		lblIndirizzo_1.setForeground(Color.BLUE);
		lblIndirizzo_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtIndirizzoCliente = new JTextField();
		txtIndirizzoCliente.setEditable(false);
		txtIndirizzoCliente.setColumns(10);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(Color.BLUE);
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtTelefonoCliente = new JTextField();
		txtTelefonoCliente.setEditable(false);
		txtTelefonoCliente.setColumns(10);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.BLUE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtEmailCliente = new JTextField();
		txtEmailCliente.setEditable(false);
		txtEmailCliente.setColumns(10);
		
		btnModificaCliente = new JButton("Modifica");
		btnModificaCliente.addActionListener(new BtnModificaClienteActionListener());
		btnModificaCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnSalvaCliente = new JButton("Salva");
		btnSalvaCliente.addActionListener(new BtnSalvaClienteActionListener());
		btnSalvaCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvaCliente.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnModificaSessione)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalvaSessione)
							.addGap(18)
							.addComponent(lblIstrSessione))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtLavoroID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtClienteID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 262, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblID)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtSessioneID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblData)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtDataSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblOre, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(txtLavoroSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblNewLabel_4))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(txtOre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblSpesa, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtSpesa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtClienteSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblMateriali, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtMateriali))
								.addComponent(immagine1)
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 618, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(txtIndirizzoLavoro, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblInizioLavori, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(txtInizioLavori, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblFineLavori, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblDettagliLavoro, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
									.addComponent(txtFineLavori, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCompletato, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbCompleted))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnModificaLavoro, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btnSalvaLavoro, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblIstrLavoro))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDettagliCliente, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblAzienda, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtAzienda, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblIndirizzo_1)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtIndirizzoCliente))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(txtIDCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblNome)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblCognome)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtTelefonoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtEmailCliente, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnModificaCliente, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSalvaCliente, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(immagine1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblID)
								.addComponent(lblNewLabel_2)
								.addComponent(txtLavoroSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4)
								.addComponent(txtClienteSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSessioneID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblData)
								.addComponent(lblOre)
								.addComponent(txtDataSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtOre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSpesa)
								.addComponent(txtSpesa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMateriali, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMateriali, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnModificaSessione)
								.addComponent(btnSalvaSessione)
								.addComponent(lblIstrSessione))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(lblDettagliLavoro, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtLavoroID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtClienteID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIndirizzoLavoro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblInizioLavori, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtInizioLavori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFineLavori, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFineLavori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCompletato, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbCompleted))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnModificaLavoro)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnSalvaLavoro)
									.addComponent(lblIstrLavoro)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDettagliCliente, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(0)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIDCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCognome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAzienda, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAzienda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIndirizzo_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIndirizzoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(58)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnModificaCliente)
								.addComponent(btnSalvaCliente))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(421)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(629)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(636)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTelefonoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmailCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
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
		txtSessioneID.setText(sessionID);
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
		txtDataSessione.setText(f.format(session.getSessionData().getTime()));
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
		txtClienteSessione.setText(costumer.getID());
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
		txtIndirizzoLavoro.setText(work.getIndirizzo());
		txtInizioLavori.setText(f.format(work.getInizioLavori().getTime()));
		if (work.getFineLavori() != null) {
			txtFineLavori.setText(f.format(work.getFineLavori().getTime()));
		}
		if (work.isCompleted() == true) {
			cbCompleted.setSelected(true);
		} else {
			cbCompleted.setSelected(false);
		}

		
		
	}

	private class BtnModificaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// si rendono editabili i campi
			txtDataSessione.setEditable(true);
			txtOre.setEditable(true);
			txtSpesa.setEditable(true);
			txtMateriali.setEditable(true);
			// si abilita il pulsante salva
			btnSalvaSessione.setEnabled(true);
			btnModificaSessione.setEnabled(false);
		}
	}

	private class BtnSalvaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// si salvano i nuovi dati
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
			try {
				Calendar c = Calendar.getInstance();
				c.setTime(f.parse(txtDataSessione.getText()));
				session.setSessionData(c);
			} catch (ParseException e1) {
				// si notifica l'errore
				lblIstrSessione.setText("Inserire la data nel formato giorno/mese/anno");
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
			lblIstrSessione.setText("");
			btnModificaSessione.setEnabled(true);
			btnSalvaSessione.setEnabled(false);
		}
	}

	private class CbCompletedItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg) {
			if(arg.getStateChange() == ItemEvent.SELECTED){
				work.setCompleted(true);
			}
			else{
				work.setCompleted(false);
			}
		}
	}
	
	private class BtnModificaLavoroActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//si rendono editabili
			txtIndirizzoLavoro.setEditable(true);
			txtInizioLavori.setEditable(true);
			txtFineLavori.setEditable(true);
			btnSalvaLavoro.setEnabled(true);
			btnModificaLavoro.setEnabled(false);
		}
	}
	private class BtnSalvaLavoroActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//si salvano i nuovi dati
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
			try {
				Calendar c = Calendar.getInstance();
				c.setTime(f.parse(txtInizioLavori.getText()));
				work.setInizioLavori(c);
				Calendar d= Calendar.getInstance();
				d.setTime(f.parse(txtFineLavori.getText()));
				work.setFineLavori(d);
			} catch (ParseException e1) {
				// si notifica l'errore
				lblIstrLavoro.setText("Inserire la data nel formato giorno/mese/anno");
				return;
			}
			work.setIndirizzo(txtIndirizzoLavoro.getText());
			btnSalvaLavoro.setEnabled(false);
			btnModificaLavoro.setEnabled(true);
		}
	}
	
	private class BtnModificaClienteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			txtNome.setEditable(true);
			txtCognome.setEditable(true);
			txtIndirizzoCliente.setEditable(true);
			txtAzienda.setEditable(true);
			txtEmailCliente.setEditable(true);
			txtTelefonoCliente.setEditable(true);
			btnModificaLavoro.setEnabled(false);
			btnSalvaLavoro.setEnabled(true);
		}
	}
	private class BtnSalvaClienteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			costumer.setNome(txtNome.getText());
			costumer.setCognome(txtCognome.getText());
			costumer.setIndirizzo(txtIndirizzoCliente.getText());
			costumer.setAzienda(txtAzienda.getText());
			costumer.setEmail(txtEmailCliente.getText());
			costumer.setTel(txtTelefonoCliente.getText());
			btnModificaCliente.setEnabled(true);
			btnSalvaCliente.setEnabled(false);		}
	}
	
}
