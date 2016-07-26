package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import application.actividadesDeSocioGUI.ColorRectCell;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class asignarActividadGUI extends Application {
	
	@FXML Button btnAtras;
	@FXML Button btnGuardar;
	@FXML ListView<Actividad> listActividades;
	@FXML TextArea textInformacion;
	@FXML TextField textBuscar;
	@FXML CheckBox chPagado;
	
	Actividad aukera;
	private static Stage pStage;
	Socio bazkidea;
	private static ObservableList<Actividad> lag ;

	@FXML private void informazioaJarri(){
		aukera = listActividades.getSelectionModel().getSelectedItem();
		textInformacion.setText(aukera.getInformation());
		btnGuardar.setDisable(false);
	}
	@FXML private void gorde(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Asignar actividad a socio");
		alert.setHeaderText("¿Ha pagado el socio la Actividad?");
		alert.setContentText("Elige una opcion.");

		ButtonType buttonTypeSi = new ButtonType("Si");
		ButtonType buttonTypeNo = new ButtonType("No");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeSi, buttonTypeNo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeCancel){
			
		} else{
			Boolean pagatua;
			if (result.get() == buttonTypeSi) {
				 pagatua = true;
			}else {
				 pagatua = false;
			}
			try {
				Class.forName("org.h2.Driver");
				 Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
					Statement stmt = conn.createStatement();
					String sql = "INSERT INTO socioActividad VALUES ('"+aukera.getId()+"','"+bazkidea.getDNI()+"','"+pagatua+"')";
					System.out.println(sql);
					stmt.executeUpdate(sql);
					int plazas = Integer.parseInt(aukera.getNumeroDisponibles())-1;
					sql = "update actividad set numero_disponibles='"+plazas+"' where id='"+aukera.getId()+"'";
					listActividades.getItems().remove(listActividades.getSelectionModel().getSelectedIndex());	
					
					conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
       
	}
	@FXML private void atras(){
		actividadesDeSocioGUI w = new actividadesDeSocioGUI();
		w.start(getPrimaryStage());
	}
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
    	asignarActividadGUI.pStage = pStage;
    }
	@Override
	public void start(Stage primaryStage){
		AnchorPane page;
		try {
			setPrimaryStage(primaryStage);

			page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/asignarActividad.fxml"));
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
	    		bazkidea = Controler.autatutakoBazkidea;
	    		Class.forName("org.h2.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
				Statement stmt = conn.createStatement();
				String sql = "SELECT * FROM actividad WHERE NOT EXISTS(SELECT * FROM socioActividad WHERE actividad.id = socioActividad.actividadId AND socioApuntado='"+bazkidea.getDNI()+"')";
				ResultSet rs =  stmt.executeQuery(sql);
				while (rs.next()){
					Actividad ac = new Actividad(rs);
					listActividades.getItems().add(ac);
				}
				lag = listActividades.getItems();
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
	    	listActividades.setCellFactory(new Callback<ListView<Actividad>, 
	                ListCell<Actividad>>() {
	                    @Override 
	                    public ListCell<Actividad> call(ListView<Actividad> list) {
	                        return new ColorRectCell();
	                    }
	                }
	            );
	    }
	    public void handleSearchByKey(String oldVal, String newVal) {
	        // If the number of characters in the text box is less than last time
	        // it must be because the user pressed delete
	        if ( oldVal != null && (newVal.length() < oldVal.length()) ) {
	            // Restore the lists original set of entries 
	            // and start from the beginning
	            listActividades.setItems( lag );
	        }
	         
	        // Break out all of the parts of the search text 
	        // by splitting on white space
	        String[] parts = newVal.toUpperCase().split(" ");
	 
	        // Filter out the entries that don't contain the entered text
	        ObservableList<Actividad> subentries = FXCollections.observableArrayList();
	        for ( Actividad entry: listActividades.getItems() ) {
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
	        listActividades.setItems(subentries);
	    }
	    
	    static class ColorRectCell extends ListCell<Actividad> {
	        int i = 0;
	    	@Override
	        
	        public void updateItem(Actividad item, boolean empty) {
	            super.updateItem(item, empty);
	 
	            
	            Text rect = new Text();
	            if (item != null) {
	            	if (Integer.parseInt(item.getNumeroDisponibles())<=0)
	                rect.setFill(Color.web("Yellow"));
	                rect.setText(item.toString());
	                setGraphic(rect);
	            }
	        }
	    }
}
