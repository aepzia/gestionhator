package application;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class nuevoSocioGUI extends Application {
	
	@FXML public ImageView imgFoto;
	@FXML public TextField textNombre;
	@FXML public TextField textApellido;
	@FXML public TextField textTel1;
	@FXML public TextField textTel2;
	@FXML public TextArea textDireccion;
	@FXML public TextField textNacimiento;
	@FXML public TextField textAlta;
	@FXML public TextField textDNI;
	@FXML public TextField textEmail;
	@FXML public TextField textTelEm;
	@FXML public TextArea textOtros;
	@FXML public TextField textBanco1;
	@FXML public TextField textBanco2;
	@FXML public TextField textBanco3;
	@FXML public TextField textBanco4;
	@FXML public ChoiceBox<String> opTipo;
	@FXML public ChoiceBox<String> opSexo;
	@FXML public ChoiceBox<String> opPensionista;
	@FXML public ChoiceBox<String> opNum;
	@FXML public Button btnExaminarFoto;
	@FXML public Button btnExaminarLOPD;
	@FXML public Button btnVer;
	@FXML public Button btnAtras;
	@FXML public Button btnGuardar;
	
	private static String imgPath;
	private static String pdfPath;
	private static Stage pStage;

	@FXML private void atras(){
		listaSociosGUI w = new listaSociosGUI();
		w.start(getPrimaryStage());
	}
	@FXML private void guardar(){
		try
        {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Asoziazioko_datuak/database.mdb;memory=false");
    		Statement stmt = conn.createStatement();
    		boolean pensionista;
    		if(opPensionista.getSelectionModel().getSelectedIndex() ==1) pensionista = true;
    		else pensionista=false;
            stmt.executeUpdate("INSERT INTO socio VALUES"
            		+opNum.getSelectionModel().getSelectedItem()
            		+","+textDNI.getText()+","+pensionista+","+textNombre.toString()
            		+","+textApellido.getText()+","+ null +","+ opTipo.getSelectionModel().getSelectedItem()
            		+","+opSexo.getSelectionModel().getSelectedItem()+
            		","+textDireccion.getText()+","+textTel1.getText()+
            		","+textTel2.getText()+","+textTelEm.getText()
            		+","+textEmail.getText()+","+null+","+null+","+imgPath);
                	
        } catch ( Exception e )
        {
			e.printStackTrace();
        }
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        nuevoSocioGUI.pStage = pStage;
    }   
    
    public static void main(String[] args) {
        launch(args);
    }

    
    @Override public void start(Stage primaryStage){
    	Parent page;
			try {
				setPrimaryStage(primaryStage);

				page = FXMLLoader.load(getClass().getResource("../itxura/nuevoSocio.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
					               
		        primaryStage.setScene(scene);
				primaryStage.setTitle("Añadir un nuevo socio");
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
    @FXML protected void initialize(){
    	
    }
}

