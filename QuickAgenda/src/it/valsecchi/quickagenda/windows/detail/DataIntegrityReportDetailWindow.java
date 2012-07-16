package it.valsecchi.quickagenda.windows.detail;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.exception.CryptographyException;
import it.valsecchi.quickagenda.data.report.DataIntegrityReportResult;
import it.valsecchi.quickagenda.settings.SettingsManager;
import it.valsecchi.quickagenda.windows.ShowProgressWindow;
import static it.valsecchi.quickagenda.data.Utility.Log;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class DataIntegrityReportDetailWindow extends JFrame {

	private static final long serialVersionUID = 1895882931117616679L;
	private JPanel contentPane;
	private DataIntegrityReportResult report;
	private DataManager data;
	private Timer timer1;
	private JLabel lblNTotaleDi;
	private JTextField txtNTot;
	private JLabel lblNTotaleDi_1;
	private JTextField txtErrori;
	private JLabel lblNTotaleDi_2;
	private JTextField txtClienti;
	private JLabel lblNTotaleDi_3;
	private JTextField txtLavori;
	private JLabel lblNTotaleDi_4;
	private JTextField txtSessioni;

	public DataIntegrityReportDetailWindow(DataIntegrityReportResult _report,
			DataManager _data) {
		addWindowListener(new ThisWindowListener());
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						DataIntegrityReportDetailWindow.class
								.getResource("/ico_small/preferences_system_session.png")));
		setTitle("Dettagli Rapporto Integrit\u00E0 Dati");
		data = _data;
		report = _report;
		initComponent();
		initData();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 635, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		lblNTotaleDi = new JLabel("N. Totale di Elementi:");
		txtNTot = new JTextField();
		txtNTot.setEditable(false);
		txtNTot.setColumns(10);
		lblNTotaleDi_1 = new JLabel("N. Totale di Errori:");
		txtErrori = new JTextField();
		txtErrori.setEditable(false);
		txtErrori.setColumns(10);
		lblNTotaleDi_2 = new JLabel("N. Totale di Clienti:");
		txtClienti = new JTextField();
		txtClienti.setEditable(false);
		txtClienti.setColumns(10);
		lblNTotaleDi_3 = new JLabel("N. Totale di Lavori:");
		txtLavori = new JTextField();
		txtLavori.setEditable(false);
		txtLavori.setColumns(10);
		lblNTotaleDi_4 = new JLabel("N. Totale di Sessioni:");
		txtSessioni = new JTextField();
		txtSessioni.setEditable(false);
		txtSessioni.setColumns(10);
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
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblNTotaleDi)
																		.addGap(18)
																		.addComponent(
																				txtNTot,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(40)
																		.addComponent(
																				lblNTotaleDi_1,
																				GroupLayout.PREFERRED_SIZE,
																				124,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtErrori,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblNTotaleDi_2,
																				GroupLayout.PREFERRED_SIZE,
																				124,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				txtClienti,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblNTotaleDi_3,
																				GroupLayout.PREFERRED_SIZE,
																				124,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				txtLavori,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				lblNTotaleDi_4,
																				GroupLayout.PREFERRED_SIZE,
																				124,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				txtSessioni,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(52, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(21)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNTotaleDi)
														.addComponent(
																txtNTot,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblNTotaleDi_1)
														.addComponent(
																txtErrori,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				lblNTotaleDi_2))
														.addComponent(
																txtClienti,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				lblNTotaleDi_3))
														.addComponent(
																txtLavori,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				lblNTotaleDi_4))
														.addComponent(
																txtSessioni,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(199, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	public void initData() {
		txtNTot.setText(Integer.toString(report.getN_tot_elements()));
		txtErrori.setText(Integer.toString(report.getN_errors()));
		txtClienti.setText(Integer.toString(report.getN_costumers()));
		txtLavori.setText(Integer.toString(report.getN_works()));
		txtSessioni.setText(Integer.toString(report.getN_sessions()));
	}

	private class ThisWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent arg0) {
			final ShowProgressWindow progress = new ShowProgressWindow(
					"Salvataggio dati in corso...",
					"Salvataggio dati",
					new ImageIcon(getClass().getResource("/ico_small/save.png")));
			progress.setVisible(true);
			try {
				// SALVATAGGIO DATI
				Log.info("salvataggio dati e preferenze");
				data.saveData();
				// si salvano le preferenze
				SettingsManager.writeSettings();
				// si chiude la finestra
				progress.setIcon(new ImageIcon(getClass().getResource(
						"/ico_small/check.png")));
				progress.setMessage("Salvataggio completato!");
				// timer
				timer1 = new Timer(1500, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// si ferma il timer
						timer1.stop();
						// si chiude progress.
						progress.dispose();
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
}
