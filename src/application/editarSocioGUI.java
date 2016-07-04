package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class editarSocioGUI extends Application {
	

    public static void main(String[] args) {
        launch(args);
    }
    @FXML
    public ImageView imgFoto;
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane  page;
			try {
				page = (AnchorPane)  FXMLLoader.load(getClass().getResource("../itxura/editarSocio.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
		
		        primaryStage.setScene(scene);
				primaryStage.setTitle("FXML is Simple");
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
}

