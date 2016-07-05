package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class listaSociosGUI extends Application {
	
	@FXML private Button btnAtras;
	@FXML private Button btnAnadirSocio;
	@FXML private Button btnElimSocio;
	@FXML private Button btnPagos;
	@FXML private Button btnVerSocio;
	@FXML private TextField textAsociacion;
	@FXML private ListView<Socio> listSocios;
	@FXML private TextArea textInformazioa;
	@FXML private ChoiceBox btnOrderBy;
	
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
        listaSociosGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				setPrimaryStage(primaryStage);

				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/listaSocios.fxml"));
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
    @FXML protected void initialize(){

    	try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Asoziazioko_datuak/database.mdb;memory=false");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM socio";
			ResultSet rs =  stmt.executeQuery(sql);
			Controler.socioKop =1;
			while ( rs.next() )
            {
				Controler.socioKop++; 
				Socio s = new Socio(rs);
				sql = "SELECT  * FROM socioActividad where socioApuntado='"+rs.getObject("DNI")+"' AND pagado='false'";
				ResultSet rs2 =  stmt.executeQuery(sql);
				if (rs2 !=null) {
					
				}
				listSocios.getItems().add(s);

            }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    	
    }
}


