package application;

import javafx.scene.image.Image;

public class Asociacion {
	private String nombre;
	private Image logo;
	private String direccion;
	private String tel1;
	private String tel2;
	private String presi;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Image getLogo() {
		return logo;
	}
	public void setLogo(Image logo) {
		this.logo = logo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getPresi() {
		return presi;
	}
	public void setPresi(String presi) {
		this.presi = presi;
	}
}
