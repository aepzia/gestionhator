package application;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	@FXML public CheckBox opPensionista;
	@FXML public ChoiceBox<Integer> opNum;
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
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = new Date();
			String NULL2 = "s";
			String NULL1 =formatoDeFecha.format(fecha);
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

    		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Asoziazioko_datuak/database.mdb;memory=false");
    		Statement stmt = conn.createStatement();
    		String banco = textBanco1.getText()+"_"+textBanco2.getText()+"_"+textBanco3.getText()+"_"+textBanco4.getText();
    		String sql = "INSERT INTO socio VALUES("
            		+opNum.getSelectionModel().getSelectedItem()
            		+",'"+textDNI.getText()+"',"+opPensionista.isSelected()+",'"+textNombre.getText()
            		+"','"+textApellido.getText()+"','"+ NULL1 +"','"+ opTipo.getSelectionModel().getSelectedItem()
            		+"','"+opSexo.getSelectionModel().getSelectedItem()+
            		"','"+textDireccion.getText()+"','"+textTel1.getText()+
            		"','"+textTel2.getText()+"','"+textTelEm.getText()
            		+"','"+textEmail.getText()+"','"+NULL1+"','"+NULL1+"','"+NULL2+"','"+NULL2+"','"+banco+"','"+textOtros.getText()+"','"+NULL2+"')";
    		System.out.println(sql);
    		stmt.executeUpdate(sql);
            stmt.close();    	
            conn.close();
        } catch ( Exception e )
        {
			e.printStackTrace();
        }
		System.out.println("gorde da");
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
				
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
    @FXML protected void initialize(){
    	opNum.setItems(FXCollections.observableArrayList(
    		    1,2,3)
    		);
    }
}

