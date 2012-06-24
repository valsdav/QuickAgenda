package it.valsecchi.quickagenda.windows;

import it.valsecchi.quickagenda.data.DataManager;
import it.valsecchi.quickagenda.settings.SettingsManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.Timer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import static it.valsecchi.quickagenda.data.Utility.Log;
import java.awt.Font;

public class CreateNewDataFileForm extends JFrame {

	private static final long serialVersionUID = 1581007532616177256L;

	private JPanel contentPane;
	private JLabel immagine1;
	private JLabel lblPath;
	private JPasswordField txtPass;
	private JButton btnNext;
	private JFrame currentFrame;
	private JButton btnSfoglia;
	private JLabel immagine2;
	private DataManager newData;
	private Timer time1;

	public CreateNewDataFileForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				CreateNewDataFileForm.class
						.getResource("/ico_small/agenda.png")));
		setTitle("Nuovo file dati");
		initComponent();
	}

	public void initComponent() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 615, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		currentFrame = this;
		btnSfoglia = new JButton("Sfoglia...");
		btnSfoglia.setIcon(new ImageIcon(CreateNewDataFileForm.class
				.getResource("/ico_small/folder.png")));
		btnSfoglia.addActionListener(new SfogliaClickHandler());

		lblPath = new JLabel("Scegli un percorso...");
		lblPath.setFont(new Font("Tahoma", Font.BOLD, 14));

		immagine1 = new JLabel("");
		immagine1.setIcon(new ImageIcon(CreateNewDataFileForm.class
				.getResource("/ico_small/document_new.png")));

		txtPass = new JPasswordField();
		txtPass.setEnabled(false);
		txtPass.addCaretListener(new CaretListener(){
			@Override
			public void caretUpdate(CaretEvent arg0) {
				// si rende disponibile il pulsante next
				if (txtPass.getPassword().length != 0) {
					btnNext.setEnabled(true);
				} else {
					btnNext.setEnabled(false);
				}
			}
		});

		JLabel lblPassword = new JLabel("Password:");

		JLabel lblInserireUnPassoword = new JLabel(
				"Inserire un passoword per il nuovo file dati:");

		btnNext = new JButton("");
		btnNext.addActionListener(new NextClickHandler());
		btnNext.setEnabled(false);
		btnNext.setIcon(new ImageIcon(CreateNewDataFileForm.class
				.getResource("/ico_small/right (1).png")));

		immagine2 = new JLabel("");
		immagine2.setIcon(new ImageIcon(CreateNewDataFileForm.class
				.getResource("/ico_128/edit.png")));
		
		//eventi finestra
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				//si riapre la finestra di inizio
				Log.info("riapertura finestra di avvio");
				StartForm form = new StartForm();
				form.setVisible(true);
				//si chiude questa finestra
				dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(immagine1)
							.addGap(29)
							.addComponent(lblPath))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInserireUnPassoword)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(immagine2)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblPassword)
										.addGap(46)
										.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))))))
					.addPreferredGap(ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSfoglia)
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(lblPath))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSfoglia)
								.addComponent(immagine1))))
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblInserireUnPassoword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword)
								.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addComponent(immagine2))
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private class SfogliaClickHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// si fa aprire un file chooser
			JFileChooser fc = new JFileChooser();
			fc.setFileHidingEnabled(true);
			fc.setAcceptAllFileFilterUsed(true);
			fc.setMultiSelectionEnabled(false);
			int res = fc.showSaveDialog(currentFrame);

			if (res == JFileChooser.APPROVE_OPTION) {
				// si aggiunge il percorso al testo
				String newPath = fc.getSelectedFile().toString()+".qad";
				lblPath.setText(newPath);
				//si abilita il campo password
                txtPass.setEnabled(true);
				//si aggiunge alle preferenze
				SettingsManager.addDataPath(newPath);
			}
		}
	}
	
	private class NextClickHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// se la password c'è
			if (txtPass.getPassword().length != 0) {
				// allora si recupera la password e la path
				String path = (String) lblPath.getText();
				//nuovo DataManager
				newData = DataManager.createVoidDataManager(path,txtPass.getPassword());
				//si imposta l'immagine
				immagine2.setIcon(new ImageIcon(CreateNewDataFileForm.class
				.getResource("/ico_128/yes.png")));
				time1 = new Timer(1000,new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						time1.stop();
						//si apre la finestra principale
						MainWindow form = new MainWindow(newData);
						form.setVisible(true);
						//si chiude questa
						dispose();
					}
				});
				time1.start();
			}
	}
}}
