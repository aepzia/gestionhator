package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    @FXML public TextField textPension;
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
    
	private static Stage pStage;
    private static Socio bazkidea;
    
    
    @FXML public void verLOPD(){
    	//TODO Datu basetik hartu pdfaren helbidea
    	File pdfFile = new File("C:\\Users\\standar\\Desktop\\musikaEskola.pdf");
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
    
    public static void main(String[] args) {
        launch(args);
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
    	//imgFoto.setImage(bazkidea.getFoto());
    	textNombre.setText(bazkidea.getIzena());
    	/*textApellido.setText(bazkidea.getAbizena());
    	textDNI.setText(bazkidea.getDNI());
    	textBtype.setText(bazkidea.getTpSocio());
    	textSexo.setText(bazkidea.getSexo());
    	int n = bazkidea.getnSocio();
    	textNum.setText(Integer.toString(n));
    	if (bazkidea.isPensionista()) textPension.setText("BAI/SI");
    	else textPension.setText("EZ/NO");
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateNacimiento = formatter.format(bazkidea.getFechaNacimiento());
    	textJaioteguna.setText(dateNacimiento);
    	String dateAlta= formatter.format(bazkidea.getFechaAlta());
    	textJaioteguna.setText(dateAlta);
    	textTel1.setText(bazkidea.getTel1());
    	textTel2.setText(bazkidea.getTel2());
    	textTelEmergencia.setText(bazkidea.getTelEmergencia());
    	textBanco.setText(bazkidea.getCuentaCorriente());
    	textDireccion.setText(bazkidea.getHelbidea());
    	textOtros.setText(bazkidea.getComentarios());
    	*/
    	
    	
    }

}

