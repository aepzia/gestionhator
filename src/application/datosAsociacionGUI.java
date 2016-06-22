package application;


import java.io.IOException;
import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class datosAsociacionGUI extends Application{

	@FXML private Button btnAtras;
	@FXML private Button btnEditar;
	@FXML private TextField textNombre;
	@FXML private TextField textTel1;
	@FXML private TextField textTel2;
	@FXML private TextField textBanco1;
	@FXML private TextField textBanco2;
	@FXML private TextField textBanco3;
	@FXML private TextField textBanco4;
	@FXML private TextArea textDireccion;
	@FXML private TextField textPresi;
	@FXML private TextField textNumeracion;
	@FXML private ImageView imgLogo;
	@FXML private TextField textCIF;

	private static Stage pStage;

	
	@FXML private void atras(){
		menuPrincipalGUI preWindow = new menuPrincipalGUI();
		preWindow.start(getPrimaryStage());
	}
	@FXML private void editar(){
		editarAsociacionGUI preWindow = new editarAsociacionGUI();
		preWindow.start(getPrimaryStage());
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        datosAsociacionGUI.pStage = pStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
    	AnchorPane page;
			try {
				setPrimaryStage(primaryStage);
				page = FXMLLoader.load(getClass().getResource("../itxura/datosAsociacion.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());        
		        primaryStage.setScene(scene);
				primaryStage.setTitle("Datos de la asociación");
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
          
    }
    
    
    @FXML protected void initialize(){

    		try
            {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Asoziazioko_datuak/database.mdb;memory=false");

        		
        		Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM asociacion");
           
                while ( rs.next() )
                {
                	textNombre.setText(rs.getObject(2).toString());
                	textTel1.setText(rs.getObject(3).toString());
                	textTel2.setText(rs.getObject(4).toString());
                	//TODO ... bete
                }
    		
        	
            } catch ( Exception e )
            {
    			e.printStackTrace();
            }
    	
    }

}

