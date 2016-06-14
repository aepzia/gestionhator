package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class menuPrincipal extends Application {
	
	@FXML private Text actiontarget;
	@FXML private Button btnAsociacion;
	@FXML private Button btnSocios;
	@FXML private Button btnActividades;
	@FXML private Button btnTerceros;
	@FXML private Button btnCalendario;
	private static Stage pStage;

	@FXML private void verAsociacion(ActionEvent event){
		datosAsociacion newWindow = new datosAsociacion();
		newWindow.start(getPrimaryStage());
	}
	@FXML private void verSocios(ActionEvent event){
		sociosTodos newWindow = new sociosTodos();
		newWindow.start(getPrimaryStage());
	}
	@FXML private void verActividades(ActionEvent event){
		actividadesTodos newWindow = new actividadesTodos();
		//newWindow.start(getPrimaryStage());
	}
	@FXML private void verTerceros(ActionEvent event){
		//datosAsociacion newWindow = new datosAsociacion();
		//newWindow.start(getPrimaryStage());
	}
	@FXML private void verCalendario(ActionEvent event){
		//datosAsociacion newWindow = new datosAsociacion();
		//newWindow.start(getPrimaryStage());
	}
	
	
	
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        menuPrincipal.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args); 
      
    }
    
    @Override
    public void start(Stage primaryStage){
    	
		try {
			setPrimaryStage(primaryStage);
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
