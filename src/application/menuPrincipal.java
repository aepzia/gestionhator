package application;

import java.io.IOException;

import com.sun.glass.ui.Window;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class menuPrincipal extends Application {
	
	@FXML private Text actiontarget;
	@FXML private Button btnAsociacion;
	@FXML private Button btnSocios;
	@FXML private Button btnActividades;
	@FXML private Button btnTerceros;
	@FXML private Button btnCalendario;
	
	@FXML private void verAsociacion(ActionEvent event) {
		try{
		    Parent root = FXMLLoader.load(getClass().getResource("datosAsociacion.fxml"));
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("datosAsociacion.fxml"));
		    datosAsociacion controller = new datosAsociacion();

		    loader.setController(controller);
		    loader.setRoot(root);

		    Stage stage = new Stage();
		    stage.setScene(new Scene(root));
		    stage.show();
		} catch (IOException ex){
			
		}
	}
	
    public static void main(String[] args) {
        launch(args); 
      
    }
    
    @Override
    public void start(Stage primaryStage){
    	
		try {
			Parent page = FXMLLoader.load(getClass().getResource("../itxura/menuPrincipal.fxml"));
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
  
}
