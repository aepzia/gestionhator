package application;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class listaActividadesGUI extends Application {
	

	
	
	private static Stage pStage;
	private static Actividad aukera;
	private static ObservableList<Actividad> lag ;

	
	@FXML ListView<Actividad> listActividad;
	@FXML TextField textAsociacion;
	@FXML TextArea textInformacion;
	@FXML Button btnAñadirActi;
	@FXML Button btnElimActi;
	@FXML Button btnPagos;
	@FXML Button btnVer;
	@FXML TextField textBuscar;
	@FXML Button btnAtras;
	@FXML ChoiceBox<String> btnOrderBy;


	
	@FXML private void atras(){
		menuPrincipalGUI preWindow = new menuPrincipalGUI();
		preWindow.start(getPrimaryStage());
	}
	@FXML private void nuevaActividad(){
		nuevaActividadGUI w = new nuevaActividadGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void eliminarActividad(){
		//TODO eliminar
	}
	@FXML private void verActividad(){
		//TODO actividadea ikusi
	}


	@FXML private void informazioaJarri(){
		aukera = listActividad.getSelectionModel().getSelectedItem();
		textInformacion.setText(aukera.getInformation());
		btnElimActi.setDisable(false);
		btnVer.setDisable(false);
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        listaActividadesGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
 
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				setPrimaryStage(primaryStage);

				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/listaActividades.fxml"));
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
			String sql = "SELECT * FROM actividad";
			ResultSet rs =  stmt.executeQuery(sql);
			while ( rs.next() )
            {
				Actividad s = new Actividad(rs);
				listActividad.getItems().add(s);

            }
			lag = listActividad.getItems();
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
            listActividad.setItems( lag );
        }
         
        // Break out all of the parts of the search text 
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");
 
        // Filter out the entries that don't contain the entered text
        ObservableList<Actividad> subentries = FXCollections.observableArrayList();
        for ( Actividad entry: listActividad.getItems() ) {
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
        listActividad.setItems(subentries);
    }
}

