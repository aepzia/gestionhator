package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
public class ficha {
	public static void getFicha() throws FileNotFoundException, DocumentException {
		Socio bazkidea = Controler.autatutakoBazkidea;
		Document document = new Document();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("fitxa.pdf"));
		document.open();
		Image img1;
		Image img2;

		try {
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
    		
    		Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM asociacion");
            String nombreAsociacion = "";
            String imgAsociacion = "";
            while ( rs.next() )
            {
            	 nombreAsociacion = rs.getObject("izena").toString().toUpperCase();
            	 imgAsociacion = rs.getObject("logo").toString();
            	
            }
			img1 = Image.getInstance(imgAsociacion);
			img1.scaleAbsolute(45f, 45f);
			img1.setAbsolutePosition(35, 760);
			img2 = Image.getInstance(bazkidea.getFoto());
			img2.scaleAbsolute(56f, 86f);
			img2.setAbsolutePosition(220, 680);
	        PdfContentByte canvas = pdfWriter.getDirectContent();
	       
	        canvas.saveState();
	        BaseFont bf = BaseFont.createFont();
	     

	        canvas.beginText();

	        //TITULUA
	        canvas.setFontAndSize(bf, 20);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, nombreAsociacion, 102, 790, 0);
	 	    //EZAUGARRIAK ES
	 	    canvas.setFontAndSize(bf, 8);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Num. de Socio:", 102, 770, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Nombre: ", 35, 745, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Domicilio: ", 35, 720, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "DNI: ", 35, 695, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Fec. Nac:", 115, 695, 0);
	 	   canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Presidente", 35, 675, 0);
	 	   canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Titular", 140, 675, 0);


	 	    //EZAUGARRIAK DATUAK
	 	    canvas.setFontAndSize(bf, 10);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, bazkidea.getnSocio(), 160, 765, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, bazkidea.getIzena() +" "+bazkidea.getAbizena(), 70, 740, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, bazkidea.getHelbidea(), 35, 710, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, bazkidea.getDNI(), 35, 685, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, bazkidea.getFechaNacimiento(), 115, 685, 0);

	 	    //EZAUGARRIAK EU
	 	    canvas.setRGBColorFill(156, 156, 156);	 	     
	 	    canvas.setFontAndSize(bf, 8);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Bazkide Zbk.:", 102, 760, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Izena: ", 35, 735, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Helbidea: ", 75, 720, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "NAN: ", 55, 695, 0);
	 	    canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Jaioteguna: ", 152, 695, 0);
		 	canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Lehendakaria", 75, 675, 0);
		 	canvas.showTextAligned(PdfContentByte.ALIGN_LEFT, "Titularra", 165, 675, 0);



	        canvas.endText();
	        canvas.rectangle(30, 810, 260, -160);
	        canvas.restoreState();
	        canvas.addImage(img1);
	        canvas.addImage(img2);

	        canvas.closePathStroke();
	    	
	     
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      


		
		//document.add(table);
	    document.close();
		File pdfFile = new File("fitxa.pdf");
    	try {
			Desktop.getDesktop().open(pdfFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
