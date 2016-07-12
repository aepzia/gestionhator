package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class menuPrincipalGUI extends Application {
	
	@FXML private Text actiontarget;
	@FXML private Button btnAsociacion;
	@FXML private Button btnSocios;
	@FXML private Button btnActividades;
	@FXML private Button btnTerceros;
	@FXML private Button btnLibro;
	@FXML private Button btnListados;
	@FXML private Button btnCalendario;
	private static Stage pStage;

	@FXML private void verAsociacion(ActionEvent event){
		datosAsociacionGUI newWindow = new datosAsociacionGUI();
		newWindow.start(getPrimaryStage());
	}
	@FXML private void verListados(ActionEvent event){
		listadosGUI newWindow = new listadosGUI();
		newWindow.start(getPrimaryStage());
	}
	@FXML private void verSocios(ActionEvent event){
		listaSociosGUI newWindow = new listaSociosGUI();
		newWindow.start(getPrimaryStage());
	}
	@FXML private void verActividades(ActionEvent event){
		listaActividadesGUI newWindow = new listaActividadesGUI();
		newWindow.start(getPrimaryStage());
	}
	@FXML private void verTerceros(ActionEvent event){
		tercerosGUI w = new tercerosGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void verCalendario(ActionEvent event){
		//datosAsociacion newWindow = new datosAsociacion();
		//newWindow.start(getPrimaryStage());
	}
	
	
	
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        menuPrincipalGUI.pStage = pStage;
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
			primaryStage.setTitle("Menu principal");
			primaryStage.show();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    protected void initialize(){
    	
       
    }
}
