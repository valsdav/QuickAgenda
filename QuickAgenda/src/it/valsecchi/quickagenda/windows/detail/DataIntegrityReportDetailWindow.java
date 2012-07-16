package it.valsecchi.quickagenda.windows.detail;

import it.valsecchi.quickagenda.data.report.DataIntegrityReportResult;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class DataIntegrityReportDetailWindow extends JFrame {

	private static final long serialVersionUID = 1895882931117616679L;
	private JPanel contentPane;
	private DataIntegrityReportResult report;
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

	public DataIntegrityReportDetailWindow(DataIntegrityReportResult _report) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DataIntegrityReportDetailWindow.class.getResource("/ico_small/preferences_system_session.png")));
		setTitle("Dettagli Rapporto Integrit\u00E0 Dati");
		report = _report;
		initComponent();
		initData();
	}
	
	private void initComponent(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNTotaleDi)
							.addGap(18)
							.addComponent(txtNTot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(lblNTotaleDi_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtErrori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNTotaleDi_2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtClienti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNTotaleDi_3, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtLavori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNTotaleDi_4, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtSessioni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNTotaleDi)
						.addComponent(txtNTot, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNTotaleDi_1)
						.addComponent(txtErrori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNTotaleDi_2))
						.addComponent(txtClienti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNTotaleDi_3))
						.addComponent(txtLavori, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNTotaleDi_4))
						.addComponent(txtSessioni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(199, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void initData(){
		txtNTot.setText(Integer.toString(report.getN_tot_elements()));
		txtErrori.setText(Integer.toString(report.getN_errors()));
		txtClienti.setText(Integer.toString(report.getN_costumers()));
		txtLavori.setText(Integer.toString(report.getN_works()));
		txtSessioni.setText(Integer.toString(report.getN_sessions()));
	}
}
