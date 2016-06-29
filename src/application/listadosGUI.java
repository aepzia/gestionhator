package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class listadosGUI extends Application {
	
	@FXML Button btnOk;
	@FXML Button btnAtras;
	@FXML CheckBox chNSocio;
	@FXML CheckBox chDNI;
	@FXML CheckBox chPensionista;
	@FXML CheckBox chNombre;
	@FXML CheckBox chApellido;
	@FXML CheckBox chFechaNacimiento;
	@FXML CheckBox chTipoSocio;
	@FXML CheckBox chSexo;
	@FXML CheckBox chDireccion;
	@FXML CheckBox chTelefono;
	@FXML CheckBox chEmail;
	@FXML CheckBox chFechaAlta;
	@FXML CheckBox chFechaBaja;
	@FXML CheckBox chMotivoBaja;
	@FXML CheckBox chFoto;
	@FXML CheckBox chBanco;
	@FXML ChoiceBox<String> listAukerak;
	
	private static Stage pStage;

	
	@FXML private void getPdf(ActionEvent event) throws FileNotFoundException, DocumentException{
		Document document = new Document();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("listado.pdf"));
		document.open();
		int columnas = 0;
		String sql = "SELECT";
		if (chNSocio.isSelected()){ sql=sql+" nSocio,"; columnas++;}
		if(chDNI.isSelected()) {sql = sql +" DNI,"; columnas++;}
		if(chPensionista.isSelected()) {sql = sql+" pensionista,"; columnas++;}
		if(chNombre.isSelected()) {sql = sql+" nombre,"; columnas++;}
		if(chApellido.isSelected()) {sql = sql + " apellido,"; columnas++;}
		if(chFechaNacimiento.isSelected()) {sql = sql +" fechaNacimiento,"; columnas++;}
		if(chTipoSocio.isSelected()) {sql = sql+" tpSocio,"; columnas++;}
		if(chSexo.isSelected()) {sql = sql +" sexo,"; columnas++;}
		if(chDireccion.isSelected()) {sql = sql +" direccion,"; columnas++;}
		if(chTelefono.isSelected()) {sql = sql +" tel1,tel2,telContacto,"; columnas++;}
		if(chEmail.isSelected()) {sql = sql +" email,"; columnas++;}
		if(chFechaAlta.isSelected()) {sql = sql +" fechaAlta,"; columnas++;}
		if(chFechaBaja.isSelected()) {sql = sql +" fechBaja,"; columnas++;}
		if(chMotivoBaja.isSelected()) {sql = sql + " motivoBaja,"; columnas++;}
		if(chFoto.isSelected()){sql = sql + " foto,"; columnas++;}
		if(chBanco.isSelected()) {sql = sql + " cuenta_corriente"; columnas++;}
		
		sql = sql.substring(0, sql.length()-1);
		sql = sql + " FROM socio";
		System.out.println(sql);
		PdfPTable table = new PdfPTable(columnas);
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Asoziazioko_datuak/database.mdb;memory=false");
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while ( rs.next() )
            {
					for(int i =1 ; i<=columnas; i++){
						table.addCell(rs.getObject(i).toString());
					}
            }
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		document.add(table);
	    document.close();
		File pdfFile = new File("listado.pdf");
    	try {
			Desktop.getDesktop().open(pdfFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 
	}
	@FXML private void atras(ActionEvent event){
		menuPrincipalGUI newWindow = new menuPrincipalGUI();
		newWindow.start(getPrimaryStage());
	}
	
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
       listadosGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args); 
      
    }
    
    @Override
    public void start(Stage primaryStage){
    	
		try {
			
			setPrimaryStage(primaryStage);
			Parent page = FXMLLoader.load(getClass().getResource("../itxura/listados.fxml"));
			Scene scene = new Scene(page);
	        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
	        primaryStage.setScene(scene);
			primaryStage.setTitle("Editar asociación");
			primaryStage.show();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    protected void initialize(){
    	
    }
}

