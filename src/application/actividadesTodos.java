package application;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class actividadesTodos extends Application {
	
	
    public static void main(String[] args) {
        launch(args);
    }
 
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/actividadesTodos.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
		               
		        primaryStage.setScene(scene);
				primaryStage.setTitle("A�adir un nuevo socio");
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
}

