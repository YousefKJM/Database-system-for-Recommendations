package src;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class PrintReportCSV {
	private PrintWriter out;
	public PrintReportCSV(String path, String[] columns, String[][] data)
	{
		
		try {
			out = new PrintWriter(new FileOutputStream(path));
			
			printColumns(columns);
			
			printData(data);
			
			
			out.close();
			JOptionPane.showMessageDialog(null, "CSV File created successfully!");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Sorry!, the file is open");
		}
	}

	private void printData(String[][] data) {
		String str;
		for (int i=0; i<data.length; i++)
		{
			str = "";
			for (int j=0; j<data[i].length; j++)
			{
				str = str+ data[i][j]+",";
			}
			str = str.substring(0, str.length()-1);
			str = str.replace("\n", "");
			out.println(str);
		}
	}

	private void printColumns(String[] columns) {
		String header = "";
		for (int i=0; i<columns.length; i++)
		{
			header = header+ columns[i]+",";
		}
		header = header.substring(0, header.length()-1);
		//System.out.println(header);
		out.println(header);
		
	}
}
