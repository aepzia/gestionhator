package application;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class sociosTodosGUI extends Application {
	
	@FXML private Button btnAtras;
	
	
	private static Stage pStage;

	
	@FXML private void atras(){
		menuPrincipalGUI preWindow = new menuPrincipalGUI();
		preWindow.start(getPrimaryStage());
	}
	@FXML private void alta(){
		nuevoSocioGUI preWindow = new nuevoSocioGUI();
		preWindow.start(getPrimaryStage());
	}
	@FXML private void baja(){
		//TODO baja lehioa
	}
	@FXML private void deudas(){
		//TODO zorren lehioa ikusi
	}
	@FXML private void verSocio(){
		nuevoSocioGUI preWindow = new nuevoSocioGUI();
		preWindow.start(getPrimaryStage());
	}
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        sociosTodosGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				setPrimaryStage(primaryStage);

				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/sociosTodos.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
		               
		        primaryStage.setScene(scene);
				primaryStage.setTitle("Añadir un nuevo socio");
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
    }
    @FXML
    protected void initialize(){
    	System.out.println("IHFDU");
    	//TODO datu basetik hartu eta datuak idatzi 
 
    	
    }
}

