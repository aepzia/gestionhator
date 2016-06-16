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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class nuevoSocioGUI extends Application {
	
	
    public static void main(String[] args) {
        launch(args);
    }
    @FXML
    public ImageView imgFoto;
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/editarAñadirSocio.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
				
		        /*
		         * Argazkia aldatzeko
		         * */
		        imgFoto = new ImageView();
		        imgFoto.setLayoutX(309);
		        imgFoto.setLayoutY(13);
		        String img = "http://vignette4.wikia.nocookie.net/mrmen/images/5/52/Small.gif/revision/latest?cb=20100731114437";
		        imgFoto.setImage(new Image(img, 140, 140, true, true));
		        page.getChildren().add(imgFoto);
		               
		        primaryStage.setScene(scene);
				primaryStage.setTitle("Añadir un nuevo socio");
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
}

