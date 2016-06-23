package application;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class editarAsociacionGUI extends Application {
	
	@FXML private Button btnAtras;
	@FXML private Button btnGuardar;
	@FXML private Button btnExaminar;
	@FXML private TextField textNombre;
	@FXML private TextField textTel1;
	@FXML private TextField textTel2;
	@FXML private TextField textBanco1;
	@FXML private TextField textBanco2;
	@FXML private TextField textBanco3;
	@FXML private TextField textBanco4;
	@FXML private TextArea textDireccion;
	@FXML private TextField textPresi;
	@FXML private ChoiceBox<String> textNumeracion;
	@FXML private ImageView imgLogo;
	@FXML private Label mezua; 
	@FXML private TextField textCIF;
	
	private static String imgPath;
	private static Stage pStage;
	
	@FXML private void atras(){
		datosAsociacionGUI preWindow = new datosAsociacionGUI();
		preWindow.start(getPrimaryStage());
	}
	@FXML private void guardar(){
		try
        {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Asoziazioko_datuak/database.mdb;memory=false");
    		Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE asociacion SET izena='"+textNombre.getText()+"', tel1='"+textTel1.getText()+"', tel2='"+textTel2.getText()
                    //TODO +"presidente='"
                    + "', logo='"+imgPath+"', direccion='"+textDireccion.toString()+"', cuenta_corriente='" +
                    textBanco1.getText() + " "+textBanco2.getText()+" "+textBanco3.getText()+" "+textBanco4.getText()+
                    "', numeracion_de_socios='"+textNumeracion.getSelectionModel().getSelectedItem()+"' WHERE id='1'"); //TODO... bete
                	
        } catch ( Exception e )
        {
			e.printStackTrace();
        }
	
		
	}
	@FXML private void examinar(){
		JFileChooser explorador = new JFileChooser("/home/");
		explorador.setFileFilter(new FileNameExtensionFilter("JPG files", "jpg", "jpeg", "png"));
		
		int seleccion = explorador.showDialog(null, "Abrir");
		  
		switch(seleccion) {
		case JFileChooser.APPROVE_OPTION:
		 break;

		case JFileChooser.CANCEL_OPTION:
		 break;

		case JFileChooser.ERROR_OPTION:
		 break;
		}

		File archivo = explorador.getSelectedFile();
	
		String ruta = archivo.getPath().replace("\\", "/");
		System.out.println(ruta);
		imgPath ="file:"+ruta;
		imgLogo.setImage(new Image(imgPath));
		
		//TODO imagena edo ruta gorde.
		
	
	}
	
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        editarAsociacionGUI.pStage = pStage;
    }   
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
            Parent page;
			try {
				setPrimaryStage(primaryStage);
				page = FXMLLoader.load(getClass().getResource("../itxura/editarAsociacion.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Editar ascoiación");
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
           
    }
    @FXML protected void initialize(){


		try
        {
          
    		Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Asoziazioko_datuak/database.mdb;memory=false");

    		
    		Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM asociacion");
       
            while ( rs.next() )
            {
            	textNombre.setText(rs.getObject("izena").toString());
            	textTel1.setText(rs.getObject("tel1").toString());
            	textTel2.setText(rs.getObject("tel2").toString());
            	ResultSet rs2 = stmt.executeQuery("SELECT nombre, apellido1, apellido2 FROM socio WHERE DNI='"+rs.getObject("presidente")+"'");
            	while ( rs2.next() )
                {
            		textPresi.setText(rs2.getObject("nombre").toString()+" "+rs2.getObject("apellido1")+" "+rs2.getObject("apellido2"));
            		
                }
            	imgPath = rs.getObject("logo").toString();
            	imgLogo.setImage(new Image(imgPath));
            	textDireccion.setText(rs.getObject("direccion").toString());
            	String s = rs.getObject("cuenta_corriente").toString();
            	String[] banc = s.split(" ");
            	textBanco1.setText(banc[0]);
            	textBanco2.setText(banc[1]);
            	textBanco3.setText(banc[2]);
            	textBanco4.setText(banc[3]);
            	//textNumeracion.setText(rs.getObject("numeracion_de_socios").toString());
            	textCIF.setText(rs.getObject("CIF").toString());
            }
		
    	
        } catch ( Exception e )
        {
			e.printStackTrace();
        }
    }
}

