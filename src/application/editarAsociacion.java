package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class editarAsociacion extends Application {
	

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
            Parent page;
			try {
				page = FXMLLoader.load(getClass().getResource("editarAsociacion.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Editar ascoiaci�n");
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
}
