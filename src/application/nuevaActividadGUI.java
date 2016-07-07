package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hsqldb.HsqlException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class nuevaActividadGUI extends Application {
	
	@FXML ChoiceBox<String> btnTipo;
	@FXML ChoiceBox<String> btnBegiralea;
	@FXML ChoiceBox<String> hasiOr;
	@FXML ChoiceBox<String> hasiMin;
	@FXML ChoiceBox<String> bukOr;
	@FXML ChoiceBox<String> bukMin;
	@FXML Button btnAtras;
	@FXML Button btnGuardar;
	@FXML TextField textPrezioa;
	@FXML TextField textPlazaKop;
	@FXML DatePicker hasData;
	@FXML DatePicker bukData;
	@FXML TextField textNombre;
	
	private static Stage pStage;
	
	@FXML private void atras(){
		listaActividadesGUI w = new listaActividadesGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void guardar(){
		//TODO Ekintzako datuak datu basean gorde
		String currentData = "";
		try
        {
			String horaIni = hasiOr.getSelectionModel().getSelectedItem()+":"+ hasiMin.getSelectionModel().getSelectedItem();
			String horaFin = bukOr.getSelectionModel().getSelectedItem()+":"+ bukMin.getSelectionModel().getSelectedItem();
			Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
    		Statement stmt = conn.createStatement();
    		int id = Controler.actiKop +1;
    		//TODO monitore clasea sortzean, onen DNIa gordeko da.
    		String sql = "INSERT INTO actividad (id,tpActividad,monitorDNI,fechaIni,fechaFin,horaIni,horaFin,nombre,numero_de_plazas,precio)"
    				+ "VALUES('"
            		+ id
            		+"','"+btnTipo.getSelectionModel().getSelectedItem()+"','"+btnBegiralea.getSelectionModel().getSelectedItem()+"','"+hasData.getValue()
            		+"','"+bukData.getValue()+"','"+horaIni +"','"+horaFin
            		+"','"+textNombre.getText()+
            		"','"+textPlazaKop.getText()+"','"+textPrezioa.getText()+
            		"')";
    		System.out.println(bukData.getValue());
    		System.out.println(sql);
    		stmt.executeUpdate(sql);
            stmt.close();    	
            conn.close();
        
        } 
		catch ( Exception e ){
			e.printStackTrace();
			if (e instanceof NullPointerException){
				//TODO sartutako datu hutsak tratatu
			}
			if(e instanceof HsqlException){
				//TODO DNI errepikatua
			}
        }		

		System.out.println("gorde da");
	
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
       nuevaActividadGUI.pStage = pStage;
    }   
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
            Parent page;
			try {
				setPrimaryStage(primaryStage);
				page = FXMLLoader.load(getClass().getResource("../itxura/nuevaActividad.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Nueva actividad");
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
           
    }
    @FXML protected void initialize(){
    	//TODO monitore aukera datu basetik osatu 
    	
    	
    }
}

