package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	@FXML private ChoiceBox<String> btnOrderBy;
	@FXML private TextField textBuscar;
	private static ObservableList<Socio> lag ;
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
    		Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM socio";
			ResultSet rs =  stmt.executeQuery(sql);
			Controler.socioKop=1;
			while ( rs.next() )
            {
				Controler.socioKop++; 
				Socio s = new Socio(rs);
				listSocios.getItems().add(s);

            }
			lag = listSocios.getItems();
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
    	btnOrderBy.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
              ordenatu();
            }    
        });
    }
    public void handleSearchByKey(String oldVal, String newVal) {
        // If the number of characters in the text box is less than last time
        // it must be because the user pressed delete
        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
            // Restore the lists original set of entries 
            // and start from the beginning
            listSocios.setItems( lag );
        }
         
        // Break out all of the parts of the search text 
        // by splitting on white space
        String[] parts = newVal.toUpperCase().split(" ");
 
        // Filter out the entries that don't contain the entered text
        ObservableList<Socio> subentries = FXCollections.observableArrayList();
        for ( Socio entry: listSocios.getItems() ) {
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
        listSocios.setItems(subentries);
    }
    Comparator<? super Socio> comparatorSocio_byNumber = new Comparator<Socio>() {
        @Override
        public int compare(Socio s1, Socio s2) {
            return Integer.parseInt(s1.getnSocio())-Integer.parseInt(s2.getnSocio());
        }
    };
    Comparator<? super Socio> comparatorSocio_byApellido = new Comparator<Socio>() {
        @Override
        public int compare(Socio s1, Socio s2) {
        	 return s1.getAbizena().compareTo(s2.getAbizena());        }
    };
    private void ordenatu(){
    	if(btnOrderBy.getSelectionModel().getSelectedIndex()==0)
    	  listSocios.getItems().sort(comparatorSocio_byNumber);
    	if(btnOrderBy.getSelectionModel().getSelectedIndex()==1)
      	  listSocios.getItems().sort(comparatorSocio_byApellido);
	}
	
}


