package src.GUI;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUI frame = new AboutUI();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AboutUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuUI.class.getResource("/de/javasoft/plaf/synthetica/bluelight/images/icon.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindow();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 454, 328);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		
		try {
			JLabel lblNewLabel = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(MainMenuUI.class.getResource("/src/img/aboutApp.png"))));
			lblNewLabel.setBounds(10, 11, 428, 277);
			getContentPane().add(lblNewLabel);
		} catch (Exception e1) {
			System.out.println(e1.getLocalizedMessage());
		}
	}
	public void closeWindow()
	{
			this.dispose();
			new MainMenuUI().setVisible(true);
	}
}
