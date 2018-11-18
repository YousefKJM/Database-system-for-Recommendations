package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;

public class JDBC {
	
	private Connection conn;
	private ResultSet rs;
	private Statement st;
	private Scanner in;
	private String type;
	private String url;
	private String username;
	private String password;
	private String dbname; // needs to be done
	private int port; // needs to be done
	private String sid;
	
	public JDBC() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException
	{
			readConfig();
			if (type.equalsIgnoreCase("oracle"))
			{
				
				// default port = 1521
				// default sid = xe 
				String connStr = "jdbc:oracle:thin:@"+url+":"+port+":"+sid;
				conn = DriverManager.getConnection(connStr,username,password);
			}
			else
			{
				
				
				
				//String driver = "com.mysql.jdbc.Driver";
				Class.forName("com.mysql.jdbc.Driver");
				
				if (url.lastIndexOf("/") == url.length()-1)
				{
					url.substring(0, url.length()-1);
				}
			      conn = DriverManager.getConnection("jdbc:mysql://"+url+":"+port+"/"+dbname+ "?user="+username+"&password="+password);
			}
			conn.setAutoCommit(true);

	}
	public void commit() throws SQLException
	{
		conn.commit();
	}
	public JDBC(String username, String password)
	{
		try {
			String connStr = "jdbc:oracle:thin:@ics-db.ccse.kfupm.edu.sa:1521:xe";
			conn = DriverManager.getConnection(connStr,username,password);
		} 
		catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	public void readConfig()
	{
		try {
			in = new Scanner(new FileInputStream("database.conf"));
			if (in.hasNextLine())
				type = in.nextLine();
			if (in.hasNextLine())
				url = in.nextLine();
			if (in.hasNextLine())
			{
				try{
					port = Integer.parseInt(in.nextLine());	
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Error! Invalid Port Number");
				}
			}
				
			if (in.hasNextLine())
			{
				String x = in.nextLine();
				dbname = x;
				sid = x;
				
			}
			
			PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
			encryptor.setPoolSize(4);          // This would be a good value for a 4-core system
			encryptor.setPassword("SalehAhmed");
			//encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
			
			
			if (in.hasNextLine())
				username = in.nextLine();
			if (in.hasNextLine())
				password = in.nextLine();
			
			in.close();
			//String encryptedText = "";
			//encryptedText = encryptor.decrypt(password);
			password = encryptor.decrypt(password);
			
			//System.out.println(type+"\t"+url+"\t"+port+"\t"+dbname+"\t"+username+"\t"+password);
			
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}
	}
	public String getType()
	{
		return type;
	}
	public String getDBName()
	{
		return dbname;
	}
	public ResultSet doQuery(String query) throws Exception
	{
		rs = null;
		st = conn.createStatement ();
		
		rs =  st.executeQuery(query);
		
		return rs;
	}
	
	public int doUpdate(String query) throws Exception
	{
		//rs = null;
		st = conn.createStatement();
		
		int result = st.executeUpdate(query);
		
		return result;
	}
	public void closeConnection() throws Exception
	{
		conn.close();
	}

}
