package application;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


//import org.hsqldb.util.DatabaseManager;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.hsqldb.HsqlException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class nuevoSocioGUI extends Application {
	
	@FXML public ImageView imgFoto;
	@FXML public TextField textNombre;
	@FXML public TextField textApellido;
	@FXML public TextField textTel1;
	@FXML public TextField textTel2;
	@FXML public TextArea textDireccion;
	@FXML public DatePicker textNacimiento;
	@FXML public TextField textDNI;
	@FXML public TextField textEmail;
	@FXML public TextField textTelEm;
	@FXML public TextArea textOtros;
	@FXML public TextField textBanco1;
	@FXML public TextField textBanco2;
	@FXML public TextField textBanco3;
	@FXML public TextField textBanco4;
	@FXML public ChoiceBox<String> opTipo;
	@FXML public ChoiceBox<String> opSexo;
	@FXML public CheckBox opPensionista;
	@FXML public ChoiceBox<Integer> opNum;
	@FXML public Button btnExaminarFoto;
	@FXML public Button btnExaminarLOPD;
	@FXML public Button btnVer;
	@FXML public Button btnAtras;
	@FXML public Button btnGuardar;
	
	private static String imgPath;
	private static String pdfPath;
	private static Stage pStage;

	@FXML private void atras(){
		listaSociosGUI w = new listaSociosGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void guardar(){
		String currentData = "";
		try
        {
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = new Date();
			currentData =formatoDeFecha.format(fecha);
	        Class.forName("org.hsqldb.jdbcDriver");  
    		Connection conn = DriverManager.getConnection("jdbc:hsqldb://C:/Users/standar/Desktop/base.odb");
    		Statement stmt = conn.createStatement();
    		String banco = textBanco1.getText()+"_"+textBanco2.getText()+"_"+textBanco3.getText()+"_"+textBanco4.getText();
    		String sql = "INSERT INTO socio (nSocio,DNI,pensionista,nombre,apellido,fechaNacimiento,tpSocio,"
    				+ "sexo,direccion,tel1,tel2,telContacto,email,fechaAlta,foto,cuenta_corriente,"
    				+ "otras_observaciones,proteccion_de_datos) VALUES("
            		+opNum.getSelectionModel().getSelectedItem()
            		+",'"+textDNI.getText()+"','"+opPensionista.isSelected()+"','"+textNombre.getText()
            		+"','"+textApellido.getText()+"','"+ textNacimiento.getValue() +"','"+ opTipo.getSelectionModel().getSelectedItem()
            		+"','"+opSexo.getSelectionModel().getSelectedItem()+
            		"','"+textDireccion.getText()+"','"+textTel1.getText()+
            		"','"+textTel2.getText()+"','"+textTelEm.getText()
            		+"','"+textEmail.getText()+"','"+currentData+"','"+imgPath+"','"+banco+"','"+textOtros.getText()+"','"+pdfPath+"')";
    		System.out.println(sql);
    		stmt.executeUpdate(sql);
            stmt.close();    	
            conn.close();
        
        } 
		catch ( Exception e ){
			e.printStackTrace();
			if (e instanceof NullPointerException){
				//TODO sartutako datu hutsak tratatu
			}
			if(e instanceof HsqlException){
				//TODO DNI errepikatua
			}
        }		

		System.out.println("gorde da");
	}	
	
	@FXML private void examinarFoto(){
		JFileChooser explorador = new JFileChooser("/home/");
		explorador.setFileFilter(new FileNameExtensionFilter("JPG files", "jpg", "jpeg", "png"));
		int seleccion = explorador.showDialog(null, "Abrir");
		  
		switch(seleccion) {
		case JFileChooser.APPROVE_OPTION:
		 break;

		case JFileChooser.CANCEL_OPTION:
		 break;

		case JFileChooser.ERROR_OPTION:
		 break;
		}

		File archivo = explorador.getSelectedFile();
	
		String ruta = archivo.getPath().replace("\\", "/");
		System.out.println(ruta);
		imgPath ="file:"+ruta;
		imgFoto.setImage(new Image(imgPath));		
	}
	
	@FXML private void examinarPDF(){
		JFileChooser explorador = new JFileChooser("/home/");
		explorador.setFileFilter(new FileNameExtensionFilter("PDF files", "pdf"));
		
		int seleccion = explorador.showDialog(null, "Abrir");
		  
		switch(seleccion) {
		case JFileChooser.APPROVE_OPTION:
		 break;

		case JFileChooser.CANCEL_OPTION:
		 break;

		case JFileChooser.ERROR_OPTION:
		 break;
		}

		File archivo = explorador.getSelectedFile();
	
		String ruta = archivo.getPath().replace("\\", "/");
		System.out.println(ruta);
		pdfPath =ruta;
	}
	 @FXML public void verLOPD(){
	    	//TODO Datu basetik hartu pdfaren helbidea
	    	File pdfFile = new File(pdfPath);
	    	try {
				Desktop.getDesktop().open(pdfFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	 
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        nuevoSocioGUI.pStage = pStage;
    }   
    
    public static void main(String[] args) {

        launch(args);
    }

    
    @Override public void start(Stage primaryStage){
    	Parent page;
			try {
				setPrimaryStage(primaryStage);

				page = FXMLLoader.load(getClass().getResource("../itxura/nuevoSocio.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
					               
		        primaryStage.setScene(scene);
				primaryStage.setTitle("Añadir un nuevo socio");
				
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
    @FXML protected void initialize(){
    	opNum.setItems(FXCollections.observableArrayList(
    		    Controler.socioKop)
    		);
    		opPensionista.setSelected(false);
    }
}

