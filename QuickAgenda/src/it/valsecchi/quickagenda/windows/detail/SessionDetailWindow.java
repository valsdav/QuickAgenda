package it.valsecchi.quickagenda.windows.detail;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.Utility;
import it.valsecchi.quickagenda.data.component.Costumer;
import it.valsecchi.quickagenda.data.component.ElementType;
import it.valsecchi.quickagenda.data.component.Session;
import it.valsecchi.quickagenda.data.component.Work;
import it.valsecchi.quickagenda.data.component.exception.IDNotFoundException;
import it.valsecchi.quickagenda.data.component.exception.SessionAlreadyExistsException;
import static it.valsecchi.quickagenda.data.Utility.Log;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Toolkit;

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
	private JLabel lblNote;
	private JSeparator separator;
	private JButton btnModificaSessione;
	private DataManager data;
	private String sessionID;
	private JLabel lblDettagliCliente;
	private JLabel lblDettagliLavoro;
	private JLabel label;
	private JLabel label_1;
	private JTextField txtSessioneID;
	private JTextField txtIDLavoroSessione;
	private JTextField txtClienteSessione;
	private JTextField txtDataSessione;
	private JTextField txtOre;
	private JTextField txtSpesa;
	private JTextField txtNote;
	private JButton btnSalvaSessione;
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
	private JLabel lblIstrSessione;
	private JButton btnDettagliLavoro;

	public SessionDetailWindow(String sessionId, DataManager _manager) {
		setTitle("Dettagli Sessione");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				SessionDetailWindow.class.getResource("/ico_small/tools.png")));
		data = _manager;
		sessionID = sessionId;
		initComponent();
		initData();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 674, 775);
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
		lblNote = new JLabel("Note:");
		lblNote.setForeground(Color.BLUE);
		lblNote.setFont(new Font("Tahoma", Font.BOLD, 14));

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
		txtIDLavoroSessione = new JTextField();
		txtIDLavoroSessione.setEditable(false);
		txtIDLavoroSessione.setColumns(10);
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
		txtNote = new JTextField();
		txtNote.setEditable(false);
		txtNote.setColumns(10);
		btnSalvaSessione = new JButton("Salva");
		btnSalvaSessione
				.addActionListener(new BtnSalvaSessioneActionListener());
		btnSalvaSessione.setEnabled(false);
		btnSalvaSessione.setFont(new Font("Tahoma", Font.BOLD, 14));

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
		cbCompleted.setEnabled(false);
		separator_1 = new JSeparator();

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

		lblIstrSessione = new JLabel("");

		btnDettagliLavoro = new JButton("Dettagli Lavoro...");
		btnDettagliLavoro
				.addActionListener(new BtnDettagliLavoroActionListener());
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(immagine1)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblID)
					.addGap(18)
					.addComponent(txtSessioneID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addComponent(txtIDLavoroSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(lblNewLabel_4)
					.addGap(11)
					.addComponent(txtClienteSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblData)
					.addGap(12)
					.addComponent(txtDataSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblOre, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(txtOre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblSpesa, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(txtSpesa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblNote, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(txtNote, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(btnModificaSessione)
					.addGap(12)
					.addComponent(btnSalvaSessione)
					.addGap(12)
					.addComponent(lblIstrSessione, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE))
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 654, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblDettagliLavoro, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnDettagliLavoro, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(label)
					.addGap(12)
					.addComponent(txtLavoroID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(label_1)
					.addGap(5)
					.addComponent(txtClienteID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblIndirizzo, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(txtIndirizzoLavoro, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblInizioLavori, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(txtInizioLavori, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(99)
							.addComponent(txtFineLavori, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblFineLavori, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(lblCompletato, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(cbCompleted))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(187)
					.addComponent(lblIstrLavoro, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 649, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDettagliCliente, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(label_3)
					.addGap(12)
					.addComponent(txtIDCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNome)
					.addGap(5)
					.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblCognome)
					.addGap(83)
					.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(lblAzienda)
					.addGap(5)
					.addComponent(txtAzienda, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblIndirizzo_1)
					.addGap(12)
					.addComponent(txtIndirizzoCliente, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(lblTelefono, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(txtTelefonoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtEmailCliente, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(759)
					.addComponent(label_2))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(793)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(immagine1)
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblID))
						.addComponent(txtSessioneID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblNewLabel_2))
						.addComponent(txtIDLavoroSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblNewLabel_4))
						.addComponent(txtClienteSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblData))
						.addComponent(txtDataSessione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblOre))
						.addComponent(txtOre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblSpesa))
						.addComponent(txtSpesa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblNote))
						.addComponent(txtNote, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModificaSessione)
						.addComponent(btnSalvaSessione)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblIstrSessione, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addGap(13)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDettagliLavoro, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(btnDettagliLavoro)))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(label))
						.addComponent(txtLavoroID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(label_1))
						.addComponent(txtClienteID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblIndirizzo))
						.addComponent(txtIndirizzoLavoro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblInizioLavori))
						.addComponent(txtInizioLavori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFineLavori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblFineLavori)))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCompletato)
						.addComponent(cbCompleted))
					.addGap(10)
					.addComponent(lblIstrLavoro, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDettagliCliente, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(label_3))
						.addComponent(txtIDCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblNome))
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblCognome))
						.addComponent(txtCognome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblAzienda))
						.addComponent(txtAzienda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblIndirizzo_1))
						.addComponent(txtIndirizzoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblTelefono))
						.addComponent(txtTelefonoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(lblEmail))
						.addComponent(txtEmailCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(465)
					.addComponent(label_2)
					.addGap(191)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void initData() {
		// si ricava la sessione
		try {
			session = data.getSession(sessionID);
		} catch (IDNotFoundException e) {
			// non si puo verificare questo errore
			Log.error("id " + e.getID() + ", non trovato");
		}
		txtSessioneID.setText(sessionID);
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
		txtDataSessione.setText(f.format(session.getSessionData().getTime()));
		txtNote.setText(session.getNote());
		// si ricava il costumer
		try {
			costumer = data.getCostumerFromSession(sessionID);
		} catch (IDNotFoundException e) {
			// non si puo verificare questo errore
			Log.error("id " + e.getID() + ", non trovato");
		}
		txtClienteSessione.setText(costumer.getID());
		txtOre.setText(Integer.toString(session.getHours()));
		txtSpesa.setText(Integer.toString(session.getSpesa()));
		// Si ricava il work
		try {
			work = data.getWorkFromSession(sessionID);
		} catch (IDNotFoundException e) {
			// non si puo verificare questo errore
			Log.error("id " + e.getID() + ", non trovato");
		}
		txtIDLavoroSessione.setText(work.getID());
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
		txtIDCliente.setText(costumer.getID());
		txtNome.setText(costumer.getNome());
		txtCognome.setText(costumer.getCognome());
		txtAzienda.setText(costumer.getAzienda());
		txtIndirizzoCliente.setText(costumer.getIndirizzo());
		txtTelefonoCliente.setText(costumer.getTelefono());
		txtEmailCliente.setText(costumer.getEmail());
	}

	private class BtnModificaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// si rendono editabili i campi
			txtDataSessione.setEditable(true);
			txtOre.setEditable(true);
			txtSpesa.setEditable(true);
			txtNote.setEditable(true);
			// si abilita il pulsante salva
			btnSalvaSessione.setEnabled(true);
			btnModificaSessione.setEnabled(false);
		}
	}

	private class BtnSalvaSessioneActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			lblIstrSessione.setText("");
			Calendar _data = null;
			// si salvano i nuovi dati
			try {
				_data = Utility
						.parseStringToCalendar(txtDataSessione.getText());
			} catch (ParseException e1) {
				// si notifica l'errore
				lblIstrSessione
						.setText("Inserire la data nel formato giorno/mese/anno");
				return;
			}
			int hours = Integer.parseInt(txtOre.getText());
			int spesa = Integer.parseInt(txtSpesa.getText());
			String note = txtNote.getText();
			// si modifica
			try {
				data.modifySession(sessionID, _data, hours, spesa, note);
			} catch (IDNotFoundException e1) {
				// non dovrebbe verificarsi questa eccezione
				Log.error("Sessione non trovata! " + e1.getID());
				return;
			}
			// si disabilitano i pulsanti
			lblIstrSessione.setText("");
			btnModificaSessione.setEnabled(true);
			btnSalvaSessione.setEnabled(false);
			txtDataSessione.setEditable(false);
			txtOre.setEditable(false);
			txtSpesa.setEditable(false);
			txtNote.setEditable(false);
		}
	}

	private class BtnDettagliLavoroActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// si apre la finestra dettagli lavoro
			WorkDetailWindow detail = new WorkDetailWindow(work.getID(), data);
			detail.setVisible(true);
		}
	}

}
