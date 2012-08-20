package it.valsecchi.quickagenda.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static it.valsecchi.quickagenda.data.Utility.Log;
import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.data.exception.CryptographyException;
import it.valsecchi.quickagenda.data.exception.FileDataVersionNotValid;
import it.valsecchi.quickagenda.data.exception.InvalidPasswordException;
import it.valsecchi.quickagenda.settings.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import org.jdom2.JDOMException;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JProgressBar;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * Finestra che permette la scelta di un file dati e l'inserimento della
 * password per il caricamento dei dati. Una volta che i dati sono stati
 * caricati si apre la finestra principale dell'applicazione.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class OpenDataWindow extends JFrame {

	private static final long serialVersionUID = -8839774040611149890L;

	private JPanel contentPane;
	private JComboBox cbPath;
	private JPasswordField txtPass;
	private JButton btnSfoglia;
	private JLabel lblPass;
	private JFrame currentFrame;
	private JButton btnNext;
	private JLabel confirmImage;
	private JLabel lblLog;
	private JProgressBar progressBar;
	private Timer timer1;
	private Timer timer2;
	private String path;
	private DataManager data;

	public OpenDataWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				OpenDataWindow.class.getResource("/ico_small/agenda.png")));
		setTitle("Carica dati...");
		initComponent();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 622, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// si memorizza il frame corrente
		currentFrame = this;
		// si ricava la lista di path
		String[] paths = SettingsManager.getDataPaths();
		cbPath = new JComboBox(paths);
		cbPath.addItemListener(new CbPathItemListener());

		JLabel lblimmagine = new JLabel("");
		lblimmagine.setIcon(new ImageIcon(OpenDataWindow.class
				.getResource("/ico_small/folder.png")));

		btnSfoglia = new JButton("Sfoglia...");
		btnSfoglia.addActionListener(new SfogliaClickHandler());
		btnSfoglia.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtPass = new JPasswordField();
		txtPass.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				// si rende disponibile il pulsante next
				if (txtPass.getPassword().length != 0) {
					btnNext.setEnabled(true);
				} else {
					btnNext.setEnabled(false);
				}
			}
		});

		lblPass = new JLabel("Password:");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnNext = new JButton("");
		btnNext.setEnabled(false);
		btnNext.setIcon(new ImageIcon(OpenDataWindow.class
				.getResource("/ico_small/next.png")));
		btnNext.addActionListener(new NextClickHandler());

		confirmImage = new JLabel("");
		confirmImage.setIcon(new ImageIcon(OpenDataWindow.class
				.getResource("/ico_128/lock_pass.png")));

		lblLog = new JLabel(
				"Scegli un file, inserisci la password e premi la freccia");
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblLog.setFont(new Font("SansSerif", Font.BOLD, 14));

		// eventi windows
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// si riapre il form di inizio
				Log.info("riapertura finestra di avvio");
				StartWindow form = new StartWindow();
				form.setVisible(true);
				// si chiude questa finestra
				dispose();
			}
		});

		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addGap(10)
																										.addComponent(
																												lblimmagine))
																						.addComponent(
																								lblPass,
																								GroupLayout.PREFERRED_SIZE,
																								79,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								txtPass)
																						.addComponent(
																								cbPath,
																								0,
																								336,
																								Short.MAX_VALUE)
																						.addComponent(
																								lblLog,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								progressBar,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED))
														.addGroup(
																Alignment.TRAILING,
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				confirmImage)
																		.addGap(135)))
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addComponent(
																btnNext,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																btnSfoglia,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(114)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addContainerGap()
																										.addComponent(
																												lblimmagine))
																						.addGroup(
																								gl_contentPane
																										.createSequentialGroup()
																										.addGap(22)
																										.addGroup(
																												gl_contentPane
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																cbPath,
																																GroupLayout.PREFERRED_SIZE,
																																GroupLayout.DEFAULT_SIZE,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																btnSfoglia))))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPane
																										.createParallelGroup(
																												Alignment.BASELINE)
																										.addComponent(
																												lblPass)
																										.addComponent(
																												txtPass,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								btnNext))
																		.addGap(125))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap(
																				137,
																				Short.MAX_VALUE)
																		.addComponent(
																				confirmImage)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(lblLog)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(progressBar,
												GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addGap(11)));
		contentPane.setLayout(gl_contentPane);
		getRootPane().setDefaultButton(btnNext);
	}

	private class NextClickHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// se la password c'è
			if (txtPass.getPassword().length != 0) {
				// allora si recupera la password e la path
				path = (String) cbPath.getSelectedItem();
				// nuovo DataManager
				// si imposta l'immagine di inizio
				confirmImage.setIcon(new ImageIcon(OpenDataWindow.class
						.getResource("/ico_128/refresh.png")));
				lblLog.setText("Caricamento in corso...");
				timer1 = new Timer(300, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						timer1.stop();
						// si eseguono le operazionio
						try {
							// si crea e si legge il DataManager
							data = DataManager.loadDataManager(path,
									txtPass.getPassword());
							confirmImage.setIcon(new ImageIcon(
									OpenDataWindow.class
											.getResource("/ico_128/yes.png")));
							lblLog.setText("Caricamento completato con successo!");

							// si apre la finestra principale
							timer2 = new Timer(500, new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									timer2.stop();
									MainWindow form = new MainWindow(data);
									form.setVisible(true);
									// si chiude la finestra corrente
									dispose();
								}
							});
							timer2.start();

						} catch (IOException e1) {
							confirmImage.setIcon(new ImageIcon(
									OpenDataWindow.class
											.getResource("/ico_128/warning.png")));
							lblLog.setText("Errore file!");
							txtPass.setText("");
						} catch (CryptographyException e2) {
							confirmImage.setIcon(new ImageIcon(
									OpenDataWindow.class
											.getResource("/ico_128/attention.png")));
							lblLog.setText("Errore criptografico!");
							txtPass.setText("");
						} catch (ParseException | JDOMException e3) {
							confirmImage.setIcon(new ImageIcon(
									OpenDataWindow.class
											.getResource("/ico_128/attention.png")));
							lblLog.setText("Errore lettura dati!");
							txtPass.setText("");
						} catch (InvalidPasswordException e4) {
							confirmImage.setIcon(new ImageIcon(
									OpenDataWindow.class
											.getResource("/ico_128/divieto.png")));
							lblLog.setText("Password errata!");
							txtPass.setText("");
						} catch (FileDataVersionNotValid e3) {
							confirmImage.setIcon(new ImageIcon(
									OpenDataWindow.class
											.getResource("/ico_128/divieto.png")));
							lblLog.setText("Errore! File dati non compatibile con la " +
									"versione corrente del programma! Aggiornare il programma" +
									" per leggere il file dati!");
							txtPass.setText("");
						}
					}
				});
				timer1.start();
			}
		}
	}

	private class SfogliaClickHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// si fa aprire un file chooser
			JFileChooser fc = new JFileChooser();
			fc.setFileHidingEnabled(true);
			fc.setFileFilter(new FileFilter() {

				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					}
					String name = f.getName();
					String ext = name.split("\\.")[1];
					return ext.equals("qad");
				}

				public String getDescription() {
					return ("File QuickAgenda Data (.qad)");
				}
			});
			fc.setAcceptAllFileFilterUsed(false);
			fc.setMultiSelectionEnabled(false);
			int res = fc.showOpenDialog(currentFrame);

			if (res == JFileChooser.APPROVE_OPTION) {
				// si aggiunge il percorso al combo box
				String newPath = fc.getSelectedFile().toString();
				cbPath.addItem(newPath);
				cbPath.setSelectedItem(newPath);
				// si aggiunge alle preferenze
				SettingsManager.addDataPath(newPath);
			}
		}
	}

	private class CbPathItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			// si imposta l'icona giusta
			confirmImage.setIcon(new ImageIcon(OpenDataWindow.class
					.getResource("/ico_128/lock_pass.png")));
			lblLog.setText("Scegli un file, inserisci la password e premi la freccia");
		}
	}
}
