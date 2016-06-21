package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class verActividadGUI extends Application {
	

	@FXML Button btnAtras;
	@FXML Button btnEditar;
	@FXML TextField textPrezioa;
	@FXML TextField textPlazaKop;
	@FXML TextField textOrHasi;
	@FXML TextField textOrBuk;
	@FXML TextField textHasiData;
	@FXML TextField textBukData;
	
	
	private static Stage pStage;
	
	@FXML private void atras(){
		listaActividadesGUI w = new listaActividadesGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void editar(){
		editarActividadGUI w = new editarActividadGUI();
		w.start(getPrimaryStage());
		
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
       verActividadGUI.pStage = pStage;
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

