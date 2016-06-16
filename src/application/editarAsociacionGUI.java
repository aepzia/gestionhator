package application;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class editarAsociacionGUI extends Application {
	
	@FXML private Button btnAtras;
	@FXML private Button btnGuardar;
	@FXML private Button btnExaminar;
	@FXML private TextField textNombre;
	@FXML private TextField textTel1;
	@FXML private TextField textTel2;
	@FXML private TextField textBanco1;
	@FXML private TextField textBanco2;
	@FXML private TextField textBanco3;
	@FXML private TextField textBanco4;
	@FXML private TextArea textDirecion;
	@FXML private TextField textPresi;
	@FXML private ChoiceBox textNumeracion;
	@FXML private ImageView imgLogo;
	@FXML private Label mezua; 
	
	private static Stage pStage;
	
	@FXML private void atras(){
		datosAsociacionGUI preWindow = new datosAsociacionGUI();
		preWindow.start(getPrimaryStage());
	}
	@FXML private void guardar(){
		//TODO Asoziazioko datuak datu basean gorde
		mezua.setText("mezua ondo bidali da");
		
	}
	@FXML private void examinar(){
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
		
		imgLogo.setImage(new Image("file:"+ruta));
		
		//TODO imagena edo ruta gorde.
		
	
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        editarAsociacionGUI.pStage = pStage;
    }   
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
            Parent page;
			try {
				setPrimaryStage(primaryStage);
				page = FXMLLoader.load(getClass().getResource("../itxura/editarAsociacion.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Editar ascoiación");
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
           
    }
    @FXML
    protected void initialize(){
    	System.out.println("IHFDU");
    	//TODO datu basetik hartu eta datuak idatzi 
 
    	
    }
}

