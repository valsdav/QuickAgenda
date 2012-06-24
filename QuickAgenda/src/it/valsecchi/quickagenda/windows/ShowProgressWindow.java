package it.valsecchi.quickagenda.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.JProgressBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ShowProgressWindow extends JFrame {

	private static final long serialVersionUID = 5344633569411391027L;
	private JPanel contentPane;
	private JProgressBar progressBar;
	private JLabel lblMess;
	private JLabel immagine;

	/**
	 * @wbp.parser.constructor
	 */
	public ShowProgressWindow(String mess,String title) {
		initComponent(mess,title);
	}

	public ShowProgressWindow(String mess, String title, Icon ico){
		initComponent(mess,title);
		immagine.setIcon(ico);
	}
	
	private void initComponent(String mess,String title){
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShowProgressWindow.class.getResource("/ico_small/view_refresh.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 186);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		{
			progressBar = new JProgressBar();
			progressBar.setIndeterminate(true);
		}
		this.setTitle(title);
		lblMess = new JLabel(mess);
		lblMess.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		immagine = new JLabel("");
		immagine.setIcon(new ImageIcon(ShowProgressWindow.class.getResource("/ico_small/view_refresh.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(immagine)
							.addGap(42)
							.addComponent(lblMess))
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 606, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(immagine)
						.addComponent(lblMess))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setMessage(String mess){
		lblMess.setText(mess);
	}
	
	public void setIcon(Icon ico){
		immagine.setIcon(ico);
	}
}
