package it.valsecchi.quickagenda.windows;

import it.valsecchi.quickagenda.data.DataManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OptionsWindow extends JFrame {
	private static final long serialVersionUID = -3600033074228709942L;
	private JPanel contentPane;
	private JButton btnSalvaFileBackup;
	private DataManager data;
	private Timer timer1;
	private ShowProgressWindow progress;

	public OptionsWindow(DataManager _data) {
		data = _data;
		setTitle("Opzioni");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				OptionsWindow.class.getResource("/ico_small/option.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 582, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		btnSalvaFileBackup = new JButton("Salva File di Backup Dati");
		btnSalvaFileBackup
				.addActionListener(new BtnSalvaFileDiActionListener());
		btnSalvaFileBackup.setIcon(new ImageIcon(OptionsWindow.class
				.getResource("/ico_small/filesaveas.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(btnSalvaFileBackup)
						.addContainerGap(303, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addGap(24)
						.addComponent(btnSalvaFileBackup,
								GroupLayout.PREFERRED_SIZE, 83,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(196, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	private class BtnSalvaFileDiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg) {
			// Si salva il file di backup
			progress = new ShowProgressWindow(
					"Salvataggio file backup dati...",
					"Salvataggio file backup dati", new ImageIcon(
							OptionsWindow.class
									.getResource("/ico_small/filesaveas.png")));
			progress.setVisible(true);
			try {
				data.saveDataBackup();
				progress.setMessage("Salvataggio completato!");
			} catch (FileNotFoundException e) {
				progress.setMessage("Errore file!");
				progress.setIcon(new ImageIcon(OptionsWindow.class
						.getResource("/ico_small/warning.png")));
			} catch (IOException e) {
				progress.setMessage("Errore file!");
				progress.setIcon(new ImageIcon(OptionsWindow.class
						.getResource("/ico_small/warning.png")));
			}
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
		}
	}
}
