package src.GUI;

import java.awt.EventQueue;




import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import src.JDBC;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;

public class MainMenuUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javax.swing.UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel");
					MainMenuUI frame = new MainMenuUI();
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
	public MainMenuUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuUI.class.getResource("/de/javasoft/plaf/synthetica/bluelight/images/icon.png")));
		setTitle("WriteRocd");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 326, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchWindow();
			}
		});
		btnSearch.setBounds(70, 127, 180, 37);
		contentPane.add(btnSearch);
		
		JButton btnImportData = new JButton("IMPORT DATA");
		btnImportData.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnImportData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	importWindow();
			JOptionPane.showMessageDialog(null, "Dose NOT Implemented yet, Finals are Coming!");

				
			}
		});
		btnImportData.setBounds(70, 168, 180, 37);
		contentPane.add(btnImportData);
		
		JButton btnDatabaseConfig = new JButton("DATABASE CONFIG");
		btnDatabaseConfig.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDatabaseConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confWindow();
			}
		});
		btnDatabaseConfig.setBounds(70, 209, 180, 37);
		contentPane.add(btnDatabaseConfig);
		
		JButton btnAbout = new JButton("ABOUT");
		btnAbout.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutWindow();
			}
		});
		btnAbout.setBounds(70, 249, 180, 37);
		contentPane.add(btnAbout);
		

		setLocationRelativeTo(null);
		try {
			
			
			JLabel lblImage = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(MainMenuUI.class.getResource("/src/img/writerocd-sm.png"))));
			lblImage.setBounds(78, 10, 164, 121);
			contentPane.add(lblImage);
			//contentPane.add(img);
		} catch (Exception e1) {
			System.out.println(e1.getLocalizedMessage());
		}
		
		setResizable(false);
	}
	
	public void aboutWindow()
	{
		new AboutUI().setVisible(true);
		this.dispose();
	}
	
	
	/*public void importWindow()
	{
		JDBC db;
		try {
			db = new JDBC();
			new ImportWindow().setVisible(true);
			db.closeConnection();
			this.dispose();
		} catch (Exception e1) {
			 JOptionPane.showMessageDialog(null, "Error! Could not connect to Database\nPlease check your Connection");
			db = null;
			//dispose();
		}

	} */
	
	
	
	public void confWindow()
	{
		new databaseConfUI().setVisible(true);
		this.dispose();
	}
	
	public void searchWindow()
	{
		JDBC db;
		try {
			db = new JDBC();
			SearchWindowUI sw = new SearchWindowUI();
			sw.setVisible(true);
			db.closeConnection();
			this.dispose();
		} catch (Exception e1) {
			 JOptionPane.showMessageDialog(null, "Error! Could not connect to Database\nPlease check your Connection");
			db = null;
			//dispose();
		}

	}
}
