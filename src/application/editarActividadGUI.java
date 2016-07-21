package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

public class editarActividadGUI extends Application {
	
	@FXML ChoiceBox<String> btnTipo;
	@FXML ChoiceBox<Monitor> btnBegiralea;
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
	private static Actividad aukera;
	@FXML private void atras(){
		listaActividadesGUI w = new listaActividadesGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void guardar(){
		try
        {
			aukera = Controler.autatutakoEkintza;
			String horaIni = hasiOr.getSelectionModel().getSelectedItem()+":"+ hasiMin.getSelectionModel().getSelectedItem();
			String horaFin = bukOr.getSelectionModel().getSelectedItem()+":"+ bukMin.getSelectionModel().getSelectedItem();
			Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
    		Statement stmt = conn.createStatement();
    		int id = Controler.actiKop +1;
    		//TODO monitore clasea sortzean, onen DNIa gordeko da.
    		String sql = "UPDATE actividad SET "
    				+ "id='"
            		+ id
            		+"',tpActividad='"+btnTipo.getSelectionModel().getSelectedItem()+"',monitorDNI='"+btnBegiralea.getSelectionModel().getSelectedItem()+"',fechaIni='"+hasData.getValue()
            		+"',fechaFin='"+bukData.getValue()+"',horaIni='"+horaIni +"',horaFin='"+horaFin
            		+"',nombre='"+textNombre.getText()+
            		"',numero_de_plazas='"+textPlazaKop.getText()+"',precio='"+textPrezioa.getText() + "' WHERE id='"+aukera.getId()+"'";
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
       editarActividadGUI.pStage = pStage;
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
    	try {
    		Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM profesional";
			ResultSet rs =  stmt.executeQuery(sql);
			while ( rs.next() )
            {
				Monitor m = new Monitor(rs);
				btnBegiralea.getItems().add(m);

            }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//TODO datu guztiak ezarri pantailan
    	
    	
    }
}

