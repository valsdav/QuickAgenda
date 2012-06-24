package it.valsecchi.quickagenda.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import static it.valsecchi.quickagenda.data.Utility.Log;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Prima finestra aperta dall'aplicazione. Essa permette all'utente di scegliere
 * tra l'apertura di un file dati esistente o la creazione di uno nuovo.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class StartWindow extends JFrame {

	private static final long serialVersionUID = 5855814906636103916L;
	private JPanel contentPane;
	private JLabel image;

	public StartWindow() {
		setTitle("Quick Agenda");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				StartWindow.class.getResource("/ico_128/bookmarks.png")));
		initComponent();
	}

	public void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		image = new JLabel("Benvenuto in Quick Agenda!");
		image.setFont(new Font("Tahoma", Font.BOLD, 15));
		image.setIcon(new ImageIcon(StartWindow.class
				.getResource("/ico_128/agenda.png")));

		JButton btnOpen = new JButton("Apri file dati");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// si apre la finestra
				Log.info("apertura finestra OpenDataForm");
				OpenDataWindow form = new OpenDataWindow();
				form.setVisible(true);
				// si chiude questa
				dispose();
			}
		});
		btnOpen.setIcon(new ImageIcon(StartWindow.class
				.getResource("/ico_128/apri.png")));
		btnOpen.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton btnNuovo = new JButton("Nuovo file dati");
		btnNuovo.setIcon(new ImageIcon(StartWindow.class
				.getResource("/ico_128/new.png")));
		btnNuovo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNuovo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//si apre la finestra
				Log.info("apertura finestra CreateNewDataFileForm");
				CreateNewDataFileWindow form = new CreateNewDataFileWindow();
				form.setVisible(true);
				//si chiude questa
				dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addGap(100)
								.addComponent(image,
										GroupLayout.PREFERRED_SIZE, 376,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(129, Short.MAX_VALUE))
				.addGroup(
						Alignment.TRAILING,
						gl_contentPane
								.createSequentialGroup()
								.addGap(32)
								.addComponent(btnOpen)
								.addPreferredGap(ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(btnNuovo)
								.addGap(27)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addContainerGap(23, Short.MAX_VALUE)
								.addComponent(image)
								.addGap(18)
								.addGroup(
										gl_contentPane
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnOpen)
												.addComponent(btnNuovo))
								.addGap(55)));
		contentPane.setLayout(gl_contentPane);
	}
}
