package application;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class listaActividadesGUI extends Application {
	

	
	
	private static Stage pStage;
	private static Actividad aukera;
	
	@FXML ListView<Actividad> listActividad;
	@FXML TextField textAsociacion;
	@FXML TextArea textInformacion;
	@FXML Button btnAñdirActi;
	@FXML Button btnElimActi;
	@FXML Button btnPagos;
	@FXML Button btnVer;
	@FXML Button btnAtras;
	@FXML ChoiceBox<String> btnOrderBy;


	
	@FXML private void atras(){
		menuPrincipalGUI preWindow = new menuPrincipalGUI();
		preWindow.start(getPrimaryStage());
	}
	@FXML private void nuevaActividad(){
		nuevaActividadGUI w = new nuevaActividadGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void eliminarActividad(){
		//TODO eliminar
	}
	@FXML private void verActividad(){
		//TODO actividadea ikusi
	}


	@FXML private void informazioaJarri(){
		aukera = listActividad.getSelectionModel().getSelectedItem();
		textInformacion.setText(aukera.getInformation());
		btnElimActi.setDisable(false);
		btnVer.setDisable(false);
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        listaActividadesGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
 
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				setPrimaryStage(primaryStage);

				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/listaActividades.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
		               
		        primaryStage.setScene(scene);
				primaryStage.setTitle("Añadir un nuevo socio");
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	   
    }
    @FXML protected void initialize(){
    	
    }
}

