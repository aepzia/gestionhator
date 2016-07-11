package application;




import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

public class actividadesDeSocioGUI extends Application {

	private static Stage pStage;
	private static Actividad aukera;
	private static Socio bazkidea;

	private static ObservableList<Actividad> lag ;

	
	@FXML ListView<Actividad> listActividad;
	@FXML TextField textAsociacion;
	@FXML TextArea textInformacion;
	@FXML Button btnAñadirActi;
	@FXML Button btnElimActi;
	@FXML Button btnPagado;
	@FXML TextField textBuscar;
	@FXML Button btnAtras;
	@FXML ChoiceBox<String> btnOrderBy;

	private ListView<Boolean> pagos = new ListView<Boolean>() ;

	@FXML private void atras(){
		verSocioGUI preWindow = new verSocioGUI();
		preWindow.start(getPrimaryStage());
	}
	@FXML private void nuevaActividad() throws Exception{
		asignarActividadGUI w = new asignarActividadGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void eliminarActividad(){
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM socioActividad  WHERE actividadId='"+aukera.getId()+"' AND socioApuntado='"+bazkidea.getDNI()+"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			pagos.getItems().remove(listActividad.getSelectionModel().getSelectedIndex());
			listActividad.getItems().remove(listActividad.getSelectionModel().getSelectedIndex());

			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@FXML private void eguneratu(){
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
			Statement stmt = conn.createStatement();
			String sql = "UPDATE socioActividad SET pagado='true' WHERE actividadId='"+aukera.getId()+"' AND socioApuntado='"+bazkidea.getDNI()+"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			pagos.getItems().set(listActividad.getSelectionModel().getSelectedIndex(), true);
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}


	@FXML private void informazioaJarri(){
		aukera = listActividad.getSelectionModel().getSelectedItem();
		
		btnElimActi.setDisable(false);
		String pagatua = "Bai / Sí";
		if (!pagos.getItems().get(listActividad.getSelectionModel().getSelectedIndex())){
			btnPagado.setDisable(false);
			pagatua = "Ez / No";
		}
		textInformacion.setText(aukera.getInformation()+"\nPagatua? / ¿Pagado? "+pagatua );
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
    	actividadesDeSocioGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
 
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				setPrimaryStage(primaryStage);

				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/actividadesDeSocio.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
		               
		        primaryStage.setScene(scene);
				primaryStage.setTitle("Actividades del socio");
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	   
    }
    @FXML protected void initialize(){
    	try {
    		System.out.println("bai");
    		bazkidea=Controler.autatutakoBazkidea;
    		Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM actividad JOIN socioActividad ON actividad.id = socioActividad.actividadId WHERE socioApuntado='"+bazkidea.getDNI()+"'";
			System.out.println(sql);
			ResultSet rs =  stmt.executeQuery(sql);
			
			while ( rs.next() )
            {	
					Actividad s = new Actividad(rs);
					listActividad.getItems().add(s);
				
				pagos.getItems().add((Boolean) rs.getObject("pagado"));
            }	
			lag = listActividad.getItems();
			conn.close();

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

