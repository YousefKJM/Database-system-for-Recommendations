package src;

import java.io.FileOutputStream;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class PrintReportPDF {
	public void createPDF (String path, String[] columns, String[][] data){
		 
		  Document doc = new Document();
		  PdfWriter docWriter = null;
		 
		  try {
		    
		   //special font sizes
		//   Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
		 //  Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
		   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
		   doc.addAuthor("Saleh AlSaleh");
		   doc.addCreationDate();
		   doc.addProducer();
		   doc.addCreator("ICS324 DB");
		   doc.addTitle("Report with Column Headings");
		   doc.setPageSize(PageSize.A4);
		   
		   //open document
		   doc.open();
		 
		   //create a paragraph
		   Paragraph paragraph = new Paragraph("Report Created By ICS 324 Project\n");
		    
		    
		   //specify column widths
		   float[] columnWidths = new float[columns.length] ;
		   
		   // 		columns = new String[]{"TERM","CRN","COURSE","SEC","INSTRUCTOR","MAX","DAYS","STARTTIME", "ENDTIME", "BLD", "ROOM"};
		   for (int i=0; i<columnWidths.length;i++)
		   {
			   if (columns[i].equalsIgnoreCase("Sec") || columns[i].equalsIgnoreCase("SectionNum") || columns[i].equalsIgnoreCase("BLD") || columns[i].equalsIgnoreCase("Building"))
			   {
				   columnWidths[i] = 2f;
			   }
			   else if (columns[i].equalsIgnoreCase("MAX") || columns[i].equalsIgnoreCase("MAX_Limit"))
			   {
				   columnWidths[i] = 2.5f;
			   }
			   else if (columns[i].equalsIgnoreCase("Term") || columns[i].equalsIgnoreCase("CRN") || columns[i].equalsIgnoreCase("ROOM"))
			   {
				   columnWidths[i] = 3f;
			   }
			   else if (columns[i].equalsIgnoreCase("Days"))
			   {
				   columnWidths[i] = 3.5f;
			   }
			   else if (columns[i].equalsIgnoreCase("Course") || columns[i].equalsIgnoreCase("CourseID"))
			   {
				   columnWidths[i] = 4f;
			   }
			   else if (columns[i].equalsIgnoreCase("SartTime") || columns[i].equalsIgnoreCase("EndTime") || columns[i].equalsIgnoreCase("Sart_Time") || columns[i].equalsIgnoreCase("End_Time"))
			   {
				   columnWidths[i] = 4f;
			   }
			   else if (columns[i].equalsIgnoreCase("INSTRUCTOR") || columns[i].equalsIgnoreCase("INSTRUCTORName"))
			   {
				   columnWidths[i] = 6f;
			   }
			   else{
				   columnWidths[i] = 3f;
			   }
		   }
		   /* Hard-coded widths
		   columnWidths[0] = 3f;
		   columnWidths[1] = 3f;
		   columnWidths[2] = 4f;
		   columnWidths[3] = 2f;
		   columnWidths[4] = 6f;
		   columnWidths[5] = 2.5f;
		   columnWidths[6] = 3.5f;
		   columnWidths[7] = 4.5f;
		   columnWidths[8] = 4.5f;
		   columnWidths[9] = 2f;
		   columnWidths[10] = 3f;
		   */
		   //create PDF table with the given widths
		   PdfPTable table = new PdfPTable(columnWidths);
		   // set table width a percentage of the page width
		   table.setWidthPercentage(95f);
		   for (int i=0; i<columns.length;i++)
		   {
			   insertCell(table, columns[i], Element.ALIGN_CENTER, 1, new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, new BaseColor(0, 0, 0)));
		   }
		   table.completeRow();

		   for(int i=0; i<data.length; i++){
		     
			   for (int j=0; j<data[i].length; j++)
			   {
				   insertCell(table,data[i][j], Element.ALIGN_LEFT, 1, new Font(FontFamily.TIMES_ROMAN, 10));
			   }
			   table.completeRow();
		     
		   }	
		   //add the PDF table to the paragraph 
		   paragraph.add(table);
		   // add the paragraph to the document
		   doc.add(paragraph);
		   JOptionPane.showMessageDialog(null, "PDF File created successfully!");
		  }
		  catch (Exception ex)
		  {
		   //ex.printStackTrace();
			  System.out.println("Error!");
      		JOptionPane.showMessageDialog(null, "Sorry!, the file is open");
		  }
		  finally
		  {
		   if (doc != null){
		    //close the document
		    doc.close();
		   }
		   if (docWriter != null){
		    //close the writer
		    docWriter.close();
		   }
		  }
		 }
		  
		 public void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
		   
		  //create a new cell with the specified Text and Font
		  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
		  //set the cell alignment
		  cell.setHorizontalAlignment(align);
		  //set the cell column span in case you want to merge two or more cells
		  cell.setColspan(colspan);
		  //in case there is no text and you wan to create an empty row
		  if(text.trim().equalsIgnoreCase("")){
		   cell.setMinimumHeight(10f);
		  }
		  //add the call to the table
		  table.addCell(cell);
		   
		 }
}
