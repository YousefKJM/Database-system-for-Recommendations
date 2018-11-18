package src.GUI;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JRadioButton;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;

public class databaseConfUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField urlField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private Scanner in;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnOracle;
	private JRadioButton rdbtnMysql;
	private JLabel lblDatabaseUrl;
	private JTextField portField;
	private JTextField sid_dbname;
	private JLabel lblSid;
	private JLabel lblPortNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					databaseConfUI frame = new databaseConfUI();
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
	public databaseConfUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuUI.class.getResource("/de/javasoft/plaf/synthetica/bluelight/images/icon.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindow();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 410, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDatabaseUrl = new JLabel("Database URL:");
		lblDatabaseUrl.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatabaseUrl.setBounds(21, 109, 129, 28);
		contentPane.add(lblDatabaseUrl);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(21, 230, 129, 22);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(21, 267, 129, 28);
		contentPane.add(lblPassword);
		
		urlField = new JTextField();
		urlField.setFont(new Font("Tahoma", Font.BOLD, 11));
		urlField.setBounds(160, 109, 200, 28);
		contentPane.add(urlField);
		urlField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.BOLD, 11));
		usernameField.setColumns(10);
		usernameField.setBounds(160, 226, 200, 28);
		contentPane.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 11));
		passwordField.setBounds(160, 263, 200, 28);
		contentPane.add(passwordField);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveConfig();
			}
		});
		btnSave.setBounds(104, 319, 180, 37);
		contentPane.add(btnSave);
		
		JLabel lblYouCanChange = new JLabel("The credentials are changeable of the DB connection");
		lblYouCanChange.setForeground(new Color(0, 128, 0));
		lblYouCanChange.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYouCanChange.setBounds(30, 32, 331, 22);
		contentPane.add(lblYouCanChange);
		
		rdbtnOracle = new JRadioButton("Oracle");
		rdbtnOracle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnOracle.isSelected())
				{
					//lblDatabaseUrl.setText("Database URL:");
					lblSid.setText("SID :");
				}
			}
		});
		rdbtnOracle.setSelected(true);
		rdbtnOracle.setFont(new Font("Tahoma", Font.BOLD, 11));
		//radioButton.setEnabled(false);
		rdbtnOracle.setBounds(160, 79, 97, 23);
		contentPane.add(rdbtnOracle);
		
		rdbtnMysql = new JRadioButton("MySQL");
		rdbtnMysql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMysql.isSelected())
				{
					//lblDatabaseUrl.setText("URL & Database name");
					lblSid.setText("Database Name: ");
				}
			}
		});
		rdbtnMysql.setFont(new Font("Tahoma", Font.BOLD, 11));
		//radioButton_1.setEnabled(false);
		rdbtnMysql.setBounds(260, 79, 86, 23);
		contentPane.add(rdbtnMysql);
		buttonGroup.add(rdbtnMysql);
		buttonGroup.add(rdbtnOracle);
		
		JLabel lblChooseADbms = new JLabel("Choose a DBMS:");
		lblChooseADbms.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChooseADbms.setBounds(21, 76, 129, 28);
		contentPane.add(lblChooseADbms);
		
		portField = new JTextField();
		portField.setFont(new Font("Tahoma", Font.BOLD, 11));
		portField.setColumns(10);
		portField.setBounds(160, 148, 200, 28);
		contentPane.add(portField);
		
		lblPortNumber = new JLabel("Port Number :");
		lblPortNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPortNumber.setBounds(21, 148, 129, 28);
		contentPane.add(lblPortNumber);
		
		sid_dbname = new JTextField();
		sid_dbname.setFont(new Font("Tahoma", Font.BOLD, 11));
		sid_dbname.setColumns(10);
		sid_dbname.setBounds(160, 187, 200, 28);
		contentPane.add(sid_dbname);
		
		lblSid = new JLabel("SID :");
		lblSid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSid.setBounds(21, 187, 129, 28);
		contentPane.add(lblSid);
		readConfig();
		setLocationRelativeTo(null);
		//setResizable(false);
	}
	
	public void closeWindow()
	{
			this.dispose();
			new MainMenuUI().setVisible(true);
	}
	@SuppressWarnings("deprecation")
	public void saveConfig()
	{
		try {
			PrintWriter out = new PrintWriter(new FileOutputStream("database.conf"));
			if (rdbtnOracle.isSelected())
			{
				out.println("Oracle");
			}
			else
			{
				out.println("MySQL");
			}
			String encryptedPass ="";
			try
			{
				
				PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
				encryptor.setPoolSize(4);          // This would be a good value for a 4-core system
				encryptor.setPassword("SalehAhmed");
				
				encryptedPass = encryptor.encrypt(passwordField.getText());
				
			}
			catch (Exception e)
			{
				System.out.println(e.getLocalizedMessage());
			}
			out.println(urlField.getText());
			out.println(portField.getText());
			out.println(sid_dbname.getText());
			out.println(usernameField.getText());
			out.println(encryptedPass);
			out.close();
			JOptionPane.showMessageDialog(null, "Configuration Saved Successfully!");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}
	
	
	public void readConfig()
	{
		try {
			in = new Scanner(new FileInputStream("database.conf"));
			if (in.hasNextLine())
			{
			String tmp = in.nextLine();
			if (tmp.equalsIgnoreCase("Oracle"))
			{
				rdbtnOracle.setSelected(true);
				lblSid.setText("SID :");
			}
			else
			{
				rdbtnMysql.setSelected(true);
				//lblDatabaseUrl.setText("URL & Database name");
				lblSid.setText("Database Name: ");
			}
			
			if (in.hasNextLine())
				urlField.setText(in.nextLine());
			if (in.hasNextLine())
				portField.setText(in.nextLine());
			if (in.hasNextLine())
				sid_dbname.setText(in.nextLine());
			if (in.hasNextLine())
				usernameField.setText(in.nextLine());
			if (in.hasNextLine())
				passwordField.setText("*************");
			
			in.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
