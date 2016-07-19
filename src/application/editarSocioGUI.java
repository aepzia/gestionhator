package application;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class editarSocioGUI extends Application {
	
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
	private static Socio bazkidea; 
	
	@FXML private void atras(){
		System.out.println("ipfuauu");
		verSocioGUI w = new verSocioGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void guardar(){
		String currentData = "";
		boolean errorea= false;
		try
        {
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = new Date();
			currentData =formatoDeFecha.format(fecha);
	        Class.forName("org.hsqldb.jdbcDriver");  
    		Connection conn = DriverManager.getConnection("jdbc:hsqldb://C:/Users/standar/Desktop/base.odb");
    		Statement stmt = conn.createStatement();
    		String banco = textBanco1.getText()+"_"+textBanco2.getText()+"_"+textBanco3.getText()+"_"+textBanco4.getText();
    		String sql = "INSERT socio SET nSocio='"
            		+opNum.getSelectionModel().getSelectedItem()
            		+"',pensionista='"+opPensionista.isSelected()+"',nombre='"+textNombre.getText()
            		+"',apellido='"+textApellido.getText()+"',fechaNacimiento='"+ textNacimiento.getValue() +"',tpSocio='"+ opTipo.getSelectionModel().getSelectedItem()
            		+"',sexo='"+opSexo.getSelectionModel().getSelectedItem()+
            		"',direccion='"+textDireccion.getText()+"',tel1='"+textTel1.getText()+
            		"',tel2='"+textTel2.getText()+"',telContacto='"+textTelEm.getText()
            		+"',email='"+textEmail.getText()+"',foto'"+imgPath+"',cuenta_corriente='"+banco+"',otras_observaciones='"+textOtros.getText()+"',proteccion_de_datos='"+pdfPath+"' where DNI='"+bazkidea.getDNI()+"'";
    		
    		System.out.println(sql);
    		stmt.executeUpdate(sql);
            stmt.close();    	
            conn.close();
        
        } 
		catch ( Exception e ){
			e.printStackTrace();
			if (e instanceof NullPointerException){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Error");
				alert.setContentText("Rellena los campos onligatorios");
				errorea = true;
				alert.showAndWait();			}
			if(e instanceof HsqlException){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Error");
				alert.setContentText("Ese DNI ya existe");
				errorea = true;
				alert.showAndWait();			
				}
        }		
		if(!errorea){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Bien!");
			alert.setContentText("El socio se ha guardado correctamente");
			errorea = true;
			alert.showAndWait();
			listaSociosGUI w = new listaSociosGUI();
			w.start(getPrimaryStage());
		}
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
        editarSocioGUI.pStage = pStage;
    }   
    
    public static void main(String[] args) {

        launch(args);
    }

    
    @Override public void start(Stage primaryStage){
    	Parent page;
			try {
				setPrimaryStage(primaryStage);

				page = FXMLLoader.load(getClass().getResource("../itxura/editarSocio.fxml"));
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
    	bazkidea = Controler.autatutakoBazkidea;
    	System.out.println(bazkidea);
    	textNombre.setText(bazkidea.getIzena());
    	textApellido.setText(bazkidea.getAbizena());
    	textDNI.setText(bazkidea.getDNI());
    	//text.setText(bazkidea.getTpSocio());
    	//textSexo.setText(bazkidea.getSexo());
    	//textNum.setText(bazkidea.getnSocio());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(bazkidea.getFechaAlta(), formatter);
    	//textAlta.setValue(date);
    	textEmail.setText(bazkidea.getEmail());
    	opPensionista.setSelected(bazkidea.isPensionista());
    	textNacimiento.setValue(LocalDate.parse(bazkidea.getFechaAlta(), formatter));
    	textTel1.setText(bazkidea.getTel1());
    	textTel2.setText(bazkidea.getTel2());
    	//textTelEmergencia.setText(bazkidea.getTelEmergencia());
    	//textBanco.setText(bazkidea.getCuentaCorriente());
    	textDireccion.setText(bazkidea.getHelbidea());
    	textOtros.setText(bazkidea.getComentarios());
    	if(!bazkidea.getFoto().equals("null")){
			imgFoto.setImage((new Image(bazkidea.getFoto())));		
		}
    }
}

