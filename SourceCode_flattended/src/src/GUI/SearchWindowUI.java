package src.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;

import src.JDBC;
import src.PrintReportCSV;
import src.PrintReportPDF;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchWindowUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCheckBox chckbxTermS;
	private JComboBox<String> TermComboBox;
	private JComboBox<String> gradeComboBox;
	private JLabel lblSearchingCriteria;
	private JCheckBox chckbxCourse;
	private JTextField courseField;
	private JCheckBox chckbxSNum;
	private JTextField snField;
	private JCheckBox chckbxLtGrade;
	private JButton btnExport;
	private JRadioButton rdbtnPdf;
	private JRadioButton rdbtnCsv;
	private JDBC db;
	private String columns[];
	private String columnsJoin[];
	private String data[][];
	private JScrollPane scrollPane;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JFileChooser fc = new JFileChooser();
	private JPanel panel;
	private JPanel panel_1;
	
	
	
	// ArrayList for columns
	private ArrayList<String> col = new ArrayList<String>();
	private String[] defaultColumns = new String[]{"SN","FNAME","LNAME","MCODE","CN","TERM","GRADE","CNAME", "MNAME"};
	private ArrayList<String> colAlians = new ArrayList<String>();



	
	
	
	// CheckBoxes for Selecting Columns
	private JCheckBox chckbxAllC;
	private JCheckBox chckbxTerm;
	private JCheckBox chckbxSN;
	private JCheckBox chckbxCNC;
	private JCheckBox chckbxCName;
	private JCheckBox chckbxStudentFN;
	private JCheckBox chckbxStudentLN;
	private JCheckBox chckbxMCode;
	private JCheckBox chckbxMname;
	private JCheckBox chckbxGrade;
	private JLabel lblChooseColumns;
	//private PrintReport pr = new PrintReport();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javax.swing.UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel");
					SearchWindowUI frame = new SearchWindowUI();
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
	public SearchWindowUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
					closeWindow();
			}
		});
	//	setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuUI.class.getResource("/de/javasoft/plaf/synthetica/bluelight/images/icon.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 929, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setForeground(Color.MAGENTA);
		panel.setBounds(36, 7, 556, 104);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*JLabel lblTerm = new JLabel("Term: ");
		lblTerm.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTerm.setBounds(10, 29, 52, 30);
		panel.add(lblTerm);*/
		
		

		
		
		
		lblSearchingCriteria = new JLabel("Select a searching criteria");
		lblSearchingCriteria.setForeground(SystemColor.windowBorder);
		lblSearchingCriteria.setBounds(131, 0, 307, 35);
		panel.add(lblSearchingCriteria);
		lblSearchingCriteria.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchingCriteria.setFont(new Font("Yu Mincho Demibold", Font.PLAIN, 24));
		
		chckbxTermS = new JCheckBox("Term");
		chckbxTermS.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxTermS.setBounds(10, 33, 90, 23);
		panel.add(chckbxTermS);
		
		chckbxCourse = new JCheckBox("Course");
		chckbxCourse.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxCourse.setBounds(10, 66, 90, 23);
		panel.add(chckbxCourse);
		
		courseField = new JTextField();
		courseField.setBounds(90, 67, 97, 20);
		panel.add(courseField);
		courseField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					processQuery();
			}
		});
		courseField.setColumns(10);
		courseField.setEditable(false);
		
		snField = new JTextField();
		snField.setBounds(315, 66, 97, 20);
		panel.add(snField);
		snField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					processQuery();
			}
		});
		snField.setColumns(10);
		snField.setEditable(false);
		
		
		chckbxSNum = new JCheckBox("Student ID");
		chckbxSNum.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxSNum.setBounds(208, 66, 101, 23);
		panel.add(chckbxSNum);
		
		chckbxLtGrade = new JCheckBox("Grade");
		chckbxLtGrade.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxLtGrade.setBounds(208, 36, 101, 23);
		panel.add(chckbxLtGrade);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(422, 66, 89, 23);
		panel.add(btnSearch);
		
		TermComboBox = new JComboBox<String>();
		TermComboBox.setBounds(90, 34, 97, 20);
		panel.add(TermComboBox);
		
		TermComboBox.setEnabled(false);
		
		
		gradeComboBox = new JComboBox<String>();
		gradeComboBox.setBounds(315, 34, 196, 20);
		panel.add(gradeComboBox);
		
		gradeComboBox.setEnabled(false);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processQuery();
			}
		});
		
		
		chckbxLtGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxLtGrade.isSelected())
				{
					gradeComboBox.setEnabled(true);
				}
				else 
				{
					gradeComboBox.setEnabled(false);
				}
			}
		});
		chckbxSNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxSNum.isSelected())
				{
					snField.setEditable(true);
					snField.setText("");
				}
				else 
				{
					snField.setEditable(false);
					snField.setText("");
				}
			}
		});
		chckbxCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxCourse.isSelected())
				{
					courseField.setEditable(true);
					courseField.setText("");
				}
				else 
				{
					courseField.setEditable(false);
					courseField.setText("");
				}
			}
		});
		chckbxTermS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxTermS.isSelected())
				{
					TermComboBox.setEnabled(true);
				}
				else
				{
					TermComboBox.setEnabled(false);
				}
			}
		});
		
		columns = new String[]{"SN","FNAME","LNAME","MCODE","CN","TERM","GRADE","CNAME", "MCODE"};
		columnsJoin = new String[]{"STUDENT.SN","STUDENT.FNAME","STUDENT.LNAME","STUDENT.MCODE","ENROLL.CN","ENROLL.TERM","ENROLL.GRADE","COURSE.CNAME", "MAJOR.MCODE"};

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 194, 893, 328);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			   // System.out.println("Right Click: "+SwingUtilities.isRightMouseButton(e));
				if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
			        int row = table.rowAtPoint(e.getPoint());
			        int column = table.columnAtPoint(e.getPoint());
			        // do some action if appropriate column
			        String name = table.getColumnName(column);
			        String value = (String) table.getModel().getValueAt(row, column);
			        if (name.equalsIgnoreCase("ENROLL") || name.equalsIgnoreCase("Grade"))
			        {
			        	//System.out.println("Cool!");
			        	System.out.println("INS: "+value);
			        	gradeComboBox.setEnabled(true);
			        	chckbxLtGrade.setSelected(true);
			        	gradeComboBox.setSelectedItem(value);
			        }
			        else if (name.equalsIgnoreCase("Term"))
			        {
			        	//System.out.println("Cool!");
			        	
			        	System.out.println("Term: "+value);
			        	TermComboBox.setEnabled(true);
			        	chckbxTermS.setSelected(true);
			        	TermComboBox.setSelectedItem(value);
			        }
			        else if (name.equalsIgnoreCase("Course") || name.equalsIgnoreCase("CN"))
			        {
			        	//System.out.println("Cool!");
			        	System.out.println("Course: "+value);
			        	chckbxCourse.setSelected(true);
			        	courseField.setText(value);
			        	courseField.setEditable(true);
			        }
			        else if (name.equalsIgnoreCase("STUDENT") || name.equalsIgnoreCase("SN"))
			        {
			        	//System.out.println("Cool!");
			        
			        	System.out.println("SN: "+value);
			        	chckbxSNum.setSelected(true);
			        	snField.setText(value);
			        	snField.setEditable(true);
			        }
			      }
				else if (SwingUtilities.isRightMouseButton(e))
				{
			        int row = table.rowAtPoint(e.getPoint());
			        int column = table.columnAtPoint(e.getPoint());
			        // do some action if appropriate column
			        String name = table.getColumnName(column);
			        String value = (String) table.getModel().getValueAt(row, column);
			        if (name.equalsIgnoreCase("ENROLL") || name.equalsIgnoreCase("Grade"))
			        {
			        	// Here should be a form displaying information about the instructor such as Name, Preference courses, and rank
			        	showFrame(value);
			      //  	System.out.println("Cool!");
			        }
				}
			}
		});
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);
		//new Object[][] {{"1234", "4321", "ICS102", "01", "Musab", "", "", "", "", "", ""}, {"5678", "8888", "ICS201", "01", "Saleh", "", "", "", "", "", ""}}
		table.setModel(new DefaultTableModel(new Object[][] {},columns));
		
		
		panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("ToolTip.background"));
		panel_1.setBounds(602, 7, 276, 104);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnExport = new JButton("Export");
		btnExport.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExport.setBounds(163, 69, 89, 23);
		panel_1.add(btnExport);
		btnExport.setEnabled(false);
				
		rdbtnCsv = new JRadioButton("CSV");
		rdbtnCsv.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnCsv.setBounds(90, 69, 67, 23);
		panel_1.add(rdbtnCsv);
		buttonGroup.add(rdbtnCsv);
		//buttonGroup.add(rdbtnCsv);
		rdbtnCsv.setEnabled(false);
		rdbtnPdf = new JRadioButton("PDF");
		rdbtnPdf.setBounds(10, 69, 73, 23);
		panel_1.add(rdbtnPdf);
		rdbtnPdf.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnPdf.setSelected(true);
		buttonGroup.add(rdbtnPdf);
		//buttonGroup.add(rdbtnPdf);		
		rdbtnPdf.setEnabled(false);
		JLabel lblChooseAnExtention = new JLabel("Choose an extension");
		lblChooseAnExtention.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAnExtention.setForeground(SystemColor.windowBorder);
		lblChooseAnExtention.setFont(new Font("Yu Mincho Demibold", Font.PLAIN, 24));
		lblChooseAnExtention.setBounds(-24, 10, 307, 35);
		panel_1.add(lblChooseAnExtention);
		
		JPanel ColumnPanel = new JPanel();
		ColumnPanel.setBackground(new Color(230, 230, 250));
		ColumnPanel.setBounds(36, 122, 842, 61);
		contentPane.add(ColumnPanel);
		ColumnPanel.setLayout(null);
		
		chckbxAllC = new JCheckBox("ALL");
		
		chckbxAllC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllColumnListener();
			}
		});
		chckbxAllC.setBounds(285, 5, 90, 23);
		ColumnPanel.add(chckbxAllC);
		chckbxAllC.setSelected(true);
		//
		chckbxTerm = new JCheckBox("Term");
		chckbxTerm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfAll();
			}
		});
		chckbxTerm.setBounds(6, 31, 59, 23);
		ColumnPanel.add(chckbxTerm);
		
		chckbxSN = new JCheckBox("Student ID");
		chckbxSN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfAll();
			}
		});
		chckbxSN.setBounds(67, 31, 120, 23);
		ColumnPanel.add(chckbxSN);
		
		chckbxCNC = new JCheckBox("Course No.");
		chckbxCNC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfAll();
			}
		});
		chckbxCNC.setBounds(158, 31, 90, 23);
		ColumnPanel.add(chckbxCNC);
		
		chckbxCName = new JCheckBox("Course Name");
		chckbxCName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfAll();
			}
		});
		chckbxCName.setBounds(246, 31, 120, 23);
		ColumnPanel.add(chckbxCName);
		
		chckbxStudentFN = new JCheckBox("Student First Name");
		chckbxStudentFN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfAll();
			}
		});
		chckbxStudentFN.setBounds(345, 31, 140, 23);
		ColumnPanel.add(chckbxStudentFN);
		
		chckbxStudentLN = new JCheckBox("Student Last Name");
		chckbxStudentLN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfAll();
			}
		});
		chckbxStudentLN.setBounds(472, 31, 140, 23);
		ColumnPanel.add(chckbxStudentLN);
		
		chckbxMCode = new JCheckBox("Major");
		chckbxMCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfAll();
			}
		});
		chckbxMCode.setBounds(598, 31, 69, 23);
		ColumnPanel.add(chckbxMCode);
		
		chckbxMname = new JCheckBox("Major Name");
		chckbxMname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfAll();
			}
		});
		chckbxMname.setBounds(659, 31, 90, 23);
		ColumnPanel.add(chckbxMname);
		
		chckbxGrade = new JCheckBox("Grade");
		chckbxGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfAll();
			}
		});
		chckbxGrade.setBounds(751, 31, 90, 23);
		ColumnPanel.add(chckbxGrade);
		
		
		lblChooseColumns = new JLabel("Choose Columns");
		lblChooseColumns.setFont(new Font("Yu Mincho Light", Font.PLAIN, 24));
		lblChooseColumns.setBounds(6, 1, 277, 23);
		ColumnPanel.add(lblChooseColumns);
		
		
		
		
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportData();
			}
		});
		
		
		try {
			db = new JDBC();
		} catch (Exception e1) {
			//e1.printStackTrace();
			 JOptionPane.showMessageDialog(null, "Error! Could not establish connection");
			db = null;
			//dispose();
		}
		AllColumnListener();
		readTermsAndGrade();
		setLocationRelativeTo(null);
		//setResizable(false);
	}
	
	protected void showFrame(String value) {
		//new Prefernce(value, this, db).setVisible(true);
	}

	protected void AllColumnListener() {
		if (chckbxAllC.isSelected())
		{
			chckbxTerm.setSelected(true);
			chckbxSN.setSelected(true);
			chckbxCNC.setSelected(true);
			chckbxCName.setSelected(true);
			chckbxStudentFN.setSelected(true);
			chckbxStudentLN.setSelected(true);
			chckbxMCode.setSelected(true);
			chckbxMname.setSelected(true);
			chckbxGrade.setSelected(true);
		}
		else
		{
			chckbxTerm.setSelected(false);
			chckbxSN.setSelected(false);
			chckbxCNC.setSelected(false);
			chckbxCName.setSelected(false);
			chckbxStudentFN.setSelected(false);
			chckbxStudentLN.setSelected(false);
			chckbxMCode.setSelected(false);
			chckbxMname.setSelected(false);
			chckbxGrade.setSelected(false);
			
		}
	}

	protected boolean checkIfAll() {

		if (chckbxTerm.isSelected() && chckbxSN.isSelected() && chckbxCNC.isSelected() && chckbxCName.isSelected() && chckbxStudentFN.isSelected() && chckbxStudentLN.isSelected() && chckbxMCode.isSelected() && chckbxMname.isSelected() && chckbxGrade.isSelected())
		{
			/*
			chckbxTerm.setEnabled(false);
			chckbxCrn.setEnabled(false);
			chckbxCourseC.setEnabled(false);
			chckbxSectionC.setEnabled(false);
			chckbxInstructorC.setEnabled(false);
			chckbxMax.setEnabled(false);
			chckbxDays.setEnabled(false);
			chckbxStartTime.setEnabled(false);
			chckbxEndtime.setEnabled(false);
			chckbxBld.setEnabled(false);
			chckbxRoom.setEnabled(false);
			*/
			
			chckbxAllC.setSelected(true);
			return true;
		}
		chckbxAllC.setSelected(false);
		return false;
	}

	public void closeWindow()
	{
		try {
			
			if (db != null)
				db.closeConnection();
			this.dispose();
			new MainMenuUI().setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		finally
		{
			this.dispose();
		}

	}
	
	public void exportData()
	{
		int returnVal;		
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter pdffilter = new FileNameExtensionFilter("PDF File (.pdf)", new String[] {"pdf"});	
		fc.addChoosableFileFilter(pdffilter);
		FileNameExtensionFilter csvfilter = new FileNameExtensionFilter("Comma Seperated Values (.csv)", new String[] {"csv"});		
		fc.addChoosableFileFilter(csvfilter);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setSelectedFile(new File(""));
		//fc.setCurrentDirectory(new File("."));
		//fc.changeToParentDirectory();
			if (rdbtnPdf.isSelected())
			{

				fc.setFileFilter(pdffilter);
				returnVal = fc.showSaveDialog(this);
				//JFileChooser.
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File f = fc.getSelectedFile();
					//if f.
					String path = f.getAbsolutePath();
					if (f.exists())
					    {
					        int answer = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
					        if (answer == JOptionPane.YES_OPTION)
					        {
								System.out.println(path);
								if (path.indexOf(".pdf") == -1)
								{
										path = path + ".pdf"; 
								}
								new PrintReportPDF().createPDF(path, columns, data);
									
					        }
					        else if (answer == JOptionPane.NO_OPTION)
					        {
					        	exportData();
					        }
					        
					}
					else 
					{
						System.out.println(path);
						if (path.indexOf(".pdf") == -1)
						{
							path = path + ".pdf"; 
						}
						
						new PrintReportPDF().createPDF(path, columns, data);
					//	JOptionPane.showMessageDialog(null, "PDF File created successfully!");
					}

				}
				else if (returnVal == JFileChooser.CANCEL_OPTION)
				{
					//JOptionPane.showMessageDialog(null, "Saving was cancelled!");
				}

			}
			else
			{
				fc.setFileFilter(csvfilter);
				returnVal = fc.showSaveDialog(this);
				if(returnVal==JFileChooser.APPROVE_OPTION){
					File f = fc.getSelectedFile();
					String path = f.getAbsolutePath();
					if (f.exists())
				    {
				        int answer = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
				        if (answer == JOptionPane.YES_OPTION)
				        {
				        	System.out.println(path);
							if (path.indexOf(".csv") == -1)
							{
								path = path + ".csv"; 
							}
							new PrintReportCSV(path, columns, data);
							
				        }
				        else if (answer == JOptionPane.NO_OPTION)
				        {
				        	exportData();
				        }
				    }
					else 
					{
						if (path.indexOf(".csv") == -1)
						{
							path = path + ".csv"; 
						}
						new PrintReportCSV(path, columns, data);
					}
					
				}
				else if (returnVal == JFileChooser.CANCEL_OPTION)
				{
					//JOptionPane.showMessageDialog(null, "Saving was cancelled!");
				}
			}
		}

	
	public void readTermsAndGrade()
	{
		String type = db.getType();
		if (type.equalsIgnoreCase("Oracle"))
		{
			String 	q = "SELECT DISTINCT TERM FROM ENROLL ORDER BY TERM";
			try {
				ResultSet r = db.doQuery(q);
				while (r.next())
				{
					TermComboBox.addItem(r.getString(1));
				}
				q = "SELECT DISTINCT GRADE FROM ENROLL ORDER BY GRADE";
				r = db.doQuery(q);
				while (r.next())
				{
					gradeComboBox.addItem(r.getString(1));
				}
				
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		else 
		{
			String databaseName = db.getDBName();
			String qu = "SELECT DISTINCT TERM FROM "+databaseName+".ENROLL ORDER BY TERM";
			try {
				ResultSet r = db.doQuery(qu);
				while (r.next())
				{
					TermComboBox.addItem(r.getString(1));
				}
				qu = "SELECT DISTINCT GRADE FROM "+databaseName+".ENROLL ORDER BY GRADE";
				r = db.doQuery(qu);
				while (r.next())
				{
					gradeComboBox.addItem(r.getString(1));
				}
				
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
	
	
	
	public void processQuery()
	{
		if (db != null)
		{
			if (db.getType().equalsIgnoreCase("Oracle"))
			{
				doQueryOracle();
			}
			else
			{
				//doQueryMySQL();
			}
				
		}
			else{
				JOptionPane.showMessageDialog(this, "No Connection to Database!");
			}
	}
	
	
	
	public void doQueryOracle()
	{
		String tableJoinQuery = getColumsJoin();
		System.out.println(tableJoinQuery);
		String query = null;
		String queryCount = null;
		String querysAll;
		String queryCountsAll;
		

		
		
		querysAll = "SELECT " +tableJoinQuery+" FROM STUDENT, ENROLL, COURSE, MAJOR WHERE STUDENT.SN = ENROLL.SN AND ENROLL.CN = COURSE.CN AND STUDENT.MCODE = MAJOR.MCODE ";
		queryCountsAll = "SELECT COUNT(*) FROM STUDENT, ENROLL, COURSE, MAJOR WHERE STUDENT.SN = ENROLL.SN AND ENROLL.CN = COURSE.CN AND STUDENT.MCODE = MAJOR.MCODE ";
		
		
		
		
		if(chckbxAllC.isSelected()) {
			
			query = querysAll + "ORDER BY SN";
			queryCount = queryCountsAll;
		}
		
		else if(!chckbxAllC.isSelected()) {
			
			
			if( chckbxSN.isSelected() || chckbxStudentFN.isSelected() || chckbxStudentLN.isSelected() || chckbxMCode.isSelected()
					|| chckbxCNC.isSelected() || chckbxGrade.isSelected() || chckbxTerm.isSelected()
					|| chckbxCName.isSelected() || chckbxMname.isSelected()
					) 
			{
				query = querysAll + "ORDER BY " + tableJoinQuery;
				queryCount = queryCountsAll;	
			}
			else {
				JOptionPane.showMessageDialog(this, "You did not Choose any Column to Display");
			}
			
		} 
		//SELECT WITH Conditions ------------------------------------------------------------------------------
		//ID and Course --> what is the Grade 
		if (chckbxSNum.isSelected() && snField.getText().trim().length() > 0 
				&& chckbxCourse.isSelected() && courseField.getText().trim().length() > 0)
		{
			query = querysAll + "AND STUDENT.SN = " + snField.getText().trim() + " AND ENROLL.CN LIKE \'%"
		+ courseField.getText().trim().toUpperCase()+"%\'";
			
			queryCount = queryCountsAll+ "AND STUDENT.SN = " + snField.getText().trim() 
					+ " AND ENROLL.CN LIKE \'%" + courseField.getText().trim().toUpperCase()+"%\'";
		}
		//ID, Term, Grade, and Course --> what is the anything in specfic term with a given grade took 
		else if (chckbxTermS.isSelected() && chckbxLtGrade.isSelected() 
				&& chckbxSNum.isSelected() && snField.getText().trim().length() > 0 
				&& chckbxCourse.isSelected() && courseField.getText().trim().length() > 0) 
		{
			query = querysAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()+ " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'"
					+ " AND STUDENT.SN = " + snField.getText().trim() + " AND ENROLL.CN LIKE \'%" + courseField.getText().trim().toUpperCase()+"%\'";
			
			queryCount = queryCountsAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()+ " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'"
					+ " AND STUDENT.SN = " + snField.getText().trim() + " AND ENROLL.CN LIKE \'%" + courseField.getText().trim().toUpperCase()+"%\'";
		}
		//ID, Term, and Grade --> what is the course in specfic term with a given grade took 
		else if (chckbxTermS.isSelected() && chckbxLtGrade.isSelected() 
				&& chckbxSNum.isSelected() && snField.getText().trim().length() > 0)
		{
			query = querysAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()+ " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'"
					+ " AND STUDENT.SN = " + snField.getText().trim();
			
			queryCount = queryCountsAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()+ " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'"
					+ " AND STUDENT.SN = " + snField.getText().trim();
		} 
		//ID and Term  --> what is the course in specfic term 
		else if (chckbxTermS.isSelected() && chckbxSNum.isSelected() && snField.getText().trim().length() > 0)
		{
			query = querysAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()+ " AND STUDENT.SN = " + snField.getText().trim();
			
			queryCount = queryCountsAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()+ " AND STUDENT.SN = " + snField.getText().trim();
		} 
		
		//ID and Grade --> what is the all course that you got a given grade in 
		else if (chckbxLtGrade.isSelected() && chckbxSNum.isSelected() && snField.getText().trim().length() > 0)
		{
			query = querysAll+ "AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'"
					+ " AND STUDENT.SN = " + snField.getText().trim();
			
			queryCount = queryCountsAll+  "AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'"
					+ " AND STUDENT.SN = " + snField.getText().trim();
		}
		// ID --> Give everything 
		else if (chckbxSNum.isSelected() && snField.getText().trim().length() > 0) 
		{
			query = querysAll +" AND STUDENT.SN = " + snField.getText().trim();
			queryCount =  queryCountsAll+ " AND STUDENT.SN = " + snField.getText().trim();
		}
		// Term and Grade
		else if (chckbxTermS.isSelected() && chckbxLtGrade.isSelected()) 
		{
			query = querysAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()+ " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'";
			
			queryCount = queryCountsAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()+ " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'";
		}
		
		// Grade and Course
		else if (chckbxLtGrade.isSelected()) 
		{
			query = querysAll+ "AND ENROLL.CN LIKE \'%" + courseField.getText().trim().toUpperCase()
					+"%\'" + " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'";
					
			queryCount = queryCountsAll+ "AND ENROLL.CN LIKE \'%" + courseField.getText().trim().toUpperCase()
					+"%\'" + " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'";
		}
		
		// Term and Grade and Course
		else if (chckbxTermS.isSelected() && chckbxLtGrade.isSelected()
				&& chckbxCourse.isSelected() && courseField.getText().trim().length() > 0) 
		{
					query = querysAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()
					+ " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'" + " AND ENROLL.CN LIKE \'%"
							+ courseField.getText().trim().toUpperCase()+"%\'";
					
					queryCount = queryCountsAll+ "AND ENROLL.TERM = " + TermComboBox.getSelectedItem()
					+ " AND ENROLL.GRADE =\'" +gradeComboBox.getSelectedItem()+"\'" + " AND ENROLL.CN LIKE \'%"
					+ courseField.getText().trim().toUpperCase()+"%\'";
		}
		//--------------------------------------------------------------------------------------------------- 
		
		
		System.out.println(query);

		
		try {
			ResultSet r = db.doQuery(queryCount);
			int rows = 0;
			if (r.next())
			{
				rows = Integer.parseInt(r.getString(1));
				System.out.println("Number of Rows: "+r.getString(1));
			}
			if (rows>0)
			{
				btnExport.setEnabled(true);
				rdbtnPdf.setEnabled(true);
				rdbtnCsv.setEnabled(true);
			}
			else 
			{
				btnExport.setEnabled(false);
				rdbtnPdf.setEnabled(false);
				rdbtnCsv.setEnabled(false);
			}
			data = new String[rows][columns.length];
			r = db.doQuery(query);
			int i=0;
			while (r.next())
			{
				for (int j=0; j<columns.length; j++)
				{
					String tmp = r.getString(j+1);
					if (tmp == null)
					{
						data[i][j] = "";
					}
					else 
					{
						data[i][j] = tmp;
					}
				}
				
				i++;
			}
			table.setModel(new DefaultTableModel(data, columns));
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		

	}
	
	
	public String getColumsJoin()
	{
		colAlians.clear();
		col.clear();
		String queryColumn = "";
		String queryColAlians = "";
		
		if (chckbxAllC.isSelected())
		{
			columns = defaultColumns;
			queryColAlians += columnsJoin[0];
			queryColumn += columns[0];
			for(int i=1; i<columnsJoin.length;i++)
			{
				queryColAlians += ", "+ columnsJoin[i];
				queryColumn += ", "+ columns[i];
			}
		}
		
		else
		{
			if (chckbxSN.isSelected())
			{
				colAlians.add("STUDENT.SN");
				col.add("SN");

			}
			if (chckbxStudentFN.isSelected())
			{
				colAlians.add("STUDENT.FNAME");
				col.add("FNAME");
			}
			if (chckbxStudentLN.isSelected())
			{
				colAlians.add("STUDENT.LNAME");
				col.add("LNAME");
			}
			if (chckbxCNC.isSelected())
			{
				colAlians.add("ENROLL.CN");
				col.add("CN");
			}
			if (chckbxCName.isSelected())
			{
				colAlians.add("COURSE.CNAME");
				col.add("CNAME");
			}
			if (chckbxTerm.isSelected())
			{
				colAlians.add("ENROLL.TERM");
				col.add("TERM");
			}
			if (chckbxGrade.isSelected())
			{
				colAlians.add("ENROLL.GRADE");
				col.add("GRADE");
			}
			if (chckbxMCode.isSelected())
			{
				colAlians.add("STUDENT.MCODE");
				col.add("MCODE");
			}
			if (chckbxMname.isSelected())
			{
				colAlians.add("MAJOR.MNAME");
				col.add("MNAME");
			}
			
			queryColAlians = colAlians.toString();
			queryColAlians = queryColAlians.substring(1, queryColAlians.length()-1);
			
			queryColumn = col.toString();
			queryColumn = queryColumn.substring(1, queryColumn.length()-1);
			columns = queryColumn.split(", ");	
		}
		
		return queryColAlians;
	}
	
}
