package it.valsecchi.quickagenda.windows;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

/**
 * Finestra che mostra la versione corrente dell'applicazione ed informazioni
 * sull'autore.
 * 
 * @author Davide Valsecchi
 * @version 1.0
 * 
 */
public class InfoWindow extends JFrame {

	private static final long serialVersionUID = 5668698471978405208L;
	private JPanel contentPane;
	private JLabel lblImmagine;
	private JTextArea txtQuickAgendaVersione;

	public InfoWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				InfoWindow.class.getResource("/ico_small/agenda.png")));
		setTitle("Quick Agenda Informazioni");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 369, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		{
			lblImmagine = new JLabel("");
			lblImmagine.setIcon(new ImageIcon(InfoWindow.class
					.getResource("/ico_small/info_box_blue.png")));
			contentPane.add(lblImmagine, BorderLayout.WEST);
		}
		{
			txtQuickAgendaVersione = new JTextArea();
			txtQuickAgendaVersione.setEditable(false);
			txtQuickAgendaVersione
					.setText("Quick Agenda\r\n\r\nVersione:  1.0.8_beta\r\nAutore:  Davide Valsecchi\r\n\r\n");
			contentPane.add(txtQuickAgendaVersione, BorderLayout.CENTER);
		}
	}

}
