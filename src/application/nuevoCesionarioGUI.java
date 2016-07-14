package application;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class nuevoCesionarioGUI extends Application {
	
	@FXML TextField textEmpresa;
	@FXML TextField textCIF;
	@FXML TextField textTel1;
	@FXML TextField textDirec;
	@FXML TextField textNombre;
	@FXML TextField textNAN;
	@FXML TextField textTel2;
	@FXML CheckBox chEqual;
	@FXML Button btnAtras;
	@FXML Button btnGuardar;
	

	private static Stage pStage;

	@FXML public void guardar(){
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:h2:C:\\Asoziazioko_datuak\\datuBasea", "", "" );
			Statement stmt = conn.createStatement();
			//TODO monitore clasea sortzean, onen DNIa gordeko da.
			String sql = "INSERT INTO cesionario "
					+ "VALUES("
	        		/*+ "'"+textEmpresa.getText()+"',"
	        		+ "'"+textDirec.getText()+"',"
	        		+ "'"+textTel1.getText()+"',"
	        		+ "'"+textNombre.getText()+"',"
	        		+ "'"+textTel2.getText()+"',"
	        		+ "'"+textNAN.getText()+"',"
	        		+ "'"+textCIF.getText()*/
	        		+ "')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
	        stmt.close();    	
	        conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@FXML public void idatziBerdin(){
		if(chEqual.isSelected()){
			textCIF.setText(textNAN.getText());
			textEmpresa.setText(textNombre.getText());
			textTel1.setText(textTel2.getText());
		}else{
			textCIF.setText("");
			textEmpresa.setText("");
			textTel1.setText("");
		}
	}
	@FXML public void atras(){
		listaCesionariosGUI w = new listaCesionariosGUI();
		w.start(getPrimaryStage());
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        nuevoCesionarioGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				setPrimaryStage(primaryStage);

				page = (AnchorPane) FXMLLoader.load(getClass().getResource("../itxura/nuevoCesionario.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
		        
		        primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
    }
    @FXML protected void initialize(){

    	
    }
    
 
}


