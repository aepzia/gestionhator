package application;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class sociosTodosGUI extends Application {
	
	@FXML private Button btnAtras;
	@FXML private Button btnAñadirSocio;
	@FXML private Button btnElimSocio;
	@FXML private Button btnPagos;
	@FXML private Button btnVerSocio;
	@FXML private TextField textAsociacion;
	@FXML private ListView<Socio> listSocios;
	@FXML private TextArea textInformazioa;
	
	private static Socio aukera;
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
		Controler.autatutakoBazkidea=aukera;
		verSocioGUI preWindow = new verSocioGUI();
		//preWindow.setSocio(aukera);
		preWindow.start(getPrimaryStage());
	}
	@FXML private void informazioaJarri(){
		aukera = listSocios.getSelectionModel().getSelectedItem();
		textInformazioa.setText(aukera.lortuInformazioa());
		btnElimSocio.setDisable(false);
		btnVerSocio.setDisable(false);
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
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
    }
    @FXML protected void initialize(){
    	Socio socio1 = new Socio();
    	socio1.setIzena("Aizpea");
    	socio1.setAbizena("Babaze Aizpurua");
    	socio1.setnSocio(250);
    	Socio socio2 = new Socio();
    	socio2.setIzena("Aizpea");
    	socio2.setAbizena("yewtureytiruyt");
    	socio2.setnSocio(251);
    	
    	List<Socio> values = Arrays.asList(socio1,socio2);
    	listSocios.setItems(FXCollections.observableList(values));
    	//TODO datu basetik hartu eta datuak idatzi 
 
    	
    }
}

