package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class darBajaGUI extends Application {
	
	@FXML Label textIzena;
	@FXML Label textAbizena;
	@FXML Label textDNI;
	@FXML ChoiceBox<String> chMotivo;
	@FXML ImageView imgFoto;
	
	private Socio bazkidea;
	private static Stage pStage;
	
	
	@FXML public void gorde(){
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		String currentData =formatoDeFecha.format(fecha);
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE socio SET fechaBaja='"+currentData+"',motivoBaja='"+ chMotivo.getSelectionModel().getSelectedItem()+"' WHERE DNI='"+bazkidea.getDNI()+"'");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	@FXML public void atras(){
		listaSociosGUI w = new listaSociosGUI();
		w.start(getPrimaryStage());
	}
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        darBajaGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				setPrimaryStage(primaryStage);

				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/darBaja.fxml"));
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
    	bazkidea = Controler.autatutakoBazkidea;
    	textIzena.setText(bazkidea.getIzena());
    	textAbizena.setText(bazkidea.getAbizena());
    	textDNI.setText(bazkidea.getDNI());
    	
		//imgFoto.setImage((new Image(bazkidea.getFoto())));		
	
    	System.out.println(bazkidea.getFoto());
    	
	}
	
}


