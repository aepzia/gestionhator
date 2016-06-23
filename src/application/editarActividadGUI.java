package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editarActividadGUI extends Application {
	
	@FXML ChoiceBox<String> btnTipo;
	@FXML ChoiceBox<String> btnBergiralea;
	@FXML Button btnAtras;
	@FXML TextField textPrezioa;
	@FXML TextField textPlazaKop;
	@FXML TextField textOrHasi;
	@FXML TextField textOrBuk;
	
	
	private static Stage pStage;
	
	@FXML private void atras(){
			//TODO ver asocioacion
	}
	@FXML private void guardar(){
		//TODO Ekintzako datuak datu basean gorde
		
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        editarActividadGUI.pStage = pStage;
    }   
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
            Parent page;
			try {
				setPrimaryStage(primaryStage);
				page = FXMLLoader.load(getClass().getResource("../itxura/editarActividad.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Editar ascoiación");
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
           
    }
    @FXML
    protected void initialize(){
    	System.out.println("IHFDU");
    	//TODO datu basetik hartu eta datuak idatzi 
 
    	
    }
}

