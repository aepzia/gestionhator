package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class verSocioGUI extends Application {
	
	@FXML public ImageView imgFoto;
    @FXML public TextField textNombre;
    @FXML public TextField textApellido;
    @FXML public TextField textDNI;
    @FXML public TextField textBtype;
    @FXML public TextField textSexo;
    @FXML public TextField textNum;
    @FXML public CheckBox opPensionista;
    @FXML public TextField textJaioteguna;
    @FXML public TextField textAlta;
    @FXML public TextField textTel1;
    @FXML public TextField textTel2;
    @FXML public TextField textTelEmergencia;
    @FXML public TextField textEmail;
    @FXML public TextField textBanco;
    @FXML public TextArea textDireccion;
    @FXML public TextArea textOtros;
    @FXML public Button btnAtras;
    @FXML public Button btnVerLOPD;
    @FXML public Button btnInprimirFicha;
    @FXML public Button btnInprimirCarnet;
    @FXML public Button btnEditar;
    @FXML public Button btnActividades;
    @FXML public Button btnExaminarFoto;
    @FXML public Button btnExaminarLOPD;


	private static Stage pStage;
    private static Socio bazkidea;
    
    @FXML public void verLOPD(){
    	//TODO Datu basetik hartu pdfaren helbidea
    	File pdfFile = new File(bazkidea.getLOPD());
    	try {
			Desktop.getDesktop().open(pdfFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @FXML public void printFicha(){
    	//TODO inprimatu ficha
    }
    @FXML public void printCarnet(){
    	//TODO inprimatu karneta
    }
    @FXML public void atras(){
    	listaSociosGUI w = new listaSociosGUI();
    	w.start(getPrimaryStage());
    }
    @FXML public void editar(){
    	editarSocioGUI w = new editarSocioGUI();
    	w.start(getPrimaryStage());
    }
    @FXML public void verActividadesSocio(){
    	//TODO datu basetik bazkide honen s
    } 
 
	public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        verSocioGUI.pStage = pStage;
    }
    
    
    @Override
    public void start(Stage primaryStage){
    	AnchorPane  page;
			try {
				setPrimaryStage(primaryStage);
				page =(AnchorPane)  FXMLLoader.load(getClass().getResource("../itxura/verSocio.fxml"));
				Scene scene = new Scene(page);
		        scene.getStylesheets().add(getClass().getResource("../itxura/style.css").toExternalForm());       
		        primaryStage.setScene(scene);
				primaryStage.setTitle("Actividades del socio");
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
           
    }
 

	@FXML protected void initialize(){
		bazkidea = Controler.autatutakoBazkidea;
		//bazkidea = bl.getAutatutakoBazkidea();
		System.out.println(bazkidea);
    	textNombre.setText(bazkidea.getIzena());
    	textApellido.setText(bazkidea.getAbizena());
    	textDNI.setText(bazkidea.getDNI());
    	textBtype.setText(bazkidea.getTpSocio());
    	textSexo.setText(bazkidea.getSexo());
    	textNum.setText(bazkidea.getnSocio());
    	textAlta.setText(bazkidea.getFechaAlta());
    	textEmail.setText(bazkidea.getEmail());
    	opPensionista.setSelected(bazkidea.isPensionista());
    	textJaioteguna.setText(bazkidea.getFechaNacimiento());
    	textJaioteguna.setText(bazkidea.getFechaAlta());
    	textTel1.setText(bazkidea.getTel1());
    	textTel2.setText(bazkidea.getTel2());
    	textTelEmergencia.setText(bazkidea.getTelEmergencia());
    	textBanco.setText(bazkidea.getCuentaCorriente());
    	textDireccion.setText(bazkidea.getHelbidea());
    	textOtros.setText(bazkidea.getComentarios());
    	imgFoto.setImage(bazkidea.getFoto());
    	
    	
    	
    }

}

