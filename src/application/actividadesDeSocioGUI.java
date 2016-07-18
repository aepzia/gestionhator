package application;




import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ListCell;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
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

	@FXML  ListView<String> listPagos;

	@FXML private void atras(){
		verSocioGUI preWindow = new verSocioGUI();
		preWindow.start(getPrimaryStage());
	}
	@FXML private void nuevaActividad() throws Exception{
		asignarActividadGUI w = new asignarActividadGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void eliminarActividad(){
		TextInputDialog dialog = new TextInputDialog(listActividad.getSelectionModel().getSelectedItem().getPrecio());
		dialog.setTitle("Despuntar socio de la actividad");
		dialog.setHeaderText("Despuntar socio de la actividad");
		dialog.setContentText("Dinero devuelto:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    try {
				//TODO result.get() -> libro diario
		    	//TODO inprimir devolucion
				Class.forName("org.h2.Driver");
				Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
				Statement stmt = conn.createStatement();
				String sql = "DELETE FROM socioActividad  WHERE actividadId='"+aukera.getId()+"' AND socioApuntado='"+bazkidea.getDNI()+"'";
				System.out.println(sql);
				stmt.executeUpdate(sql);
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
	
		
	}
	@FXML private void eguneratu(){
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
			Statement stmt = conn.createStatement();
			String sql = "UPDATE socioActividad SET pagado='true' WHERE actividadId='"+aukera.getId()+"' AND socioApuntado='"+bazkidea.getDNI()+"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			listPagos.getItems().set(listActividad.getSelectionModel().getSelectedIndex(), "true");
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
		if(aukera.pagatua=="No / Ez") btnPagado.setDisable(false);
		btnElimActi.setDisable(false);
		
		textInformacion.setText(aukera.getInformation()+"\nPagatua? / ¿Pagado? "+aukera.pagatua );
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
					if((Boolean) rs.getObject("pagado")) s.pagatua="Si / Bai";
					else s.pagatua = "No / Ez";
					listActividad.getItems().add(s);
            }	
			lag = listActividad.getItems();
			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	listActividad.setCellFactory(new Callback<ListView<Actividad>, 
                ListCell<Actividad>>() {
                    @Override 
                    public ListCell<Actividad> call(ListView<Actividad> list) {
                        return new ColorRectCell();
                    }
                }
            );
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
    static class ColorRectCell extends ListCell<Actividad> {
        int i = 0;
    	@Override
        
        public void updateItem(Actividad item, boolean empty) {
            super.updateItem(item, empty);
 

            Text rect = new Text();
            if (item != null) {
            	if (item.pagatua=="No / Ez")
                rect.setFill(Color.web("red"));
                rect.setText(item.toString());
                setGraphic(rect);
            }
        }
    }
    
   
}

