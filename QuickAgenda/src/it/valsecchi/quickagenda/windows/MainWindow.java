package it.valsecchi.quickagenda.windows;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.component.Session;
import it.valsecchi.quickagenda.data.exception.CryptographyException;
import it.valsecchi.quickagenda.settings.SettingsManager;
import it.valsecchi.quickagenda.windows.addelements.AddSessionWindow;
import static it.valsecchi.quickagenda.data.Utility.Log;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;

import com.toedter.calendar.JCalendar;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -2881568803908491595L;
	private JPanel contentPane;
	private DataManager data;
	private JTable table;
	private JCalendar calendar;
	private JToolBar toolBar;
	private JButton btnClienti;
	private JButton btnLavori;
	private JButton btnNewSession;
	private JButton btnInfo;
	private JButton btnOpzioni;
	private JButton btnSalva;
	private JSeparator separator;
	private JButton btnCerca;
	private JSeparator separator_1;

	public MainWindow(DataManager _data) {
		addWindowListener(new ThisWindowListener());
		setBackground(Color.WHITE);
		setTitle("Quick Agenda");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainWindow.class.getResource("/ico_small/agenda.png")));
		// si memorizza la fonte dati
		data = _data;
		// si inizializzano i componenti
		initComponent();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1279, 605);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Panel.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// si ricavano i dati per la tabella
		AbstractTableModel model = this.getTableModel(GregorianCalendar
				.getInstance());
		table = new JTable(model);
		table.addMouseListener(new TableMouseListener());
		table.setAutoCreateColumnsFromModel(true);
		calendar = new JCalendar();
		calendar.addPropertyChangeListener(new CalendarPropertyChangeListener());
		calendar.getMonthChooser().getComboBox()
				.setFont(new Font("Tahoma", Font.BOLD, 13));

		toolBar = new JToolBar();
		toolBar.setBackground(UIManager.getColor("ToolBar.dockingBackground"));
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);

		btnNewSession = new JButton("Aggiungi Nuova Sessione...");
		btnNewSession.addActionListener(new BtnNewSessionActionListener());
		btnNewSession.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewSession.setIcon(new ImageIcon(MainWindow.class
				.getResource("/ico_small/add1.png")));
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(calendar, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewSession))
					.addGap(28)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(calendar, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewSession)))
					.addContainerGap(64, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(toolBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
					.addContainerGap())
		);
		{
			btnClienti = new JButton("Gestione Clienti");
			btnClienti.setBackground(UIManager
					.getColor("ToolBar.dockingBackground"));
			btnClienti.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnClienti.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnClienti.setHorizontalTextPosition(SwingConstants.CENTER);
			btnClienti.setIcon(new ImageIcon(MainWindow.class
					.getResource("/ico_small/users.png")));
			toolBar.add(btnClienti);
		}
		{
			toolBar.addSeparator();
			btnLavori = new JButton("Gestione Lavori");
			btnLavori.setBackground(UIManager
					.getColor("ToolBar.dockingBackground"));
			btnLavori.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnLavori.setHorizontalTextPosition(SwingConstants.CENTER);
			btnLavori.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnLavori.setIcon(new ImageIcon(MainWindow.class
					.getResource("/ico_small/work2.png")));
			toolBar.add(btnLavori);
		}

		btnOpzioni = new JButton("Opzioni");
		btnOpzioni.setBackground(UIManager
				.getColor("ToolBar.dockingBackground"));
		btnOpzioni.setIcon(new ImageIcon(MainWindow.class
				.getResource("/ico_small/option.png")));
		btnOpzioni.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new BtnSalvaActionListener());
		btnSalva.setBackground(UIManager.getColor("ToolBar.dockingBackground"));
		btnSalva.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalva.setIcon(new ImageIcon(MainWindow.class
				.getResource("/ico_small/save.png")));
		{
			separator_1 = new JSeparator();
			toolBar.add(separator_1);
		}
		{
			btnCerca = new JButton("Cerca...");
			btnCerca.setIcon(new ImageIcon(MainWindow.class
					.getResource("/ico_small/search.png")));
			btnCerca.setFont(new Font("Tahoma", Font.BOLD, 14));
			toolBar.add(btnCerca);
		}
		toolBar.addSeparator();
		toolBar.add(btnSalva);
		toolBar.addSeparator();
		toolBar.add(btnOpzioni);
		toolBar.addSeparator();

		btnInfo = new JButton("Info");
		btnInfo.setBackground(UIManager.getColor("ToolBar.dockingBackground"));
		btnInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInfo.setIcon(new ImageIcon(MainWindow.class
				.getResource("/ico_small/info_box_blue.png")));
		toolBar.add(btnInfo);
		contentPane.setLayout(gl_contentPane);
	}

	private AbstractTableModel getTableModel(Calendar calendar2) {
		// si deve creare un table model con le Session della data passata come
		// parametro
		// si imposta la data nel modo giusto
		calendar2.set(Calendar.HOUR_OF_DAY, 12);
		final Calendar current = calendar2;

		// si crea il data model
		return new AbstractTableModel() {
			private static final long serialVersionUID = -8582114483485505968L;
			// dati
			// si recuperano le session
			List<Session> sessions = data.querySessionsByDate(current);
			// colonne
			String[] columns = { "ID", "ID del Lavoro", "ID del Cliente",
					"N° di ore", "Spesa", "Materiali" };

			@Override
			public int getRowCount() {
				return sessions.size();
			}

			@Override
			public int getColumnCount() {
				return columns.length;
			}

			@Override
			public Object getValueAt(int row, int column) {
				switch (column) {
				case 0:
					return sessions.get(row).getID();
				case 1:
					return sessions.get(row).getWorkID();
				case 2:
					return sessions.get(row).getCostumerID();
				case 3:
					return sessions.get(row).getHours();
				case 4:
					return sessions.get(row).getSpesa();
				case 5:
					StringBuilder build = new StringBuilder();
					for (String s : sessions.get(row).getMateriali()) {
						build.append(s + ", ");
					}
					return build.toString();
				default:
					return null;
				}
			}

			/**
			 * Sono editabili i campi n° di ore, spesa e
			 */
			@Override
			public boolean isCellEditable(int a, int b) {
				return false;
			}
		};
	}

	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			// si ricava la riga selezionata
		}
	}

	private class CalendarPropertyChangeListener implements
			PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent arg0) {
			// si ricava la data selezionata
			Calendar c = calendar.getCalendar();
			c.set(Calendar.HOUR_OF_DAY, 12);
			// si aggiorna la tabella
			table = new JTable(getTableModel(c));
		}
	}

	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			// controllo salvataggio
			int r = JOptionPane.showConfirmDialog(contentPane,
					"Salvare le modifiche prima di uscire?",
					"Salvataggio modifiche...", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE, new ImageIcon(getClass()
							.getResource("/ico_small/save.png")));
			if (r == JOptionPane.YES_OPTION) {
				// finestra progresso
				final ShowProgressWindow progress = new ShowProgressWindow(
						"Salvataggio dati in corso...", "Salvataggio dati",
						new ImageIcon(getClass().getResource(
								"/ico_small/save.png")));
				progress.setVisible(true);
				try {
					// SALVATAGGIO DATI
					data.saveData();
					// si salvano le preferenze
					SettingsManager.writeSettings();
					// si chiude la finestra
					progress.setIcon(new ImageIcon(getClass().getResource(
							"/ico_small/check.png")));
					progress.setMessage("Salvataggio completato!");
					// timer
					Timer timer1 = new Timer(2000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// si chiude progress.
							progress.dispose();
							// si riapre la finestra di partenza
							StartWindow form = new StartWindow();
							form.setVisible(true);
							// Si chiude questa
							dispose();
						}
					});
					timer1.start();
				} catch (CryptographyException e) {
					// in caso si errore non si chiude la finestra
					Log.error("errore di criptografia");
					progress.setIcon(new ImageIcon(getClass().getResource(
							"/ico_small/warning.png")));
					progress.setMessage("Errore di criptografia! Dati non salvati. Riprovare.");
				} catch (IOException e) {
					Log.error("errore IO");
					progress.setIcon(new ImageIcon(getClass().getResource(
							"/ico_small/warning.png")));
					progress.setMessage("Errore file! Impossibile scrivere dati! Riprovare.");
				}
			} else {
				// si salvano solo le preferenze
				SettingsManager.writeSettings();
				// si riapre la finestra di partenza
				StartWindow form = new StartWindow();
				form.setVisible(true);
				// Si chiude questa
				dispose();
			}
		}
	}

	private class BtnSalvaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// si salva
			// finestra progresso
			final ShowProgressWindow progress = new ShowProgressWindow(
					"Salvataggio dati in corso...",
					"Salvataggio dati",
					new ImageIcon(getClass().getResource("/ico_small/save.png")));
			progress.setVisible(true);
			try {
				// SALVATAGGIO DATI
				data.saveData();
				// si salvano le preferenze
				SettingsManager.writeSettings();
				// si chiude la finestra
				progress.setIcon(new ImageIcon(getClass().getResource(
						"/ico_small/check.png")));
				progress.setMessage("Salvataggio completato!");
				// timer
				Timer timer1 = new Timer(1500, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// si chiude progress.
						progress.dispose();
						// si riapre la finestra di partenza
						StartWindow form = new StartWindow();
						form.setVisible(true);
						// Si chiude questa
						dispose();
					}
				});
				timer1.start();
			} catch (CryptographyException e) {
				// in caso si errore non si chiude la finestra
				Log.error("errore di criptografia");
				progress.setIcon(new ImageIcon(getClass().getResource(
						"/ico_small/warning.png")));
				progress.setMessage("Errore di criptografia! Dati non salvati. Riprovare.");
			} catch (IOException e) {
				Log.error("errore IO");
				progress.setIcon(new ImageIcon(getClass().getResource(
						"/ico_small/warning.png")));
				progress.setMessage("Errore file! Impossibile scrivere dati! Riprovare.");
			}
		}
	}
	private class BtnNewSessionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//si apre la finestra di aggiunta
			AddSessionWindow form = new AddSessionWindow(data);
			form.setVisible(true);
		}
	}
}
