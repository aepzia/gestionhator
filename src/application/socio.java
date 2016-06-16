package application;

import java.util.Date;
import java.util.List;


import javafx.scene.image.Image;

public class socio {
	private int nSocio;
	private String DNI;
	private boolean pensionista;
	private String izena;
	private String abizena;
	private Date fechaNacimiento;
	private String tpSocio;
	private char sexo;
	private Image foto;
	private String helbidea;
	private String tel1;
	private String tel2;
	private String telEmergencia;
	private String email;
	private Date fechaAlta;
	private Date fechaBaja;
	private String motivoBaja;
	public List<String> actividades;
	public List<Boolean> pagos;
	public boolean baja;
	public boolean cuotaActualPago;
	
	@Override
	public String toString(){
		return getnSocio() + " " + getIzena()+" "+ getAbizena();
	}
	public String lortuInformazioa(){
		return getIzena()+" "+getAbizena()+"\n"+"NAN/DNI: " +getDNI()+"\n"+"Znb/Nº " +
	getnSocio()+"\n"+"Tel.: "+getTel1()+" " + getTel2();
	}
	
	
	
	public int getnSocio() {
		return nSocio;
	}
	public void setnSocio(int nSocio) {
		this.nSocio = nSocio;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public boolean isPensionista() {
		return pensionista;
	}
	public void setPensionista(boolean pensionista) {
		this.pensionista = pensionista;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getAbizena() {
		return abizena;
	}
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getTpSocio() {
		return tpSocio;
	}
	public void setTpSocio(String tpSocio) {
		this.tpSocio = tpSocio;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public Image getFoto() {
		return foto;
	}
	public void setFoto(Image foto) {
		this.foto = foto;
	}
	public String getHelbidea() {
		return helbidea;
	}
	public void setHelbidea(String helbidea) {
		this.helbidea = helbidea;
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
	public String getTelEmergencia() {
		return telEmergencia;
	}
	public void setTelEmergencia(String telEmergencia) {
		this.telEmergencia = telEmergencia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getMotivoBaja() {
		return motivoBaja;
	}
	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}
	public List<String> getActividades() {
		return actividades;
	}
	public void setActividades(List<String> actividades) {
		this.actividades = actividades;
	}
	public List<Boolean> getPagos() {
		return pagos;
	}
	public void setPagos(List<Boolean> pagos) {
		this.pagos = pagos;
	}
	public boolean isBaja() {
		return baja;
	}
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

		
}
