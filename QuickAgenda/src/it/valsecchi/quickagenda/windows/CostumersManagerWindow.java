package it.valsecchi.quickagenda.windows;

import it.valsecchi.quickagenda.data.DataManager;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class CostumersManagerWindow extends JFrame {

	private static final long serialVersionUID = 5532739737947952966L;
	private JPanel contentPane;
	private DataManager data;

	public CostumersManagerWindow(DataManager d) {
		data = d;
		setTitle("Gestione Clienti");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CostumersManagerWindow.class.getResource("/ico_small/agenda.png")));
		initComponent();
	}

	public void initComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 649, 737);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
