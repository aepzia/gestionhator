package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class datosAsociacion extends Application {
	
	@FXML private Button btnAtras;
	
	
	private static Stage pStage;

	
	@FXML private void atras(){
		menuPrincipal preWindow = new menuPrincipal();
		preWindow.start(getPrimaryStage());
	}
	
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        datosAsociacion.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
            Parent page;
			try {
				setPrimaryStage(primaryStage);
				page = FXMLLoader.load(getClass().getResource("../itxura/datosAsociacion.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Datos de la asociación");
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
}

