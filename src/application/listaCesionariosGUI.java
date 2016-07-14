package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class listaCesionariosGUI extends Application {
	
	@FXML private Button btnAtras;
	@FXML private Button btnAnadirMoni;
	@FXML private Button btnElimMoni;
	@FXML private Button btnPagos;
	@FXML private Button btnVerSocio;
	@FXML private TextField textAsociacion;
	@FXML private ListView<Monitor> listMonitores;
	@FXML private ChoiceBox<String> btnOrderBy;
	@FXML private TextField textBuscar;
	@FXML private TextArea textEmpresa;
	@FXML private TextArea textProfesional;
	
	private static ObservableList<Monitor> lag ;
	private static Stage pStage;
	
	@FXML private void atras(){
		
	}
	@FXML private void nuevoMoni(){
	
	}
	@FXML private void elimMoni(){
		
	}
	
	
	@FXML private void informazioaJarri(){
		btnElimMoni.setDisable(false);
		textEmpresa.setText(listMonitores.getSelectionModel().getSelectedItem().getInformacionEmpresa());
		textProfesional.setText(listMonitores.getSelectionModel().getSelectedItem().getInformacionProfesional());

	}
	
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
    	listaCesionariosGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				setPrimaryStage(primaryStage);

				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/listaCesionarios.fxml"));
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
    		Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM profesional";
			ResultSet rs =  stmt.executeQuery(sql);
			while ( rs.next() )
            {
				Monitor m = new Monitor(rs);
				listMonitores.getItems().add(m);

            }
			lag = listMonitores.getItems();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	textBuscar.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable,
    	            String oldValue, String newValue) {

    	    	handleSearchByKey(oldValue, (String)newValue);
    	    }
    	});
    	
    }
    public void handleSearchByKey(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            listMonitores.setItems( lag );
        }
         
        // Break out all of the parts of the search text 
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");
 
        // Filter out the entries that don't contain the entered text
        ObservableList<Monitor> subentries = FXCollections.observableArrayList();
        for ( Monitor entry: listMonitores.getItems() ) {
            boolean match = true;
            String entryText = entry.toString();
            for ( String part: parts ) {
                // The entry needs to contain all portions of the
                // search string *but* in any order
                if ( ! entryText.toUpperCase().contains(part) ) {
                    match = false;
                    break;
                }
            }
 
            if ( match ) {
                subentries.add(entry);
            }
        }
        listMonitores.setItems(subentries);
    }
 
}


