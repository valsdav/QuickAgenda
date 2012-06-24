package it.valsecchi.quickagenda.windows.detail;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Costumer;
import it.valsecchi.quickagenda.data.component.Session;
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
import java.text.SimpleDateFormat;

import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class SessionDetailWindow extends JFrame {

	private static final long serialVersionUID = 7746985618232386389L;
	private JPanel contentPane;
	private JLabel immagine1;
	private JLabel lblID;
	private JLabel id;
	private JLabel lblNewLabel_2;
	private JLabel workid;
	private JLabel lblNewLabel_4;
	private JLabel costumerid;
	private JLabel lblData;
	private JLabel data;
	private JLabel lblOre;
	private JLabel hours;
	private JLabel lblSpesa;
	private JLabel spesa;
	private JLabel lblMateriali;
	private JList list;
	private JLabel materiali;
	private JSeparator separator;
	private JButton btnModifica;
	private DataManager manager;
	private String sessionID;
	private JLabel lblDettagliCliente;

	public SessionDetailWindow(String sessionId,DataManager _manager) {
		manager = _manager;
		sessionID = sessionId;
		initComponent();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 733);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		immagine1 = new JLabel("Dettagli Sessione");
		immagine1.setFont(new Font("Tahoma", Font.BOLD, 15));
		immagine1.setIcon(new ImageIcon(SessionDetailWindow.class.getResource("/ico_small/tools.png")));
		lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblID.setForeground(Color.BLUE);
		id = new JLabel("workid");
		lblNewLabel_2 = new JLabel("ID Lavoro:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.BLUE);
		workid = new JLabel("workid");
		lblNewLabel_4 = new JLabel("ID Cliente:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setForeground(Color.BLUE);
		costumerid = new JLabel("costumerid");
		lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblData.setForeground(Color.BLUE);
		data = new JLabel("data");
		lblOre = new JLabel("N\u00B0 Ore:");
		lblOre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOre.setForeground(Color.BLUE);
		hours = new JLabel("ore");
		lblSpesa = new JLabel("Spesa:");
		lblSpesa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpesa.setForeground(Color.BLUE);
		spesa = new JLabel("spesa");
		lblMateriali = new JLabel("Materiali:");
		lblMateriali.setForeground(Color.BLUE);
		lblMateriali.setFont(new Font("Tahoma", Font.BOLD, 14));
		list = new JList();
		
		materiali = new JLabel("");
		
		separator = new JSeparator();
		
		btnModifica = new JButton("Modifica");
		
		lblDettagliCliente = new JLabel("Dettagli Cliente");
		lblDettagliCliente.setIcon(new ImageIcon(SessionDetailWindow.class.getResource("/ico_small/users.png")));
		lblDettagliCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblData)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(data, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblOre, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(hours, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblID)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(id)
											.addGap(67)
											.addComponent(lblNewLabel_2)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(workid)))
									.addGap(43)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblNewLabel_4)
											.addGap(7)
											.addComponent(costumerid))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblSpesa, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(spesa, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblMateriali, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(materiali, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(188))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE))
						.addComponent(immagine1))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(462, Short.MAX_VALUE)
					.addComponent(btnModifica)
					.addGap(172))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDettagliCliente, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(502, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(immagine1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(46))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblID)
								.addComponent(id)
								.addComponent(lblNewLabel_4)
								.addComponent(costumerid)
								.addComponent(workid)
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblData)
								.addComponent(data)
								.addComponent(hours)
								.addComponent(lblSpesa)
								.addComponent(spesa)
								.addComponent(lblOre))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMateriali, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(materiali, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(25)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnModifica)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDettagliCliente, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(369))
		);
		contentPane.setLayout(gl_contentPane);
		id.setText(sessionID);
		//si ricava la sessione
		Session s = null;
		try {
			s = manager.getSession(sessionID);
		} catch (IDNotFoundException e) {
			//non si puo verificare questo errore
			Log.error("id "+ e.getID() +", non trovato");
		}
		workid.setText(s.getWorkID());
		costumerid.setText(s.getCostumerID());
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yy");
		data.setText(f.format(s.getSessionData().getTime()));
		hours.setText(Integer.toString(s.getHours()));
		spesa.setText(Integer.toString(s.getSpesa()));
		StringBuilder b = new StringBuilder();
		for(String t:s.getMateriali()){
			b.append(t+",");
		}
		materiali.setText(b.toString());
		
		//si ricava il costumer
		Costumer c = null;
		try {
			c= manager.getCostumerFromSession(sessionID);
		} catch (IDNotFoundException e) {
			//non si puo verificare questo errore
			Log.error("id "+ e.getID() +", non trovato");
		}
		
		
	}
}
