package application;

import java.io.File;
import java.io.IOException;

import javafx.scene.image.Image;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class nuevoSocio extends Application {
	@FXML
    private ImageView imgFoto;
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
            Parent page;
			try {
				
				page = FXMLLoader.load(getClass().getResource("editarAñadirSocio.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		        File file = new File("perfil.png");
		        Image image = new Image(file.toURI().toString());
		        imgFoto = new ImageView(image);
		        primaryStage.setScene(scene);
				primaryStage.setTitle("FXML is Simple");
				
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
}

